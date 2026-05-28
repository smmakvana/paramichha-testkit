package com.paramichha.testkit.generator;

/**
 * A pure syntax writer.
 *
 * Receives a CoderInfo — a complete description of what to write.
 * Returns a Java source string.
 *
 * Zero test knowledge. Zero DataFactory. Zero ClassInfo.
 * Same coder writes test class, source class, nested class — it does not care.
 *
 * @param <T> the CoderInfo type this coder handles
 */
public interface FreshCoder<T> {
    String code(T info);
}
