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

            // Containers: one valid element of the declared type argument(s), e.g. List<Integer>,
            // Map<String, Integer>. DataFactory's field-level API only sees the raw type.
            if (java.util.Collection.class.isAssignableFrom(rawType) || java.util.Map.class.isAssignableFrom(rawType)) {
                Object container = buildOneValidContainer(field);
                return container != null ? List.of(container) : List.of();
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
    /**
     * A container of the field's type holding one valid element (or entry) of its declared type
     * arguments, or null when no element can be produced.
     */
    private Object buildOneValidContainer(FieldInfo field) {
        try {
            Class<?> raw = field.rawType();
            if (java.util.Map.class.isAssignableFrom(raw)) {
                Object key = oneElement(field, typeArgument(field, 0), ".key");
                Object value = oneElement(field, typeArgument(field, 1), ".value");
                return key == null || value == null ? null : java.util.Map.of(key, value);
            }
            Object element = oneElement(field, typeArgument(field, 0), "");
            if (element == null) return null;
            if (java.util.Set.class.isAssignableFrom(raw)) return java.util.Set.of(element);
            if (java.util.Queue.class.isAssignableFrom(raw)) return new java.util.ArrayDeque<>(List.of(element));
            return List.of(element);
        } catch (Exception e) {
            return null;
        }
    }

    /** The class of the field's {@code index}-th type argument, or String when it is unknown. */
    private static Class<?> typeArgument(FieldInfo field, int index) {
        if (field.genericType() instanceof java.lang.reflect.ParameterizedType pt
                && pt.getActualTypeArguments().length > index) {
            java.lang.reflect.Type arg = pt.getActualTypeArguments()[index];
            if (arg instanceof Class<?> c) return c;
            if (arg instanceof java.lang.reflect.ParameterizedType p && p.getRawType() instanceof Class<?> c) return c;
        }
        return String.class;
    }

    @SuppressWarnings("unchecked")
    private Object oneElement(FieldInfo field, Class<?> type, String suffix) {
        // Map<String, Object>, List<?>: any value is valid for Object, so use a String.
        if (type == Object.class) type = String.class;
        if (type.isEnum()) {
            Object[] constants = type.getEnumConstants();
            return constants.length == 0 ? null : constants[0];
        }
        if (!type.getPackageName().startsWith("java.")) {
            var elem = DataFactory.of(type).seed(seed).valid();
            return elem.isSuccess() ? elem.get() : null;
        }
        return DataFactory.field(field.name() + suffix, (Class<Object>) type, List.of()).seed(seed).valid();
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