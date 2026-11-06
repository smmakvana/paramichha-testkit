package com.paramichha.testkit.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms {@link ClassData} + {@link ClassDataConfig} into {@link ClassCoderInfo}.
 *
 * Two modes:
 * - transform()      → single ClassCoderInfo tree (single file)
 * - transformSplit() → flat List<ClassCoderInfo> (split files)
 */
final class ClassDataTransformer {

    // ── Single file ───────────────────────────────────────────────────────

    static ClassCoderInfo transform(ClassData data, ClassDataConfig config) {
        return renameTestMethods(buildRoot(data, config, buildNestedClasses(data, config)), config.methodAffix());
    }

    /**
     * Wraps the configured method affix around every test method — the ones annotated
     * {@code @Test} or {@code @ParameterizedTest}. Value streams and setup methods keep their names.
     * Applied to the whole tree, so nested classes are covered too.
     */
    static ClassCoderInfo renameTestMethods(ClassCoderInfo c, com.paramichha.testkit.Affix affix) {
        List<MethodCoderInfo> methods = c.methods().stream()
                .map(m -> isTestMethod(m)
                        ? new MethodCoderInfo(m.annotations(), m.modifiers(), m.returnType(),
                                affix.apply(m.name()), m.params(), m.body())
                        : m)
                .toList();
        List<ClassCoderInfo> nested = c.nestedClasses().stream()
                .map(n -> renameTestMethods(n, affix))
                .toList();
        return new ClassCoderInfo(c.packageName(), c.imports(), c.annotations(), c.modifiers(),
                c.name(), c.fields(), methods, nested);
    }

    private static boolean isTestMethod(MethodCoderInfo m) {
        return m.annotations().stream().anyMatch(a ->
                a.equals("@Test") || a.startsWith("@Test(") || a.startsWith("@ParameterizedTest"));
    }

    // ── Split files ───────────────────────────────────────────────────────

    static List<ClassCoderInfo> transformSplit(ClassData data, ClassDataConfig config) {
        List<ClassCoderInfo> result = new ArrayList<>();

        // Root — instance field + @BeforeAll only
        result.add(buildRoot(data, config, List.of()));

        // Creation → GodRecordCreationTest (the test suffix is applied to every name)
        if (!config.isUtilityClass()) {
            ClassCoderInfo creation = CreationTransformer.transform(data, config);
            result.add(promoteToTopLevel(creation, data, config));
        }

        // Fields → GodRecordField<FieldName>Test
        if (!config.isUtilityClass()) {
            for (MemberData member : data.members()) {
                ClassCoderInfo field = FieldTransformer.transform(member, data, config);
                String splitName = com.paramichha.testkit.NameFormat.splitFileName(
                        config.template(), config.sourceClassName(), "Field", member.name());
                result.add(promoteToTopLevel(withName(field, splitName), data, config));
            }
        }

        // Methods → GodRecordMethod<MethodName>Test
        for (MethodData method : data.methods()) {
            ClassCoderInfo methodClass = MethodTransformer.transform(method, config);
            String splitName = com.paramichha.testkit.NameFormat.splitFileName(
                    config.template(), config.sourceClassName(), "Method", method.name());
            result.add(promoteToTopLevel(withName(methodClass, splitName), data, config));
        }

        return result.stream().map(c -> renameTestMethods(c, config.methodAffix())).toList();
    }

    // ── Promote nested → standalone top-level ─────────────────────────────

    private static ClassCoderInfo promoteToTopLevel(ClassCoderInfo nested,
                                                    ClassData data,
                                                    ClassDataConfig config) {
        // Strip @Nested — not valid on top-level class
        List<String> annotations = nested.annotations().stream()
                .filter(a -> !a.equals("@Nested"))
                .toList();

        // Prepend validator field + instance field + @BeforeAll
        List<FieldCoderInfo>  fields  = buildStandaloneFields(nested, data, config);
        List<MethodCoderInfo> methods = buildStandaloneMethods(nested, data, config);

        return new ClassCoderInfo(
                config.targetPackage(),
                config.imports(),
                annotations,
                List.of(),
                nested.name(),
                fields,
                methods,
                List.of());
    }

