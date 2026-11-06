package com.paramichha.testkit.examples;

import com.paramichha.utils.DataValidation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;
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

@DisplayName("ObjectAnnotatedService")
class ObjectAnnotatedServiceTest {

  private static Validator validator;
  ObjectAnnotatedService objectAnnotatedService =
      new ObjectAnnotatedService(
          "Annabell Lynch",
          new BigDecimal("1.50"),
          LocalDate.of(2020, 6, 15),
          "don.thiel@yahoo.com");

  @BeforeAll
  static void setUpValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Nested
  @DisplayName("ObjectAnnotatedService Creation")
  @Tag("creation")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectAnnotatedServiceCreationTest {

    @Test
    @DisplayName("can be created")
    void testCanBeCreated() {
      assertThat(objectAnnotatedService).isNotNull();
    }

    @Test
    @DisplayName("holds expected values")
    void testHoldsExpectedValues() {
      assertThat(objectAnnotatedService.getName()).isEqualTo("Annabell Lynch");
      assertThat(objectAnnotatedService.getPrice()).isEqualTo(new BigDecimal("1.50"));
      assertThat(objectAnnotatedService.getCreatedDate()).isEqualTo(LocalDate.of(2020, 6, 15));
      assertThat(objectAnnotatedService.getEmail()).isEqualTo("don.thiel@yahoo.com");
    }

    @Test
    @DisplayName("is valid (no constraint violations)")
    void testIsValid() {
      assertThat(validator.validate(objectAnnotatedService)).isEmpty();
    }

    @Test
    @DisplayName("constructor arguments are valid")
    void testConstructorArgumentsAreValid() {
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(
                              ObjectAnnotatedService.class.getDeclaredConstructors())
                          .filter(
                              c ->
                                  java.util.Arrays.equals(
                                      c.getParameterTypes(),
                                      new Class<?>[] {
                                        String.class,
                                        BigDecimal.class,
                                        LocalDate.class,
                                        String.class
                                      }))
                          .findFirst()
                          .orElseThrow(),
                      new Object[] {
                        "Annabell Lynch",
                        new BigDecimal("1.50"),
                        LocalDate.of(2020, 6, 15),
                        "don.thiel@yahoo.com"
                      }))
          .isEmpty();
    }
  }

  @Nested
  @DisplayName("ObjectAnnotatedService Field name")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectAnnotatedServiceNameTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validNames")
    @DisplayName("accepts valid value")
    void testAcceptsName(String description, String name) {
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(
                              ObjectAnnotatedService.class.getDeclaredConstructors())
                          .filter(
                              c ->
                                  java.util.Arrays.equals(
                                      c.getParameterTypes(),
                                      new Class<?>[] {
                                        String.class,
                                        BigDecimal.class,
                                        LocalDate.class,
                                        String.class
                                      }))
                          .findFirst()
                          .orElseThrow(),
                      new Object[] {
                        name,
                        new BigDecimal("1.50"),
                        LocalDate.of(2020, 6, 15),
                        "don.thiel@yahoo.com"
                      }))
          .isEmpty();
      ObjectAnnotatedService obj =
          new ObjectAnnotatedService(
              name, new BigDecimal("1.50"), LocalDate.of(2020, 6, 15), "don.thiel@yahoo.com");
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validNames() {
      return Stream.of(Arguments.of("name 0", "Annabell Lynch"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidNames")
    @DisplayName("rejects invalid value")
    void testRejectsName(String description, String name) {
      var violations =
          validator
              .forExecutables()
              .validateConstructorParameters(
                  java.util.Arrays.stream(ObjectAnnotatedService.class.getDeclaredConstructors())
                      .filter(
                          c ->
                              java.util.Arrays.equals(
                                  c.getParameterTypes(),
                                  new Class<?>[] {
                                    String.class, BigDecimal.class, LocalDate.class, String.class
                                  }))
                      .findFirst()
                      .orElseThrow(),
                  new Object[] {
                    name, new BigDecimal("1.50"), LocalDate.of(2020, 6, 15), "don.thiel@yahoo.com"
                  });
      // the violation must be on parameter 'name', not on the other (valid) ones
      assertThat(violations)
          .anyMatch(
              v ->
                  v.getPropertyPath().toString().endsWith(".name")
                      || v.getPropertyPath().toString().endsWith(".arg0"));
    }

    static Stream<Arguments> invalidNames() {
      return Stream.of(Arguments.of("null name", (String) null), Arguments.of("blank name", ""));
    }
  }

  @Nested
  @DisplayName("ObjectAnnotatedService Field price")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectAnnotatedServicePriceTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validPrices")
    @DisplayName("accepts valid value")
    void testAcceptsPrice(String description, BigDecimal price) {
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(
                              ObjectAnnotatedService.class.getDeclaredConstructors())
                          .filter(
                              c ->
                                  java.util.Arrays.equals(
                                      c.getParameterTypes(),
                                      new Class<?>[] {
                                        String.class,
                                        BigDecimal.class,
                                        LocalDate.class,
                                        String.class
                                      }))
                          .findFirst()
                          .orElseThrow(),
                      new Object[] {
                        "Annabell Lynch", price, LocalDate.of(2020, 6, 15), "don.thiel@yahoo.com"
                      }))
          .isEmpty();
      ObjectAnnotatedService obj =
          new ObjectAnnotatedService(
              "Annabell Lynch", price, LocalDate.of(2020, 6, 15), "don.thiel@yahoo.com");
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
      var violations =
          validator
              .forExecutables()
              .validateConstructorParameters(
                  java.util.Arrays.stream(ObjectAnnotatedService.class.getDeclaredConstructors())
                      .filter(
                          c ->
                              java.util.Arrays.equals(
                                  c.getParameterTypes(),
                                  new Class<?>[] {
                                    String.class, BigDecimal.class, LocalDate.class, String.class
                                  }))
                      .findFirst()
                      .orElseThrow(),
                  new Object[] {
                    "Annabell Lynch", price, LocalDate.of(2020, 6, 15), "don.thiel@yahoo.com"
                  });
      // the violation must be on parameter 'price', not on the other (valid) ones
      assertThat(violations)
          .anyMatch(
              v ->
                  v.getPropertyPath().toString().endsWith(".price")
                      || v.getPropertyPath().toString().endsWith(".arg1"));
    }

    static Stream<Arguments> invalidPrices() {
      return Stream.of(
          Arguments.of("null price", (BigDecimal) null),
          Arguments.of("invalid price 2", new BigDecimal("0")));
    }
  }

  @Nested
  @DisplayName("ObjectAnnotatedService Field createdDate")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectAnnotatedServiceCreatedDateTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCreatedDates")
    @DisplayName("accepts valid value")
    void testAcceptsCreatedDate(String description, LocalDate createdDate) {
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(
                              ObjectAnnotatedService.class.getDeclaredConstructors())
                          .filter(
                              c ->
                                  java.util.Arrays.equals(
                                      c.getParameterTypes(),
                                      new Class<?>[] {
                                        String.class,
                                        BigDecimal.class,
                                        LocalDate.class,
                                        String.class
                                      }))
                          .findFirst()
                          .orElseThrow(),
                      new Object[] {
                        "Annabell Lynch", new BigDecimal("1.50"), createdDate, "don.thiel@yahoo.com"
                      }))
          .isEmpty();
      ObjectAnnotatedService obj =
          new ObjectAnnotatedService(
              "Annabell Lynch", new BigDecimal("1.50"), createdDate, "don.thiel@yahoo.com");
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validCreatedDates() {
      return Stream.of(Arguments.of("valid createdDate 1", LocalDate.of(2020, 6, 15)));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCreatedDates")
    @DisplayName("rejects invalid value")
    void testRejectsCreatedDate(String description, LocalDate createdDate) {
      var violations =
          validator
              .forExecutables()
              .validateConstructorParameters(
                  java.util.Arrays.stream(ObjectAnnotatedService.class.getDeclaredConstructors())
                      .filter(
                          c ->
                              java.util.Arrays.equals(
                                  c.getParameterTypes(),
                                  new Class<?>[] {
                                    String.class, BigDecimal.class, LocalDate.class, String.class
                                  }))
                      .findFirst()
                      .orElseThrow(),
                  new Object[] {
                    "Annabell Lynch", new BigDecimal("1.50"), createdDate, "don.thiel@yahoo.com"
                  });
      // the violation must be on parameter 'createdDate', not on the other (valid) ones
      assertThat(violations)
          .anyMatch(
              v ->
                  v.getPropertyPath().toString().endsWith(".createdDate")
                      || v.getPropertyPath().toString().endsWith(".arg2"));
    }

    static Stream<Arguments> invalidCreatedDates() {
      return Stream.of(
          Arguments.of("null createdDate", (LocalDate) null),
          Arguments.of("invalid createdDate 2", LocalDate.of(2099, 6, 15)));
    }
  }

  @Nested
  @DisplayName("ObjectAnnotatedService Field email")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectAnnotatedServiceEmailTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validEmails")
    @DisplayName("accepts valid value")
    void testAcceptsEmail(String description, String email) {
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(
                              ObjectAnnotatedService.class.getDeclaredConstructors())
                          .filter(
                              c ->
                                  java.util.Arrays.equals(
                                      c.getParameterTypes(),
                                      new Class<?>[] {
                                        String.class,
                                        BigDecimal.class,
                                        LocalDate.class,
                                        String.class
                                      }))
                          .findFirst()
                          .orElseThrow(),
                      new Object[] {
                        "Annabell Lynch", new BigDecimal("1.50"), LocalDate.of(2020, 6, 15), email
                      }))
          .isEmpty();
      ObjectAnnotatedService obj =
          new ObjectAnnotatedService(
              "Annabell Lynch", new BigDecimal("1.50"), LocalDate.of(2020, 6, 15), email);
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validEmails() {
      return Stream.of(Arguments.of("email 0", "don.thiel@yahoo.com"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidEmails")
    @DisplayName("rejects invalid value")
    void testRejectsEmail(String description, String email) {
      var violations =
          validator
              .forExecutables()
              .validateConstructorParameters(
                  java.util.Arrays.stream(ObjectAnnotatedService.class.getDeclaredConstructors())
                      .filter(
                          c ->
                              java.util.Arrays.equals(
                                  c.getParameterTypes(),
                                  new Class<?>[] {
                                    String.class, BigDecimal.class, LocalDate.class, String.class
                                  }))
                      .findFirst()
                      .orElseThrow(),
                  new Object[] {
                    "Annabell Lynch", new BigDecimal("1.50"), LocalDate.of(2020, 6, 15), email
                  });
      // the violation must be on parameter 'email', not on the other (valid) ones
      assertThat(violations)
          .anyMatch(
              v ->
                  v.getPropertyPath().toString().endsWith(".email")
                      || v.getPropertyPath().toString().endsWith(".arg3"));
    }

    static Stream<Arguments> invalidEmails() {
      return Stream.of(Arguments.of("email 0", "not-an-email"));
    }
  }

  @Nested
  @DisplayName("ObjectAnnotatedService Method applyTax()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectAnnotatedServiceMethodApplyTaxTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validApplyTaxInputs")
    @DisplayName("applyTax accepts valid input")
    void testAcceptsValidInput(String description, BigDecimal taxRate) {
      BigDecimal result = objectAnnotatedService.applyTax(taxRate);
      DataValidation.notNull(result);
      DataValidation.positive(result);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidApplyTaxInputs")
    @DisplayName("applyTax rejects invalid input")
    void testRejectsInvalidInput(String description, BigDecimal taxRate) {
      // Enforce @DecimalMin: DataValidation.decimalMin("0.0", taxRate);
      // Enforce @DecimalMax: DataValidation.decimalMax("1.0", taxRate);
      assertThatThrownBy(() -> objectAnnotatedService.applyTax(taxRate))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validApplyTaxInputs() {
      return Stream.of(Arguments.of("valid applyTax", new BigDecimal("0.50")));
    }

    static Stream<Arguments> invalidApplyTaxInputs() {
      return Stream.of(
          Arguments.of("invalid taxRate", new BigDecimal("-1")),
          Arguments.of("invalid taxRate", new BigDecimal("2")));
    }
  }

  @Nested
  @DisplayName("ObjectAnnotatedService Method copyFrom()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectAnnotatedServiceMethodCopyFromTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCopyFromInputs")
    @DisplayName("copyFrom accepts valid input")
    void testAcceptsValidInput(String description, ObjectAnnotatedService source, String reason) {
      assertThatCode(() -> objectAnnotatedService.copyFrom(source, reason))
          .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCopyFromInputs")
    @DisplayName("copyFrom rejects invalid input")
    void testRejectsInvalidInput(String description, ObjectAnnotatedService source, String reason) {
      // Enforce @NotNull: DataValidation.notNull(source);
      // Enforce @NotBlank: DataValidation.notBlank(reason);
      assertThatThrownBy(() -> objectAnnotatedService.copyFrom(source, reason))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validCopyFromInputs() {
      return Stream.of(
          Arguments.of(
              "valid copyFrom",
              new ObjectAnnotatedService(
                  "Coleman Crooks",
                  new BigDecimal("1.50"),
                  LocalDate.of(2020, 6, 15),
                  "rolando.pagac@yahoo.com"),
              "Sed veniam beatae."));
    }

    static Stream<Arguments> invalidCopyFromInputs() {
      return Stream.of(
          Arguments.of("invalid source", (ObjectAnnotatedService) null, "Sed veniam beatae."),
          Arguments.of(
              "invalid reason",
              new ObjectAnnotatedService(
                  "Coleman Crooks",
                  new BigDecimal("1.50"),
                  LocalDate.of(2020, 6, 15),
                  "rolando.pagac@yahoo.com"),
              (String) null),
          Arguments.of(
              "invalid reason",
              new ObjectAnnotatedService(
                  "Coleman Crooks",
                  new BigDecimal("1.50"),
                  LocalDate.of(2020, 6, 15),
                  "rolando.pagac@yahoo.com"),
              ""));
    }
  }

  @Nested
  @DisplayName("ObjectAnnotatedService Method formatDisplay()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectAnnotatedServiceMethodFormatDisplayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFormatDisplayInputs")
    @DisplayName("formatDisplay accepts valid input")
    void testAcceptsValidInput(String description, String prefix) {
      String result = objectAnnotatedService.formatDisplay(prefix);
      DataValidation.notBlank(result);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidFormatDisplayInputs")
    @DisplayName("formatDisplay rejects invalid input")
    void testRejectsInvalidInput(String description, String prefix) {
      // Enforce @NotBlank: DataValidation.notBlank(prefix);
      assertThatThrownBy(() -> objectAnnotatedService.formatDisplay(prefix))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validFormatDisplayInputs() {
      return Stream.of(Arguments.of("valid formatDisplay", "Mr."));
    }

    static Stream<Arguments> invalidFormatDisplayInputs() {
      return Stream.of(
          Arguments.of("invalid prefix", (String) null), Arguments.of("invalid prefix", ""));
    }
  }

  @Nested
  @DisplayName("ObjectAnnotatedService Method merge()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectAnnotatedServiceMethodMergeTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validMergeInputs")
    @DisplayName("merge accepts valid input")
    void testAcceptsValidInput(String description, ObjectAnnotatedService other, String newName) {
      ObjectAnnotatedService result = objectAnnotatedService.merge(other, newName);
      assertThat(validator.validate(result)).isEmpty();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidMergeInputs")
    @DisplayName("merge rejects invalid input")
    void testRejectsInvalidInput(String description, ObjectAnnotatedService other, String newName) {
      // Enforce @NotNull: DataValidation.notNull(other);
      // Enforce @NotBlank: DataValidation.notBlank(newName);
      assertThatThrownBy(() -> objectAnnotatedService.merge(other, newName))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validMergeInputs() {
      return Stream.of(
          Arguments.of(
              "valid merge",
              new ObjectAnnotatedService(
                  "Bret Kautzer",
                  new BigDecimal("1.50"),
                  LocalDate.of(2020, 6, 15),
                  "krystle.mertz@yahoo.com"),
              "Dr. Tommie O'Keefe"));
    }

    static Stream<Arguments> invalidMergeInputs() {
      return Stream.of(
          Arguments.of("invalid other", (ObjectAnnotatedService) null, "Dr. Tommie O'Keefe"),
          Arguments.of(
              "invalid newName",
              new ObjectAnnotatedService(
                  "Bret Kautzer",
                  new BigDecimal("1.50"),
                  LocalDate.of(2020, 6, 15),
                  "krystle.mertz@yahoo.com"),
              (String) null),
          Arguments.of(
              "invalid newName",
              new ObjectAnnotatedService(
                  "Bret Kautzer",
                  new BigDecimal("1.50"),
                  LocalDate.of(2020, 6, 15),
                  "krystle.mertz@yahoo.com"),
              ""));
    }
  }

  @Nested
  @DisplayName("ObjectAnnotatedService Method reset()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectAnnotatedServiceMethodResetTest {

    @Test
    @DisplayName("executes without exception")
    void testExecutesWithoutException() {
      assertThatCode(() -> objectAnnotatedService.reset()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("object remains valid after execution")
    void testObjectRemainsValidAfterExecution() {
      objectAnnotatedService.reset();
      assertThat(validator.validate(objectAnnotatedService)).isEmpty();
    }
  }
}
