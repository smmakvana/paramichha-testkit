package com.paramichha.testkit;

import com.paramichha.utils.Tokens;

import java.util.Locale;

/**
 * Builds the file names and display names of generated tests from a {@link TestKitTemplate}.
 *
 * <p>Tokens:
 * <ul>
 *   <li>{@code {Class}}: the class name with the class prefix ({@code UnitCircle})</li>
 *   <li>{@code {Kind}}: {@code Creation}, {@code Field} or {@code Method}</li>
 *   <li>{@code {kind}}: the same, lowercase (display only)</li>
 *   <li>{@code {Name}}: the member name. File names capitalise it ({@code Radius}); display names keep it as written
 *       ({@code radius}, and {@code area()} for methods)</li>
 *   <li>{@code {Suffix}}: the test suffix ({@code Test}, {@code IT})</li>
 * </ul>
 *
 * <p>With the default template the output is exactly what TestKit has always generated.
 */
public final class NameFormat {

    private NameFormat() {}

    /** Name of a split file, without {@code .java}. {@code name} is capitalised; pass "" for creation. */
    public static String splitFileName(TestKitTemplate template, String sourceClass, String kind, String name) {
        return Tokens.resolve(template.splitFilePattern,
                "Class",  template.classPrefix + sourceClass,
                "Kind",   kind,
                "Name",   capitalise(name),
                "Suffix", template.testSuffix);
    }

    /** Text shown in the test tree for a nested or split class. */
    public static String display(TestKitTemplate template, String sourceClass, String kind, String name) {
        String pattern = template.nestedStyle ? "{Class} › {kind} {Name}" : template.displayPattern;
        String text = Tokens.resolve(pattern,
                "Class", sourceClass,
                "Kind",  kind,
                "kind",  kind.toLowerCase(Locale.ROOT),
                "Name",  name);
        text = text.replaceAll("\\s+", " ").trim();
        if (template.showSuffixInDisplay) text = text + " (" + template.testSuffix + ")";
        if (!template.displayPrefix.isEmpty()) text = template.displayPrefix + " " + text;
        return text;
    }

    private static String capitalise(String name) {
        if (name == null || name.isEmpty()) return "";
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
