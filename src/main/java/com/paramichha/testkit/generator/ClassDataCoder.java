package com.paramichha.testkit.generator;

import com.paramichha.testkit.utils.TestNames;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Pure string assembly from {@link ClassData}.
 * <p>
 * Zero DataFactory. Zero ClassInfo. Zero TestSourceContext.
 * Fully deterministic — same ClassData always produces same String.
 * Fully testable in isolation — construct ClassData manually, assert output.
 */
final class ClassDataCoder {

    private static final String INDENT = "    ";
    private static final String SECTION_LINE =
            "  // =========================================================================\n";

    String code(ClassData data, ClassDataConfig config) {
        StringBuilder sb = new StringBuilder();

        // Header + package + imports
        sb.append(config.header()).append("\n");
        sb.append("package ").append(config.targetPackage()).append(";\n\n");
        config.imports().stream().sorted().forEach(i -> sb.append("import ").append(i).append(";\n"));
        sb.append("\n");

        // Class open
        sb.append("@DisplayName(\"").append(config.sourceClassName()).append("\")\n");
        sb.append("class ").append(data.className()).append(" {\n\n");

        // Static fields
        if (config.hasJakarta()) {
            sb.append(INDENT).append("private static Validator ").append(config.validatorField()).append(";\n\n");
            sb.append(INDENT).append("@BeforeAll\n");
            sb.append(INDENT).append("static void ").append(config.validatorSetup()).append("() {\n");
            sb.append(INDENT).append(INDENT).append(config.validatorField())
                    .append(" = Validation.buildDefaultValidatorFactory().getValidator();\n");
            sb.append(INDENT).append("}\n\n");
        }

        // Valid instance field
        if (!data.members().isEmpty()) {
            String constructorCall = buildConstructorCall(config.sourceClassName(), data, config);
            sb.append(INDENT).append(config.sourceClassName()).append(" ")
                    .append(config.instanceName()).append(" =\n");
            sb.append(INDENT).append(INDENT).append(constructorCall).append(";\n\n");
        }

        // Nested — Creation
        if (!config.isUtilityClass()) {
            sb.append(SECTION_LINE);
            sb.append("  // Creation\n");
            sb.append(SECTION_LINE).append("\n");
            sb.append(codeCreationNested(data, config));
        }

        // Nested — per field
        if (!config.isUtilityClass()) {
            for (MemberData member : data.members()) {
                String display = member.name();
                sb.append(SECTION_LINE);
                sb.append("  // ").append(display).append("\n");
                sb.append(SECTION_LINE).append("\n");
                sb.append(codeFieldNested(member, data, config));
            }
        }

        // Nested — per method
        for (MethodData method : data.methods()) {
            sb.append(SECTION_LINE);
            sb.append("  // ").append(method.displayName()).append("\n");
            sb.append(SECTION_LINE).append("\n");
            sb.append(codeMethodNested(method, data, config));
        }

        sb.append("}\n");
        return sb.toString();
    }

    // ── Creation nested ───────────────────────────────────────────────────

