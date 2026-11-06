package com.paramichha.testkit.utils;


import com.paramichha.datafactory.utils.JavaNames;
import com.paramichha.utils.Texts;

/**
 * Naming helpers for generated test code that the templates ({@code TestKitTemplate}) don't
 * cover: accessor names, the test-instance variable, field data sources and the label parameter.
 */
public final class TestNames {

    private TestNames() {}

    // =========================================================================
    // Accessor naming — pure string utility
    // =========================================================================

    /**
     * Returns the correct accessor name for a field.
     * <ul>
     *   <li>Record: {@code name()} → {@code "name"}</li>
     *   <li>Boolean field: {@code active} → {@code "isActive"}</li>
     *   <li>Everything else: {@code email} → {@code "getEmail"}</li>
     * </ul>
     */
    public static String getterName(String fieldName, boolean isBoolean, boolean isRecord) {
        if (isRecord) return fieldName;
        String suffix = Texts.capitalize(fieldName);
        return isBoolean ? "is" + suffix : "get" + suffix;
    }

    /** "Circle" → "circle" */
    public static String toVariable(String className) {
        return Texts.decapitalize(className);
    }

    // =========================================================================
    // Data source method names
    // =========================================================================

    /** "radius" → "validRadiusValues" */
    public static String validSource(String memberName) {
        return "valid" + JavaNames.className(memberName) + "Values";
    }

    /** "radius" → "invalidRadiusValues" */
    public static String invalidSource(String memberName) {
        return "invalid" + JavaNames.className(memberName) + "Values";
    }

    /**
     * The name of the first parameter of a parameterized test, which only carries the case label
     * shown by {@code @ParameterizedTest(name = "{0}")}: {@code description}, or another name if a
     * test parameter is already called that (a field or method parameter named "description").
     */
    public static String labelParameter(java.util.Collection<String> otherParameters) {
        String name = "description";
        if (!otherParameters.contains(name)) return name;
        name = "caseDescription";
        for (int i = 2; otherParameters.contains(name); i++) name = "caseDescription" + i;
        return name;
    }
}
