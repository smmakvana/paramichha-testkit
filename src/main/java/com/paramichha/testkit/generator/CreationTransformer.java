package com.paramichha.testkit.generator;

import com.paramichha.testkit.utils.TestNames;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms {@link ClassData} into the Creation nested {@link ClassCoderInfo}.
 * Knows: creation test patterns — canBeCreated, holdsExpectedValues, isValid.
 */
final class CreationTransformer {

    static ClassCoderInfo transform(ClassData data, ClassDataConfig config) {
        List<MethodCoderInfo> methods = new ArrayList<>();
        String instanceName = config.instanceName();

        // testCanBeCreated
        methods.add(MethodCoderInfo.of(
                List.of("@Test", "@DisplayName(\"can be created\")"),
                "void", "testCanBeCreated",
                "assertThat(" + instanceName + ").isNotNull();"));

        // testHoldsExpectedValues
        StringBuilder holdsBody = new StringBuilder();
        List<Object>     validParts = data.validValues();
        List<MemberData> members    = data.members();
        for (int i = 0; i < members.size(); i++) {
            MemberData m   = members.get(i);
            Object     val = i < validParts.size() ? validParts.get(i) : null;
            if (!LiteralConverter.canSerialize(val)) continue;
            if (isComplexType(m.type())) continue;
            String accessor = config.isRecord()
                    ? instanceName + "." + m.name() + "()"
                    : instanceName + "." + TestNames.getterName(
                            m.name(), isBooleanType(m.type()), config.isRecord()) + "()";
            holdsBody.append("assertThat(").append(accessor)
                    .append(").isEqualTo(").append(LiteralConverter.toLiteral(val)).append(");\n");
        }
        methods.add(MethodCoderInfo.of(
                List.of("@Test", "@DisplayName(\"holds expected values\")"),
                "void", "testHoldsExpectedValues",
                holdsBody.toString().stripTrailing()));

        // testIsValid — only if Jakarta + constrained
        if (config.hasJakarta() && data.isConstrained()) {
            methods.add(MethodCoderInfo.of(
                    List.of("@Test", "@DisplayName(\"is valid (no constraint violations)\")"),
                    "void", "testIsValid",
                    "assertThat(" + config.validatorField()
                    + ".validate(" + instanceName + ")).isEmpty();"));
        }

        return ClassCoderInfo.nested(
                List.of("@Nested", "@DisplayName(\"Creation\")"),
                List.of(),
                config.sourceClassName() + "CreationTest",
                List.of(),
                List.copyOf(methods),
                List.of());
    }

    // ── Helpers ───────────────────────────────────────────────────────────

    private static boolean isBooleanType(ClassData type) {
        return (type.isPrimitive() && type.className().equals("boolean"))
                || type.className().equals("Boolean");
    }

    private static boolean isComplexType(ClassData type) {
        if (type.isPrimitive() || type.isVoid()) return false;
        if (type.isArray() || type.isCollection() || type.isMap()) return false;
        String pkg = type.packageName();
        return !pkg.startsWith("java.") && !pkg.startsWith("jakarta.")
                && !type.isEnum() && !type.className().equals("String");
    }
}