    private String codeCreationNested(ClassData data, ClassDataConfig config) {
        StringBuilder sb = new StringBuilder();
        String nestedName = config.classAffix().apply(config.sourceClassName() + "Creation");
        String instanceName = config.instanceName();

        sb.append(INDENT).append("@Nested\n");
        sb.append(INDENT).append("@DisplayName(\"")
                .append(com.paramichha.testkit.NameFormat.display(config.template(), config.sourceClassName(), "Creation", ""))
                .append("\")\n");
        sb.append(INDENT).append("class ").append(nestedName).append(" {\n\n");

        // testCanBeCreated
        sb.append(INDENT).append(INDENT).append("@Test\n");
        sb.append(INDENT).append(INDENT).append("@DisplayName(\"can be created\")\n");
        sb.append(INDENT).append(INDENT).append("void testCanBeCreated() {\n");
        sb.append(INDENT).append(INDENT).append(INDENT)
                .append("assertThat(").append(instanceName).append(").isNotNull();\n");
        sb.append(INDENT).append(INDENT).append("}\n\n");

        // testHoldsExpectedValues
        sb.append(INDENT).append(INDENT).append("@Test\n");
        sb.append(INDENT).append(INDENT).append("@DisplayName(\"holds expected values\")\n");
        sb.append(INDENT).append(INDENT).append("void testHoldsExpectedValues() {\n");
        List<Object> validParts = data.validValues();
        List<MemberData> members = data.members();
        for (int i = 0; i < members.size(); i++) {
            MemberData m = members.get(i);
            Object val = i < validParts.size() ? validParts.get(i) : null;
            if (!LiteralConverter.canSerialize(val)) continue;
            if (isComplexType(m.type())) continue;
            String accessor = config.isRecord()
                    ? instanceName + "." + m.name() + "()"
                    : instanceName + "." + TestNames.getterName(m.name(), isBooleanType(m.type()), config.isRecord()) + "()";
            sb.append(INDENT).append(INDENT).append(INDENT)
                    .append("assertThat(").append(accessor).append(").isEqualTo(")
                    .append(LiteralConverter.toLiteral(val)).append(");\n");
        }
        sb.append(INDENT).append(INDENT).append("}\n\n");

        // testIsValid
        if (config.hasJakarta() && data.isConstrained()) {
            sb.append(INDENT).append(INDENT).append("@Test\n");
            sb.append(INDENT).append(INDENT).append("@DisplayName(\"is valid (no constraint violations)\")\n");
            sb.append(INDENT).append(INDENT).append("void testIsValid() {\n");
            sb.append(INDENT).append(INDENT).append(INDENT)
                    .append("assertThat(").append(config.validatorField())
                    .append(".validate(").append(instanceName).append(")).isEmpty();\n");
            sb.append(INDENT).append(INDENT).append("}\n\n");
        }

        sb.append(INDENT).append("}\n\n");
        return sb.toString();
    }

    // ── Field nested ──────────────────────────────────────────────────────

