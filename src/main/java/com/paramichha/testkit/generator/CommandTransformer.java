package com.paramichha.testkit.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms a COMMAND {@link MethodData} into {@link MethodCoderInfo} list.
 * Knows: command test patterns.
 * Produces: method syntax descriptions.
 */
final class CommandTransformer {

    static List<MethodCoderInfo> transform(MethodData method, ClassDataConfig config) {
        List<MethodCoderInfo> result = new ArrayList<>();
        String call = config.instanceName() + "." + method.name() + "()";

        // testExecutesWithoutException
        result.add(MethodCoderInfo.of(
                List.of("@Test",
                        "@DisplayName(\"executes without exception\")"),
                "void",
                "testExecutesWithoutException",
                "assertThatCode(() -> " + call + ").doesNotThrowAnyException();"));

        // testObjectRemainsValidAfterExecution — only if Jakarta present
        if (config.hasJakarta()) {
            result.add(MethodCoderInfo.of(
                    List.of("@Test",
                            "@DisplayName(\"object remains valid after execution\")"),
                    "void",
                    "testObjectRemainsValidAfterExecution",
                    call + ";\n"
                    + "assertThat(" + config.validatorField()
                    + ".validate(" + config.instanceName() + ")).isEmpty();"));
        }

        return List.copyOf(result);
    }
}
