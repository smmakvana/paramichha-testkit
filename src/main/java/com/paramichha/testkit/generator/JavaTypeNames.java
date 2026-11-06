package com.paramichha.testkit.generator;

/**
 * Java type names as they are written in source code.
 */
final class JavaTypeNames {

    private JavaTypeNames() {}

    /**
     * Returns the source-level type name, handling inner classes.
     * e.g. OuterClass$Inner → OuterClass.Inner
     */
    static String of(Class<?> type) {
        if (type.getEnclosingClass() != null)
            return type.getEnclosingClass().getSimpleName() + "." + type.getSimpleName();
        return type.getSimpleName();
    }
}
