package com.paramichha.testkit.examples;

import com.paramichha.utils.DataValidation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Positive;
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

@DisplayName("PrimitiveMixedService")
class PrimitiveMixedServiceTest {

  private static Validator validator;
  PrimitiveMixedService primitiveMixedService =
      new PrimitiveMixedService(1, 100L, 1.5d, (byte) 127, true);

  @BeforeAll
  static void setUpValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Nested
  @DisplayName("PrimitiveMixedService Creation")
  @Tag("creation")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveMixedServiceCreationTest {

    @Test
    @DisplayName("can be created")
    void testCanBeCreated() {
      assertThat(primitiveMixedService).isNotNull();
    }

    @Test
    @DisplayName("holds expected values")
    void testHoldsExpectedValues() {
      assertThat(primitiveMixedService.getQuantity()).isEqualTo(1);
      assertThat(primitiveMixedService.getPrice()).isEqualTo(100L);
      assertThat(primitiveMixedService.getRate()).isEqualTo(1.5d);
      assertThat(primitiveMixedService.getLevel()).isEqualTo((byte) 127);
      assertThat(primitiveMixedService.isActive()).isEqualTo(true);
    }

    @Test
    @DisplayName("is valid (no constraint violations)")
    void testIsValid() {
      assertThat(validator.validate(primitiveMixedService)).isEmpty();
    }
  }

  @Nested
  @DisplayName("PrimitiveMixedService Field quantity")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveMixedServiceQuantityTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validQuantities")
    @DisplayName("accepts valid value")
    void testAcceptsQuantity(String description, int quantity) {
      PrimitiveMixedService obj = new PrimitiveMixedService(quantity, 100L, 1.5d, (byte) 127, true);
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validQuantities() {
      return Stream.of(
          Arguments.of("valid quantity 1", 1),
          Arguments.of("valid quantity 2", 1),
          Arguments.of("valid quantity 3", 2),
          Arguments.of("valid quantity 4", 500),
          Arguments.of("valid quantity 5", 999),
          Arguments.of("valid quantity 6", 1000));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidQuantities")
    @DisplayName("rejects invalid value")
    void testRejectsQuantity(String description, int quantity) {
      try {
        PrimitiveMixedService obj =
            new PrimitiveMixedService(quantity, 100L, 1.5d, (byte) 127, true);
        assertThat(validator.validate(obj)).isNotEmpty();
      } catch (Exception ignored) {
        // Constructor enforced the contract — test passes
      }
    }

    static Stream<Arguments> invalidQuantities() {
      return Stream.of(
          Arguments.of("invalid quantity 1", 0), Arguments.of("invalid quantity 2", 1001));
    }
  }

  @Nested
  @DisplayName("PrimitiveMixedService Field price")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveMixedServicePriceTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validPrices")
    @DisplayName("accepts valid value")
    void testAcceptsPrice(String description, long price) {
      PrimitiveMixedService obj = new PrimitiveMixedService(1, price, 1.5d, (byte) 127, true);
      assertThat(obj.getPrice()).isEqualTo(price);
    }

    static Stream<Arguments> validPrices() {
      return Stream.of(
          Arguments.of("valid price 1", 100L),
          Arguments.of("valid price 2", -9223372036854775808L),
          Arguments.of("valid price 3", -9223372036854775807L),
          Arguments.of("valid price 4", 0L),
          Arguments.of("valid price 5", 9223372036854775806L),
          Arguments.of("valid price 6", 9223372036854775807L));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidPrices")
    @DisplayName("rejects invalid value")
    void testRejectsPrice(String description, long price) {
      // TODO: add constraints to price, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      PrimitiveMixedService obj = new PrimitiveMixedService(1, price, 1.5d, (byte) 127, true);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidPrices() {
      // TODO: add constraints to price, or delete this test
      return Stream.of(Arguments.of("invalid price 1", -1L));
    }
  }

  @Nested
  @DisplayName("PrimitiveMixedService Field rate")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveMixedServiceRateTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validRates")
    @DisplayName("accepts valid value")
    void testAcceptsRate(String description, double rate) {
      PrimitiveMixedService obj = new PrimitiveMixedService(1, 100L, rate, (byte) 127, true);
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
        PrimitiveMixedService obj = new PrimitiveMixedService(1, 100L, rate, (byte) 127, true);
        assertThat(validator.validate(obj)).isNotEmpty();
      } catch (Exception ignored) {
        // Constructor enforced the contract — test passes
      }
    }

