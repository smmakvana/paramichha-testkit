package com.paramichha.testkit.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms {@link ClassData} + {@link ClassDataConfig} into {@link ClassCoderInfo}.
 *
 * Two modes:
 * - transform()      → single ClassCoderInfo tree (single file)
 * - transformSplit() → flat List<ClassCoderInfo> (split files)
 *
 * Delegates nested class building to:
 * {@link CreationTransformer}, {@link FieldTransformer}, {@link MethodTransformer}
 */
final class ClassDataTransformer {

    // ── Single file ───────────────────────────────────────────────────────

    static ClassCoderInfo transform(ClassData data, ClassDataConfig config) {
        List<ClassCoderInfo> nested = buildNestedClasses(data, config);
        return buildRoot(data, config, nested);
    }

    // ── Split files ───────────────────────────────────────────────────────

    static List<ClassCoderInfo> transformSplit(ClassData data, ClassDataConfig config) {
        List<ClassCoderInfo> result = new ArrayList<>();

        // Root — no nested classes, just instance field + @BeforeAll
        result.add(buildRoot(data, config, List.of()));

        // Each nested becomes a standalone top-level class:
        // Fix 1 — strip @Nested annotation (not valid on top-level class)
        // Fix 2 — add own instance field + @BeforeAll so it can stand alone
        for (ClassCoderInfo nested : buildNestedClasses(data, config)) {
            result.add(promoteToTopLevel(nested, data, config));
        }

        return List.copyOf(result);
    }

    /**
     * Promotes a nested ClassCoderInfo to a standalone top-level class.
     * Fix 1: strips @Nested annotation.
     * Fix 2: injects instance field (and @BeforeAll if Jakarta) so it can compile standalone.
     */
    private static ClassCoderInfo promoteToTopLevel(ClassCoderInfo nested,
                                                    ClassData data,
                                                    ClassDataConfig config) {
        // Fix 1 — strip @Nested, keep only @DisplayName
        List<String> annotations = nested.annotations().stream()
                .filter(a -> !a.equals("@Nested"))
                .toList();

        // Fix 2 — prepend instance field (and validator field + @BeforeAll if Jakarta)
        List<FieldCoderInfo>  fields  = buildStandaloneFields(nested, data, config);
        List<MethodCoderInfo> methods = buildStandaloneMethods(nested, config);

        return new ClassCoderInfo(
                config.targetPackage(),
                config.imports(),
                annotations,
                List.of(),                  // no modifiers — top-level class
                nested.name(),
                fields,
                methods,
                List.of());                 // no further nesting
    }

    /**
     * Builds fields for a standalone split class:
     * - validator field (if Jakarta)
     * - instance field with constructor call
     * - any fields the nested class already had (e.g. wrapper records)
     */
    private static List<FieldCoderInfo> buildStandaloneFields(ClassCoderInfo nested,
                                                              ClassData data,
                                                              ClassDataConfig config) {
        List<FieldCoderInfo> fields = new ArrayList<>();

        // Validator field
        if (config.hasJakarta()) {
            fields.add(new FieldCoderInfo(
                    List.of(),
                    List.of("private", "static"),
                    "Validator",
                    config.validatorField(),
                    null));
        }

        // Instance field
        if (!data.members().isEmpty()) {
            fields.add(new FieldCoderInfo(
                    List.of(), List.of(),
                    config.sourceClassName(),
                    config.instanceName(),
                    buildConstructorCall(data, config)));
        }

        // Any existing fields from nested (e.g. wrapper records)
        fields.addAll(nested.fields());

        return List.copyOf(fields);
    }

    /**
     * Builds methods for a standalone split class:
     * - @BeforeAll validator setup (if Jakarta)
     * - all existing methods from the nested class
     */
    private static List<MethodCoderInfo> buildStandaloneMethods(ClassCoderInfo nested,
                                                                ClassDataConfig config) {
        List<MethodCoderInfo> methods = new ArrayList<>();

        // @BeforeAll validator setup
        if (config.hasJakarta()) {
            methods.add(new MethodCoderInfo(
                    List.of("@BeforeAll"),
                    List.of("static"),
                    "void",
                    config.validatorSetup(),
                    List.of(),
                    config.validatorField()
                            + " = Validation.buildDefaultValidatorFactory().getValidator();"));
        }

        // All existing methods from nested
        methods.addAll(nested.methods());

        return List.copyOf(methods);
    }

    // ── Root class ────────────────────────────────────────────────────────

    private static ClassCoderInfo buildRoot(ClassData data, ClassDataConfig config,
                                            List<ClassCoderInfo> nestedClasses) {
        return new ClassCoderInfo(
                config.targetPackage(),
                config.imports(),
                List.of("@DisplayName(\"" + config.sourceClassName() + "\")"),
                List.of(),
                data.className(),
                buildRootFields(data, config),
                buildRootMethods(config),
                nestedClasses);
    }

    private static List<FieldCoderInfo> buildRootFields(ClassData data, ClassDataConfig config) {
        List<FieldCoderInfo> fields = new ArrayList<>();

        if (config.hasJakarta()) {
            fields.add(new FieldCoderInfo(
                    List.of(),
                    List.of("private", "static"),
                    "Validator",
                    config.validatorField(),
                    null));
        }

        if (!data.members().isEmpty()) {
            fields.add(new FieldCoderInfo(
                    List.of(), List.of(),
                    config.sourceClassName(),
                    config.instanceName(),
                    buildConstructorCall(data, config)));
        }

        return List.copyOf(fields);
    }

    private static List<MethodCoderInfo> buildRootMethods(ClassDataConfig config) {
        List<MethodCoderInfo> methods = new ArrayList<>();

        if (config.hasJakarta()) {
            methods.add(new MethodCoderInfo(
                    List.of("@BeforeAll"),
                    List.of("static"),
                    "void",
                    config.validatorSetup(),
                    List.of(),
                    config.validatorField()
                            + " = Validation.buildDefaultValidatorFactory().getValidator();"));
        }

        return List.copyOf(methods);
    }

    // ── Nested classes ────────────────────────────────────────────────────

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
        List<Object>     parts   = data.validValues();
        List<MemberData> members = data.members();
        StringBuilder    sb      = new StringBuilder("new ")
                .append(config.sourceClassName()).append("(\n");
        for (int i = 0; i < members.size(); i++) {
            if (i > 0) sb.append(",\n");
            Object val = i < parts.size() ? parts.get(i) : null;
            String lit = val == null
                    ? "(" + members.get(i).type().className() + ") null"
                    : LiteralConverter.canSerialize(val) ? LiteralConverter.toLiteral(val)
                    : "(" + members.get(i).type().className() + ") null";
            sb.append("    ").append(lit);
        }
        sb.append(")");
        return sb.toString();
    }
}