package com.paramichha.testkit.generator;

import com.paramichha.testkit.utils.TestNames;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms {@link ClassData} into the Creation nested {@link ClassCoderInfo}.
 */
final class CreationTransformer {

    static ClassCoderInfo transform(ClassData data, ClassDataConfig config) {
        List<MethodCoderInfo> methods = new ArrayList<>();
        String instanceName  = config.instanceName();
        String className     = config.sourceClassName();
        String displayName   = className + " Creation";

        // testCanBeCreated
        methods.add(MethodCoderInfo.of(
                List.of("@Test",
                        "@DisplayName(\"can be created\")"),
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
                List.of("@Test",
                        "@DisplayName(\"holds expected values\")"),
                "void", "testHoldsExpectedValues",
                holdsBody.toString().stripTrailing()));

        // testIsValid — only meaningful when validate(obj) has constraints to check
        boolean propertyConstraints = data.members().stream().anyMatch(MemberData::isPropertyConstrained);
        if (config.hasJakarta() && propertyConstraints) {
            methods.add(MethodCoderInfo.of(
                    List.of("@Test",
                            "@DisplayName(\"is valid (no constraint violations)\")"),
                    "void", "testIsValid",
                    "assertThat(" + config.validatorField()
                            + ".validate(" + instanceName + ")).isEmpty();"));
        }

        // Constructor-parameter constraints: validate() never sees them, check the fixture's arguments
        boolean constructorConstraints = data.members().stream().anyMatch(MemberData::isConstructorConstrained);
        if (config.hasJakarta() && constructorConstraints) {
            methods.add(MethodCoderInfo.of(
                    List.of("@Test",
                            "@DisplayName(\"constructor arguments are valid\")"),
                    "void", "testConstructorArgumentsAreValid",
                    "assertThat(" + FieldTransformer.constructorViolations(
                            className, data, null, config.validatorField()) + ").isEmpty();"));
        }

        return ClassCoderInfo.nested(
                List.of("@Nested",
                        "@DisplayName(\"" + displayName + "\")",
                        "@Tag(\"creation\")",
                        "@TestMethodOrder(MethodOrderer.MethodName.class)"),
                List.of(),
                className + "Creation" + config.testSuffix(),
                List.of(),
                List.copyOf(methods),
                List.of());
    }

    private static boolean isBooleanType(ClassData type) {
        return type.isPrimitive() && type.className().equals("boolean");
    }

    private static boolean isComplexType(ClassData type) {
        if (type.isPrimitive() || type.isVoid()) return false;
        if (type.isArray() || type.isCollection() || type.isMap()) return false;
        String pkg = type.packageName();
        return !pkg.startsWith("java.") && !pkg.startsWith("jakarta.")
                && !type.isEnum() && !type.className().equals("String");
    }
}