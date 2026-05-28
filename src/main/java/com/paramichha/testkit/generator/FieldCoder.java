package com.paramichha.testkit.generator;

import java.util.stream.Collectors;

/**
 * Renders a {@link FieldCoderInfo} to Java source.
 * Zero test knowledge. Zero method knowledge.
 */
public final class FieldCoder implements FreshCoder<FieldCoderInfo> {

    @Override
    public String code(FieldCoderInfo info) {
        String annotations = info.annotations().stream()
                .map(a -> a + "\n")
                .collect(Collectors.joining());
        String modifiers = info.modifiers().stream()
                .map(m -> m + " ")
                .collect(Collectors.joining());
        String initializer = (info.initializer() != null && !info.initializer().isBlank())
                ? " =\n        " + info.initializer()
                : "";
        return CoderTemplates.FIELD
                .replace("{annotations}", annotations)
                .replace("{modifiers}", modifiers)
                .replace("{type}", info.type())
                .replace("{name}", info.name())
                .replace("{initializer}", initializer);
    }
}
