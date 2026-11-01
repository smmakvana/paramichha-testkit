package com.paramichha.testkit.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JavaFormattingTest {

    private static final String UGLY = "class A{int x;}";

    @Test
    void formatsWhenAvailable() {
        // surefire runs with the jdk.compiler exports, so the formatter is available here
        assertThat(JavaFormatting.enabled()).isTrue();
        assertThat(JavaFormatting.format(UGLY)).isEqualTo("class A {\n  int x;\n}\n");
    }

    @Test
    void propertyTurnsFormattingOff() {
        String old = System.getProperty(JavaFormatting.FORMAT_PROPERTY);
        System.setProperty(JavaFormatting.FORMAT_PROPERTY, "false");
        try {
            assertThat(JavaFormatting.enabled()).isFalse();
            assertThat(JavaFormatting.format(UGLY)).isEqualTo(UGLY);
            assertThat(JavaFormatting.removeUnusedImportsAndFormat(UGLY)).isEqualTo(UGLY);
        } finally {
            if (old == null) System.clearProperty(JavaFormatting.FORMAT_PROPERTY);
            else System.setProperty(JavaFormatting.FORMAT_PROPERTY, old);
        }
    }

    @Test
    void unparseableSourceIsReturnedUnchanged() {
        assertThat(JavaFormatting.format("class {")).isEqualTo("class {");
    }
}
