package com.paramichha.testkit.generator;


/**
 * Method return type with all DataFactory values pre-resolved.
 * {@link #type()} is null when {@link #isVoid()} is true.
 */
public interface ReturnData {

    ClassData type();              // null if void; recursive for domain types

    boolean   isVoid();
    boolean   isConstrained();     // return type has Jakarta annotations

    // ── Pre-resolved ──────────────────────────────────────────────────────

    String    assertionCode();     // pre-built assertion e.g. "DataValidation.notNull(result);"
}
