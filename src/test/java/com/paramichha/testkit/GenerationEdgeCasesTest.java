package com.paramichha.testkit;

import com.paramichha.testkit.model.ClassInfo;
import com.paramichha.testkit.model.ConstructorStyle;
import com.paramichha.testkit.model.FieldInfo;
import com.paramichha.testkit.model.MethodInfo;
import com.paramichha.testkit.model.MethodKind;
import com.paramichha.testkit.model.ParameterInfo;
import com.paramichha.testkit.model.ReturnTypeInfo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** Class shapes and failures that the example classes don't cover. */
class GenerationEdgeCasesTest {

    // ── Fixtures ──────────────────────────────────────────────────────────

    /** Only static methods and a private constructor: tested through its methods alone. */
    public static final class MathUtil {
        private MathUtil() {}
        public static int add(int a, int b) { return a + b; }
    }

    /** Private constructor, but a static factory returns the type: not a utility class. */
    public static final class Money {
        private final BigDecimal amount;
        private Money(BigDecimal amount) { this.amount = amount; }
        public static Money of(BigDecimal amount) { return new Money(amount); }
        public BigDecimal getAmount() { return amount; }
    }

    /** Constructor parameters named differently from the fields. */
    public static final class Point {
        private final int x;
        private final int y;
        public Point(int px, int py) { this.x = px; this.y = py; }
        public int getX() { return x; }
        public int getY() { return y; }
    }

    /** Two fields, but no constructor takes both. */
    public static final class Partial {
        private final int a;
        private final int b;
        public Partial(int a) { this.a = a; this.b = 0; }
        public int getA() { return a; }
        public int getB() { return b; }
    }

    /** Each return rule turns into a DataValidation check of the result. */
    public static final class Rules {
        @NotEmpty public List<String> tags() { return List.of("a"); }
        @PositiveOrZero public int count() { return 0; }
        @Negative public int debt() { return -1; }
        @NegativeOrZero public int balance() { return 0; }
        @Size(min = 1, max = 3) public String code() { return "AB"; }
        @Pattern(regexp = "[A-Z]+") public String upper() { return "AB"; }
    }

    /** Fields whose values TestKit produces in an unusual way. */
    public record Odd(List<List<String>> matrix, List<DayOfWeek> days, List<Nothing> nothing, @Null String legacy) {}

    /** DataFactory has no generator for Object or StringBuilder. */
    public record Unbuildable(Object anything, List<StringBuilder> builders) {}

    public enum Nothing {}

    public interface Shape {}

    /** DataFactory can't build the interface field, so there is no valid object. */
    public record Drawing(Shape shape) {}

    public enum Color { RED, GREEN }

    /** Field types listed in a different order from the constructor parameters. */
    public static final class Reversed {
        private final BigDecimal amount;
        private final String currency;
        public Reversed(String currency, BigDecimal amount) { this.amount = amount; this.currency = currency; }
        public BigDecimal getAmount() { return amount; }
        public String getCurrency() { return currency; }
    }

    /** Constructor takes wider types than the fields. */
    public static final class Loose {
        private final String a;
        private final String b;
        public Loose(CharSequence a, CharSequence b) { this.a = a.toString(); this.b = b.toString(); }
        public String getA() { return a; }
        public String getB() { return b; }
    }

    /** Domain-typed parameters, a long no value can satisfy, and types DataFactory can't generate. */
    public static final class Desk {
        public void pay(Reversed money) { }
        public void label(Loose text) { }
        public void reserve(@Min(5) @Max(1) long count) { }
        public void note(@jakarta.validation.constraints.NotNull StringBuilder text) { }
        public void attach(Object anything) { }
    }

    /** The constructor parameter has a rule the field doesn't; the other field's only valid value is null. */
    public static final class Tagged {
        private final String name;
        @Null private final String legacy;
        public Tagged(@jakarta.validation.constraints.NotBlank String name, String legacy) {
            this.name = name;
            this.legacy = legacy;
        }
        public String getName() { return name; }
        public String getLegacy() { return legacy; }
    }

    private static String test(Class<?> type) {
        return TestKit.of(type).generateTest().asString();
    }

    @Nested
    @DisplayName("class shapes")
    class Shapes {

        @Test
        void utilityClassIsTestedThroughItsMethods() {
            // static methods are called on the class: a utility class has no instance in the test
            assertThat(test(MathUtil.class)).contains("MathUtil.add(a, b)").doesNotContain("mathUtil.");
        }

        @Test
        void aStaticFactoryMakesItAnOrdinaryClass() {
            assertThat(test(Money.class)).contains("class MoneyTest");
        }

        @Test
        void constructorParameterNamesNeedNotMatchFields() {
            assertThat(test(Point.class)).contains("new Point(");
        }

