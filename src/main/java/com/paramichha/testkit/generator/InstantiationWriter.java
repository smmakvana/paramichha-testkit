package com.paramichha.testkit.generator;

import com.paramichha.datafactory.DataFactory;
import com.paramichha.testkit.model.ClassInfo;
import com.paramichha.testkit.model.FieldInfo;
import com.paramichha.utils.Texts;

import java.lang.annotation.Annotation;

import java.util.List;

/**
 * Picks the valid and invalid values for a class's fields, from DataFactory.
 * How a generated test creates the instance is decided in {@link CreationExpressions}.
 */
final class InstantiationWriter {

    private final ClassInfo meta;
    private final long      seed;

    InstantiationWriter(ClassInfo meta, long seed) {
        this.meta = meta;
        this.seed = seed;
    }

    /**
     * Returns valid field values by calling DataFactory.field(f).validList() directly.
     * This bypasses the DefaultDataBuilder.validList() skip of fields with <= 1 value,
     * ensuring every field always has at least one valid value for test generation.
     */
    List<?> validValuesFor(Class<?> type, FieldInfo field) {
        try {
            // Use field name + type + FieldMeta annotations — NOT field.getAnnotations()
            // because for records, Jakarta annotations live on the record component/
            // constructor parameter, not the backing field, so field.getAnnotations()
            // returns empty and DataFactory generates unconstrained values.
            List<Annotation> annotations = field.validations();
            Class<?> rawType = field.rawType();

            // @Null constraint — the only valid value IS null
            boolean isMustBeNull = annotations.stream()
                    .anyMatch(a -> a.annotationType().getSimpleName().equals("Null"));
            if (isMustBeNull) {
                return java.util.Arrays.asList((Object) null);
            }

            // List<T> has no shaper in FieldBuilder — generate one element instead
            if (rawType == java.util.List.class || rawType == java.util.Collection.class) {
                Object oneElement = buildOneValidListElement(field);
                return oneElement != null ? List.of(oneElement) : List.of();
            }

            // Domain type (not a java/jakarta type): use DataFactory.valid() directly
            // since FieldBuilder has no shaper for custom types
            String pkg = rawType.getPackageName();
            boolean isDomainType = !rawType.isPrimitive() && !rawType.isEnum()
                    && !pkg.startsWith("java.") && !pkg.startsWith("jakarta.");
            if (isDomainType) {
                try {
                    Object domainValid = DataFactory.of(rawType).seed(seed).valid().getOrNull();
                    return domainValid != null ? List.of(domainValid) : List.of();
                } catch (Exception ignored2) {
                    return List.of();
                }
            }

            @SuppressWarnings("unchecked")
            List<?> values = DataFactory.field(
                    field.name(), (Class<Object>) rawType, annotations).seed(seed).validList();
            // validAll may return empty for truly unknown types — fall back to valid()
            if (values.isEmpty()) {
                Object single = DataFactory.field(
                        field.name(), (Class<Object>) rawType, annotations).seed(seed).valid();
                return single != null ? List.of(single) : List.of();
            }
            return values;
        } catch (Exception e) {
            return List.of();
        }
    }

    /**
     * Builds a single-element list as the valid value for a List<T> field.
     * Uses DataFactory to generate one valid element of the list's element type.
     */
    @SuppressWarnings("unchecked")
    private Object buildOneValidListElement(FieldInfo field) {
        try {
            // Extract element type from FieldMeta.genericType (e.g. List<String> → String)
            Class<?> elemType = String.class;
            if (field.genericType() instanceof java.lang.reflect.ParameterizedType pt) {
                java.lang.reflect.Type arg = pt.getActualTypeArguments()[0];
                if (arg instanceof Class<?> c) elemType = c;
            }
            // Domain type — use DefaultDataBuilder to create a valid instance
            if (!elemType.getPackageName().startsWith("java.")) {
                var elem = DataFactory.of(elemType).seed(seed).valid();
                return elem.isSuccess() ? java.util.List.of(elem.get()) : java.util.List.of();
            }
            // Java type — use FieldBuilder
            Object elem = DataFactory.field(field.name(), (Class<Object>) elemType, java.util.List.of()).seed(seed).valid();
            return elem != null ? java.util.List.of(elem) : java.util.List.of("x");
        } catch (Exception e) {
            return java.util.List.of();
        }
    }

    /**
     * Returns invalid field values by calling DataFactory.field(f).invalidList().
     */
    List<?> invalidValuesFor(Class<?> type, FieldInfo field) {
        try {
            List<Annotation> annotations = field.validations();
            @SuppressWarnings("unchecked")
            List<?> values = DataFactory.field(
                    field.name(), (Class<Object>) field.rawType(), annotations).seed(seed).invalidList();
            return values;
        } catch (Exception e) {
            return List.of();
        }
    }

    private String capitalize(String s) {
        return Texts.capitalize(s);
    }
}