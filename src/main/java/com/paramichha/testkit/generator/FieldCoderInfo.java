package com.paramichha.testkit.generator;

import java.util.List;

/**
 * Complete description of a Java field declaration.
 * Fed to {@link FieldCoder} to produce source.
 */
public record FieldCoderInfo(
        List<String> annotations,
        List<String> modifiers,
        String type,
        String name,
        String initializer      // null or blank = no initializer
) {
    public FieldCoderInfo {
        annotations = List.copyOf(annotations);
        modifiers   = List.copyOf(modifiers);
    }

    /** Convenience — no annotations, no initializer */
    public static FieldCoderInfo of(List<String> modifiers, String type, String name) {
        return new FieldCoderInfo(List.of(), modifiers, type, name, null);
    }
}
