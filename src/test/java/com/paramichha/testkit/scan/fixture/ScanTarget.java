package com.paramichha.testkit.scan.fixture;

import com.paramichha.testkit.scan.Testable;

/** Found by TestableScanner; its tests go to another package. */
@Testable(targetPackage = "com.example.generated")
public record ScanTarget(String name) {}
