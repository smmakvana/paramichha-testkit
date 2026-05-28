package com.paramichha.testkit.generator;

import java.util.stream.Collectors;

/**
 * Renders a {@link ParameterCoderInfo} to Java source.
 * Zero test knowledge. Zero class knowledge.
 */
public final class ParameterCoder implements FreshCoder<ParameterCoderInfo> {

    @Override
    public String code(ParameterCoderInfo info) {
        String annotations = info.annotations().stream()
                .map(a -> a + " ")
                .collect(Collectors.joining());
        return CoderTemplates.PARAMETER
                .replace("{annotations}", annotations)
                .replace("{type}", info.type())
                .replace("{name}", info.name());
    }
}
