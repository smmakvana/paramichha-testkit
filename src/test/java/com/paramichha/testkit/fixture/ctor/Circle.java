package com.paramichha.testkit.fixture.ctor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.Positive;

/**
 * Regression fixture (TestBuddy bug report): constraints live on the constructor parameters,
 * not on the fields; setters carry no constraints.
 */
public class Circle {
    private int x;
    private int y;
    private int z;

    public Circle() {
        this(1, -1, 1);
    }

    public Circle(@Positive int x, @Negative int y, @Max(5) @Min(1) int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getZ() { return z; }
    public void setZ(int z) { this.z = z; }
}
