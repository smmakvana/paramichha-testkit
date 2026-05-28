package com.paramichha.testkit.generator;

import java.util.stream.Collectors;

/**
 * Renders a {@link MethodCoderInfo} to Java source.
 * Zero test knowledge. Zero class knowledge.
 */
public final class MethodCoder implements FreshCoder<MethodCoderInfo> {

    private static final ParameterCoder PARAM_CODER = new ParameterCoder();

    @Override
    public String code(MethodCoderInfo info) {
        String annotations = info.annotations().stream()
                .map(a -> a + "\n")
                .collect(Collectors.joining());
        String modifiers = info.modifiers().stream()
                .map(m -> m + " ")
                .collect(Collectors.joining());
        String params = info.params().stream()
                .map(PARAM_CODER::code)
                .collect(Collectors.joining(", "));
        return CoderTemplates.METHOD
                .replace("{annotations}", annotations)
                .replace("{modifiers}", modifiers)
                .replace("{returnType}", info.returnType())
                .replace("{name}", info.name())
                .replace("{params}", params)
                .replace("{body}", info.body());
    }
}