        @Test
        void noConstructorForAllFieldsIsReported() {
            assertThat(test(Partial.class))
                    .contains("// Generation failed for: Partial")
                    .contains("no constructor takes all 2 fields");
        }

        @Test
        void aClassWithoutAValidObjectGetsAPlaceholder() {
            assertThat(test(Drawing.class)).contains("// Generation failed for: Drawing");
            assertThat(TestKit.of(Drawing.class).generateTests().asString()).contains("// Generation failed for: Drawing");
        }

        @Test
        void enumsInSplitModeGetTheEnumTest() {
            assertThat(TestKit.of(Color.class).generateTests().asString()).contains("every constant is found by its name");
        }
    }

    @Nested
    @DisplayName("values")
    class Values {

        @Test
        void returnRulesAreCheckedWithDataValidation() {
            assertThat(test(Rules.class))
                    .contains("DataValidation.notEmpty(result)")
                    .contains("DataValidation.positiveOrZero(result)")
                    .contains("DataValidation.negative(result)")
                    .contains("DataValidation.negativeOrZero(result)")
                    .contains("DataValidation.size(1, 3, result)")
                    .contains("DataValidation.pattern(\"[A-Z]+\", result)");
        }

        @Test
        void unusualFieldValues() {
            assertThat(test(Odd.class))
                    .contains("class OddTest")
                    .contains("DayOfWeek.MONDAY")
                    .contains("List.of(List.of(");
        }

        @Test
        void fieldsWithNoGeneratorAreNamedInThePlaceholder() {
            assertThat(test(Unbuildable.class))
                    .contains("// Generation failed for: Unbuildable")
                    .contains("field 'anything'");
        }

        @Test
        void domainParametersAreBuiltThroughAnyFittingConstructor() {
            // Loose is built through its CharSequence constructor, but only a constructor taking
            // exactly the field types can be written back as source, so the literal stays null.
            assertThat(test(Desk.class))
                    .contains("desk.label(text)")
                    .contains("desk.pay(money)")
                    .contains("desk.reserve(count)")
                    .contains("desk.note(text)")
                    .contains("desk.attach(anything)");
        }

        @Test
        void aConstructorRuleIsCheckedWithTheOtherArgumentsAsLiterals() {
            assertThat(test(Tagged.class)).contains("(String) null");
        }
    }

    // ── A class model that fails while it is read ─────────────────────────

    /** A field written by hand, relying on the interface defaults. */
    private record PlainField(String name, Class<?> rawType) implements FieldInfo {
        @Override public boolean isConstrained() { return false; }
        @Override public Type genericType() { return rawType; }
        @Override public List<Annotation> validations() { return List.of(); }
        @Override public boolean required() { return false; }
        @Override public boolean hasIndirectConstraints() { return false; }
    }

    /** A list field whose element type has no generator. */
    private record BuilderListField(String name) implements FieldInfo {
        @Override public boolean isConstrained() { return false; }
        @Override public Class<?> rawType() { return List.class; }
        @Override public Type genericType() {
            return new java.lang.reflect.ParameterizedType() {
                @Override public Type[] getActualTypeArguments() { return new Type[]{StringBuilder.class}; }
                @Override public Type getRawType() { return List.class; }
                @Override public Type getOwnerType() { return null; }
            };
        }
        @Override public List<Annotation> validations() { return List.of(); }
        @Override public boolean required() { return false; }
        @Override public boolean hasIndirectConstraints() { return false; }
    }

    /** A field whose constraints can't be read at all. */
    private record UnreadableField(String name) implements FieldInfo {
        @Override public boolean isConstrained() { return false; }
        @Override public Class<?> rawType() { return String.class; }
        @Override public Type genericType() { return String.class; }
        @Override public List<Annotation> validations() { throw new IllegalStateException("unreadable"); }
        @Override public boolean required() { return false; }
        @Override public boolean hasIndirectConstraints() { return false; }
    }

    /** A field whose property constraints, read only while building its tests, fail. */
    private record BrokenField(String name) implements FieldInfo {
        @Override public boolean isConstrained() { return false; }
        @Override public Class<?> rawType() { return String.class; }
        @Override public Type genericType() { return String.class; }
        @Override public List<Annotation> validations() { return List.of(); }
        @Override public List<Annotation> propertyValidations() { throw new IllegalStateException("unreadable"); }
        @Override public boolean required() { return false; }
        @Override public boolean hasIndirectConstraints() { return false; }
    }

    /** A method whose kind, read only while building its tests, fails. */
    private record BrokenMethod(String name) implements MethodInfo {
        @Override public boolean hasParameters() { return false; }
        @Override public boolean hasReturnConstraints() { return false; }
        @Override public ReturnTypeInfo returnType() {
            return new com.paramichha.testkit.model.ReflectionReturnTypeInfo(void.class, List.of());
        }
        @Override public List<ParameterInfo> parameters() { return List.of(); }
        @Override public MethodKind kind() { throw new IllegalStateException("unknown kind"); }
    }

