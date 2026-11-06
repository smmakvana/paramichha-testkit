package com.paramichha.testkit.model;

import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** The model types users can build themselves for {@code TestKit.of(ClassInfo)}. */
class ModelTypesTest {

    record Sample(@NotNull String name) {}

    /** {@code @NotNull} does not target record components; the compiler puts it on the field. */
    private static final Annotation NOT_NULL = notNull();

    private static Annotation notNull() {
        try {
            return Sample.class.getDeclaredField("name").getAnnotation(NotNull.class);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    @DisplayName("a field: short constructors default to no indirect constraints, no constructor rules")
    void fieldInfo() {
        ReflectionFieldInfo plain = new ReflectionFieldInfo("name", String.class, String.class, List.of(NOT_NULL), true);
        assertThat(plain).isEqualTo(new ReflectionFieldInfo("name", String.class, String.class, List.of(NOT_NULL), true, false));
        assertThat(plain.hashCode())
                .isEqualTo(new ReflectionFieldInfo("name", String.class, String.class, List.of(NOT_NULL), true, false).hashCode());
        assertThat(plain.hasIndirectConstraints()).isFalse();
        assertThat(plain.isConstrained()).isTrue();
        assertThat(plain.required()).isTrue();
        assertThat(new ReflectionFieldInfo("name", String.class, String.class, List.of(), false).isConstrained()).isFalse();
        assertThat(plain.constructorValidations()).isEmpty();
        assertThat(plain.constructorIndex()).isEqualTo(-1);
        assertThat(plain.toString()).contains("name").contains("String");
    }

    @Test
    @DisplayName("a parameter: the short constructor means no indirect constraints")
    void parameterInfo() {
        ReflectionParameterInfo p = new ReflectionParameterInfo("name", String.class, List.of(NOT_NULL));
        assertThat(p).isEqualTo(new ReflectionParameterInfo("name", String.class, List.of(NOT_NULL), false));
        assertThat(p.hashCode()).isEqualTo(new ReflectionParameterInfo("name", String.class, List.of(NOT_NULL), false).hashCode());
        assertThat(p.hasIndirectConstraints()).isFalse();
        assertThat(p.isConstrained()).isTrue();
        assertThat(p.toString()).contains("name");
    }

    @Test
    @DisplayName("a method: parameters and return rules are reported")
    void methodInfo() {
        ReflectionParameterInfo p = new ReflectionParameterInfo("name", String.class, List.of());
        ReflectionMethodInfo rename = new ReflectionMethodInfo("rename", void.class, List.of(p), MethodKind.MUTATOR);
        assertThat(rename.hasParameters()).isTrue();
        assertThat(rename.hasReturnConstraints()).isFalse();
        assertThat(rename).isEqualTo(new ReflectionMethodInfo("rename", void.class, List.of(), List.of(p), MethodKind.MUTATOR));
        assertThat(rename.hashCode())
                .isEqualTo(new ReflectionMethodInfo("rename", void.class, List.of(), List.of(p), MethodKind.MUTATOR).hashCode());
        assertThat(rename.toString()).contains("rename");

        ReflectionMethodInfo name = new ReflectionMethodInfo("name", String.class, List.of(NOT_NULL), List.of(), MethodKind.QUERY);
        assertThat(name.hasParameters()).isFalse();
        assertThat(name.hasReturnConstraints()).isTrue();
    }

    @Test
    @DisplayName("a return type")
    void returnTypeInfo() {
        ReflectionReturnTypeInfo r = new ReflectionReturnTypeInfo(String.class, List.of(NOT_NULL));
        assertThat(r).isEqualTo(new ReflectionReturnTypeInfo(String.class, List.of(NOT_NULL)));
        assertThat(r.hashCode()).isEqualTo(new ReflectionReturnTypeInfo(String.class, List.of(NOT_NULL)).hashCode());
        assertThat(r.toString()).contains("String");
    }

    @Test
    @DisplayName("a class: short constructors mean no methods and not a utility class")
    void classInfo() {
        ReflectionFieldInfo f = new ReflectionFieldInfo("name", String.class, String.class, List.of(), false);
        ReflectionClassInfo info = new ReflectionClassInfo(ConstructorStyle.RECORD, List.of(f), Sample.class);
        assertThat(info).isEqualTo(new ReflectionClassInfo(ConstructorStyle.RECORD, List.of(f), List.of(), Sample.class))
                .isNotEqualTo(new ReflectionClassInfo(ConstructorStyle.ALL_ARGS, List.of(f), Sample.class))
                .isNotEqualTo("not a class info");
        assertThat(info.hashCode())
                .isEqualTo(new ReflectionClassInfo(ConstructorStyle.RECORD, List.of(f), List.of(), false, Sample.class).hashCode());
        assertThat(info.methods()).isEmpty();
        assertThat(info.utilityClass()).isFalse();
        assertThat(info.packageName()).isEqualTo("com.paramichha.testkit.model");
        assertThat(info.toString()).contains("RECORD");

        // methods are sorted by name, then by parameter count (overloads)
        ReflectionParameterInfo p = new ReflectionParameterInfo("n", int.class, List.of());
        ReflectionMethodInfo twoArgs = new ReflectionMethodInfo("move", void.class, List.of(p, p), MethodKind.COMMAND);
        ReflectionMethodInfo oneArg = new ReflectionMethodInfo("move", void.class, List.of(p), MethodKind.COMMAND);
        ReflectionMethodInfo area = new ReflectionMethodInfo("area", double.class, List.of(), MethodKind.QUERY);
        assertThat(new ReflectionClassInfo(ConstructorStyle.RECORD, List.of(f), List.of(twoArgs, oneArg, area), Sample.class)
                .methods()).containsExactly(area, oneArg, twoArgs);
    }

    @Test
    @DisplayName("a fixture knows whether any field of the class has a constraint")
    void fixtureHasJakarta() {
        TestFixture fixture = new TestFixture(Sample.class, null, List.of(), java.util.Map.of(), List.of(), null);
        ReflectionFieldInfo constrained = new ReflectionFieldInfo("name", String.class, String.class, List.of(NOT_NULL), true);
        ReflectionFieldInfo plain = new ReflectionFieldInfo("name", String.class, String.class, List.of(), false);
        assertThat(fixture.hasJakarta(new ReflectionClassInfo(ConstructorStyle.RECORD, List.of(constrained), Sample.class))).isTrue();
        assertThat(fixture.hasJakarta(new ReflectionClassInfo(ConstructorStyle.RECORD, List.of(plain), Sample.class))).isFalse();
        assertThat(fixture.hasError()).isFalse();
    }
}
