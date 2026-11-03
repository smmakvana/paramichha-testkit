package com.paramichha.testkit.generator;

import com.paramichha.datafactory.DataFactory;
import com.paramichha.testkit.ReflectionClassReader;
import com.paramichha.testkit.TestCodeData;
import com.paramichha.testkit.TestKitException;
import com.paramichha.testkit.TestKitTemplate;
import com.paramichha.testkit.model.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Builds a complete {@link ClassData} tree from a {@link TestCodeData}.
 *
 * This is the ONLY class that calls DataFactory anywhere in the V2 pipeline.
 * Everything it produces is plain data — no DataFactory below this point.
 *
 * Flow:
 *   TestCodeData → ClassDataFactory.build() → ClassData → ClassDataCoder → String
 */
final class ClassDataFactory {

    private ClassDataFactory() {}

    static ClassData build(TestCodeData payload) {
        return build(payload, new HashSet<>());
    }

    private static ClassData build(TestCodeData payload, Set<Class<?>> visiting) {
        ClassInfo       classInfo = payload.classInfo();
        TestFixture     fixture   = payload.fixture();
        TestKitTemplate template  = payload.template();
        long            seed      = effectiveSeed(payload);

        InstantiationWriter iw = new InstantiationWriter(classInfo, seed);

        List<Object> validParts;
        try { validParts = resolveValidParts(fixture, classInfo, iw); }
        catch (Exception e) { validParts = new ArrayList<>(); }

        List<MemberData> members;
        try { members = buildMembers(classInfo, fixture, iw, validParts); }
        catch (Exception e) { members = new ArrayList<>(); }

        // Not caught: a method that cannot be built fails the class with its name and reason,
        // rather than disappearing from the tests without a word.
        List<MethodData> methods = buildMethods(classInfo, template, seed,
                payload.projectType().hasValidator(), visiting);

        boolean isConstrained = false;
        try { isConstrained = classInfo.fields().stream().anyMatch(FieldInfo::isConstrained); }
        catch (Exception ignored) {}

        boolean isRecord = classInfo.constructorStyle() == com.paramichha.testkit.model.ConstructorStyle.RECORD;

        return new DefaultClassData(
                template.classAffix().apply(classInfo.simpleName()),
                payload.targetPackage(),
                false, false, false,
                isRecord, false, false, false,
                isConstrained,
                null,
                members, methods,
                validParts, List.of()
        );
    }

    // ── Field value resolution ────────────────────────────────────────────

    private static List<Object> resolveValidParts(TestFixture fixture, ClassInfo classInfo,
                                                  InstantiationWriter iw) {
        List<Object> parts = new ArrayList<>();
        for (FieldInfo f : classInfo.fields()) {
            try {
                List<?> vals = iw.validValuesFor(fixture != null ? fixture.type() : classInfo.type(), f);
                parts.add(vals.isEmpty() ? null : vals.get(0));
            } catch (Exception ignored) {
                parts.add(null);
            }
        }
        return List.copyOf(parts);
    }

    // ── Members ───────────────────────────────────────────────────────────

    private static List<MemberData> buildMembers(ClassInfo classInfo, TestFixture fixture,
                                                 InstantiationWriter iw,
                                                 List<Object> validParts) {
        List<MemberData> result = new ArrayList<>();
        List<FieldInfo>  fields = classInfo.fields();
        for (FieldInfo field : fields) {
            try {
                Class<?> rawType = field.rawType();
                List<Object> validVals   = castList(iw.validValuesFor(
                        fixture != null ? fixture.type() : classInfo.type(), field));
                List<Object> invalidVals = castList(iw.invalidValuesFor(
                        fixture != null ? fixture.type() : classInfo.type(), field))
                        .stream()
                        // compare against the boxed type: int.class.isAssignableFrom(Integer.class) is false,
                        // which used to drop every invalid value for primitive fields
                        .filter(v -> v == null || boxed(rawType).isAssignableFrom(v.getClass()))
                        .collect(Collectors.toList());
                invalidVals = distinct(withTypeExtremes(rawType, field, invalidVals));
                validVals   = distinct(validVals);
                ClassData fieldType = classDataForType(rawType, field.genericType());
                boolean propertyConstrained    = !field.propertyValidations().isEmpty() || field.hasIndirectConstraints();
                boolean constructorConstrained = !field.constructorValidations().isEmpty();
                List<String> conflicts = com.paramichha.datafactory.constraint.ConstraintConflicts.find(
                        com.paramichha.datafactory.constraint.AnnotationResolver.analyze(
                                field.name(), rawType, field.validations()));
                result.add(new DefaultMemberData(
                        field.name(), fieldType, field.required(),
                        validVals, invalidVals,
                        propertyConstrained, constructorConstrained, field.constructorIndex(), conflicts));
            } catch (Exception ignored) {
                ClassData fieldType = classDataForType(field.rawType(), field.genericType());
                result.add(new DefaultMemberData(
                        field.name(), fieldType,
                        false, false, List.of(), List.of()));
            }
        }
        return List.copyOf(result);
    }

