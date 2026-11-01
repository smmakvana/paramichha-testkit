package com.paramichha.testkit.fixture.ctor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/** Regression fixture: a constructor constraint no value can satisfy. */
public class ImpossibleRange {
    private final int quantity;

    public ImpossibleRange(@Min(5) @Max(3) int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() { return quantity; }
}