    private String codeFieldNested(MemberData member, ClassData classData,
                                   ClassDataConfig config) {
        StringBuilder sb = new StringBuilder();
        String className = config.sourceClassName();
        String name = member.name();
        String boxedType = member.type().className();
        String nestedName = config.classAffix().apply(className + com.paramichha.datafactory.utils.JavaNames.className(name));
        String acceptsM = "testAccepts" + com.paramichha.utils.Texts.capitalize(name);
        String rejectsM = "testRejects" + com.paramichha.utils.Texts.capitalize(name);
        String validSrc = TestNames.validSource(name);
        String invalidSrc = TestNames.invalidSource(name);

        List<Object> validVals = member.validValues().stream()
                .filter(LiteralConverter::canSerialize).collect(Collectors.toList());
        List<Object> invalidVals = member.invalidValues().stream()
                .filter(LiteralConverter::canSerialize).collect(Collectors.toList());

        sb.append(INDENT).append("@Nested\n");
        sb.append(INDENT).append("@DisplayName(\"")
                .append(com.paramichha.testkit.NameFormat.display(config.template(), config.sourceClassName(), "Field", name))
                .append("\")\n");
        sb.append(INDENT).append("class ").append(nestedName).append(" {\n\n");

        // Accepts test
        if (!validVals.isEmpty()) {
            String constructorWithVar = buildConstructorCallWithVar(className, classData, member, config);
            String assertion = member.isConstrained()
                    ? "assertThat(" + config.validatorField() + ".validate(obj)).isEmpty();"
                    : "assertThat(obj." + TestNames.getterName(name, isBooleanType(member.type()), config.isRecord()) + "()).isEqualTo(" + name + ");";

            sb.append(INDENT).append(INDENT)
                    .append("@ParameterizedTest(name = \"{0}\")\n");
            sb.append(INDENT).append(INDENT)
                    .append("@MethodSource(\"").append(validSrc).append("\")\n");
            sb.append(INDENT).append(INDENT)
                    .append("@DisplayName(\"accepts valid value\")\n");
            sb.append(INDENT).append(INDENT)
                    .append("void ").append(acceptsM)
                    .append("(String description, ").append(boxedType).append(" ").append(name).append(") {\n");
            sb.append(INDENT).append(INDENT).append(INDENT)
                    .append(className).append(" obj = ").append(constructorWithVar).append(";\n");
            sb.append(INDENT).append(INDENT).append(INDENT).append(assertion).append("\n");
            sb.append(INDENT).append(INDENT).append("}\n\n");

            // Valid stream
            sb.append(INDENT).append(INDENT)
                    .append("static Stream<Arguments> ").append(validSrc).append("() {\n");
            sb.append(INDENT).append(INDENT).append(INDENT).append("return Stream.of(\n");
            for (int j = 0; j < validVals.size(); j++) {
                Object v = validVals.get(j);
                String lit = v == null ? "(" + boxedType + ") null" : LiteralConverter.toLiteral(v);
                sb.append(INDENT).append(INDENT).append(INDENT).append(INDENT)
                        .append("Arguments.of(\"").append(labelFor(v, name, "valid", j))
                        .append("\", ").append(lit).append(")");
                sb.append(j < validVals.size() - 1 ? ",\n" : "\n");
            }
            sb.append(INDENT).append(INDENT).append(INDENT).append(");\n");
            sb.append(INDENT).append(INDENT).append("}\n\n");
        }

        // Rejects test — always emitted
        {
            String constructorWithVar = buildConstructorCallWithVar(className, classData, member, config);
            sb.append(INDENT).append(INDENT)
                    .append("@ParameterizedTest(name = \"{0}\")\n");
            sb.append(INDENT).append(INDENT)
                    .append("@MethodSource(\"").append(invalidSrc).append("\")\n");
            sb.append(INDENT).append(INDENT)
                    .append("@DisplayName(\"rejects invalid value\")\n");
            sb.append(INDENT).append(INDENT)
                    .append("void ").append(rejectsM)
                    .append("(String description, ").append(boxedType).append(" ").append(name).append(") {\n");

            if (!member.isConstrained()) {
                // No constraints yet — create object with candidate value and assert it was created.
                // TODO: add constraints to this field, then replace assertThat(obj).isNotNull()
                //       with assertThat(validator.validate(obj)).isNotEmpty()
                sb.append(INDENT).append(INDENT).append(INDENT)
                        .append("// TODO: add constraints to ").append(name)
                        .append(", then replace assertThat(obj).isNotNull()\n");
                sb.append(INDENT).append(INDENT).append(INDENT)
                        .append("//       with assertThat(validator.validate(obj)).isNotEmpty()\n");
                sb.append(INDENT).append(INDENT).append(INDENT)
                        .append(className).append(" obj = ").append(constructorWithVar).append(";\n");
                sb.append(INDENT).append(INDENT).append(INDENT)
                        .append("assertThat(obj).isNotNull();\n");
            } else {
                sb.append(INDENT).append(INDENT).append(INDENT).append("try {\n");
                sb.append(INDENT).append(INDENT).append(INDENT).append(INDENT)
                        .append(className).append(" obj = ").append(constructorWithVar).append(";\n");
                sb.append(INDENT).append(INDENT).append(INDENT).append(INDENT)
                        .append("assertThat(").append(config.validatorField()).append(".validate(obj)).isNotEmpty();\n");
                sb.append(INDENT).append(INDENT).append(INDENT)
                        .append("} catch (Exception ignored) {\n");
                sb.append(INDENT).append(INDENT).append(INDENT).append(INDENT)
                        .append("// Constructor enforced the contract — test passes\n");
                sb.append(INDENT).append(INDENT).append(INDENT).append("}\n");
            }
            sb.append(INDENT).append(INDENT).append("}\n\n");

            // Invalid stream
            sb.append(INDENT).append(INDENT)
                    .append("static Stream<Arguments> ").append(invalidSrc).append("() {\n");
            if (!member.isConstrained()) {
                sb.append(INDENT).append(INDENT).append(INDENT)
                        .append("// TODO: add constraints to ").append(name).append(", or delete this test\n");
            }
            if (invalidVals.isEmpty()) {
                // Emit one placeholder so @ParameterizedTest always has at least one argument
                String placeholder = placeholderLiteral(member.type(), boxedType);
                sb.append(INDENT).append(INDENT).append(INDENT).append("return Stream.of(\n");
                sb.append(INDENT).append(INDENT).append(INDENT).append(INDENT)
                        .append("Arguments.of(\"no constraints defined — add some!\", ")
                        .append(placeholder).append("));\n");
            } else {
                sb.append(INDENT).append(INDENT).append(INDENT).append("return Stream.of(\n");
                for (int j = 0; j < invalidVals.size(); j++) {
                    Object v = invalidVals.get(j);
                    String lit = v == null ? "(" + boxedType + ") null" : LiteralConverter.toLiteral(v);
                    sb.append(INDENT).append(INDENT).append(INDENT).append(INDENT)
                            .append("Arguments.of(\"").append(labelFor(v, name, "invalid", j))
                            .append("\", ").append(lit).append(")");
                    sb.append(j < invalidVals.size() - 1 ? ",\n" : "\n");
                }
                sb.append(INDENT).append(INDENT).append(INDENT).append(");\n");
            }
            sb.append(INDENT).append(INDENT).append("}\n\n");
        }

        sb.append(INDENT).append("}\n\n");
        return sb.toString();
    }