    // ── Methods ───────────────────────────────────────────────────────────

    private static List<MethodData> buildMethods(ClassInfo classInfo, TestKitTemplate template,
                                                 long seed, boolean hasJakarta,
                                                 Set<Class<?>> visiting) {
        List<MethodData> result     = new ArrayList<>();
        List<String>     failed     = new ArrayList<>();
        String           className  = classInfo.simpleName();
        Set<String>      fieldNames = classInfo.fields().stream().map(FieldInfo::name).collect(Collectors.toSet());
        for (MethodInfo method : classInfo.methods()) {
            boolean accessor = isAccessor(method, fieldNames);
            if (template.foldAccessors && accessor) continue;
            try {
                result.add(buildMethodData(method, className, template, seed, hasJakarta, visiting, accessor));
            } catch (Exception e) {
                failed.add(method.name() + " (" + e.getMessage() + ")");
            }
        }
        if (!failed.isEmpty()) {
            throw new TestKitException("could not build tests for method(s): " + String.join(", ", failed));
        }
        return List.copyOf(result);
    }

    /**
     * A getter or setter for a field of the class ({@code getR}, {@code isR}, {@code setR} for field {@code r}).
     * Its value is checked by the field's own test; with folding on, it gets no method test at all.
     */
    static boolean isAccessor(MethodInfo method, Set<String> fieldNames) {
        String name = method.name();
        int    args = method.parameters().size();
        String prop = null;
        if (args == 0 && name.startsWith("get") && name.length() > 3) prop = name.substring(3);
        if (args == 0 && name.startsWith("is")  && name.length() > 2) prop = name.substring(2);
        if (args == 1 && name.startsWith("set") && name.length() > 3) prop = name.substring(3);
        if (prop == null) return false;
        return fieldNames.contains(Character.toLowerCase(prop.charAt(0)) + prop.substring(1));
    }

    private static MethodData buildMethodData(MethodInfo method, String className,
                                              TestKitTemplate template, long seed,
                                              boolean hasJakarta, Set<Class<?>> visiting,
                                              boolean accessor) {
        String methodName      = method.name();
        String nestedClassName = template.resolveMethod(template.methodClassName, className, methodName);
        String displayName     = template.resolveMethod(template.methodDisplay, className, methodName);
        String validSrc        = template.resolveMethod(template.validInputsSource, className, methodName);
        String invalidSrc      = template.resolveMethod(template.invalidInputsSource, className, methodName);

        List<ParamData> params     = buildParams(method.parameters(), seed, methodName, visiting);
        ReturnData      returnData = buildReturnData(method.returnType(), methodName, hasJakarta);
        if (accessor && !returnData.isVoid()) {
            // The value of an accessor is checked by the field's test. Here: the call returns something.
            // No TODO, so accessors don't fail the build until someone writes an expectation.
            returnData = new DefaultReturnData(returnData.type(), false, returnData.isConstrained(),
                    returnData.needsWrapperRecord(), "assertThat(result).isNotNull();", returnData.wrapperRecord());
        }

        return new DefaultMethodData(
                methodName, method.kind(), params, returnData,
                nestedClassName, displayName, validSrc, invalidSrc);
    }

