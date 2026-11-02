package com.paramichha.testkit.runner;

import com.paramichha.testkit.GenerationResult;
import com.paramichha.testkit.TestKit;
import com.paramichha.testkit.scan.TestableScanner;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * Batch runner — generate tests for multiple classes at once.
 *
 * <pre>
 * TestKitRunner.scanPackage("com.example.requests")
 *              .writeTo(Path.of("src/test/java"));
 *
 * TestKitRunner.forClasses(UserRequest.class, PaymentRequest.class)
 *              .writeTo(Path.of("src/test/java"));
 * </pre>
 */
public final class TestKitRunner {

    private final List<TestableScanner.ScanResult> targets;

    private TestKitRunner(List<TestableScanner.ScanResult> targets) {
        this.targets = targets;
    }

    public static TestKitRunner scanPackage(String packageName) {
        return new TestKitRunner(TestableScanner.scan(packageName));
    }

    public static TestKitRunner forClasses(Class<?>... classes) {
        return new TestKitRunner(
                Arrays.stream(classes)
                        .map(c -> new TestableScanner.ScanResult(c, c.getPackageName()))
                        .toList()
        );
    }

    public List<Path> writeTo(Path sourceRoot) {
        return targets.stream()
                .map(t -> TestKit.of(t.type())
                        .withPackage(t.targetPackage())
                        .generateTest()
                        .writeTo(sourceRoot))
                .toList();
    }

    public List<GenerationResult> generate() {
        return targets.stream()
                .map(t -> TestKit.of(t.type())
                        .withPackage(t.targetPackage())
                        .generateTest())
                .toList();
    }

    public int targetCount() { return targets.size(); }
}