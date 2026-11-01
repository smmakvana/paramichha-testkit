package com.paramichha.testkit.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

public interface FieldInfo {
    boolean isConstrained();

    String name();

    Class<?> rawType();

    Type genericType();

    /** All constraints that apply to this field's value — property and constructor parameter. */
    List<Annotation> validations();

    /**
     * Constraints checked by {@code Validator.validate(obj)}: annotations on the field itself and
     * on its getter. Defaults to {@link #validations()} for implementations that don't distinguish.
     */
    default List<Annotation> propertyValidations() { return validations(); }

    /**
     * Constraints on the constructor parameter that sets this field. Bean Validation checks these
     * only through {@code ExecutableValidator.validateConstructorParameters(...)}, never through
     * {@code Validator.validate(obj)} — and a constructor doesn't enforce them by itself.
     */
    default List<Annotation> constructorValidations() { return List.of(); }

    /** Index of the constructor parameter that sets this field, or -1 if not known. */
    default int constructorIndex() { return -1; }

    boolean required();

    boolean hasIndirectConstraints();
}
