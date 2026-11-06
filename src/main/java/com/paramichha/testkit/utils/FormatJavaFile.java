package com.paramichha.testkit.utils;


public final class FormatJavaFile {

    private FormatJavaFile() {}

    public static String removeUnUsedImport(String source) {
        return JavaFormatting.removeUnusedImportsAndFormat(source);
    }
}
