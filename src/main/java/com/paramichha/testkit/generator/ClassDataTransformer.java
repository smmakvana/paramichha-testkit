package com.paramichha.testkit.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        // Root — no nested classes
        result.add(buildRoot(data, config, List.of()));

        // Each nested becomes a standalone root
        for (ClassCoderInfo nested : buildNestedClasses(data, config)) {
            result.add(new ClassCoderInfo(
                    config.targetPackage(),   // gets its own package
                    config.imports(),         // same imports
                    nested.annotations(),
                    nested.modifiers(),
                    nested.name(),
                    nested.fields(),
                    nested.methods(),
                    List.of()));              // no further nesting
        }

        return List.copyOf(result);
    }

    // ── Root class ────────────────────────────────────────────────────────

    private static ClassCoderInfo buildRoot(ClassData data, ClassDataConfig config,
                                             List<ClassCoderInfo> nestedClasses) {
        List<FieldCoderInfo>  fields  = buildRootFields(data, config);
        List<MethodCoderInfo> methods = buildRootMethods(config);

        return new ClassCoderInfo(
                config.targetPackage(),
                config.imports(),
                List.of("@DisplayName(\"" + config.sourceClassName() + "\")"),
                List.of(),
                data.className(),
                fields,
                methods,
                nestedClasses);
    }

    private static List<FieldCoderInfo> buildRootFields(ClassData data, ClassDataConfig config) {
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
            String constructor = buildConstructorCall(data, config);
            fields.add(new FieldCoderInfo(
                    List.of(), List.of(),
                    config.sourceClassName(),
                    config.instanceName(),
                    constructor));
        }

        return List.copyOf(fields);
    }

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

    private static List<MethodCoderInfo> buildRootMethods(ClassDataConfig config) {
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

        return List.copyOf(methods);
    }

    // ── Nested classes ────────────────────────────────────────────────────

    private static List<ClassCoderInfo> buildNestedClasses(ClassData data, ClassDataConfig config) {
        List<ClassCoderInfo> nested = new ArrayList<>();

        if (!config.isUtilityClass()) {
            // Creation nested
            nested.add(CreationTransformer.transform(data, config));

            // Field nested — one per member
            for (MemberData member : data.members()) {
                nested.add(FieldTransformer.transform(member, data, config));
            }
        }

        // Method nested — one per method
        for (MethodData method : data.methods()) {
            nested.add(MethodTransformer.transform(method, config));
        }

        return List.copyOf(nested);
    }
}
