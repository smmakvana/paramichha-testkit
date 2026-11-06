package com.paramichha.testkit.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** Small helpers of the generator, including the fallbacks ordinary classes don't reach. */
class GeneratorHelpersTest {

    /** A record whose accessor fails: its value can't be read back for a literal. */
    record Unreadable(int value) {
        @Override
        public int value() {
            throw new IllegalStateException("accessor fails");
        }
    }

    record Inner(String name) {}

    @Test
    @DisplayName("BigInteger, Date and null have literals and imports")
    void moreLiterals() {
        assertThat(LiteralConverter.canSerialize(BigInteger.TEN)).isTrue();
        assertThat(LiteralConverter.toLiteral(BigInteger.TEN)).isEqualTo("BigInteger.valueOf(10L)");
        assertThat(LiteralConverter.importsFor(BigInteger.TEN)).containsExactly("java.math.BigInteger");

        Date epoch = new Date(0L);
        assertThat(LiteralConverter.canSerialize(epoch)).isTrue();
        assertThat(LiteralConverter.toLiteral(epoch)).isEqualTo("new Date(0L)");
        assertThat(LiteralConverter.importsFor(epoch)).containsExactly("java.util.Date");

        assertThat(LiteralConverter.toLiteral(null)).isEqualTo("null");
    }

    @Test
    @DisplayName("a value with no literal form becomes a TODO, not broken code")
    void noLiteralForm() {
        StringBuilder sb = new StringBuilder("x");
        assertThat(LiteralConverter.canSerialize(sb)).isFalse();
        assertThat(LiteralConverter.toLiteral(sb)).isEqualTo("/* TODO: StringBuilder */ null");
    }

    @Test
    @DisplayName("a record whose accessor throws is not serializable, and its literal is a TODO")
    void recordWithFailingAccessor() {
        Unreadable r = new Unreadable(1);
        assertThat(LiteralConverter.canSerialize(r)).isFalse();
        assertThat(LiteralConverter.toLiteral(r)).isEqualTo("/* TODO: Unreadable */ null");
        assertThat(LiteralConverter.importsFor(r)).isNotNull();
        assertThat(LiteralConverter.toLiteral(new Inner("a"))).isEqualTo("new GeneratorHelpersTest.Inner(\"a\")");
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource(quoteCharacter = '"', value = {
            "int, 0", "long, 0L", "double, 0.0d", "float, 0.0f", "boolean, false",
            "byte, (byte) 0", "short, (short) 0", "char, '\\0'"})
    @DisplayName("a primitive argument without a value gets its zero value")
    void zeroLiterals(String primitive, String literal) {
        ClassData type = new DefaultClassData(primitive, "", true, false, false, false, false, false,
                List.of(), List.of(), List.of());
        assertThat(MutatorTransformer.literalFor(null, type)).isEqualTo(literal);
    }

    @Test
    @DisplayName("an object argument without a value is a typed null; a value is written as a literal")
    void objectLiterals() {
        ClassData string = ClassDataFactory.classDataForType(String.class);
        assertThat(MutatorTransformer.literalFor(null, string)).isEqualTo("(String) null");
        assertThat(MutatorTransformer.literalFor("a", string)).isEqualTo("\"a\"");
        ClassData unknown = new DefaultClassData("void", "", true, false, false, false, false, false,
                List.of(), List.of(), List.of());
        assertThat(MutatorTransformer.literalFor(null, unknown)).isEqualTo("0");
    }

    @Test
    @DisplayName("primitive defaults and boxing cover every primitive")
    void primitives() {
        assertThat(ClassDataFactory.primitiveDefault(int.class)).isEqualTo(0);
        assertThat(ClassDataFactory.primitiveDefault(long.class)).isEqualTo(0L);
        assertThat(ClassDataFactory.primitiveDefault(char.class)).isEqualTo('A');
        assertThat(ClassDataFactory.boxed(boolean.class)).isEqualTo(Boolean.class);
        assertThat(ClassDataFactory.boxed(char.class)).isEqualTo(Character.class);
        assertThat(ClassDataFactory.boxed(String.class)).isEqualTo(String.class);
    }

    @Test
    @DisplayName("a nested type is named with its enclosing class")
    void nestedTypeNames() {
        assertThat(JavaTypeNames.of(Inner.class)).isEqualTo("GeneratorHelpersTest.Inner");
        assertThat(JavaTypeNames.of(String.class)).isEqualTo("String");
    }

    @Test
    @DisplayName("coders write annotations and modifiers when they are given")
    void codersWriteAnnotationsAndModifiers() {
        assertThat(new ParameterCoder().code(new ParameterCoderInfo(List.of("@NotNull"), "String", "name")))
                .contains("@NotNull String name");
        assertThat(new FieldCoder().code(new FieldCoderInfo(List.of("@Mock"), List.of("private"), "Repo", "repo", null)))
                .contains("@Mock\n").contains("private Repo repo");
        assertThat(new ClassCoder().code(ClassCoderInfo.nested(List.of(), List.of("static"), "Inner",
                List.of(), List.of(), List.of()))).contains("static class Inner");
    }
}
