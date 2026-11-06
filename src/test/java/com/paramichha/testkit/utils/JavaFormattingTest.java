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

    @Test
    void removesUnusedImportsThenFormats() {
        assertThat(JavaFormatting.removeUnusedImportsAndFormat("import java.util.List;\nclass A{}"))
                .isEqualTo("class A {}\n");
    }

    @Test
    void aJvmWithoutTheCompilerExportsGetsUnformattedSourceAndOneWarning() {
        JavaFormatting.Step closed = src -> { throw new IllegalAccessError("jdk.compiler not exported"); };
        // Two callers failing at once: the outer one fails after the inner one already turned
        // formatting off, as two threads would. Only the first may warn.
        JavaFormatting.Step racing = src -> {
            JavaFormatting.run(src, closed);
            throw new IllegalAccessError("second caller");
        };
        try {
            String err = stderr(() -> {
                assertThat(JavaFormatting.run(UGLY, racing)).isEqualTo(UGLY);
                assertThat(JavaFormatting.enabled()).isFalse();   // off for the rest of the run
                assertThat(JavaFormatting.format(UGLY)).isEqualTo(UGLY);
            });
            assertThat(err).containsOnlyOnce("google-java-format is unavailable in this JVM (IllegalAccessError)");
        } finally {
            JavaFormatting.reset();
        }
    }

    @Test
    void theProbeReportsWhetherTheFormatterCanRun() {
        String err = stderr(() -> {
            assertThat(JavaFormatting.probe(s -> { throw new IllegalAccessError(); })).isFalse();
            assertThat(JavaFormatting.probe(s -> { throw new IllegalStateException("bad input"); })).isTrue();
        });
        assertThat(err).contains("unavailable");
    }

    private static String stderr(Runnable r) {
        java.io.PrintStream original = System.err;
        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        System.setErr(new java.io.PrintStream(buf, true));
        try { r.run(); } finally { System.setErr(original); }
        return buf.toString();
    }
}
