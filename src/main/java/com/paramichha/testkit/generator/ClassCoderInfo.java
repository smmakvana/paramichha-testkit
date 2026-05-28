package com.paramichha.testkit.generator;

import java.util.List;

/**
 * Complete description of a Java class.
 * Fed to {@link ClassCoder} to produce source.
 *
 * Works for any class — test class, nested class, source class.
 * ClassCoder does not know which kind it is writing.
 *
 * For nested classes: packageName and imports are empty.
 * For root classes: packageName and imports are populated.
 *
 * nestedClasses is a recursive list — ClassCoder renders them inline (single file)
 * or ClassDataTransformer flattens them (split file).
 */
public record ClassCoderInfo(
        String packageName,
        List<String> imports,
        List<String> annotations,
        List<String> modifiers,
        String name,
        List<FieldCoderInfo> fields,
        List<MethodCoderInfo> methods,
        List<ClassCoderInfo> nestedClasses
) {
    public ClassCoderInfo {
        imports        = List.copyOf(imports);
        annotations    = List.copyOf(annotations);
        modifiers      = List.copyOf(modifiers);
        fields         = List.copyOf(fields);
        methods        = List.copyOf(methods);
        nestedClasses  = List.copyOf(nestedClasses);
    }

    /** Convenience — nested class (no package, no imports) */
    public static ClassCoderInfo nested(List<String> annotations, List<String> modifiers,
                                        String name, List<FieldCoderInfo> fields,
                                        List<MethodCoderInfo> methods,
                                        List<ClassCoderInfo> nestedClasses) {
        return new ClassCoderInfo("", List.of(), annotations, modifiers,
                name, fields, methods, nestedClasses);
    }
}
