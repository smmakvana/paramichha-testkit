package com.paramichha.testkit.generator;

import java.util.List;

/**
 * Complete description of a Java method parameter.
 * Fed to {@link ParameterCoder} to produce source.
 */
public record ParameterCoderInfo(
        List<String> annotations,
        String type,
        String name
) {
    public ParameterCoderInfo {
        annotations = List.copyOf(annotations);
    }

    /** Convenience — no annotations */
    public static ParameterCoderInfo of(String type, String name) {
        return new ParameterCoderInfo(List.of(), type, name);
    }
}
