package com.paramichha.testkit.generator;

import com.paramichha.testkit.model.ClassInfo;
import com.paramichha.testkit.model.ConstructorStyle;
import com.paramichha.testkit.model.FieldInfo;
import com.paramichha.utils.Texts;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Whether a generated test can create the class at all. When it can't, TestKit reports why
 * instead of writing a test that does not compile.
 */
final class Creatability {

    static final String SUPPORTED = "Supported: record, Lombok @Builder, a constructor taking every field,"
            + " or a no-args constructor with a setter per field.";

    private Creatability() {
    }

    /** Why the class can't be created by a generated test, or empty when it can. */
    static Optional<String> problem(ClassInfo info) {
        Class<?> type = info.type();
        if (type == null || info.fields().isEmpty() || info.utilityClass()) return Optional.empty();
        ConstructorStyle style = info.constructorStyle();
        if (style == ConstructorStyle.SETTER) {
            for (FieldInfo f : info.fields()) {
                if (!hasSetter(type, f)) {
                    return Optional.of("field '" + f.name() + "' has no setter, and no constructor takes "
                            + allFields(info) + ". " + SUPPORTED);
                }
            }
        }
        if (style == ConstructorStyle.ALL_ARGS && !hasConstructorWithArity(type, info.fields().size())) {
            return Optional.of("no constructor takes " + allFields(info) + ". " + SUPPORTED);
        }
        return Optional.empty();
    }

    private static String allFields(ClassInfo info) {
        int n = info.fields().size();
        return n == 1 ? "its one field" : "all " + n + " fields";
    }

    private static boolean hasSetter(Class<?> type, FieldInfo f) {
        String name = "set" + Texts.capitalize(f.name());
        for (Method m : type.getMethods()) {
            if (m.getName().equals(name) && m.getParameterCount() == 1) return true;
        }
        return false;
    }

    private static boolean hasConstructorWithArity(Class<?> type, int arity) {
        for (Constructor<?> c : type.getDeclaredConstructors()) {
            if (c.getParameterCount() == arity) return true;
        }
        return false;
    }
}
