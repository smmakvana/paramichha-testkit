package com.paramichha.testkit.generator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.*;

/**
 * Converts a runtime value to a Java source literal string.
 *
 * <pre>
 * "alice@example.com"  → "\"alice@example.com\""
 * 34                   → "34"
 * LocalDate.of(...)    → "LocalDate.of(1990, 1, 15)"
 * null                 → "null"
 * </pre>
 *
 * Complex objects that cannot be expressed as a literal (nested records,
 * domain objects, etc.) return null from {@link #toLiteralOrNull} and
 * are filtered out by callers before writing.
 */
final class LiteralConverter {

    private LiteralConverter() {}

    /**
     * Returns true if this value can be expressed as a Java source literal.
     * Used by writers to skip fields/objects they cannot inline.
     */
    static boolean canSerialize(Object value) {
        if (value == null) return true;
        return switch (value) {
            case String ignored      -> true;
            case Character ignored   -> true;
            case Boolean ignored     -> true;
            case Integer ignored     -> true;
            case Long ignored        -> true;
            case Short ignored       -> true;
            case Byte ignored        -> true;
            case Float ignored       -> true;
            case Double ignored      -> true;
            case BigDecimal ignored  -> true;
            case BigInteger ignored  -> true;
            case UUID ignored        -> true;
            case LocalDate ignored   -> true;
            case LocalDateTime ignored -> true;
            case LocalTime ignored   -> true;
            case Instant ignored     -> true;
            case ZonedDateTime ignored -> true;
            case OffsetDateTime ignored -> true;
            case Year ignored        -> true;
            case YearMonth ignored   -> true;
            case Date ignored        -> true;
            case Enum<?> ignored     -> true;
            case List<?> list        -> list.stream().allMatch(LiteralConverter::canSerialize);
            case java.util.Set<?> set -> set.stream().allMatch(LiteralConverter::canSerialize);
            case java.util.Queue<?> queue -> queue.stream().allMatch(LiteralConverter::canSerialize);
            case java.util.Map<?, ?> map -> map.entrySet().stream()
                    .allMatch(e -> canSerialize(e.getKey()) && canSerialize(e.getValue()));
            default                  -> {
                Class<?> c = value.getClass();
                if (c.isArray()) yield canSerializeArray(value);
                if (c.isRecord()) yield canSerializeRecord(value);
                yield canSerializeConstructed(value);
            }
        };
    }

    /**
     * Converts a value to a literal, or returns null if the value cannot
     * be expressed as a Java source literal.
     * Callers should use {@link #canSerialize} to filter before calling this.
     */
    static String toLiteralOrNull(Object value) {
        if (!canSerialize(value)) return null;
        return toLiteral(value);
    }

    public static String toLiteral(Object value) {
        return switch (value) {
            case null                -> "null";
            case String s            -> "\"" + escape(s) + "\"";
            case Character c         -> "'" + (c == '\'' ? "\\'": c == '\\' ? "\\\\" : String.valueOf(c)) + "'";
            case Boolean b           -> b.toString();
            case Integer i           -> i.toString();
            case Long l              -> l + "L";
            case Short s             -> "(short) " + s;
            case Byte b              -> "(byte) " + b;
            case Float f             -> f + "f";
            case Double d            -> d + "d";
            case BigDecimal bd       -> "new BigDecimal(\"" + bd.toPlainString() + "\")";
            case BigInteger bi       -> "BigInteger.valueOf(" + bi + "L)";
            case UUID u              -> "UUID.fromString(\"" + u + "\")";
            case LocalDate d         -> "LocalDate.of(" + d.getYear() + ", " + d.getMonthValue() + ", " + d.getDayOfMonth() + ")";
            case LocalDateTime d     -> "LocalDateTime.of(" + d.getYear() + ", " + d.getMonthValue() + ", " + d.getDayOfMonth() + ", " + d.getHour() + ", " + d.getMinute() + ")";
            case LocalTime t         -> "LocalTime.of(" + t.getHour() + ", " + t.getMinute() + ")";
            case Instant i           -> "Instant.parse(\"" + i + "\")";
            case ZonedDateTime z     -> "ZonedDateTime.parse(\"" + z + "\")";
            case OffsetDateTime o    -> "OffsetDateTime.parse(\"" + o + "\")";
            case Year y              -> "Year.of(" + y.getValue() + ")";
            case YearMonth ym        -> "YearMonth.of(" + ym.getYear() + ", " + ym.getMonthValue() + ")";
            case Date d              -> "new Date(" + d.getTime() + "L)";
            case Enum<?> e           -> e.getClass().getSimpleName() + "." + e.name();
            case List<?> list        -> {
                if (list.isEmpty()) yield "List.of()";
                if (!list.stream().allMatch(LiteralConverter::canSerialize)) yield "List.of()";
                yield "List.of(" + list.stream().map(LiteralConverter::toLiteral)
                        .reduce((a, b) -> a + ", " + b).orElse("") + ")";
            }
            case java.util.Set<?> set -> {
                if (set.isEmpty()) yield "Set.of()";
                if (!set.stream().allMatch(LiteralConverter::canSerialize)) yield "Set.of()";
                yield "Set.of(" + set.stream().map(LiteralConverter::toLiteral)
                        .reduce((a, b) -> a + ", " + b).orElse("") + ")";
            }
            case java.util.Queue<?> queue -> "new java.util.ArrayDeque<>(List.of("
                    + queue.stream().map(LiteralConverter::toLiteral).reduce((a, b) -> a + ", " + b).orElse("")
                    + "))";
            case java.util.Map<?, ?> map -> {
                if (map.isEmpty()) yield "Map.of()";
                yield "Map.ofEntries(" + map.entrySet().stream()
                        .map(e -> "Map.entry(" + toLiteral(e.getKey()) + ", " + toLiteral(e.getValue()) + ")")
                        .reduce((a, b) -> a + ", " + b).orElse("") + ")";
            }
            default                  -> {
                Class<?> dc = value.getClass();
                if (dc.isArray()) yield toLiteralArray(value);
                if (dc.isRecord()) yield toLiteralRecord(value);
                if (canSerializeConstructed(value)) yield toLiteralConstructed(value);
                yield "/* TODO: " + dc.getSimpleName() + " */ null";
            }
        };
    }