    // ── Method nested ─────────────────────────────────────────────────────

    private String codeMethodNested(MethodData method, ClassData classData,
                                    ClassDataConfig config) {
        StringBuilder sb = new StringBuilder();

        sb.append(INDENT).append("@Nested\n");
        sb.append(INDENT).append("@DisplayName(\"").append(method.displayName()).append("\")\n");
        sb.append(INDENT).append("class ").append(method.nestedClassName()).append(" {\n\n");

        if (method.returnData().needsWrapperRecord())
            sb.append(INDENT).append(INDENT)
                    .append(method.returnData().wrapperRecord()).append("\n\n");

        String body = switch (method.kind()) {
            case COMMAND -> codeCommand(method, config);
            case QUERY -> codeQuery(method, config);
            case MUTATOR,
                 TRANSFORMER,
                 OPERATION -> codeMutatorOrTransformer(method, config);
        };
        sb.append(body);

        sb.append(INDENT).append("}\n\n");
        return sb.toString();
    }

    private String codeCommand(MethodData method, ClassDataConfig config) {
        StringBuilder sb = new StringBuilder();
        String call = config.instanceName() + "." + method.name() + "()";

        sb.append(INDENT).append(INDENT).append("@Test\n");
        sb.append(INDENT).append(INDENT).append("@DisplayName(\"executes without exception\")\n");
        sb.append(INDENT).append(INDENT).append("void testExecutesWithoutException() {\n");
        sb.append(INDENT).append(INDENT).append(INDENT)
                .append("assertThatCode(() -> ").append(call).append(").doesNotThrowAnyException();\n");
        sb.append(INDENT).append(INDENT).append("}\n\n");

        if (config.hasJakarta()) {
            sb.append(INDENT).append(INDENT).append("@Test\n");
            sb.append(INDENT).append(INDENT)
                    .append("@DisplayName(\"object remains valid after execution\")\n");
            sb.append(INDENT).append(INDENT)
                    .append("void testObjectRemainsValidAfterExecution() {\n");
            sb.append(INDENT).append(INDENT).append(INDENT).append(call).append(";\n");
            sb.append(INDENT).append(INDENT).append(INDENT)
                    .append("assertThat(").append(config.validatorField())
                    .append(".validate(").append(config.instanceName()).append(")).isEmpty();\n");
            sb.append(INDENT).append(INDENT).append("}\n\n");
        }
        return sb.toString();
    }

