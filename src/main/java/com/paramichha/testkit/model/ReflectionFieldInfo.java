package com.paramichha.testkit.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public final class ReflectionFieldInfo implements FieldInfo {
    private final String name;
    private final Class<?> rawType;
    private final Type genericType;
    private final List<Annotation> validations;
    private final boolean required;
    private final boolean hasIndirectConstraints;
    private final List<Annotation> propertyValidations;
    private final List<Annotation> constructorValidations;
    private final int constructorIndex;

    /**
     * @param propertyValidations    field and getter constraints — checked by {@code Validator.validate}
     * @param constructorValidations constraints on the constructor parameter that sets this field
     * @param constructorIndex       that parameter's index, or -1
     */
    public ReflectionFieldInfo(String name, Class<?> rawType, Type genericType,
                               List<Annotation> propertyValidations,
                               List<Annotation> constructorValidations, int constructorIndex,
                               boolean required, boolean hasIndirectConstraints) {
        this.name = name;
        this.rawType = rawType;
        this.genericType = genericType;
        List<Annotation> all = new java.util.ArrayList<>(propertyValidations);
        for (Annotation a : constructorValidations) if (!all.contains(a)) all.add(a);
        this.validations = java.util.List.copyOf(all);
        this.propertyValidations = java.util.List.copyOf(propertyValidations);
        this.constructorValidations = java.util.List.copyOf(constructorValidations);
        this.constructorIndex = constructorIndex;
        this.required = required;
        this.hasIndirectConstraints = hasIndirectConstraints;
    }

    public ReflectionFieldInfo(
            String name,
            Class<?> rawType,
            Type genericType,
            List<Annotation> validations,
            boolean required,
            boolean hasIndirectConstraints
    ) {
        this.name = name;
        this.rawType = rawType;
        this.genericType = genericType;
        this.validations = validations;
        this.required = required;
        this.hasIndirectConstraints = hasIndirectConstraints;
        this.propertyValidations = validations;
        this.constructorValidations = java.util.List.of();
        this.constructorIndex = -1;
    }

    @Override public List<Annotation> propertyValidations()    { return propertyValidations; }
    @Override public List<Annotation> constructorValidations() { return constructorValidations; }
    @Override public int constructorIndex()                    { return constructorIndex; }

    public ReflectionFieldInfo(String name, Class<?> rawType, Type genericType,
                               List<Annotation> validations, boolean required) {
        this(name, rawType, genericType, validations, required, false);
    }

    @Override
    public boolean isConstrained() {
        return !validations.isEmpty() || hasIndirectConstraints;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Class<?> rawType() {
        return rawType;
    }

    @Override
    public Type genericType() {
        return genericType;
    }

    @Override
    public List<Annotation> validations() {
        return validations;
    }

    @Override
    public boolean required() {
        return required;
    }

    @Override
    public boolean hasIndirectConstraints() {
        return hasIndirectConstraints;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof ReflectionFieldInfo o)) return false;
        return Objects.equals(name, o.name)
                && Objects.equals(rawType, o.rawType)
                && Objects.equals(genericType, o.genericType)
                && Objects.equals(validations, o.validations)
                && required == o.required
                && hasIndirectConstraints == o.hasIndirectConstraints
                && Objects.equals(propertyValidations, o.propertyValidations)
                && Objects.equals(constructorValidations, o.constructorValidations)
                && constructorIndex == o.constructorIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rawType, genericType, validations, required, hasIndirectConstraints, propertyValidations, constructorValidations, constructorIndex);
    }

    @Override
    public String toString() {
        return "ReflectionFieldInfo[" +
                "name=" + name + ", " +
                "rawType=" + rawType + ", " +
                "genericType=" + genericType + ", " +
                "validations=" + validations + ", " +
                "required=" + required + ", " +
                "hasIndirectConstraints=" + hasIndirectConstraints + ']';
    }

}