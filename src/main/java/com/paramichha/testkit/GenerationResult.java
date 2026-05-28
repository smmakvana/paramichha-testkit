package com.paramichha.testkit;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Output of {@link TestKit#generateTest()} or {@link TestKit#generateTests()}.
 *
 * <p>Contains one or more {@link JavaSourceCode}s:
 * <ul>
 *   <li>{@code generateTest()}  — one file (full nested-class test)</li>
 *   <li>{@code generateTests()} — N files (split mode)</li>
 * </ul>
 *
 * <p>Client code is always identical:
 * <pre>
 * TestKit.of(UserRequest.class).generateTest().writeTo(path);
 * TestKit.of(UserRequest.class).generateTests().writeTo(path);
 * </pre>
 */
public record GenerationResult(List<JavaSourceCode> files) {

    public GenerationResult {
        if (files == null || files.isEmpty())
            throw new TestKitException("GenerationResult must have at least one file");
        files = List.copyOf(files);
    }

    // ── Convenience factory ───────────────────────────────────────────────

    public static GenerationResult of(String className, String packageName, String source) {
        return new GenerationResult(List.of(new JavaSourceCode(packageName, className, source)));
    }

    /**
     * Flattens all files from all results into one GenerationResult.
     * Used by split mode — each ClassCoderInfo produces its own GenerationResult,
     * this merges them all into one deliverable.
     */
    public static GenerationResult ofAll(List<GenerationResult> results) {
        List<JavaSourceCode> allFiles = results.stream()
                .flatMap(r -> r.files().stream())
                .collect(Collectors.toList());
        return new GenerationResult(allFiles);
    }

    // ── Write — writes ALL files ──────────────────────────────────────────

    /**
     * Writes all files. Returns path of first file — backward compatible.
     */
    public Path writeTo(Path sourceRoot) {
        Path first = null;
        for (JavaSourceCode file : files) {
            Path written = file.writeTo(sourceRoot);
            if (first == null) first = written;
        }
        return first;
    }

    // ── Backward-compatible convenience — delegates to first file ─────────

    public String className() {
        return files.getFirst().className();
    }

    public String packageName() {
        return files.getFirst().packageName();
    }

    public String source() {
        return files.getFirst().source();
    }

    public String asString() {
        return files.getFirst().asString();
    }
}