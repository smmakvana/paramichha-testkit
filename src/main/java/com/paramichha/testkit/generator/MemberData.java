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

    // ── Where the constraints live — decides how a test can check them ─────
    /** Field/getter constraints: {@code Validator.validate(obj)} sees them. */
    default boolean isPropertyConstrained()    { return isConstrained(); }
    /** Constructor-parameter constraints: only {@code ExecutableValidator} sees them. */
    default boolean isConstructorConstrained() { return false; }
    /** Index of the constructor parameter that sets this field, or -1. */
    default int     constructorIndex()         { return -1; }
    /** Constraints no value can satisfy (e.g. {@code @Min(5) @Max(3)}); empty when fine. */
    default List<String> conflicts()           { return List.of(); }
}
