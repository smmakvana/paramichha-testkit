package com.paramichha.testkit.examples;

import com.paramichha.utils.DataValidation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("SampleService")
class SampleServiceTest {

  private static Validator validator;
  SampleService sampleService = new SampleService("Daina Goodwin", 1, new BigDecimal("1.50"));

  @BeforeAll
  static void setUpValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Nested
  @DisplayName("Creation")
  class SampleServiceCreationTest {

    @Test
    @DisplayName("can be created")
    void testCanBeCreated() {
      assertThat(sampleService).isNotNull();
    }

    @Test
    @DisplayName("holds expected values")
    void testHoldsExpectedValues() {
      assertThat(sampleService.getName()).isEqualTo("Daina Goodwin");
      assertThat(sampleService.getQuantity()).isEqualTo(1);
      assertThat(sampleService.getPrice()).isEqualTo(new BigDecimal("1.50"));
    }

    @Test
    @DisplayName("is valid (no constraint violations)")
    void testIsValid() {
      assertThat(validator.validate(sampleService)).isEmpty();
    }
  }

  @Nested
  @DisplayName("name")
  class SampleServiceNameTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validNames")
    @DisplayName("accepts valid value")
    void testAcceptsName(String description, String name) {
      SampleService obj = new SampleService(name, 1, new BigDecimal("1.50"));
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validNames() {
      return Stream.of(Arguments.of("name 0", "Daina Goodwin"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidNames")
    @DisplayName("rejects invalid value")
    void testRejectsName(String description, String name) {
      try {
        SampleService obj = new SampleService(name, 1, new BigDecimal("1.50"));
        assertThat(validator.validate(obj)).isNotEmpty();
      } catch (Exception ignored) {
        // Constructor enforced the contract — test passes
      }
    }

    static Stream<Arguments> invalidNames() {
      return Stream.of(Arguments.of("null name", (String) null), Arguments.of("blank name", ""));
    }
  }

  @Nested
  @DisplayName("quantity")
  class SampleServiceQuantityTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validQuantities")
    @DisplayName("accepts valid value")
    void testAcceptsQuantity(String description, int quantity) {
      SampleService obj = new SampleService("Daina Goodwin", quantity, new BigDecimal("1.50"));
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validQuantities() {
      return Stream.of(
          Arguments.of("valid quantity 1", 1),
          Arguments.of("valid quantity 2", 1),
          Arguments.of("valid quantity 3", 2),
          Arguments.of("valid quantity 4", 50),
          Arguments.of("valid quantity 5", 99),
          Arguments.of("valid quantity 6", 100));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidQuantities")
    @DisplayName("rejects invalid value")
    void testRejectsQuantity(String description, int quantity) {
      try {
        SampleService obj = new SampleService("Daina Goodwin", quantity, new BigDecimal("1.50"));
        assertThat(validator.validate(obj)).isNotEmpty();
      } catch (Exception ignored) {
        // Constructor enforced the contract — test passes
      }
    }

    static Stream<Arguments> invalidQuantities() {
      return Stream.of(Arguments.of("no constraints defined — add some!", 0));
    }
  }

  @Nested
  @DisplayName("price")
  class SampleServicePriceTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validPrices")
    @DisplayName("accepts valid value")
    void testAcceptsPrice(String description, BigDecimal price) {
      SampleService obj = new SampleService("Daina Goodwin", 1, price);
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validPrices() {
      return Stream.of(
          Arguments.of("valid price 1", new BigDecimal("1.50")),
          Arguments.of("valid price 2", new BigDecimal("1.00")),
          Arguments.of("valid price 3", new BigDecimal("2.00")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidPrices")
    @DisplayName("rejects invalid value")
    void testRejectsPrice(String description, BigDecimal price) {
      try {
        SampleService obj = new SampleService("Daina Goodwin", 1, price);
        assertThat(validator.validate(obj)).isNotEmpty();
      } catch (Exception ignored) {
        // Constructor enforced the contract — test passes
      }
    }

    static Stream<Arguments> invalidPrices() {
      return Stream.of(
          Arguments.of("null price", (BigDecimal) null),
          Arguments.of("invalid price 2", new BigDecimal("0")));
    }
  }

  @Nested
  @DisplayName("applyDiscount()")
  class SampleServiceMethodApplyDiscountTest {

    private record ApplyDiscountResult(@NotNull @Positive BigDecimal value) {}
    ;

    @ParameterizedTest(name = "{0}")
    @MethodSource("validApplyDiscountInputs")
    @DisplayName("applyDiscount accepts valid input")
    void testAcceptsValidInput(String description, BigDecimal arg0) {
      BigDecimal result = sampleService.applyDiscount(arg0);
      DataValidation.notNull(result);
      DataValidation.positive(result);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidApplyDiscountInputs")
    @DisplayName("applyDiscount rejects invalid input")
    void testRejectsInvalidInput(String description, BigDecimal arg0) {
      // Enforce @DecimalMin: DataValidation.decimalMin("0.0", arg0);
      // Enforce @DecimalMax: DataValidation.decimalMax("1.0", arg0);
      assertThatThrownBy(() -> sampleService.applyDiscount(arg0)).isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validApplyDiscountInputs() {
      return Stream.of(Arguments.of("valid applyDiscount", new BigDecimal("0.50")));
    }

    static Stream<Arguments> invalidApplyDiscountInputs() {
      return Stream.of(
          Arguments.of("invalid arg0", new BigDecimal("-1")),
          Arguments.of("invalid arg0", new BigDecimal("2")));
    }
  }

  @Nested
  @DisplayName("combine()")
  class SampleServiceMethodCombineTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCombineInputs")
    @DisplayName("combine accepts valid input")
    void testAcceptsValidInput(String description, int arg0, int arg1) {
      int result = sampleService.combine(arg0, arg1);
    }

    static Stream<Arguments> validCombineInputs() {
      return Stream.of(Arguments.of("valid combine", 0, 0));
    }
  }

  @Nested
  @DisplayName("describe()")
  class SampleServiceMethodDescribeTest {

    private record DescribeResult(@NotBlank String value) {}
    ;

    @ParameterizedTest(name = "{0}")
    @MethodSource("validDescribeInputs")
    @DisplayName("describe accepts valid input")
    void testAcceptsValidInput(String description, String arg0) {
      String result = sampleService.describe(arg0);
      DataValidation.notBlank(result);
    }

    static Stream<Arguments> validDescribeInputs() {
      return Stream.of(Arguments.of("valid describe", (String) null));
    }
  }

  @Nested
  @DisplayName("encode()")
  class SampleServiceMethodEncodeTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validEncodeInputs")
    @DisplayName("encode accepts valid input")
    void testAcceptsValidInput(String description, String arg0) {
      String result = sampleService.encode(arg0);
      assertThat(result).isNotNull();
    }

    static Stream<Arguments> validEncodeInputs() {
      return Stream.of(Arguments.of("valid encode", (String) null));
    }
  }

  @Nested
  @DisplayName("formatName()")
  class SampleServiceMethodFormatNameTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFormatNameInputs")
    @DisplayName("formatName accepts valid input")
    void testAcceptsValidInput(String description, String arg0) {
      String result = sampleService.formatName(arg0);
      assertThat(result).isNotNull();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidFormatNameInputs")
    @DisplayName("formatName rejects invalid input")
    void testRejectsInvalidInput(String description, String arg0) {
      // Enforce @NotBlank: DataValidation.notBlank(arg0);
      assertThatThrownBy(() -> sampleService.formatName(arg0)).isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validFormatNameInputs() {
      return Stream.of(Arguments.of("valid formatName", "natus"));
    }

    static Stream<Arguments> invalidFormatNameInputs() {
      return Stream.of(
          Arguments.of("invalid arg0", (String) null), Arguments.of("invalid arg0", ""));
    }
  }

  @Nested
  @DisplayName("merge()")
  class SampleServiceMethodMergeTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validMergeInputs")
    @DisplayName("merge accepts valid input")
    void testAcceptsValidInput(String description, SampleService arg0, String arg1) {
      SampleService result = sampleService.merge(arg0, arg1);
      assertThat(validator.validate(result)).isEmpty();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidMergeInputs")
    @DisplayName("merge rejects invalid input")
    void testRejectsInvalidInput(String description, SampleService arg0, String arg1) {
      // Enforce @NotNull: DataValidation.notNull(arg0);
      // Enforce @NotBlank: DataValidation.notBlank(arg1);
      assertThatThrownBy(() -> sampleService.merge(arg0, arg1)).isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validMergeInputs() {
      return Stream.of(Arguments.of("valid merge", (SampleService) null, "optio"));
    }

    static Stream<Arguments> invalidMergeInputs() {
      return Stream.of(
          Arguments.of("invalid arg0", (SampleService) null, "optio"),
          Arguments.of("invalid arg1", (SampleService) null, (String) null),
          Arguments.of("invalid arg1", (SampleService) null, ""));
    }
  }

  @Nested
  @DisplayName("multiply()")
  class SampleServiceMethodMultiplyTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validMultiplyInputs")
    @DisplayName("multiply accepts valid input")
    void testAcceptsValidInput(String description, int arg0) {
      int result = sampleService.multiply(arg0);
    }

    static Stream<Arguments> validMultiplyInputs() {
      return Stream.of(Arguments.of("valid multiply", 0));
    }
  }

  @Nested
  @DisplayName("reset()")
  class SampleServiceMethodResetTest {

    @Test
    @DisplayName("executes without exception")
    void testExecutesWithoutException() {
      assertThatCode(() -> sampleService.reset()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("object remains valid after execution")
    void testObjectRemainsValidAfterExecution() {
      sampleService.reset();
      assertThat(validator.validate(sampleService)).isEmpty();
    }
  }

  @Nested
  @DisplayName("swap()")
  class SampleServiceMethodSwapTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validSwapInputs")
    @DisplayName("swap accepts valid input")
    void testAcceptsValidInput(String description, String arg0, String arg1) {
      assertThatCode(() -> sampleService.swap(arg0, arg1)).doesNotThrowAnyException();
    }

    static Stream<Arguments> validSwapInputs() {
      return Stream.of(Arguments.of("valid swap", (String) null, (String) null));
    }
  }

  @Nested
  @DisplayName("transferTo()")
  class SampleServiceMethodTransferToTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validTransferToInputs")
    @DisplayName("transferTo accepts valid input")
    void testAcceptsValidInput(String description, SampleService arg0, int arg1) {
      assertThatCode(() -> sampleService.transferTo(arg0, arg1)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidTransferToInputs")
    @DisplayName("transferTo rejects invalid input")
    void testRejectsInvalidInput(String description, SampleService arg0, int arg1) {
      // Enforce @NotNull: DataValidation.notNull(arg0);
      assertThatThrownBy(() -> sampleService.transferTo(arg0, arg1)).isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validTransferToInputs() {
      return Stream.of(Arguments.of("valid transferTo", (SampleService) null, 0));
    }

    static Stream<Arguments> invalidTransferToInputs() {
      return Stream.of(Arguments.of("invalid arg0", (SampleService) null, 0));
    }
  }
}
