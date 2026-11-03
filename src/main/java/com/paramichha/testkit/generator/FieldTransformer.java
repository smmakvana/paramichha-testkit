package com.paramichha.testkit.generator;

import com.paramichha.testkit.utils.TestNames;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Transforms a {@link MemberData} into a nested {@link ClassCoderInfo}.
 */
final class FieldTransformer {

    static ClassCoderInfo transform(MemberData member, ClassData classData,
                                    ClassDataConfig config) {
        String className   = config.sourceClassName();
        String name        = member.name();
        String boxedType   = member.type().className();
        String nestedName  = config.classAffix().apply(className + com.paramichha.datafactory.utils.JavaNames.className(name));
        String displayName = com.paramichha.testkit.NameFormat.display(config.template(), className, "Field", name);
        String acceptsM    = "testAccepts" + com.paramichha.utils.Texts.capitalize(name);
        String rejectsM    = "testRejects" + com.paramichha.utils.Texts.capitalize(name);
        String validSrc    = TestNames.validSource(name);
        String invalidSrc  = TestNames.invalidSource(name);

        List<Object> validVals   = member.validValues().stream()
                .filter(LiteralConverter::canSerialize).collect(Collectors.toList());
        List<Object> invalidVals = member.invalidValues().stream()
                .filter(LiteralConverter::canSerialize).collect(Collectors.toList());

        List<MethodCoderInfo> methods = new ArrayList<>();
        String validator = config.validatorField();
        boolean canValidate = config.hasJakarta();

        // Constraints no value can satisfy: say so explicitly instead of generating fixtures
        // that can't be valid.
        if (!member.conflicts().isEmpty()) {
            methods.add(new MethodCoderInfo(
                    List.of("@Test", "@DisplayName(\"has satisfiable constraints\")"),
                    List.of(), "void", "testConstraintsAreSatisfiable", List.of(),
                    "org.assertj.core.api.Assertions.fail(\"" + escape(String.join("; ", member.conflicts()))
                            + " — fix the annotations on '" + name + "'\");"));
            return nested(displayName, nestedName, methods);
        }

        // Accepts test + valid stream
        if (!validVals.isEmpty()) {
            String constructorWithVar = buildConstructorWithVar(className, classData, member, config);
            String body;
            if (canValidate && member.isConstructorConstrained()) {
                // Constructor-parameter constraints are only seen by ExecutableValidator.
                body = "assertThat(" + constructorViolations(className, classData, member, validator) + ").isEmpty();";
                if (member.isPropertyConstrained()) {
                    body += "\n" + className + " obj = " + constructorWithVar + ";\n"
                            + "assertThat(" + validator + ".validate(obj)).isEmpty();";
                }
            } else if (canValidate && member.isPropertyConstrained()) {
                body = className + " obj = " + constructorWithVar + ";\n"
                        + "assertThat(" + validator + ".validate(obj)).isEmpty();";
            } else {
                body = className + " obj = " + constructorWithVar + ";\n"
                        + "assertThat(obj." + TestNames.getterName(name,
                        isBooleanType(member.type()), config.isRecord()) + "()).isEqualTo(" + name + ");";
            }
            methods.add(new MethodCoderInfo(
                    List.of("@ParameterizedTest(name = \"{0}\")",
                            "@MethodSource(\"" + validSrc + "\")",
                            "@DisplayName(\"accepts valid value\")"),
                    List.of(), "void", acceptsM,
                    List.of(ParameterCoderInfo.of("String", "description"),
                            ParameterCoderInfo.of(boxedType, name)),
                    body));
            methods.add(new MethodCoderInfo(
                    List.of(), List.of("static"),
                    "Stream<Arguments>", validSrc, List.of(), argumentsBody(validVals, name, boxedType, "valid")));
        }

        // Rejects test — only when something actually checks the constraint. An unconstrained
        // field, or a project without a Jakarta Validation provider, gets no rejects test: any
        // assertion there would pass whatever the value.
        boolean constrained = member.isConstructorConstrained() || member.isPropertyConstrained();
        if (constrained && !invalidVals.isEmpty()) {
            String body;
            if (canValidate && member.isConstructorConstrained()) {
                // Checked without calling the constructor, which needn't enforce anything itself.
                int index = member.constructorIndex();
                body = "var violations = " + constructorViolations(className, classData, member, validator) + ";\n"
                        + "// the violation must be on parameter '" + name + "', not on the other (valid) ones\n"
                        + "assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().endsWith(\"." + name + "\")\n"
                        + "        || v.getPropertyPath().toString().endsWith(\".arg" + index + "\"));";
            } else if (canValidate) {
                // Jakarta present: the validator must report the violation. A constructor that
                // throws here is an error, so the test fails.
                String constructorWithVar = buildConstructorWithVar(className, classData, member, config);
                body = className + " obj = " + constructorWithVar + ";\n"
                        + "assertThat(" + validator + ".validate(obj)).anyMatch(v -> {\n"
                        + "    String path = v.getPropertyPath().toString();\n"
                        + "    return path.equals(\"" + name + "\") || path.startsWith(\"" + name + ".\") || path.startsWith(\"" + name + "[\");\n"
                        + "});";
            } else {
                // No validator (core library): the constructor itself must reject the value, as an
                // IllegalArgumentException. If the object is created, the hint says what to fix.
                String constructorWithVar = buildConstructorWithVar(className, classData, member, config);
                body = "assertThatThrownBy(() -> " + constructorWithVar + ")\n"
                        + "        .as(\"" + escape(className) + ": field '" + name + "' = %s must be rejected with"
                        + " IllegalArgumentException, but the object was created. Fix: check '" + name
                        + "' in the constructor and throw IllegalArgumentException\", " + name + ")\n"
                        + "        .isInstanceOf(IllegalArgumentException.class);";
            }
            methods.add(new MethodCoderInfo(
                    List.of("@ParameterizedTest(name = \"{0}\")",
                            "@MethodSource(\"" + invalidSrc + "\")",
                            "@DisplayName(\"rejects invalid value\")"),
                    List.of(), "void", rejectsM,
                    List.of(ParameterCoderInfo.of("String", "description"),
                            ParameterCoderInfo.of(boxedType, name)),
                    body));
            methods.add(new MethodCoderInfo(
                    List.of(), List.of("static"),
                    "Stream<Arguments>", invalidSrc, List.of(), argumentsBody(invalidVals, name, boxedType, "invalid")));
        }

        return nested(displayName, nestedName, methods);
    }