    /** Returns true if all record components can be serialized. */
    private static boolean canSerializeRecord(Object record) {
        try {
            for (java.lang.reflect.RecordComponent rc : record.getClass().getRecordComponents()) {
                Object val = rc.getAccessor().invoke(record);
                if (!canSerialize(val)) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** Serializes a record as a constructor call: new Foo("bar", 42). */
    private static String toLiteralRecord(Object record) {
        try {
            java.lang.reflect.RecordComponent[] rcs = record.getClass().getRecordComponents();
            String typeName = JavaTypeNames.of(record.getClass());
            StringBuilder sb = new StringBuilder("new ")
                    .append(typeName).append("(");
            for (int i = 0; i < rcs.length; i++) {
                Object val = rcs[i].getAccessor().invoke(record);
                sb.append(toLiteral(val));
                if (i < rcs.length - 1) sb.append(", ");
            }
            return sb.append(")").toString();
        } catch (Exception e) {
            return "/* TODO: " + record.getClass().getSimpleName() + " */ null";
        }
    }

    // ── Plain classes: new Foo(a, b) via a field-matching public constructor ──

    /** Guards against object graphs that reference themselves. */
    private static final ThreadLocal<java.util.Set<Object>> CONSTRUCTING =
            ThreadLocal.withInitial(() -> java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<>()));

    /** Instance fields in declaration order (declared on the class itself). */
    private static List<java.lang.reflect.Field> instanceFields(Class<?> c) {
        List<java.lang.reflect.Field> out = new java.util.ArrayList<>();
        for (java.lang.reflect.Field f : c.getDeclaredFields()) {
            int m = f.getModifiers();
            if (java.lang.reflect.Modifier.isStatic(m) || f.isSynthetic()) continue;
            out.add(f);
        }
        return out;
    }

    /**
     * A public constructor whose parameter types are exactly the instance field types,
     * in declaration order — so {@code new Foo(field1, field2, ...)} rebuilds the object.
     */
    private static java.lang.reflect.Constructor<?> fieldMatchingConstructor(Class<?> c) {
        if (c.isInterface() || java.lang.reflect.Modifier.isAbstract(c.getModifiers())) return null;
        if (c.isMemberClass() && !java.lang.reflect.Modifier.isStatic(c.getModifiers())) return null;
        String pkg = c.getPackageName();
        if (pkg.startsWith("java.") || pkg.startsWith("javax.") || pkg.startsWith("jakarta.")) return null;
        List<java.lang.reflect.Field> fields = instanceFields(c);
        if (fields.isEmpty()) return null;
        for (java.lang.reflect.Constructor<?> k : c.getConstructors()) {
            Class<?>[] p = k.getParameterTypes();
            if (p.length != fields.size()) continue;
            boolean match = true;
            for (int i = 0; i < p.length && match; i++) match = p[i] == fields.get(i).getType();
            if (match) return k;
        }
        return null;
    }

    private static boolean canSerializeConstructed(Object value) {
        if (fieldMatchingConstructor(value.getClass()) == null) return false;
        if (!CONSTRUCTING.get().add(value)) return false; // cycle
        try {
            for (java.lang.reflect.Field f : instanceFields(value.getClass())) {
                f.setAccessible(true);
                if (!canSerialize(f.get(value))) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            CONSTRUCTING.get().remove(value);
        }
    }

    /** Serializes a plain class as a constructor call: new Foo("bar", 42). */
    private static String toLiteralConstructed(Object value) {
        try {
            StringBuilder sb = new StringBuilder("new ").append(JavaTypeNames.of(value.getClass())).append("(");
            List<java.lang.reflect.Field> fields = instanceFields(value.getClass());
            for (int i = 0; i < fields.size(); i++) {
                fields.get(i).setAccessible(true);
                sb.append(toLiteral(fields.get(i).get(value)));
                if (i < fields.size() - 1) sb.append(", ");
            }
            return sb.append(")").toString();
        } catch (Exception e) {
            return "/* TODO: " + value.getClass().getSimpleName() + " */ null";
        }
    }

    public static List<String> importsFor(Object value) {
        if (value == null) return List.of();
        List<String> base = switch (value) {
            case BigDecimal ignored    -> List.of("java.math.BigDecimal");
            case BigInteger ignored    -> List.of("java.math.BigInteger");
            case UUID ignored          -> List.of("java.util.UUID");
            case LocalDate ignored     -> List.of("java.time.LocalDate");
            case LocalDateTime ignored -> List.of("java.time.LocalDateTime");
            case LocalTime ignored     -> List.of("java.time.LocalTime");
            case Instant ignored       -> List.of("java.time.Instant");
            case ZonedDateTime ignored -> List.of("java.time.ZonedDateTime");
            case OffsetDateTime ignored-> List.of("java.time.OffsetDateTime");
            case Year ignored          -> List.of("java.time.Year");
            case YearMonth ignored     -> List.of("java.time.YearMonth");
            case Date ignored          -> List.of("java.util.Date");
            default                    -> List.of();
        };
        // For records: recurse into each component to collect transitive imports
        if (value.getClass().isRecord()) {
            java.util.Set<String> all = new java.util.LinkedHashSet<>(base);
            all.addAll(importsForClass(value.getClass()));
            try {
                for (java.lang.reflect.RecordComponent rc : value.getClass().getRecordComponents()) {
                    Object v = rc.getAccessor().invoke(value);
                    all.addAll(importsFor(v));
                    all.addAll(importsForClass(rc.getType()));
                }
            } catch (Exception ignored2) {}
            return List.copyOf(all);
        }
        // For constructed plain classes: the class plus each field's imports
        if (canSerializeConstructed(value)) {
            java.util.Set<String> all = new java.util.LinkedHashSet<>(base);
            all.addAll(importsForClass(value.getClass()));
            try {
                for (java.lang.reflect.Field f : instanceFields(value.getClass())) {
                    f.setAccessible(true);
                    all.addAll(importsFor(f.get(value)));
                }
            } catch (Exception ignored3) {}
            return List.copyOf(all);
        }
        // For lists: recurse into each element
        if (value instanceof List<?> list) {
            java.util.Set<String> all = new java.util.LinkedHashSet<>(base);
            for (Object elem : list) all.addAll(importsFor(elem));
            return List.copyOf(all);
        }
        // For enums: import the enum class
        if (value instanceof Enum<?>) {
            java.util.Set<String> all = new java.util.LinkedHashSet<>(base);
            all.addAll(importsForClass(value.getClass()));
            return List.copyOf(all);
        }
        return base;
    }

    /**
     * Returns the import string for a class, walking up to the top-level enclosing class.
     * Returns empty list for java.lang, primitives, and fixed imports.
     */
    static List<String> importsForClass(Class<?> type) {
        if (type == null || type.isPrimitive()) return List.of();
        if (type.isArray()) return importsForClass(type.getComponentType());
        Class<?> top = type;
        while (top.getEnclosingClass() != null) top = top.getEnclosingClass();
        String pkg = top.getPackageName();
        if (pkg.isEmpty() || pkg.equals("java.lang")) return List.of();
        if (top == java.util.List.class
                || top == java.util.Set.class
                || top == java.util.Map.class
                || top == java.util.Collection.class) return List.of(); // in FIXED imports
        return List.of(pkg + "." + top.getSimpleName());
    }

    /** Returns true if all array elements can be serialized. */
    private static boolean canSerializeArray(Object arr) {
        int len = java.lang.reflect.Array.getLength(arr);
        for (int i = 0; i < len; i++) {
            if (!canSerialize(java.lang.reflect.Array.get(arr, i))) return false;
        }
        return true;
    }

    /** Converts an array to a Java source literal e.g. {@code new String[]{"a","b"}}. */
    private static String toLiteralArray(Object arr) {
        Class<?> comp = arr.getClass().getComponentType();
        int      len  = java.lang.reflect.Array.getLength(arr);
        String   typeName = comp.isPrimitive() ? comp.getName() : comp.getSimpleName();
        StringBuilder sb = new StringBuilder("new ").append(typeName).append("[]{");
        for (int i = 0; i < len; i++) {
            if (i > 0) sb.append(", ");
            sb.append(toLiteral(java.lang.reflect.Array.get(arr, i)));
        }
        return sb.append("}").toString();
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }
}