    static Stream<Arguments> invalidRates() {
      return Stream.of(Arguments.of("invalid rate 1", 0.0d));
    }
  }

  @Nested
  @DisplayName("PrimitiveMixedService Field level")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveMixedServiceLevelTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLevels")
    @DisplayName("accepts valid value")
    void testAcceptsLevel(String description, byte level) {
      PrimitiveMixedService obj = new PrimitiveMixedService(1, 100L, 1.5d, level, true);
      assertThat(obj.getLevel()).isEqualTo(level);
    }

    static Stream<Arguments> validLevels() {
      return Stream.of(
          Arguments.of("valid level 1", (byte) 127),
          Arguments.of("valid level 2", (byte) -128),
          Arguments.of("valid level 3", (byte) -127),
          Arguments.of("valid level 4", (byte) 0),
          Arguments.of("valid level 5", (byte) 126),
          Arguments.of("valid level 6", (byte) 127));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLevels")
    @DisplayName("rejects invalid value")
    void testRejectsLevel(String description, byte level) {
      // TODO: add constraints to level, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      PrimitiveMixedService obj = new PrimitiveMixedService(1, 100L, 1.5d, level, true);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidLevels() {
      // TODO: add constraints to level, or delete this test
      return Stream.of(Arguments.of("invalid level 1", (byte) -1));
    }
  }

  @Nested
  @DisplayName("PrimitiveMixedService Field active")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveMixedServiceActiveTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validActives")
    @DisplayName("accepts valid value")
    void testAcceptsActive(String description, boolean active) {
      PrimitiveMixedService obj = new PrimitiveMixedService(1, 100L, 1.5d, (byte) 127, active);
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
      PrimitiveMixedService obj = new PrimitiveMixedService(1, 100L, 1.5d, (byte) 127, active);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidActives() {
      // TODO: add constraints to active, or delete this test
      return Stream.of(Arguments.of("invalid active 1", false));
    }
  }

  @Nested
  @DisplayName("PrimitiveMixedService Method applyDiscount()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveMixedServiceMethodApplyDiscountTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validApplyDiscountInputs")
    @DisplayName("applyDiscount accepts valid input")
    void testAcceptsValidInput(String description, int arg0) {
      double result = primitiveMixedService.applyDiscount(arg0);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidApplyDiscountInputs")
    @DisplayName("applyDiscount rejects invalid input")
    void testRejectsInvalidInput(String description, int arg0) {
      // Enforce @Min: DataValidation.min(0, arg0);
      // Enforce @Max: DataValidation.max(100, arg0);
      assertThatThrownBy(() -> primitiveMixedService.applyDiscount(arg0))
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
  @DisplayName("PrimitiveMixedService Method combine()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveMixedServiceMethodCombineTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCombineInputs")
    @DisplayName("combine accepts valid input")
    void testAcceptsValidInput(String description, int arg0, int arg1) {
      int result = primitiveMixedService.combine(arg0, arg1);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCombineInputs")
    @DisplayName("combine rejects invalid input")
    void testRejectsInvalidInput(String description, int arg0, int arg1) {
      // Enforce @Min: DataValidation.min(0, arg0);
      assertThatThrownBy(() -> primitiveMixedService.combine(arg0, arg1))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validCombineInputs() {
      return Stream.of(Arguments.of("valid combine", 1, 0));
    }

    static Stream<Arguments> invalidCombineInputs() {
      return Stream.of(Arguments.of("invalid arg0", -1, 0));
    }
  }

  @Nested
  @DisplayName("PrimitiveMixedService Method reset()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveMixedServiceMethodResetTest {

    @Test
    @DisplayName("executes without exception")
    void testExecutesWithoutException() {
      assertThatCode(() -> primitiveMixedService.reset()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("object remains valid after execution")
    void testObjectRemainsValidAfterExecution() {
      primitiveMixedService.reset();
      assertThat(validator.validate(primitiveMixedService)).isEmpty();
    }
  }

  @Nested
  @DisplayName("PrimitiveMixedService Method scaledRate()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveMixedServiceMethodScaledRateTest {

    private record ScaledRateResult(@Positive Double value) {}
    ;

    @ParameterizedTest(name = "{0}")
    @MethodSource("validScaledRateInputs")
    @DisplayName("scaledRate accepts valid input")
    void testAcceptsValidInput(String description, int arg0) {
      double result = primitiveMixedService.scaledRate(arg0);
      DataValidation.positive(result);
    }

    static Stream<Arguments> validScaledRateInputs() {
      return Stream.of(Arguments.of("valid scaledRate", 0));
    }
  }

  @Nested
  @DisplayName("PrimitiveMixedService Method transfer()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveMixedServiceMethodTransferTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validTransferInputs")
    @DisplayName("transfer accepts valid input")
    void testAcceptsValidInput(String description, int arg0, int arg1) {
      assertThatCode(() -> primitiveMixedService.transfer(arg0, arg1)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidTransferInputs")
    @DisplayName("transfer rejects invalid input")
    void testRejectsInvalidInput(String description, int arg0, int arg1) {
      // Enforce @Min: DataValidation.min(1, arg0);
      assertThatThrownBy(() -> primitiveMixedService.transfer(arg0, arg1))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validTransferInputs() {
      return Stream.of(Arguments.of("valid transfer", 2, 0));
    }

    static Stream<Arguments> invalidTransferInputs() {
      return Stream.of(Arguments.of("invalid arg0", 0, 0));
    }
  }
}