    private static ClassCoderInfo nested(String displayName, String nestedName, List<MethodCoderInfo> methods) {
        return ClassCoderInfo.nested(
                List.of("@Nested",
                        "@DisplayName(\"" + displayName + "\")",
                        "@Tag(\"field\")",
                        "@TestMethodOrder(MethodOrderer.MethodName.class)"),
                List.of(),
                nestedName,
                List.of(),
                List.copyOf(methods),
                List.of());
    }

    // ── Helpers ───────────────────────────────────────────────────────────

    private static String buildConstructorWithVar(String className, ClassData classData,
                                                  MemberData target, ClassDataConfig config) {
        List<Object>     parts   = classData.validValues();
        List<MemberData> members = classData.members();
        StringBuilder    sb      = new StringBuilder("new ").append(className).append("(\n");
        for (int i = 0; i < members.size(); i++) {
            if (i > 0) sb.append(",\n");
            MemberData m = members.get(i);
            if (m.name().equals(target.name())) {
                sb.append("    ").append(m.name());
            } else {
                Object val = i < parts.size() ? parts.get(i) : null;
                String lit = val == null
                        ? "(" + m.type().className() + ") null"
                        : LiteralConverter.canSerialize(val) ? LiteralConverter.toLiteral(val)
                        : "(" + m.type().className() + ") null";
                sb.append("    ").append(lit);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * {@code validator.forExecutables().validateConstructorParameters(<ctor>, new Object[] {...})}
     * with {@code target} as the variable (or none, if null) and every other parameter at its valid
     * baseline value.
     * The constructor is looked up by parameter types without checked exceptions.
     */
    static String constructorViolations(String className, ClassData classData,
                                        MemberData target, String validator) {
        List<MemberData> members = classData.members();
        StringBuilder types = new StringBuilder();
        for (int i = 0; i < members.size(); i++) {
            if (i > 0) types.append(", ");
            types.append(rawName(members.get(i).type().className())).append(".class");
        }
        return validator + ".forExecutables().validateConstructorParameters(\n"
                + "    java.util.Arrays.stream(" + className + ".class.getDeclaredConstructors())\n"
                + "        .filter(c -> java.util.Arrays.equals(c.getParameterTypes(), new Class<?>[] {" + types + "}))\n"
                + "        .findFirst().orElseThrow(),\n"
                + "    new Object[] {" + argumentsWithVar(classData, target) + "})";
    }

    /** {@code x, -1, 3}: the constructor arguments with {@code target} as the test variable. */
    private static String argumentsWithVar(ClassData classData, MemberData target) {
        List<Object>     parts   = classData.validValues();
        List<MemberData> members = classData.members();
        StringBuilder    sb      = new StringBuilder();
        for (int i = 0; i < members.size(); i++) {
            if (i > 0) sb.append(", ");
            MemberData m = members.get(i);
            if (target != null && m.name().equals(target.name())) {
                sb.append(m.name());
            } else {
                Object val = i < parts.size() ? parts.get(i) : null;
                sb.append(val != null && LiteralConverter.canSerialize(val)
                        ? LiteralConverter.toLiteral(val)
                        : "(" + m.type().className() + ") null");
            }
        }
        return sb.toString();
    }

    /** {@code List<String>} → {@code List}: class literals can't carry type arguments. */
    private static String rawName(String typeName) {
        int lt = typeName.indexOf('<');
        return lt < 0 ? typeName : typeName.substring(0, lt);
    }

    private static String argumentsBody(List<Object> values, String name, String boxedType, String kind) {
        StringBuilder body = new StringBuilder("return Stream.of(\n");
        for (int j = 0; j < values.size(); j++) {
            Object v   = values.get(j);
            String lit = v == null ? "(" + boxedType + ") null" : LiteralConverter.toLiteral(v);
            body.append("    Arguments.of(\"").append(labelFor(v, name, kind, j))
                .append("\", ").append(lit).append(")");
            body.append(j < values.size() - 1 ? ",\n" : "\n");
        }
        return body.append(");").toString();
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static boolean isBooleanType(ClassData type) {
        return (type.isPrimitive() && type.className().equals("boolean"));
    }

    private static String labelFor(Object value, String name, String kind, int index) {
        if (value == null) return "null " + name;
        if (value instanceof String s) return s.isEmpty() ? "blank " + name : name + " " + index;
        return kind + " " + name + " " + (index + 1);
    }
}