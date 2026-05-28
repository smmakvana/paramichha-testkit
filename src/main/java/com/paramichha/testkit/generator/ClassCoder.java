package com.paramichha.testkit.generator;

import java.util.stream.Collectors;

/**
 * Renders a {@link ClassCoderInfo} to Java source.
 *
 * Zero test knowledge. Renders any Java class — test, source, nested.
 * Recurses into nestedClasses for single-file mode.
 */
public final class ClassCoder implements FreshCoder<ClassCoderInfo> {

    private static final FieldCoder  FIELD_CODER  = new FieldCoder();
    private static final MethodCoder METHOD_CODER = new MethodCoder();

    @Override
    public String code(ClassCoderInfo info) {
        String pkg = info.packageName() != null && !info.packageName().isBlank()
                ? "package " + info.packageName() + ";\n\n"
                : "";
        String imports = info.imports().stream()
                .sorted()
                .map(i -> "import " + i + ";\n")
                .collect(Collectors.joining());
        if (!imports.isBlank()) imports += "\n";

        String annotations = info.annotations().stream()
                .map(a -> a + "\n")
                .collect(Collectors.joining());
        String modifiers = info.modifiers().stream()
                .map(m -> m + " ")
                .collect(Collectors.joining());
        String fields = info.fields().stream()
                .map(FIELD_CODER::code)
                .collect(Collectors.joining());
        String methods = info.methods().stream()
                .map(METHOD_CODER::code)
                .collect(Collectors.joining("\n"));
        String nested = info.nestedClasses().stream()
                .map(this::code)
                .collect(Collectors.joining("\n"));

        return CoderTemplates.CLASS
                .replace("{package}", pkg)
                .replace("{imports}", imports)
                .replace("{annotations}", annotations)
                .replace("{modifiers}", modifiers)
                .replace("{name}", info.name())
                .replace("{fields}", fields)
                .replace("{methods}", methods)
                .replace("{nestedClasses}", nested);
    }
}
