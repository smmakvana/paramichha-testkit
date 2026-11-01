package com.paramichha.testkit.utils;


public class FormatJavaFile {

    public static String removeUnUsedImport(String source) {
        return JavaFormatting.removeUnusedImportsAndFormat(source);
    }
}
