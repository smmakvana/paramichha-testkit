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
        String nestedName  = TestNames.memberClass(className, name);
        String displayName = className + " Field " + name;
        String acceptsM    = "testAccepts" + com.paramichha.utils.Texts.capitalize(name);
        String rejectsM    = "testRejects" + com.paramichha.utils.Texts.capitalize(name);
        String validSrc    = TestNames.validSource(name);
        String invalidSrc  = TestNames.invalidSource(name);

        List<Object> validVals   = member.validValues().stream()
                .filter(LiteralConverter::canSerialize).collect(Collectors.toList());
        List<Object> invalidVals = member.invalidValues().stream()
                .filter(LiteralConverter::canSerialize).collect(Collectors.toList());

        List<MethodCoderInfo> methods = new ArrayList<>();

        // Accepts test + valid stream
        if (!validVals.isEmpty()) {
            String constructorWithVar = buildConstructorWithVar(className, classData, member, config);
            String assertion = member.isConstrained()
                    ? "assertThat(" + config.validatorField() + ".validate(obj)).isEmpty();"
                    : "assertThat(obj." + TestNames.getterName(name,
                    isBooleanType(member.type()), config.isRecord())
                    + "()).isEqualTo(" + name + ");";

            methods.add(new MethodCoderInfo(
                    List.of("@ParameterizedTest(name = \"{0}\")",
                            "@MethodSource(\"" + validSrc + "\")",
                            "@DisplayName(\"accepts valid value\")"),
                    List.of(), "void", acceptsM,
                    List.of(ParameterCoderInfo.of("String", "description"),
                            ParameterCoderInfo.of(boxedType, name)),
                    className + " obj = " + constructorWithVar + ";\n" + assertion));

            StringBuilder validBody = new StringBuilder("return Stream.of(\n");
            for (int j = 0; j < validVals.size(); j++) {
                Object v   = validVals.get(j);
                String lit = v == null ? "(" + boxedType + ") null" : LiteralConverter.toLiteral(v);
                validBody.append("    Arguments.of(\"")
                        .append(labelFor(v, name, "valid", j))
                        .append("\", ").append(lit).append(")");
                validBody.append(j < validVals.size() - 1 ? ",\n" : "\n");
            }
            validBody.append(");");
            methods.add(new MethodCoderInfo(
                    List.of(), List.of("static"),
                    "Stream<Arguments>", validSrc, List.of(), validBody.toString()));
        }

        // Rejects test + invalid stream — always emitted
        {
            String constructorWithVar = buildConstructorWithVar(className, classData, member, config);
            String rejectsBody;
            if (!member.isConstrained()) {
                rejectsBody = "// TODO: add constraints to " + name
                        + ", then replace assertThat(obj).isNotNull()\n"
                        + "//       with assertThat(validator.validate(obj)).isNotEmpty()\n"
                        + className + " obj = " + constructorWithVar + ";\n"
                        + "assertThat(obj).isNotNull();";
            } else {
                rejectsBody = "try {\n"
                        + "    " + className + " obj = " + constructorWithVar + ";\n"
                        + "    assertThat(" + config.validatorField() + ".validate(obj)).isNotEmpty();\n"
                        + "} catch (Exception ignored) {\n"
                        + "    // Constructor enforced the contract — test passes\n"
                        + "}";
            }

            methods.add(new MethodCoderInfo(
                    List.of("@ParameterizedTest(name = \"{0}\")",
                            "@MethodSource(\"" + invalidSrc + "\")",
                            "@DisplayName(\"rejects invalid value\")"),
                    List.of(), "void", rejectsM,
                    List.of(ParameterCoderInfo.of("String", "description"),
                            ParameterCoderInfo.of(boxedType, name)),
                    rejectsBody));

            StringBuilder invalidBody = new StringBuilder();
            if (!member.isConstrained()) {
                invalidBody.append("// TODO: add constraints to ").append(name)
                        .append(", or delete this test\n");
            }
            if (invalidVals.isEmpty()) {
                String placeholder = placeholderLiteral(member.type(), boxedType);
                invalidBody.append("return Stream.of(\n")
                        .append("    Arguments.of(\"no constraints defined — add some!\", ")
                        .append(placeholder).append("));");
            } else {
                invalidBody.append("return Stream.of(\n");
                for (int j = 0; j < invalidVals.size(); j++) {
                    Object v   = invalidVals.get(j);
                    String lit = v == null ? "(" + boxedType + ") null" : LiteralConverter.toLiteral(v);
                    invalidBody.append("    Arguments.of(\"")
                            .append(labelFor(v, name, "invalid", j))
                            .append("\", ").append(lit).append(")");
                    invalidBody.append(j < invalidVals.size() - 1 ? ",\n" : "\n");
                }
                invalidBody.append(");");
            }
            methods.add(new MethodCoderInfo(
                    List.of(), List.of("static"),
                    "Stream<Arguments>", invalidSrc, List.of(), invalidBody.toString()));
        }

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

    private static boolean isBooleanType(ClassData type) {
        return (type.isPrimitive() && type.className().equals("boolean"));
    }

    private static String placeholderLiteral(ClassData type, String boxedType) {
        if (type.isPrimitive()) {
            return switch (type.className()) {
                case "int"     -> "0";
                case "long"    -> "0L";
                case "double"  -> "0.0d";
                case "float"   -> "0.0f";
                case "boolean" -> "false";
                case "byte"    -> "(byte) 0";
                case "short"   -> "(short) 0";
                case "char"    -> "'\\0'";
                default        -> "0";
            };
        }
        return "(" + boxedType + ") null";
    }

    private static String labelFor(Object value, String name, String kind, int index) {
        if (value == null) return "null " + name;
        if (value instanceof String s) return s.isEmpty() ? "blank " + name : name + " " + index;
        return kind + " " + name + " " + (index + 1);
    }
}