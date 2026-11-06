package com.paramichha.testkit.utils;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.RemoveUnusedImports;

/**
 * Optional google-java-format step for generated sources.
 *
 * <p>google-java-format uses {@code jdk.compiler} internals, which the JVM only allows when it
 * is started with {@code --add-exports jdk.compiler/com.sun.tools.javac.*=ALL-UNNAMED}. An
 * embedding host — an IDE plugin, a build tool, a plain {@code main} — usually isn't, and the
 * formatter then fails with {@link IllegalAccessError} (an {@code Error}, which slips past
 * {@code catch (Exception)}).
 *
 * <p>This class never lets that escape: formatting is probed once, and if it can't run the
 * source is returned unformatted (it is still valid Java) with a single warning naming the fix.
 * Set {@code -Dtestkit.format=false} to skip formatting entirely, e.g. when the host applies
 * its own code style.
 */
public final class JavaFormatting {

    /** System property that turns formatting off when set to {@code false}. */
    public static final String FORMAT_PROPERTY = "testkit.format";

    private static volatile Boolean available;

    /** One formatting step; may fail with a {@link LinkageError} when jdk.compiler is closed. */
    @FunctionalInterface
    interface Step {
        String apply(String source) throws Exception;
    }

    private static final Step FORMAT = source -> new Formatter().formatSource(source);

    private JavaFormatting() {}

    /** Formats {@code source}, or returns it unchanged if formatting is off or can't run. */
    public static String format(String source) {
        return run(source, FORMAT);
    }

    /** Removes unused imports, then formats; returns the input unchanged on any failure. */
    public static String removeUnusedImportsAndFormat(String source) {
        return run(source, s -> FORMAT.apply(RemoveUnusedImports.removeUnusedImports(s)));
    }

    /** Applies {@code step}; on any failure returns {@code source}, and turns formatting off for a LinkageError. */
    static String run(String source, Step step) {
        if (!enabled()) return source;
        try {
            return step.apply(source);
        } catch (LinkageError e) {
            disable(e);
            return source;
        } catch (Exception e) {
            // Unparseable output — keep generation going with the raw source.
            return source;
        }
    }

    /** Whether formatting will be applied in this JVM. */
    public static boolean enabled() {
        if (!Boolean.parseBoolean(System.getProperty(FORMAT_PROPERTY, "true"))) return false;
        Boolean a = available;
        if (a == null) {
            synchronized (JavaFormatting.class) {
                if (available == null) available = probe(FORMAT);
                a = available;
            }
        }
        return a;
    }

    static boolean probe(Step step) {
        try {
            step.apply("class P {}");
            return true;
        } catch (LinkageError e) {
            warn(e);
            return false;
        } catch (Exception e) {
            return true; // formatter runs; this input just didn't parse
        }
    }

    /** Forgets the probe result, so the next call probes again. For tests. */
    static void reset() {
        available = null;
    }

    private static void disable(LinkageError e) {
        synchronized (JavaFormatting.class) {
            if (Boolean.FALSE.equals(available)) return;
            available = false;
        }
        warn(e);
    }

    private static void warn(Throwable e) {
        System.err.println("TestKit [WARN] google-java-format is unavailable in this JVM ("
                + e.getClass().getSimpleName() + ") — generated tests are left unformatted. "
                + "To format, start the JVM with --add-exports jdk.compiler/com.sun.tools.javac."
                + "{api,code,file,parser,tree,util}=ALL-UNNAMED, or set -D" + FORMAT_PROPERTY
                + "=false to silence this.");
    }
}
