package com.paramichha.testkit.fixture.shapes;

import jakarta.validation.constraints.Positive;

/** Every public method kind: query, computed getter, constrained setter, command, transformer. */
public class Shape {
    private double radius;

    public Shape(@Positive double radius) { this.radius = radius; }

    public double area() { return Math.PI * radius * radius; }
    public double diameter() { return 2 * radius; }
    public double getArea() { return area(); }
    public double getDiameter() { return diameter(); }
    public double getRadius() { return radius; }
    public void setRadius(@Positive double radius) { this.radius = radius; }
    public boolean contains(double x) { return x <= radius; }
    public Shape scale(double factor) { return new Shape(radius * factor); }
    public void reset() { radius = 1; }
}
