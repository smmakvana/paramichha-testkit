package com.paramichha.testkit.generator;

import java.util.List;

/**
 * A class field with all DataFactory values pre-resolved.
 * The field's type is a full {@link ClassData} — recursive for domain types.
 */
public interface MemberData {

    String    name();
    ClassData type();              // recursive — full ClassData for domain types

    boolean   isConstrained();     // has Jakarta validation annotations
    boolean   isRequired();        // @NotNull / @NotBlank etc

    // ── Pre-resolved values ───────────────────────────────────────────────

    List<Object> validValues();    // boundary-covering valid values for this field
    List<Object> invalidValues();  // constraint-violating values for this field
}
