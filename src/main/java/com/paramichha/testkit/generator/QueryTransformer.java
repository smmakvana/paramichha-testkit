package com.paramichha.testkit.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms a QUERY {@link MethodData} into {@link MethodCoderInfo} list.
 * Knows: query test patterns.
 * Produces: method syntax descriptions.
 */
final class QueryTransformer {

    static List<MethodCoderInfo> transform(MethodData method, ClassDataConfig config) {
        List<MethodCoderInfo> result = new ArrayList<>();
        ReturnData ret      = method.returnData();
        String     typeName = ret.type() != null ? ret.type().className() : "void";
        String     call     = config.instanceName() + "." + method.name() + "()";

        // testReturnsResult
        String returnsBody = typeName + " result = " + call + ";";
        if (!ret.assertionCode().isBlank())
            returnsBody += "\n" + ret.assertionCode();

        result.add(MethodCoderInfo.of(
                List.of("@Test",
                        "@DisplayName(\"" + method.name() + " returns a result\")"),
                "void",
                "testReturnsResult",
                returnsBody));

        // testReturnsConsistentResult
        result.add(MethodCoderInfo.of(
                List.of("@Test",
                        "@DisplayName(\"" + method.name() + " returns consistent result\")"),
                "void",
                "testReturnsConsistentResult",
                "assertThat(" + call + ").isEqualTo(" + call + ");"));

        return List.copyOf(result);
    }
}