    public record Pair(String first, String second) {}

    /** A query whose return carries a foreign "Min" annotation. */
    private record ForeignRuleQuery(String name, Annotation rule) implements MethodInfo {
        @Override public boolean hasParameters() { return false; }
        @Override public boolean hasReturnConstraints() { return true; }
        @Override public ReturnTypeInfo returnType() {
            return new com.paramichha.testkit.model.ReflectionReturnTypeInfo(int.class, List.of(rule));
        }
        @Override public List<ParameterInfo> parameters() { return List.of(); }
        @Override public MethodKind kind() { return MethodKind.QUERY; }
    }

    private static ClassInfo pairWith(List<FieldInfo> fields, List<MethodInfo> methods) {
        return new ClassInfo() {
            @Override public String simpleName() { return "Pair"; }
            @Override public String packageName() { return Pair.class.getPackageName(); }
            @Override public ConstructorStyle constructorStyle() { return ConstructorStyle.RECORD; }
            @Override public List<FieldInfo> fields() { return fields; }
            @Override public List<MethodInfo> methods() { return methods; }
            @Override public boolean utilityClass() { return false; }
            @Override public Class<?> type() { return Pair.class; }
        };
    }

    @Nested
    @DisplayName("a hand-built class model")
    class HandBuilt {

        @Test
        void aFieldUsingTheInterfaceDefaultsWorks() {
            ClassInfo info = pairWith(List.of(new PlainField("first", String.class), new PlainField("second", String.class)),
                    List.of());
            assertThat(TestKit.of(info).generateTest().asString()).contains("class PairTest").contains("new Pair(");
        }

        @Test
        void aModelThatCantBeReadGivesAPlaceholderWithTheReason() {
            ClassInfo info = pairWith(List.of(new PlainField("first", String.class), new UnreadableField("second")), List.of());
            assertThat(TestKit.of(info).generateTest().asString()).contains("// Reason: unreadable");
            assertThat(TestKit.of(info).generateTests().asString()).contains("// Reason: unreadable");
        }

        @Test
        void aFieldThatFailsToReadKeepsTheRestOfTheTest() {
            ClassInfo info = pairWith(List.of(new PlainField("first", String.class), new BrokenField("second")), List.of());
            assertThat(TestKit.of(info).generateTest().asString()).contains("class PairTest");
        }

        @Test
        void aMethodThatFailsToReadNamesItself() {
            ClassInfo info = pairWith(List.of(new PlainField("first", String.class), new PlainField("second", String.class)),
                    List.of(new BrokenMethod("swap")));
            assertThat(TestKit.of(info).generateTest().asString())
                    .contains("could not build tests for method(s): swap (unknown kind)");
            assertThat(TestKit.of(info).generateTests().asString())
                    .contains("could not build tests for method(s): swap (unknown kind)");
        }

        @Test
        void aContainerWhoseElementCantBeGeneratedGetsNoValue() {
            ClassInfo info = pairWith(List.of(new PlainField("first", String.class), new BuilderListField("second")),
                    List.of());
            assertThat(TestKit.of(info).generateTest().asString()).contains("class PairTest");
        }

        @Test
        void aRuleWithAnUnexpectedShapeIsSkipped() {
            // named like jakarta's Min, but without value(): no DataValidation call can be written
            Annotation foreignMin = new Annotation() {
                @Override public Class<? extends Annotation> annotationType() { return com.paramichha.testkit.foreign.Min.class; }
            };
            ClassInfo info = pairWith(List.of(new PlainField("first", String.class), new PlainField("second", String.class)),
                    List.of(new ForeignRuleQuery("size", foreignMin)));
            assertThat(TestKit.of(info).generateTest().asString()).contains("class PairTest").doesNotContain("DataValidation.min");
        }

        @Test
        void withoutMethodsKeepsEverythingElse() {
            ClassInfo info = pairWith(List.of(new PlainField("first", String.class)), List.of(new BrokenMethod("swap")));
            ClassInfo plain = TestKit.withoutMethods(info);
            assertThat(plain.methods()).isEmpty();
            assertThat(plain.packageName()).isEqualTo(info.packageName());
            assertThat(plain.simpleName()).isEqualTo("Pair");
            assertThat(plain.fields()).isEqualTo(info.fields());
            assertThat(plain.constructorStyle()).isEqualTo(ConstructorStyle.RECORD);
            assertThat(plain.utilityClass()).isFalse();
            assertThat(plain.type()).isEqualTo(Pair.class);
        }

        @Test
        void aNullModelIsRejected() {
            org.assertj.core.api.Assertions.assertThatThrownBy(() -> TestKit.of((ClassInfo) null))
                    .isInstanceOf(TestKitException.class);
        }
    }
}