    // ── Parameters ────────────────────────────────────────────────────────

    private static List<ParamData> buildParams(List<ParameterInfo> params, long seed,
                                               String methodName, Set<Class<?>> visiting) {
        List<ParamData> result = new ArrayList<>();
        for (ParameterInfo p : params) {
            long         paramSeed   = com.paramichha.utils.Seeds.stableHash(methodName + p.name()) ^ seed;
            List<Object> validVals   = resolveParamValid(p, paramSeed);
            List<Object> invalidVals = resolveParamInvalid(p);
            List<String> hints       = buildEnforcementHints(p);
            ClassData    paramType   = resolveParamClassData(p, paramSeed, visiting);
            result.add(new DefaultParamData(
                    p.name(), paramType, p.isConstrained(),
                    validVals, invalidVals, hints));
        }
        return List.copyOf(result);
    }

    // ── Return type ───────────────────────────────────────────────────────

    private static ReturnData buildReturnData(ReturnTypeInfo ret, String methodName,
                                              boolean hasJakarta) {
        if (ret.isVoid())
            return new DefaultReturnData(null, true, false, false, "", null);

        ClassData type          = primitiveOrSimpleClassData(ret.type());
        boolean   constrained   = ret.isConstrained();
        String    assertionCode = buildReturnAssertion(ret, hasJakarta);
        boolean   needsWrapper  = needsWrapperRecord(ret);
        String    wrapperRecord = needsWrapper ? buildWrapperRecord(methodName, ret) : null;

        return new DefaultReturnData(type, false, constrained, needsWrapper,
                assertionCode, wrapperRecord);
    }

    // ── DataFactory calls ─────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private static List<Object> resolveParamValid(ParameterInfo param, long seed) {
        Class<?> type = param.type();

        if (type.isPrimitive()) {
            try {
                Object val = param.validations().isEmpty()
                        ? DataFactory.of(type).seed(seed).valid().getOrNull()
                        : DataFactory.field(param.name(), (Class<Object>) type,
                        param.validations()).seed(seed).valid();
                if (val != null) return List.of(val);
            } catch (Exception ignored) {}
            return List.of(primitiveDefault(type));
        }

        String  pkg      = type.getPackageName();
        boolean isDomain = !type.isEnum()
                && !pkg.startsWith("java.") && !pkg.startsWith("jakarta.")
                && !pkg.startsWith("javax.");
        if (isDomain) {
            try {
                ClassInfo           meta  = ReflectionClassReader.INSTANCE.read(type);
                InstantiationWriter iw    = new InstantiationWriter(meta, seed);
                List<Object>        parts = new ArrayList<>();
                for (FieldInfo f : meta.fields()) {
                    List<?> vals = iw.validValuesFor(type, f);
                    parts.add(vals.isEmpty() ? null : vals.get(0));
                }
                Object instance = buildInstance(type, meta, parts);
                if (instance != null) return List.of(instance);
            } catch (Exception ignored) {}
            return List.of();
        }

