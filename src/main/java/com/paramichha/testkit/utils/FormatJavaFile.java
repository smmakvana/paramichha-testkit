package com.paramichha.testkit.utils;

import com.google.googlejavaformat.java.RemoveUnusedImports;

public class FormatJavaFile {

    public static String removeUnUsedImport(String source) {
        try {
            String cleaned = RemoveUnusedImports.removeUnusedImports(source);
            return new com.google.googlejavaformat.java.Formatter().formatSource(cleaned);
        } catch (Exception | IllegalAccessError ignored) {
            return source;
        }
    }
}