    private String codeQuery(MethodData method, ClassDataConfig config) {
        StringBuilder sb = new StringBuilder();
        ReturnData ret = method.returnData();
        String typeName = ret.type() != null ? ret.type().className() : "void";
        String call = config.instanceName() + "." + method.name() + "()";

        sb.append(INDENT).append(INDENT).append("@Test\n");
        sb.append(INDENT).append(INDENT)
                .append("@DisplayName(\"").append(method.name()).append(" returns a result\")\n");
        sb.append(INDENT).append(INDENT).append("void testReturnsResult() {\n");
        sb.append(INDENT).append(INDENT).append(INDENT)
                .append(typeName).append(" result = ").append(call).append(";\n");
        if (!ret.assertionCode().isBlank())
            sb.append(INDENT).append(INDENT).append(INDENT).append(ret.assertionCode()).append("\n");
        sb.append(INDENT).append(INDENT).append("}\n\n");

        sb.append(INDENT).append(INDENT).append("@Test\n");
        sb.append(INDENT).append(INDENT)
                .append("@DisplayName(\"").append(method.name()).append(" returns consistent result\")\n");
        sb.append(INDENT).append(INDENT).append("void testReturnsConsistentResult() {\n");
        sb.append(INDENT).append(INDENT).append(INDENT)
                .append("assertThat(").append(call).append(").isEqualTo(").append(call).append(");\n");
        sb.append(INDENT).append(INDENT).append("}\n\n");
        return sb.toString();
    }

