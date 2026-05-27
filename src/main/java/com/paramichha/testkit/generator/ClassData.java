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

    String className();       // simple name: "UserRequest"
    String packageName();     // "com.example.requests"

    default String qualifiedName() {
        String pkg = packageName();
        return (pkg == null || pkg.isBlank()) ? className() : pkg + "." + className();
    }

    // ── Type characteristics ──────────────────────────────────────────────

    boolean isPrimitive();     // int, long, boolean, etc.
    boolean isVoid();          // void / Void
    boolean isEnum();          // enum types
    boolean isRecord();        // Java record
    boolean isArray();         // int[], String[], UserRequest[]
    boolean isCollection();    // List, Set, Collection
    boolean isMap();           // Map, HashMap, etc.
    boolean isConstrained();   // has at least one Jakarta annotation

    // ── Component type — for arrays and collections ───────────────────────

    ClassData componentType(); // null for non-array/collection/map

    // ── Structure — only populated for domain types ───────────────────────

    List<MemberData> members();    // fields in declaration order
    List<MethodData> methods();    // methods sorted alphabetically

    // ── Pre-resolved values (ALL from DataFactory, set in ClassDataFactory) ──

    List<Object> validValues();    // boundary-covering valid instances
    List<Object> invalidValues();  // one-constraint-violated instances
}