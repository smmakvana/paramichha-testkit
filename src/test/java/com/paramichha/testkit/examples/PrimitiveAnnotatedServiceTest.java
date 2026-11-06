package com.paramichha.testkit.examples;

import com.paramichha.utils.DataValidation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
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

    @Test
    @DisplayName("constructor arguments are valid")
    void testConstructorArgumentsAreValid() {
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(
                              PrimitiveAnnotatedService.class.getDeclaredConstructors())
                          .filter(
                              c ->
                                  java.util.Arrays.equals(
                                      c.getParameterTypes(),
                                      new Class<?>[] {
                                        int.class,
                                        long.class,
                                        double.class,
                                        byte.class,
                                        short.class,
                                        boolean.class
                                      }))
                          .findFirst()
                          .orElseThrow(),
                      new Object[] {1, 100L, 1.5d, (byte) 63, (short) 5, true}))
          .isEmpty();
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
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(
                              PrimitiveAnnotatedService.class.getDeclaredConstructors())
                          .filter(
                              c ->
                                  java.util.Arrays.equals(
                                      c.getParameterTypes(),
                                      new Class<?>[] {
                                        int.class,
                                        long.class,
                                        double.class,
                                        byte.class,
                                        short.class,
                                        boolean.class
                                      }))
                          .findFirst()
                          .orElseThrow(),
                      new Object[] {quantity, 100L, 1.5d, (byte) 63, (short) 5, true}))
          .isEmpty();
      PrimitiveAnnotatedService obj =
          new PrimitiveAnnotatedService(quantity, 100L, 1.5d, (byte) 63, (short) 5, true);
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validQuantities() {
      return Stream.of(
          Arguments.of("valid quantity 1", 1),
          Arguments.of("valid quantity 2", 2),
          Arguments.of("valid quantity 3", 50),
          Arguments.of("valid quantity 4", 99),
          Arguments.of("valid quantity 5", 100));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidQuantities")
    @DisplayName("rejects invalid value")
    void testRejectsQuantity(String description, int quantity) {
      var violations =
          validator
              .forExecutables()
              .validateConstructorParameters(
                  java.util.Arrays.stream(PrimitiveAnnotatedService.class.getDeclaredConstructors())
                      .filter(
                          c ->
                              java.util.Arrays.equals(
                                  c.getParameterTypes(),
                                  new Class<?>[] {
                                    int.class,
                                    long.class,
                                    double.class,
                                    byte.class,
                                    short.class,
                                    boolean.class
                                  }))
                      .findFirst()
                      .orElseThrow(),
                  new Object[] {quantity, 100L, 1.5d, (byte) 63, (short) 5, true});
      // the violation must be on parameter 'quantity', not on the other (valid) ones
      assertThat(violations)
          .anyMatch(
              v ->
                  v.getPropertyPath().toString().endsWith(".quantity")
                      || v.getPropertyPath().toString().endsWith(".arg0"));
    }

    static Stream<Arguments> invalidQuantities() {
      return Stream.of(
          Arguments.of("invalid quantity 1", 0),
          Arguments.of("invalid quantity 2", 101),
          Arguments.of("invalid quantity 3", -2147483648),
          Arguments.of("invalid quantity 4", 2147483647));
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
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(
                              PrimitiveAnnotatedService.class.getDeclaredConstructors())
                          .filter(
                              c ->
                                  java.util.Arrays.equals(
                                      c.getParameterTypes(),
                                      new Class<?>[] {
                                        int.class,
                                        long.class,
                                        double.class,
                                        byte.class,
                                        short.class,
                                        boolean.class
                                      }))
                          .findFirst()
                          .orElseThrow(),
                      new Object[] {1, price, 1.5d, (byte) 63, (short) 5, true}))
          .isEmpty();
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
      var violations =
          validator
              .forExecutables()
              .validateConstructorParameters(
                  java.util.Arrays.stream(PrimitiveAnnotatedService.class.getDeclaredConstructors())
                      .filter(
                          c ->
                              java.util.Arrays.equals(
                                  c.getParameterTypes(),
                                  new Class<?>[] {
                                    int.class,
                                    long.class,
                                    double.class,
                                    byte.class,
                                    short.class,
                                    boolean.class
                                  }))
                      .findFirst()
                      .orElseThrow(),
                  new Object[] {1, price, 1.5d, (byte) 63, (short) 5, true});
      // the violation must be on parameter 'price', not on the other (valid) ones
      assertThat(violations)
          .anyMatch(
              v ->
                  v.getPropertyPath().toString().endsWith(".price")
                      || v.getPropertyPath().toString().endsWith(".arg1"));
    }

    static Stream<Arguments> invalidPrices() {
      return Stream.of(
          Arguments.of("invalid price 1", -1L),
          Arguments.of("invalid price 2", 1000000L),
          Arguments.of("invalid price 3", -9223372036854775808L),
          Arguments.of("invalid price 4", 9223372036854775807L));
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
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(
                              PrimitiveAnnotatedService.class.getDeclaredConstructors())
                          .filter(
                              c ->
                                  java.util.Arrays.equals(
                                      c.getParameterTypes(),
                                      new Class<?>[] {
                                        int.class,
                                        long.class,
                                        double.class,
                                        byte.class,
                                        short.class,
                                        boolean.class
                                      }))
                          .findFirst()
                          .orElseThrow(),
                      new Object[] {1, 100L, rate, (byte) 63, (short) 5, true}))
          .isEmpty();
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
      var violations =
          validator
              .forExecutables()
              .validateConstructorParameters(
                  java.util.Arrays.stream(PrimitiveAnnotatedService.class.getDeclaredConstructors())
                      .filter(
                          c ->
                              java.util.Arrays.equals(
                                  c.getParameterTypes(),
                                  new Class<?>[] {
                                    int.class,
                                    long.class,
                                    double.class,
                                    byte.class,
                                    short.class,
                                    boolean.class
                                  }))
                      .findFirst()
                      .orElseThrow(),
                  new Object[] {1, 100L, rate, (byte) 63, (short) 5, true});
      // the violation must be on parameter 'rate', not on the other (valid) ones
      assertThat(violations)
          .anyMatch(
              v ->
                  v.getPropertyPath().toString().endsWith(".rate")
                      || v.getPropertyPath().toString().endsWith(".arg2"));
    }

    static Stream<Arguments> invalidRates() {
      return Stream.of(Arguments.of("invalid rate 1", 0.0d));
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
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(
                              PrimitiveAnnotatedService.class.getDeclaredConstructors())
                          .filter(
                              c ->
                                  java.util.Arrays.equals(
                                      c.getParameterTypes(),
                                      new Class<?>[] {
                                        int.class,
                                        long.class,
                                        double.class,
                                        byte.class,
                                        short.class,
                                        boolean.class
                                      }))
                          .findFirst()
                          .orElseThrow(),
                      new Object[] {1, 100L, 1.5d, level, (short) 5, true}))
          .isEmpty();
      PrimitiveAnnotatedService obj =
          new PrimitiveAnnotatedService(1, 100L, 1.5d, level, (short) 5, true);
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validLevels() {
      return Stream.of(
          Arguments.of("valid level 1", (byte) 63),
          Arguments.of("valid level 2", (byte) 0),
          Arguments.of("valid level 3", (byte) 1),
          Arguments.of("valid level 4", (byte) 126),
          Arguments.of("valid level 5", (byte) 127));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLevels")
    @DisplayName("rejects invalid value")
    void testRejectsLevel(String description, byte level) {
      var violations =
          validator
              .forExecutables()
              .validateConstructorParameters(
                  java.util.Arrays.stream(PrimitiveAnnotatedService.class.getDeclaredConstructors())
                      .filter(
                          c ->
                              java.util.Arrays.equals(
                                  c.getParameterTypes(),
                                  new Class<?>[] {
                                    int.class,
                                    long.class,
                                    double.class,
                                    byte.class,
                                    short.class,
                                    boolean.class
                                  }))
                      .findFirst()
                      .orElseThrow(),
                  new Object[] {1, 100L, 1.5d, level, (short) 5, true});
      // the violation must be on parameter 'level', not on the other (valid) ones
      assertThat(violations)
          .anyMatch(
              v ->
                  v.getPropertyPath().toString().endsWith(".level")
                      || v.getPropertyPath().toString().endsWith(".arg3"));
    }

    static Stream<Arguments> invalidLevels() {
      return Stream.of(
          Arguments.of("invalid level 1", (byte) -1), Arguments.of("invalid level 2", (byte) -128));
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
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(
                              PrimitiveAnnotatedService.class.getDeclaredConstructors())
                          .filter(
                              c ->
                                  java.util.Arrays.equals(
                                      c.getParameterTypes(),
                                      new Class<?>[] {
                                        int.class,
                                        long.class,
                                        double.class,
                                        byte.class,
                                        short.class,
                                        boolean.class
                                      }))
                          .findFirst()
                          .orElseThrow(),
                      new Object[] {1, 100L, 1.5d, (byte) 63, score, true}))
          .isEmpty();
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
      var violations =
          validator
              .forExecutables()
              .validateConstructorParameters(
                  java.util.Arrays.stream(PrimitiveAnnotatedService.class.getDeclaredConstructors())
                      .filter(
                          c ->
                              java.util.Arrays.equals(
                                  c.getParameterTypes(),
                                  new Class<?>[] {
                                    int.class,
                                    long.class,
                                    double.class,
                                    byte.class,
                                    short.class,
                                    boolean.class
                                  }))
                      .findFirst()
                      .orElseThrow(),
                  new Object[] {1, 100L, 1.5d, (byte) 63, score, true});
      // the violation must be on parameter 'score', not on the other (valid) ones
      assertThat(violations)
          .anyMatch(
              v ->
                  v.getPropertyPath().toString().endsWith(".score")
                      || v.getPropertyPath().toString().endsWith(".arg4"));
    }

    static Stream<Arguments> invalidScores() {
      return Stream.of(
          Arguments.of("invalid score 1", (short) -1),
          Arguments.of("invalid score 2", (short) -32768));
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
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Method applyDiscount()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceMethodApplyDiscountTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validApplyDiscountInputs")
    @DisplayName("applyDiscount accepts valid input")
    void testAcceptsValidInput(String description, int discountPercent) {
      double result = primitiveAnnotatedService.applyDiscount(discountPercent);
      DataValidation.positive(result);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidApplyDiscountInputs")
    @DisplayName("applyDiscount rejects invalid input")
    void testRejectsInvalidInput(String description, int discountPercent) {
      // Enforce @Min: DataValidation.min(0, discountPercent);
      // Enforce @Max: DataValidation.max(100, discountPercent);
      assertThatThrownBy(() -> primitiveAnnotatedService.applyDiscount(discountPercent))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validApplyDiscountInputs() {
      return Stream.of(Arguments.of("valid applyDiscount", 1));
    }

    static Stream<Arguments> invalidApplyDiscountInputs() {
      return Stream.of(
          Arguments.of("invalid discountPercent", -1),
          Arguments.of("invalid discountPercent", 101));
    }
  }

  @Nested
  @DisplayName("PrimitiveAnnotatedService Method combine()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class PrimitiveAnnotatedServiceMethodCombineTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCombineInputs")
    @DisplayName("combine accepts valid input")
    void testAcceptsValidInput(String description, int a, int b) {
      int result = primitiveAnnotatedService.combine(a, b);
      DataValidation.positiveOrZero(result);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCombineInputs")
    @DisplayName("combine rejects invalid input")
    void testRejectsInvalidInput(String description, int a, int b) {
      // Enforce @Min: DataValidation.min(0, a);
      // Enforce @Min: DataValidation.min(0, b);
      assertThatThrownBy(() -> primitiveAnnotatedService.combine(a, b))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validCombineInputs() {
      return Stream.of(Arguments.of("valid combine", 1, 1));
    }

    static Stream<Arguments> invalidCombineInputs() {
      return Stream.of(Arguments.of("invalid a", -1, 1), Arguments.of("invalid b", 1, -1));
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
    void testAcceptsValidInput(String description, int amount, int targetLevel) {
      assertThatCode(() -> primitiveAnnotatedService.transfer(amount, targetLevel))
          .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidTransferInputs")
    @DisplayName("transfer rejects invalid input")
    void testRejectsInvalidInput(String description, int amount, int targetLevel) {
      // Enforce @Min: DataValidation.min(1, amount);
      // Enforce @Min: DataValidation.min(0, targetLevel);
      assertThatThrownBy(() -> primitiveAnnotatedService.transfer(amount, targetLevel))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validTransferInputs() {
      return Stream.of(Arguments.of("valid transfer", 100, 1));
    }

    static Stream<Arguments> invalidTransferInputs() {
      return Stream.of(
          Arguments.of("invalid amount", 0, 1), Arguments.of("invalid targetLevel", 100, -1));
    }
  }
}