    private String codeMutatorOrTransformer(MethodData method, ClassDataConfig config) {
        StringBuilder sb = new StringBuilder();
        ReturnData ret = method.returnData();
        boolean anyConstrained = method.anyParamConstrained();
        String paramSig = buildParamSig(method.params());
        String paramCall = buildParamCall(method.params());
        String call = config.instanceName() + "." + method.name() + "(" + paramCall + ")";

        // testAcceptsValidInput
        sb.append(INDENT).append(INDENT)
                .append("@ParameterizedTest(name = \"{0}\")\n");
        sb.append(INDENT).append(INDENT)
                .append("@MethodSource(\"").append(method.validSourceName()).append("\")\n");
        sb.append(INDENT).append(INDENT)
                .append("@DisplayName(\"").append(method.name()).append(" accepts valid input\")\n");
        sb.append(INDENT).append(INDENT)
                .append("void testAcceptsValidInput(String description, ")
                .append(paramSig).append(") {\n");
        if (ret.isVoid()) {
            sb.append(INDENT).append(INDENT).append(INDENT)
                    .append("assertThatCode(() -> ").append(call).append(").doesNotThrowAnyException();\n");
        } else {
            sb.append(INDENT).append(INDENT).append(INDENT)
                    .append(ret.type().className()).append(" result = ").append(call).append(";\n");
            if (!ret.assertionCode().isBlank())
                sb.append(INDENT).append(INDENT).append(INDENT).append(ret.assertionCode()).append("\n");
        }
        sb.append(INDENT).append(INDENT).append("}\n\n");

        // testRejectsInvalidInput
        if (anyConstrained) {
            sb.append(INDENT).append(INDENT)
                    .append("@ParameterizedTest(name = \"{0}\")\n");
            sb.append(INDENT).append(INDENT)
                    .append("@MethodSource(\"").append(method.invalidSourceName()).append("\")\n");
            sb.append(INDENT).append(INDENT)
                    .append("@DisplayName(\"").append(method.name()).append(" rejects invalid input\")\n");
            sb.append(INDENT).append(INDENT)
                    .append("void testRejectsInvalidInput(String description, ")
                    .append(paramSig).append(") {\n");
            for (ParamData p : method.params())
                p.enforcementHints().forEach(h ->
                        sb.append(INDENT).append(INDENT).append(INDENT).append(h).append("\n"));
            sb.append(INDENT).append(INDENT).append(INDENT)
                    .append("assertThatThrownBy(() -> ").append(call).append(")\n");
            sb.append(INDENT).append(INDENT).append(INDENT).append(INDENT)
                    .append(".isInstanceOf(Exception.class);\n");
            sb.append(INDENT).append(INDENT).append("}\n\n");
        }

        // Valid stream
        sb.append(INDENT).append(INDENT)
                .append("static Stream<Arguments> ").append(method.validSourceName()).append("() {\n");
        sb.append(INDENT).append(INDENT).append(INDENT).append("return Stream.of(\n");
        sb.append(INDENT).append(INDENT).append(INDENT).append(INDENT)
                .append("Arguments.of(\"valid ").append(method.name()).append("\"");
        for (ParamData p : method.params()) {
            Object val = p.validValues().isEmpty() ? null : p.validValues().get(0);
            String lit = literalFor(val, p.type());
            sb.append(",\n").append(INDENT).append(INDENT).append(INDENT)
                    .append(INDENT).append(INDENT).append(lit);
        }
        sb.append(")\n");
        sb.append(INDENT).append(INDENT).append(INDENT).append(");\n");
        sb.append(INDENT).append(INDENT).append("}\n\n");

        // Invalid stream
        if (anyConstrained) {
            sb.append(INDENT).append(INDENT)
                    .append("static Stream<Arguments> ").append(method.invalidSourceName()).append("() {\n");
            sb.append(INDENT).append(INDENT).append(INDENT).append("return Stream.of(\n");
            List<String> rows = new ArrayList<>();
            for (int pi = 0; pi < method.params().size(); pi++) {
                ParamData p = method.params().get(pi);
                if (!p.isConstrained()) continue;
                for (Object inv : p.invalidValues()) {
                    String invLit = literalFor(inv, p.type());
                    StringBuilder row = new StringBuilder();
                    row.append(INDENT).append(INDENT).append(INDENT).append(INDENT)
                            .append("Arguments.of(\"invalid ").append(p.name()).append("\"");
                    for (int qi = 0; qi < method.params().size(); qi++) {
                        ParamData q = method.params().get(qi);
                        Object val = q.validValues().isEmpty() ? null : q.validValues().get(0);
                        String lit = qi == pi ? invLit : literalFor(val, q.type());
                        row.append(",\n").append(INDENT).append(INDENT).append(INDENT)
                                .append(INDENT).append(INDENT).append(lit);
                    }
                    row.append(")");
                    rows.add(row.toString());
                }
            }
            for (int ri = 0; ri < rows.size(); ri++) {
                sb.append(rows.get(ri));
                sb.append(ri < rows.size() - 1 ? ",\n" : "\n");
            }
            sb.append(INDENT).append(INDENT).append(INDENT).append(");\n");
            sb.append(INDENT).append(INDENT).append("}\n\n");
        }

        return sb.toString();
    }

    // ── Constructor call helpers ──────────────────────────────────────────

    private String buildConstructorCall(String className, ClassData data,
                                        ClassDataConfig config) {
        List<Object> parts = data.validValues();
        List<MemberData> members = data.members();
        StringBuilder sb = new StringBuilder("new ").append(className).append("(\n");
        for (int i = 0; i < members.size(); i++) {
            if (i > 0) sb.append(",\n");
            Object val = i < parts.size() ? parts.get(i) : null;
            String lit = val == null
                    ? "(" + members.get(i).type().className() + ") null"
                    : LiteralConverter.canSerialize(val) ? LiteralConverter.toLiteral(val)
                    : "(" + members.get(i).type().className() + ") null";
            sb.append(INDENT).append(INDENT).append(INDENT).append(lit);
        }
        sb.append(")");
        return sb.toString();
    }

