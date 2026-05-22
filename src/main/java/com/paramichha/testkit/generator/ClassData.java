package com.paramichha.testkit.generator;

import java.util.List;

/**
 * Recursive data model for a Java class — fields, methods, values all pre-resolved.
 *
 * <p>A field type, parameter type, or return type can itself be a {@code ClassData} —
 * enabling deep generation for domain objects passed as method arguments.
 *
 * <p>All DataFactory calls happen in {@link ClassDataFactory} before this is created.
 * {@link ClassDataCoder} receives this and produces a pure String — no DataFactory, no reflection.
 *
 * <pre>
 * Class&lt;?&gt;
 *     ↓  ReflectionClassReader
 * ClassInfo
 *     ↓  ClassDataFactory  (ALL DataFactory calls here)
 * ClassData  ← you are here
 *     ↓  ClassDataCoder
 * String → GenerationResult
 * </pre>
 */
public interface ClassData {

    // ── Identity ──────────────────────────────────────────────────────────

    String  className();
    String  packageName();

    // ── Type characteristics ──────────────────────────────────────────────

    boolean isPrimitive();
    boolean isVoid();
    boolean isEnum();
    boolean isConstrained();       // has Jakarta validation annotations

    // ── Structure ─────────────────────────────────────────────────────────

    List<MemberData> members();    // fields — in declaration order
    List<MethodData> methods();    // methods — sorted alphabetically

    // ── Pre-resolved values ───────────────────────────────────────────────

    List<Object> validValues();    // boundary-covering valid instances
    List<Object> invalidValues();  // one-constraint-violated instances
}
