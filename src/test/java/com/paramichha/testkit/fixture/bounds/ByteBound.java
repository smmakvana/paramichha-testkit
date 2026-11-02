package com.paramichha.testkit.fixture.bounds;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/** Regression fixture: bounds at the limits of a byte. */
public class ByteBound {
    private final byte level;

    public ByteBound(@Min(1) @Max(127) byte level) {
        this.level = level;
    }

    public byte getLevel() { return level; }
}