    private String buildConstructorCallWithVar(String className, ClassData data,
                                               MemberData targetMember,
                                               ClassDataConfig config) {
        List<Object> parts = data.validValues();
        List<MemberData> members = data.members();
        StringBuilder sb = new StringBuilder("new ").append(className).append("(\n");
        for (int i = 0; i < members.size(); i++) {
            if (i > 0) sb.append(",\n");
            MemberData m = members.get(i);
            if (m.name().equals(targetMember.name())) {
                sb.append(INDENT).append(INDENT).append(INDENT).append(m.name());
            } else {
                Object val = i < parts.size() ? parts.get(i) : null;
                String lit = val == null
                        ? "(" + m.type().className() + ") null"
                        : LiteralConverter.canSerialize(val) ? LiteralConverter.toLiteral(val)
                        : "(" + m.type().className() + ") null";
                sb.append(INDENT).append(INDENT).append(INDENT).append(lit);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    // ── Param helpers ─────────────────────────────────────────────────────

    private static String buildParamSig(List<ParamData> params) {
        return params.stream()
                .map(p -> p.type().className() + " " + p.name())
                .collect(Collectors.joining(", "));
    }

    private static String buildParamCall(List<ParamData> params) {
        return params.stream().map(ParamData::name).collect(Collectors.joining(", "));
    }

    // ── Utilities ─────────────────────────────────────────────────────────

    private static boolean isBooleanType(ClassData type) {
        return (type.isPrimitive() && type.className().equals("boolean"));
    }

    private static boolean isComplexType(ClassData type) {
        if (type.isPrimitive() || type.isVoid()) return false;
        if (type.isArray() || type.isCollection() || type.isMap()) return false;
        String pkg = type.packageName();
        return !pkg.startsWith("java.") && !pkg.startsWith("jakarta.")
                && !type.isEnum() && !type.className().equals("String");
    }

    private static String labelFor(Object value, String name, String kind, int index) {
        if (value == null) return "null " + name;
        if (value instanceof String s) return s.isEmpty() ? "blank " + name : name + " " + index;
        return kind + " " + name + " " + (index + 1);
    }

    /**
     * Returns a placeholder literal for unconstrained rejects stream.
     * Primitives get their zero/default, reference types get null cast.
     */
    private static String placeholderLiteral(ClassData type, String boxedType) {
        if (type.isPrimitive()) {
            return switch (type.className()) {
                case "int" -> "0";
                case "long" -> "0L";
                case "double" -> "0.0d";
                case "float" -> "0.0f";
                case "boolean" -> "false";
                case "byte" -> "(byte) 0";
                case "short" -> "(short) 0";
                case "char" -> "'\\0'";
                default -> "0";
            };
        }
        return "(" + boxedType + ") null";
    }

    // capitalize removed — use Texts.capitalize() or TestNames

    // getterName — delegated to TestNames.getterName(fieldName, isBoolean, isRecord)

    // ── Literal — primitives get default value, references get null cast ──

    private static String literalFor(Object val, ClassData type) {
        if (val != null) {
            String lit = LiteralConverter.toLiteralOrNull(val);
            if (lit != null) return lit;
        }
        if (type.isPrimitive()) {
            return switch (type.className()) {
                case "int" -> "0";
                case "long" -> "0L";
                case "double" -> "0.0d";
                case "float" -> "0.0f";
                case "boolean" -> "false";
                case "byte" -> "(byte) 0";
                case "short" -> "(short) 0";
                case "char" -> "'\0'";
                default -> "0";
            };
        }
        return "(" + type.className() + ") null";
    }
}