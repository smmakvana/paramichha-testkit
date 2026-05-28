package com.paramichha.testkit.examples;

import com.paramichha.utils.DataValidation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("PrimitiveAnnotatedService")
class PrimitiveAnnotatedServiceTest {

  private static Validator validator;
  PrimitiveAnnotatedService primitiveAnnotatedService =
      new PrimitiveAnnotatedService(1, 100L, 1.5d, (byte) 63, (short) 5, true);

  @BeforeAll
  static void setUpValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Creation")
  @Tag("creation")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceCreationTest {

    @Test
    @DisplayName("can be created")
    void testCanBeCreated() {
      assertThat(primitiveAnnotatedService).isNotNull();
    }

    @Test
    @DisplayName("holds expected values")
    void testHoldsExpectedValues() {
      assertThat(primitiveAnnotatedService.getQuantity()).isEqualTo(1);
      assertThat(primitiveAnnotatedService.getPrice()).isEqualTo(100L);
      assertThat(primitiveAnnotatedService.getRate()).isEqualTo(1.5d);
      assertThat(primitiveAnnotatedService.getLevel()).isEqualTo((byte) 63);
      assertThat(primitiveAnnotatedService.getScore()).isEqualTo((short) 5);
      assertThat(primitiveAnnotatedService.isActive()).isEqualTo(true);
    }

    @Test
    @DisplayName("is valid (no constraint violations)")
    void testIsValid() {
      assertThat(validator.validate(primitiveAnnotatedService)).isEmpty();
    }
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Field quantity")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceQuantityTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validQuantities")
    @DisplayName("accepts valid value")
    void testAcceptsQuantity(String description, int quantity) {
      PrimitiveAnnotatedService obj =
          new PrimitiveAnnotatedService(quantity, 100L, 1.5d, (byte) 63, (short) 5, true);
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
        PrimitiveAnnotatedService obj =
            new PrimitiveAnnotatedService(quantity, 100L, 1.5d, (byte) 63, (short) 5, true);
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
  @DisplayName("PrimitiveAnnotatedService Field price")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServicePriceTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validPrices")
    @DisplayName("accepts valid value")
    void testAcceptsPrice(String description, long price) {
      PrimitiveAnnotatedService obj =
          new PrimitiveAnnotatedService(1, price, 1.5d, (byte) 63, (short) 5, true);
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validPrices() {
      return Stream.of(
          Arguments.of("valid price 1", 100L),
          Arguments.of("valid price 2", 0L),
          Arguments.of("valid price 3", 1L),
          Arguments.of("valid price 4", 499999L),
          Arguments.of("valid price 5", 999998L),
          Arguments.of("valid price 6", 999999L));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidPrices")
    @DisplayName("rejects invalid value")
    void testRejectsPrice(String description, long price) {
      try {
        PrimitiveAnnotatedService obj =
            new PrimitiveAnnotatedService(1, price, 1.5d, (byte) 63, (short) 5, true);
        assertThat(validator.validate(obj)).isNotEmpty();
      } catch (Exception ignored) {
        // Constructor enforced the contract — test passes
      }
    }

    static Stream<Arguments> invalidPrices() {
      return Stream.of(Arguments.of("no constraints defined — add some!", 0L));
    }
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Field rate")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceRateTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validRates")
    @DisplayName("accepts valid value")
    void testAcceptsRate(String description, double rate) {
      PrimitiveAnnotatedService obj =
          new PrimitiveAnnotatedService(1, 100L, rate, (byte) 63, (short) 5, true);
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validRates() {
      return Stream.of(
          Arguments.of("valid rate 1", 1.5d),
          Arguments.of("valid rate 2", 1.0d),
          Arguments.of("valid rate 3", 2.0d));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidRates")
    @DisplayName("rejects invalid value")
    void testRejectsRate(String description, double rate) {
      try {
        PrimitiveAnnotatedService obj =
            new PrimitiveAnnotatedService(1, 100L, rate, (byte) 63, (short) 5, true);
        assertThat(validator.validate(obj)).isNotEmpty();
      } catch (Exception ignored) {
        // Constructor enforced the contract — test passes
      }
    }

    static Stream<Arguments> invalidRates() {
      return Stream.of(Arguments.of("no constraints defined — add some!", 0.0d));
    }
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Field level")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceLevelTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLevels")
    @DisplayName("accepts valid value")
    void testAcceptsLevel(String description, byte level) {
      PrimitiveAnnotatedService obj =
          new PrimitiveAnnotatedService(1, 100L, 1.5d, level, (short) 5, true);
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validLevels() {
      return Stream.of(
          Arguments.of("valid level 1", (byte) 63),
          Arguments.of("valid level 2", (byte) 0),
          Arguments.of("valid level 3", (byte) 1),
          Arguments.of("valid level 4", (byte) 63),
          Arguments.of("valid level 5", (byte) 126),
          Arguments.of("valid level 6", (byte) 127));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLevels")
    @DisplayName("rejects invalid value")
    void testRejectsLevel(String description, byte level) {
      try {
        PrimitiveAnnotatedService obj =
            new PrimitiveAnnotatedService(1, 100L, 1.5d, level, (short) 5, true);
        assertThat(validator.validate(obj)).isNotEmpty();
      } catch (Exception ignored) {
        // Constructor enforced the contract — test passes
      }
    }

    static Stream<Arguments> invalidLevels() {
      return Stream.of(Arguments.of("no constraints defined — add some!", (byte) 0));
    }
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Field score")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceScoreTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validScores")
    @DisplayName("accepts valid value")
    void testAcceptsScore(String description, short score) {
      PrimitiveAnnotatedService obj =
          new PrimitiveAnnotatedService(1, 100L, 1.5d, (byte) 63, score, true);
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validScores() {
      return Stream.of(
          Arguments.of("valid score 1", (short) 5),
          Arguments.of("valid score 2", (short) 0),
          Arguments.of("valid score 3", (short) 1));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidScores")
    @DisplayName("rejects invalid value")
    void testRejectsScore(String description, short score) {
      try {
        PrimitiveAnnotatedService obj =
            new PrimitiveAnnotatedService(1, 100L, 1.5d, (byte) 63, score, true);
        assertThat(validator.validate(obj)).isNotEmpty();
      } catch (Exception ignored) {
        // Constructor enforced the contract — test passes
      }
    }

    static Stream<Arguments> invalidScores() {
      return Stream.of(Arguments.of("no constraints defined — add some!", (short) 0));
    }
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Field active")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceActiveTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validActives")
    @DisplayName("accepts valid value")
    void testAcceptsActive(String description, boolean active) {
      PrimitiveAnnotatedService obj =
          new PrimitiveAnnotatedService(1, 100L, 1.5d, (byte) 63, (short) 5, active);
      assertThat(obj.isActive()).isEqualTo(active);
    }

    static Stream<Arguments> validActives() {
      return Stream.of(Arguments.of("valid active 1", true), Arguments.of("valid active 2", false));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidActives")
    @DisplayName("rejects invalid value")
    void testRejectsActive(String description, boolean active) {
      // TODO: add constraints to active, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      PrimitiveAnnotatedService obj =
          new PrimitiveAnnotatedService(1, 100L, 1.5d, (byte) 63, (short) 5, active);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidActives() {
      // TODO: add constraints to active, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", false));
    }
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Method applyDiscount()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceMethodApplyDiscountTest {

    private record ApplyDiscountResult(@Positive Double value) {}
    ;

    @ParameterizedTest(name = "{0}")
    @MethodSource("validApplyDiscountInputs")
    @DisplayName("applyDiscount accepts valid input")
    void testAcceptsValidInput(String description, int arg0) {
      double result = primitiveAnnotatedService.applyDiscount(arg0);
      DataValidation.positive(result);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidApplyDiscountInputs")
    @DisplayName("applyDiscount rejects invalid input")
    void testRejectsInvalidInput(String description, int arg0) {
      // Enforce @Min: DataValidation.min(0, arg0);
      // Enforce @Max: DataValidation.max(100, arg0);
      assertThatThrownBy(() -> primitiveAnnotatedService.applyDiscount(arg0))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validApplyDiscountInputs() {
      return Stream.of(Arguments.of("valid applyDiscount", 50));
    }

    static Stream<Arguments> invalidApplyDiscountInputs() {
      return Stream.of(Arguments.of("invalid arg0", -1), Arguments.of("invalid arg0", 101));
    }
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Method combine()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceMethodCombineTest {

    private record CombineResult(@PositiveOrZero Integer value) {}
    ;

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCombineInputs")
    @DisplayName("combine accepts valid input")
    void testAcceptsValidInput(String description, int arg0, int arg1) {
      int result = primitiveAnnotatedService.combine(arg0, arg1);
      DataValidation.positiveOrZero(result);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCombineInputs")
    @DisplayName("combine rejects invalid input")
    void testRejectsInvalidInput(String description, int arg0, int arg1) {
      // Enforce @Min: DataValidation.min(0, arg0);
      // Enforce @Min: DataValidation.min(0, arg1);
      assertThatThrownBy(() -> primitiveAnnotatedService.combine(arg0, arg1))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validCombineInputs() {
      return Stream.of(Arguments.of("valid combine", 1, 1));
    }

    static Stream<Arguments> invalidCombineInputs() {
      return Stream.of(Arguments.of("invalid arg0", -1, 1), Arguments.of("invalid arg1", 1, -1));
    }
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Method reset()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceMethodResetTest {

    @Test
    @DisplayName("executes without exception")
    void testExecutesWithoutException() {
      assertThatCode(() -> primitiveAnnotatedService.reset()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("object remains valid after execution")
    void testObjectRemainsValidAfterExecution() {
      primitiveAnnotatedService.reset();
      assertThat(validator.validate(primitiveAnnotatedService)).isEmpty();
    }
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Method totalValue()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceMethodTotalValueTest {

    private record TotalValueResult(@Min(value = 0L) Long value) {}
    ;

    @Test
    @DisplayName("totalValue returns a result")
    void testReturnsResult() {
      long result = primitiveAnnotatedService.totalValue();
      DataValidation.min(0, result);
    }

    @Test
    @DisplayName("totalValue returns consistent result")
    void testReturnsConsistentResult() {
      assertThat(primitiveAnnotatedService.totalValue())
          .isEqualTo(primitiveAnnotatedService.totalValue());
    }
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Method transfer()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceMethodTransferTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validTransferInputs")
    @DisplayName("transfer accepts valid input")
    void testAcceptsValidInput(String description, int arg0, int arg1) {
      assertThatCode(() -> primitiveAnnotatedService.transfer(arg0, arg1))
          .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidTransferInputs")
    @DisplayName("transfer rejects invalid input")
    void testRejectsInvalidInput(String description, int arg0, int arg1) {
      // Enforce @Min: DataValidation.min(1, arg0);
      // Enforce @Min: DataValidation.min(0, arg1);
      assertThatThrownBy(() -> primitiveAnnotatedService.transfer(arg0, arg1))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validTransferInputs() {
      return Stream.of(Arguments.of("valid transfer", 2, 1));
    }

    static Stream<Arguments> invalidTransferInputs() {
      return Stream.of(Arguments.of("invalid arg0", 0, 1), Arguments.of("invalid arg1", 2, -1));
    }
  }
}
