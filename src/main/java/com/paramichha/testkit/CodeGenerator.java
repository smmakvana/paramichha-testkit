package com.paramichha.testkit;

/**
 * Generates test source from a fully assembled {@link TestCodeData} pipeline context.
 * Implement to produce a different test style (e.g. Spock, Kotest).
 */
public interface CodeGenerator {
    GenerationResult generate(TestCodeData payload);

    GenerationResult generateAll(TestCodeData payload);
}