    /** Rename a ClassCoderInfo — everything else identical */
    private static ClassCoderInfo withName(ClassCoderInfo info, String name) {
        return new ClassCoderInfo(
                info.packageName(), info.imports(), info.annotations(),
                info.modifiers(), name, info.fields(), info.methods(), info.nestedClasses());
    }

    // ── Standalone fields/methods ─────────────────────────────────────────

    private static List<FieldCoderInfo> buildStandaloneFields(ClassCoderInfo nested,
                                                              ClassData data,
                                                              ClassDataConfig config) {
        List<FieldCoderInfo> fields = new ArrayList<>();

        if (config.hasJakarta()) {
            fields.add(new FieldCoderInfo(
                    List.of(), List.of("private", "static"),
                    "Validator", config.validatorField(), null));
        }

        if (!data.members().isEmpty()) {
            fields.add(new FieldCoderInfo(
                    List.of(), List.of(),
                    config.sourceClassName(), config.instanceName(),
                    buildConstructorCall(data, config)));
        }

        fields.addAll(nested.fields());
        return List.copyOf(fields);
    }

    private static List<MethodCoderInfo> buildStandaloneMethods(ClassCoderInfo nested,
                                                                ClassData data,
                                                                ClassDataConfig config) {
        List<MethodCoderInfo> methods = new ArrayList<>();

        if (config.hasJakarta()) {
            methods.add(new MethodCoderInfo(
                    List.of("@BeforeAll"), List.of("static"),
                    "void", config.validatorSetup(), List.of(),
                    config.validatorField()
                            + " = Validation.buildDefaultValidatorFactory().getValidator();"));
        }

        CreationExpressions.factoryMethod(data, config).ifPresent(methods::add);
        methods.addAll(nested.methods());
        return List.copyOf(methods);
    }

    // ── Root class ────────────────────────────────────────────────────────

    private static ClassCoderInfo buildRoot(ClassData data, ClassDataConfig config,
                                            List<ClassCoderInfo> nestedClasses) {
        List<FieldCoderInfo>  fields  = new ArrayList<>();
        List<MethodCoderInfo> methods = new ArrayList<>();

        if (config.hasJakarta()) {
            fields.add(new FieldCoderInfo(
                    List.of(), List.of("private", "static"),
                    "Validator", config.validatorField(), null));
            methods.add(new MethodCoderInfo(
                    List.of("@BeforeAll"), List.of("static"),
                    "void", config.validatorSetup(), List.of(),
                    config.validatorField()
                            + " = Validation.buildDefaultValidatorFactory().getValidator();"));
        }

        if (!data.members().isEmpty()) {
            fields.add(new FieldCoderInfo(
                    List.of(), List.of(),
                    config.sourceClassName(), config.instanceName(),
                    buildConstructorCall(data, config)));
        }
        CreationExpressions.factoryMethod(data, config).ifPresent(methods::add);

        return new ClassCoderInfo(
                config.targetPackage(),
                config.imports(),
                List.of("@DisplayName(\"" + config.sourceClassName() + "\")"),
                List.of(),
                data.className(),
                List.copyOf(fields),
                List.copyOf(methods),
                nestedClasses);
    }

    // ── Nested classes (single file) ──────────────────────────────────────

    private static List<ClassCoderInfo> buildNestedClasses(ClassData data, ClassDataConfig config) {
        List<ClassCoderInfo> nested = new ArrayList<>();

        if (!config.isUtilityClass()) {
            nested.add(CreationTransformer.transform(data, config));
            for (MemberData member : data.members()) {
                nested.add(FieldTransformer.transform(member, data, config));
            }
        }

        for (MethodData method : data.methods()) {
            nested.add(MethodTransformer.transform(method, config));
        }

        return List.copyOf(nested);
    }

    // ── Constructor call ──────────────────────────────────────────────────

    private static String buildConstructorCall(ClassData data, ClassDataConfig config) {
        return CreationExpressions.baseline(data, config);
    }
}