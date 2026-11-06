package com.paramichha.testkit.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms a QUERY {@link MethodData} into {@link MethodCoderInfo} list.
 * Knows: query test patterns.
 * Produces: method syntax descriptions.
 */
final class QueryTransformer {

    private QueryTransformer() {}

    /**
     * Tag on a test that fails until a developer states the expected value. A build can leave
     * these out ({@code excludedGroups}) while the expectations are being written.
     */
    static final String TODO_TAG = "todo";

    static List<MethodCoderInfo> transform(MethodData method, ClassDataConfig config) {
        List<MethodCoderInfo> result = new ArrayList<>();
        ReturnData ret      = method.returnData();
        String     typeName = ret.type() != null ? ret.type().className() : "void";
        String     call     = config.instanceName() + "." + method.name() + "()";

        // testReturnsResult
        String returnsBody = typeName + " result = " + call + ";";
        boolean todo = false;
        if (!ret.assertionCode().isBlank()) {
            returnsBody += "\n" + ret.assertionCode();
        } else if (!ret.isVoid()) {
            // No known expected value: fail until a person states it, so the test cannot pass silently.
            returnsBody += "\norg.assertj.core.api.Assertions.fail(\"TODO: define the expected result of "
                    + method.name() + "(), e.g. assertThat(result).isEqualTo(...)\");";
            todo = true;
        }

        List<String> returnsAnnotations = new ArrayList<>(List.of("@Test",
                "@DisplayName(\"" + method.name() + " returns a value\")"));
        if (todo) returnsAnnotations.add("@Tag(\"" + TODO_TAG + "\")");
        result.add(MethodCoderInfo.of(
                returnsAnnotations,
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
