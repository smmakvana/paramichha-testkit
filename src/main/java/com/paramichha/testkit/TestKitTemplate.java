package com.paramichha.testkit;

import com.paramichha.utils.Texts;
import com.paramichha.utils.Tokens;

/**
 * Controls all generated names and display strings.
 * All strings support tokens replaced at generation time.
 */
public final class TestKitTemplate {

    // ── Class names ───────────────────────────────────────────────────────
    /**
     * Suffix of every generated test class: {@code CircleTest}, {@code CircleIT}, {@code CircleSpec}.
     * Must be a Java identifier part; the default keeps the historical {@code Test} names.
     */
    public String testSuffix = "Test";
    /** Text before the class name: {@code Unit} → {@code UnitCircleTest}. Empty by default. */
    public String classPrefix = "";

    // ── Test method names ────────────────────────────────────────────────
    /**
     * Wrapped around the name of every test method ({@code @Test}, {@code @ParameterizedTest}):
     * {@code methodPrefix + "testRejectsX" + methodSuffix}. Helper methods (value streams, setup)
     * keep their names. Both empty by default.
     */
    public String methodPrefix = "";
    public String methodSuffix = "";

    /** The class-name affix, used for every generated test class. */
    public Affix classAffix() { return new Affix(classPrefix, testSuffix); }

    /** The test-method affix. */
    public Affix methodAffix() { return new Affix(methodPrefix, methodSuffix); }

    // ── Validator ─────────────────────────────────────────────────────────
    public String validatorField   = "validator";
    public String validatorSetup   = "setUpValidator";

    // ── Level 2 — Creation ────────────────────────────────────────────────
    public String creationClassName      = "{ClassName}CreationTest";
    public String creationDisplay        = "Creation";
    public String canBeCreated           = "testCanBeCreated";
    public String canBeCreatedDisplay    = "can be created";
    public String holdsExpectedValues    = "testHoldsExpectedValues";
    public String holdsExpectedDisplay   = "holds expected values";
    public String isValid                = "testIsValid";
    public String isValidDisplay         = "is valid (no constraint violations)";

    // ── Level 3 — Member ──────────────────────────────────────────────────
    public String memberClassName        = "{ClassName}{MemberName}Test";
    public String memberDisplay          = "{memberName}";
    public String acceptsMethod          = "testAccepts{MemberName}";
    public String acceptsDisplay         = "accepts valid value";
    public String rejectsMethod          = "testRejects{MemberName}";
    public String rejectsDisplay         = "rejects invalid value";
    public String validDataSource        = "valid{MemberName}s";
    public String invalidDataSource      = "invalid{MemberName}s";

    // ── Level 4 — Method ──────────────────────────────────────────────────
    public String methodClassName              = "{ClassName}Method{MethodName}Test";
    public String methodDisplay                = "{methodName}()";
    public String testExecutesWithoutException = "testExecutesWithoutException";
    public String testObjectRemainsValid       = "testObjectRemainsValidAfterExecution";
    public String testReturnsResult            = "testReturnsResult";
    public String testReturnsConsistentResult  = "testReturnsConsistentResult";
    public String testAcceptsValidInput        = "testAcceptsValidInput";
    public String testRejectsInvalidInput      = "testRejectsInvalidInput";
    public String validInputsSource            = "valid{MethodName}Inputs";
    public String invalidInputsSource          = "invalid{MethodName}Inputs";

    // ── Level 5 — Scenario ────────────────────────────────────────────────
    public String scenarioClassName      = "{ClassName}ScenarioTest";
    public String scenarioDisplay        = "{ClassName} scenarios";

    // ── Default singleton ─────────────────────────────────────────────────
    public static final TestKitTemplate DEFAULT = new TestKitTemplate();

    /** Start from the defaults and change what you need: {@code TestKitTemplate.builder().testSuffix("IT").build()}. */
    public static Builder builder() {
        return new Builder();
    }

    /** Fluent construction for naming conventions. Unset values keep their defaults. */
    public static final class Builder {
        private final TestKitTemplate template = new TestKitTemplate();

        /** {@code "Test"} (default), {@code "IT"}, {@code "Spec"}, … */
        public Builder testSuffix(String suffix) {
            if (suffix == null || suffix.isEmpty() || !Character.isJavaIdentifierStart(suffix.charAt(0))) {
                throw new IllegalArgumentException("testSuffix must be a Java identifier, got: " + suffix);
            }
            Affix.check("testSuffix", suffix, false);
            template.testSuffix = suffix;
            return this;
        }

        /** Text before every test class name: {@code "Unit"} → {@code UnitCircleTest}. Empty is allowed. */
        public Builder classPrefix(String prefix) {
            Affix.check("classPrefix", prefix, true);
            template.classPrefix = prefix;
            return this;
        }

        /** Text before every test method name: {@code "given"} → {@code giventestRejectsX}. Empty is allowed. */
        public Builder methodPrefix(String prefix) {
            Affix.check("methodPrefix", prefix, true);
            template.methodPrefix = prefix;
            return this;
        }

        /** Text after every test method name: {@code "_IT"} → {@code testRejectsX_IT}. Empty is allowed. */
        public Builder methodSuffix(String suffix) {
            Affix.check("methodSuffix", suffix, true);
            template.methodSuffix = suffix;
            return this;
        }

        public Builder validatorField(String name) {
            template.validatorField = name;
            return this;
        }

        public TestKitTemplate build() {
            TestKitTemplate copy = new TestKitTemplate();
            copy.testSuffix = template.testSuffix;
            copy.classPrefix = template.classPrefix;
            copy.methodPrefix = template.methodPrefix;
            copy.methodSuffix = template.methodSuffix;
            copy.validatorField = template.validatorField;
            return copy;
        }
    }

    // ── Token resolution ──────────────────────────────────────────────────

    public String resolve(String template, String className, String memberName) {
        return Tokens.resolve(template,
                "ClassName",  className,
                "className",  Texts.decapitalize(className),
                "MemberName", Texts.capitalize(memberName),
                "memberName", memberName);
    }

    public String resolveMethod(String template, String className, String methodName) {
        return Tokens.resolve(template,
                "ClassName",  className,
                "className",  Texts.decapitalize(className),
                "MethodName", Texts.capitalize(methodName),
                "methodName", methodName);
    }

}