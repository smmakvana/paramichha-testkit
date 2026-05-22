package com.paramichha.testkit.generator;

import java.util.List;

/**
 * A method parameter with all DataFactory values pre-resolved.
 * The parameter's type is a full {@link ClassData} — recursive for domain types.
 */
public interface ParamData {

    String    name();
    ClassData type();              // recursive — full ClassData for domain types

    boolean   isConstrained();     // has Jakarta validation annotations

    // ── Pre-resolved values ───────────────────────────────────────────────

    List<Object> validValues();    // valid values for this parameter
    List<Object> invalidValues();  // constraint-violating values for this parameter

    // ── Enforcement hints ─────────────────────────────────────────────────

    List<String> enforcementHints(); // e.g. "// Enforce @NotNull: DataValidation.notNull(name);"
}
