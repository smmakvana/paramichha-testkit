package com.paramichha.testkit.generator;

import java.util.List;

/**
 * Complete description of a Java method.
 * Fed to {@link MethodCoder} to produce source.
 *
 * Body is a pre-built string — the transformer owns all body logic.
 * MethodCoder just wraps it in the template.
 */
public record MethodCoderInfo(
        List<String> annotations,
        List<String> modifiers,
        String returnType,
        String name,
        List<ParameterCoderInfo> params,
        String body
) {
    public MethodCoderInfo {
        annotations = List.copyOf(annotations);
        modifiers   = List.copyOf(modifiers);
        params      = List.copyOf(params);
    }

    /** Convenience — no modifiers, no params */
    public static MethodCoderInfo of(List<String> annotations, String returnType,
                                     String name, String body) {
        return new MethodCoderInfo(annotations, List.of(), returnType, name, List.of(), body);
    }
}