        try {
            Object val = param.validations().isEmpty()
                    ? DataFactory.of(type).seed(seed).valid().getOrNull()
                    : DataFactory.field(param.name(), (Class<Object>) type,
                    param.validations()).seed(seed).valid();
            if (val != null) return List.of(val);
        } catch (Exception ignored) {}
        return List.of();
    }

    @SuppressWarnings("unchecked")
    private static List<Object> resolveParamInvalid(ParameterInfo param) {
        if (!param.isConstrained()) return List.of();
        try {
            List<?> invalids = DataFactory.field(
                    param.name(), (Class<Object>) param.type(),
                    param.validations()).invalidList();
            return new ArrayList<>(invalids);
        } catch (Exception ignored) {}
        return List.of();
    }

    private static ClassData resolveParamClassData(ParameterInfo param, long seed,
                                                   Set<Class<?>> visiting) {
        Class<?> type = param.type();
        String   pkg  = type.isPrimitive() ? "" : type.getPackageName();
        boolean isDomain = !type.isPrimitive() && !type.isEnum()
                && !pkg.startsWith("java.") && !pkg.startsWith("jakarta.")
                && !pkg.startsWith("javax.");

        if (isDomain && visiting.contains(type)) return primitiveOrSimpleClassData(type);
        return classDataForType(type, null);
    }

    // ── Instance builder ──────────────────────────────────────────────────

    private static Object buildInstance(Class<?> type, ClassInfo meta, List<Object> parts) {
        try {
            Class<?>[] types = meta.fields().stream()
                    .map(FieldInfo::rawType).toArray(Class<?>[]::new);
            return type.getDeclaredConstructor(types).newInstance(parts.toArray());
        } catch (Exception e) {
            try {
                for (java.lang.reflect.Constructor<?> c : type.getDeclaredConstructors()) {
                    if (c.getParameterCount() == parts.size()) {
                        c.setAccessible(true);
                        return c.newInstance(parts.toArray());
                    }
                }
            } catch (Exception ignored) {}
            return null;
        }
    }

    // ── ClassData construction ────────────────────────────────────────────

    /**
     * Builds a correct ClassData for any type — correctly sets isArray, isCollection,
     * isMap, isRecord, componentType.
     */
    static ClassData classDataForType(Class<?> type, java.lang.reflect.Type genericType) {
        if (type == null) return primitiveOrSimpleClassData(void.class);

        boolean isPrimitive  = type.isPrimitive();
        boolean isVoid       = type == void.class || type == Void.class;
        boolean isEnum       = type.isEnum();
        boolean isArray      = type.isArray();
        boolean isCollection = !isArray && isCollectionType(type);
        boolean isMap        = isMapType(type);
        boolean isRecord     = type.isRecord();
        String  pkg          = isPrimitive ? "" : type.getPackageName();

        ClassData component = null;
        if (isArray) {
            component = classDataForType(type.getComponentType(), null);
        } else if (isCollection && genericType instanceof java.lang.reflect.ParameterizedType pt) {
            java.lang.reflect.Type[] args = pt.getActualTypeArguments();
            if (args.length == 1 && args[0] instanceof Class<?> argClass)
                component = classDataForType(argClass, null);
        } else if (isMap && genericType instanceof java.lang.reflect.ParameterizedType pt) {
            java.lang.reflect.Type[] args = pt.getActualTypeArguments();
            if (args.length == 2 && args[1] instanceof Class<?> valClass)
                component = classDataForType(valClass, null);
        }

        return new DefaultClassData(
                JavaTypeNames.of(type),
                pkg,
                isPrimitive, isVoid, isEnum,
                isRecord, isArray, isCollection, isMap,
                false, // constrained — field-level, not type-level
                component,
                List.of(), List.of(), List.of(), List.of());
    }

    private static ClassData primitiveOrSimpleClassData(Class<?> type) {
        return classDataForType(type, null);
    }

    private static boolean isCollectionType(Class<?> type) {
        return java.util.Collection.class.isAssignableFrom(type);
    }

    private static boolean isMapType(Class<?> type) {
        return java.util.Map.class.isAssignableFrom(type);
    }

    // ── Primitive defaults ────────────────────────────────────────────────

    private static Object primitiveDefault(Class<?> type) {
        return switch (type.getName()) {
            case "int"     -> 0;
            case "long"    -> 0L;
            case "double"  -> 0.0d;
            case "float"   -> 0.0f;
            case "boolean" -> false;
            case "byte"    -> (byte) 0;
            case "short"   -> (short) 0;
            case "char"    -> 'A';
            default        -> 0;
        };
    }

    // ── Assertion helpers ─────────────────────────────────────────────────

    private static String buildReturnAssertion(ReturnTypeInfo ret, boolean hasJakarta) {
        if (ret.isVoid()) return "";
        if (ret.isConstrained()) {
            StringBuilder sb = new StringBuilder();
            for (Annotation a : ret.annotations()) {
                if (a.annotationType().getSimpleName().equals("Valid")) {
                    sb.append("assertThat(validator.validate(result)).isEmpty();");
                } else {
                    String call = dataValidationCall(a, "result");
                    if (call != null) sb.append("DataValidation.").append(call).append(";");
                }
            }
            return sb.toString();
        }
        if (ret.isPrimitive()) return "";
        return "assertThat(result).isNotNull();";
    }

    private static boolean needsWrapperRecord(ReturnTypeInfo ret) {
        if (ret.isVoid() || !ret.isConstrained()) return false;
        Class<?> t = ret.type();
        return t.isPrimitive() || t == String.class || t == Character.class
                || t.getPackageName().startsWith("java.")
                || t.getPackageName().startsWith("javax.");
    }

    private static String buildWrapperRecord(String methodName, ReturnTypeInfo ret) {
        String annotations = buildAnnotationSource(ret.annotations());
        String boxedType   = JavaTypeNames.boxed(ret.type());
        String name        = Character.toUpperCase(methodName.charAt(0))
                + methodName.substring(1) + "Result";
        return "private record " + name + "(" + annotations + boxedType + " value) {}";
    }

    private static List<String> buildEnforcementHints(ParameterInfo param) {
        List<String> hints = new ArrayList<>();
        for (Annotation a : param.validations()) {
            String call = dataValidationCall(a, param.name());
            if (call != null)
                hints.add("// Enforce @" + a.annotationType().getSimpleName()
                        + ": DataValidation." + call + ";");
        }
        return List.copyOf(hints);
    }

    // ── Annotation helpers ────────────────────────────────────────────────

    private static String dataValidationCall(Annotation a, String expr) {
        String name = a.annotationType().getSimpleName();
        try {
            return switch (name) {
                case "NotNull"        -> "notNull(" + expr + ")";
                case "NotBlank"       -> "notBlank(" + expr + ")";
                case "NotEmpty"       -> "notEmpty(" + expr + ")";
                case "Positive"       -> "positive(" + expr + ")";
                case "PositiveOrZero" -> "positiveOrZero(" + expr + ")";
                case "Negative"       -> "negative(" + expr + ")";
                case "NegativeOrZero" -> "negativeOrZero(" + expr + ")";
                case "Min"            -> "min(" + longAttr(a, "value") + ", " + expr + ")";
                case "Max"            -> "max(" + longAttr(a, "value") + ", " + expr + ")";
                case "DecimalMin"     -> "decimalMin(\"" + stringAttr(a, "value") + "\", " + expr + ")";
                case "DecimalMax"     -> "decimalMax(\"" + stringAttr(a, "value") + "\", " + expr + ")";
                case "Size"           -> "size(" + intAttr(a, "min") + ", " + intAttr(a, "max") + ", " + expr + ")";
                case "Pattern"        -> "pattern(\"" + stringAttr(a, "regexp") + "\", " + expr + ")";
                default               -> null;
            };
        } catch (Exception ignored) { return null; }
    }

    private static String buildAnnotationSource(List<Annotation> annotations) {
        if (annotations.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Annotation a : annotations) sb.append(annotationToSource(a)).append(" ");
        return sb.toString();
    }

    private static String annotationToSource(Annotation a) {
        String   name  = a.annotationType().getSimpleName();
        Method[] attrs = a.annotationType().getDeclaredMethods();
        if (attrs.length == 0) return "@" + name;
        if (attrs.length == 1 && attrs[0].getName().equals("value")) {
            try {
                Object val = attrs[0].invoke(a);
                String src = attrToSource(val);
                if (src == null) return "@" + name;
                if (src.endsWith("L")) src = src.substring(0, src.length() - 1);
                return "@" + name + "(" + src + ")";
            } catch (Exception ignored) {}
        }
        StringBuilder sb = new StringBuilder("@").append(name).append("(");
        boolean first = true;
        for (Method m : attrs) {
            try {
                Object val = m.invoke(a);
                Object def = m.getDefaultValue();
                if (def != null && def.equals(val)) continue;
                String src = attrToSource(val);
                if (src == null) continue;
                if (!first) sb.append(", ");
                sb.append(m.getName()).append("=").append(src);
                first = false;
            } catch (Exception ignored) {}
        }
        sb.append(")");
        return sb.toString().equals("@" + name + "()") ? "@" + name : sb.toString();
    }

    private static String attrToSource(Object val) {
        if (val instanceof String s)       return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
        if (val instanceof Long l)         return l + "L";
        if (val instanceof Class<?> c)     return c.getSimpleName() + ".class";
        if (val instanceof Annotation ann) return annotationToSource(ann);
        if (val != null && val.getClass().isArray()) return null;
        return val == null ? null : String.valueOf(val);
    }

    // ── Utilities ─────────────────────────────────────────────────────────

    private static long effectiveSeed(TestCodeData payload) {
        return payload.seed() != null
                ? payload.seed()
                : com.paramichha.utils.Seeds.stableHash(payload.classInfo().type());
    }

    @SuppressWarnings("unchecked")
    /**
     * For integer fields with a bound, add the type's extreme beyond that bound as a second
     * invalid value: {@code @Positive int} → also {@code Integer.MIN_VALUE}, {@code @Max(5)} → also
     * {@code Integer.MAX_VALUE}. DataFactory gives the value just outside the bound; the extreme
     * catches checks that only reject values close to it (and gives @Positive a negative case).
     */
    private static List<Object> withTypeExtremes(Class<?> rawType, FieldInfo field, List<Object> invalid) {
        Class<?> t = boxed(rawType);
        long typeMin, typeMax;
        if      (t == Integer.class) { typeMin = Integer.MIN_VALUE; typeMax = Integer.MAX_VALUE; }
        else if (t == Long.class)    { typeMin = Long.MIN_VALUE;    typeMax = Long.MAX_VALUE;    }
        else if (t == Short.class)   { typeMin = Short.MIN_VALUE;   typeMax = Short.MAX_VALUE;   }
        else if (t == Byte.class)    { typeMin = Byte.MIN_VALUE;    typeMax = Byte.MAX_VALUE;    }
        else return invalid;
        var bounds = com.paramichha.datafactory.constraint.AnnotationResolver
                .analyze(field.name(), rawType, field.validations()).bounds();
        if (bounds.isContradictory()) return invalid;
        List<Object> out = new ArrayList<>(invalid);
        Long min = bounds.min(), max = bounds.max();
        if (min != null && min - 1 > typeMin) addIfAbsent(out, cast(t, typeMin));
        if (max != null && max + 1 < typeMax) addIfAbsent(out, cast(t, typeMax));
        return out;
    }

    /** Keeps the first occurrence of each value, in order (DataFactory can repeat a boundary). */
    private static List<Object> distinct(List<Object> values) {
        List<Object> out = new ArrayList<>();
        for (Object v : values) if (!out.contains(v)) out.add(v);
        return out;
    }

    private static void addIfAbsent(List<Object> values, Object v) {
        if (!values.contains(v)) values.add(v);
    }

    private static Object cast(Class<?> t, long v) {
        if (t == Integer.class) return (int) v;
        if (t == Short.class)   return (short) v;
        if (t == Byte.class)    return (byte) v;
        return v;
    }

    private static Class<?> boxed(Class<?> t) {
        if (!t.isPrimitive()) return t;
        if (t == int.class)     return Integer.class;
        if (t == long.class)    return Long.class;
        if (t == double.class)  return Double.class;
        if (t == float.class)   return Float.class;
        if (t == short.class)   return Short.class;
        if (t == byte.class)    return Byte.class;
        if (t == char.class)    return Character.class;
        if (t == boolean.class) return Boolean.class;
        return Void.class;
    }

    private static List<Object> castList(List<?> list) {
        return new ArrayList<>((List<Object>) list);
    }

    private static TestCodeData wrapAsPayload(ClassInfo meta, long seed) {
        return new com.paramichha.testkit.TestCodeData(
                meta, null, meta.packageName(),
                com.paramichha.testkit.TestKitTemplate.DEFAULT,
                com.paramichha.testkit.ProjectType.detect(),
                seed);
    }

    private static long   longAttr  (Annotation a, String attr) throws Exception { return (long)   a.annotationType().getMethod(attr).invoke(a); }
    private static String stringAttr(Annotation a, String attr) throws Exception { return (String) a.annotationType().getMethod(attr).invoke(a); }
    private static int    intAttr   (Annotation a, String attr) throws Exception { return (int)    a.annotationType().getMethod(attr).invoke(a); }
}