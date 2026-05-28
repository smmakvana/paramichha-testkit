package com.paramichha.testkit.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Transforms a MUTATOR/TRANSFORMER/OPERATION {@link MethodData} into {@link MethodCoderInfo} list.
 * Knows: mutator test patterns.
 * Produces: method syntax descriptions.
 */
final class MutatorTransformer {

    static List<MethodCoderInfo> transform(MethodData method, ClassDataConfig config) {
        List<MethodCoderInfo> result = new ArrayList<>();
        ReturnData ret          = method.returnData();
        boolean    anyConstrained = method.anyParamConstrained();
        String     paramSig     = buildParamSig(method.params());
        String     paramCall    = buildParamCall(method.params());
        String     call         = config.instanceName() + "." + method.name() + "(" + paramCall + ")";

        // testAcceptsValidInput
        String acceptsBody;
        if (ret.isVoid()) {
            acceptsBody = "assertThatCode(() -> " + call + ").doesNotThrowAnyException();";
        } else {
            acceptsBody = ret.type().className() + " result = " + call + ";";
            if (!ret.assertionCode().isBlank())
                acceptsBody += "\n" + ret.assertionCode();
        }

        result.add(new MethodCoderInfo(
                List.of("@ParameterizedTest(name = \"{0}\")",
                        "@MethodSource(\"" + method.validSourceName() + "\")",
                        "@DisplayName(\"" + method.name() + " accepts valid input\")"),
                List.of(),
                "void",
                "testAcceptsValidInput",
                buildParams(method.params()),
                acceptsBody));

        // testRejectsInvalidInput
        if (anyConstrained) {
            String rejectsBody = method.params().stream()
                    .flatMap(p -> p.enforcementHints().stream())
                    .collect(Collectors.joining("\n"));
            if (!rejectsBody.isBlank()) rejectsBody += "\n";
            rejectsBody += "assertThatThrownBy(() -> " + call + ")\n"
                    + "    .isInstanceOf(Exception.class);";

            result.add(new MethodCoderInfo(
                    List.of("@ParameterizedTest(name = \"{0}\")",
                            "@MethodSource(\"" + method.invalidSourceName() + "\")",
                            "@DisplayName(\"" + method.name() + " rejects invalid input\")"),
                    List.of(),
                    "void",
                    "testRejectsInvalidInput",
                    buildParams(method.params()),
                    rejectsBody));
        }

        // validSourceName stream
        String validStreamBody = buildValidStream(method);
        result.add(new MethodCoderInfo(
                List.of(),
                List.of("static"),
                "Stream<Arguments>",
                method.validSourceName(),
                List.of(),
                validStreamBody));

        // invalidSourceName stream
        if (anyConstrained) {
            String invalidStreamBody = buildInvalidStream(method);
            result.add(new MethodCoderInfo(
                    List.of(),
                    List.of("static"),
                    "Stream<Arguments>",
                    method.invalidSourceName(),
                    List.of(),
                    invalidStreamBody));
        }

        return List.copyOf(result);
    }

    // ── Helpers ───────────────────────────────────────────────────────────

    private static List<ParameterCoderInfo> buildParams(List<ParamData> params) {
        List<ParameterCoderInfo> result = new ArrayList<>();
        result.add(ParameterCoderInfo.of("String", "description"));
        params.forEach(p -> result.add(ParameterTransformer.transform(p)));
        return List.copyOf(result);
    }

    private static String buildParamSig(List<ParamData> params) {
        return params.stream()
                .map(p -> p.type().className() + " " + p.name())
                .collect(Collectors.joining(", "));
    }

    private static String buildParamCall(List<ParamData> params) {
        return params.stream().map(ParamData::name).collect(Collectors.joining(", "));
    }

    private static String buildValidStream(MethodData method) {
        StringBuilder sb = new StringBuilder("return Stream.of(\n");
        sb.append("    Arguments.of(\"valid ").append(method.name()).append("\"");
        for (ParamData p : method.params()) {
            Object val = p.validValues().isEmpty() ? null : p.validValues().get(0);
            sb.append(",\n        ").append(literalFor(val, p.type()));
        }
        sb.append(")\n);");
        return sb.toString();
    }

    private static String buildInvalidStream(MethodData method) {
        StringBuilder sb = new StringBuilder("return Stream.of(\n");
        List<String> rows = new ArrayList<>();
        for (int pi = 0; pi < method.params().size(); pi++) {
            ParamData p = method.params().get(pi);
            if (!p.isConstrained()) continue;
            for (Object inv : p.invalidValues()) {
                StringBuilder row = new StringBuilder();
                row.append("    Arguments.of(\"invalid ").append(p.name()).append("\"");
                for (int qi = 0; qi < method.params().size(); qi++) {
                    ParamData q   = method.params().get(qi);
                    Object    val = q.validValues().isEmpty() ? null : q.validValues().get(0);
                    String    lit = qi == pi ? literalFor(inv, p.type()) : literalFor(val, q.type());
                    row.append(",\n        ").append(lit);
                }
                row.append(")");
                rows.add(row.toString());
            }
        }
        for (int i = 0; i < rows.size(); i++) {
            sb.append(rows.get(i));
            sb.append(i < rows.size() - 1 ? ",\n" : "\n");
        }
        sb.append(");");
        return sb.toString();
    }

    private static String literalFor(Object val, ClassData type) {
        if (val != null) {
            String lit = LiteralConverter.toLiteralOrNull(val);
            if (lit != null) return lit;
        }
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
        return "(" + type.className() + ") null";
    }
}
