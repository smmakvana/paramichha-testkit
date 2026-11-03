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
        if (!ret.assertionCode().isBlank()) {
            returnsBody += "\n" + ret.assertionCode();
        } else if (!ret.isVoid()) {
            // No known expected value: fail until a person states it, so the test cannot pass silently.
            returnsBody += "\norg.assertj.core.api.Assertions.fail(\"TODO: define the expected result of "
                    + method.name() + "(), e.g. assertThat(result).isEqualTo(...)\");";
        }

        result.add(MethodCoderInfo.of(
                List.of("@Test",
                        "@DisplayName(\"" + method.name() + " returns a value\")"),
                "void",
                "testReturnsResult",
                returnsBody));

        // testReturnsConsistentResult
        result.add(MethodCoderInfo.of(
                List.of("@Test",
                        "@DisplayName(\"" + method.name() + " returns the same value on repeat calls\")"),
                "void",
                "testReturnsConsistentResult",
                "assertThat(" + call + ").isEqualTo(" + call + ");"));

        return List.copyOf(result);
    }
}
