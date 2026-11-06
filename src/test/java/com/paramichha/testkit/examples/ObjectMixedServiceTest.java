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

@DisplayName("ObjectMixedService")
class ObjectMixedServiceTest {

  private static Validator validator;
  ObjectMixedService objectMixedService =
      new ObjectMixedService(
          "Harvey Dach",
          new BigDecimal("-1.00"),
          LocalDate.of(2024, 1, 15),
          "nathalie.mcclure@hotmail.com");

  @BeforeAll
  static void setUpValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Nested
  @DisplayName("ObjectMixedService Creation")
  @Tag("creation")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectMixedServiceCreationTest {

    @Test
    @DisplayName("can be created")
    void testCanBeCreated() {
      assertThat(objectMixedService).isNotNull();
    }

    @Test
    @DisplayName("holds expected values")
    void testHoldsExpectedValues() {
      assertThat(objectMixedService.getName()).isEqualTo("Harvey Dach");
      assertThat(objectMixedService.getPrice()).isEqualTo(new BigDecimal("-1.00"));
      assertThat(objectMixedService.getCreatedDate()).isEqualTo(LocalDate.of(2024, 1, 15));
      assertThat(objectMixedService.getEmail()).isEqualTo("nathalie.mcclure@hotmail.com");
    }

    @Test
    @DisplayName("is valid (no constraint violations)")
    void testIsValid() {
      assertThat(validator.validate(objectMixedService)).isEmpty();
    }

    @Test
    @DisplayName("constructor arguments are valid")
    void testConstructorArgumentsAreValid() {
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(ObjectMixedService.class.getDeclaredConstructors())
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
                        "Harvey Dach",
                        new BigDecimal("-1.00"),
                        LocalDate.of(2024, 1, 15),
                        "nathalie.mcclure@hotmail.com"
                      }))
          .isEmpty();
    }
  }

  @Nested
  @DisplayName("ObjectMixedService Field name")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectMixedServiceNameTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validNames")
    @DisplayName("accepts valid value")
    void testAcceptsName(String description, String name) {
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(ObjectMixedService.class.getDeclaredConstructors())
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
                        new BigDecimal("-1.00"),
                        LocalDate.of(2024, 1, 15),
                        "nathalie.mcclure@hotmail.com"
                      }))
          .isEmpty();
      ObjectMixedService obj =
          new ObjectMixedService(
              name,
              new BigDecimal("-1.00"),
              LocalDate.of(2024, 1, 15),
              "nathalie.mcclure@hotmail.com");
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validNames() {
      return Stream.of(Arguments.of("name 0", "Harvey Dach"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidNames")
    @DisplayName("rejects invalid value")
    void testRejectsName(String description, String name) {
      var violations =
          validator
              .forExecutables()
              .validateConstructorParameters(
                  java.util.Arrays.stream(ObjectMixedService.class.getDeclaredConstructors())
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
                    name,
                    new BigDecimal("-1.00"),
                    LocalDate.of(2024, 1, 15),
                    "nathalie.mcclure@hotmail.com"
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
  @DisplayName("ObjectMixedService Field price")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectMixedServicePriceTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validPrices")
    @DisplayName("accepts valid value")
    void testAcceptsPrice(String description, BigDecimal price) {
      ObjectMixedService obj =
          new ObjectMixedService(
              "Harvey Dach", price, LocalDate.of(2024, 1, 15), "nathalie.mcclure@hotmail.com");
      assertThat(obj.getPrice()).isEqualTo(price);
    }

    static Stream<Arguments> validPrices() {
      return Stream.of(
          Arguments.of("valid price 1", new BigDecimal("-1.00")),
          Arguments.of("valid price 2", new BigDecimal("0.00")),
          Arguments.of("valid price 3", new BigDecimal("70.57")));
    }
  }

  @Nested
  @DisplayName("ObjectMixedService Field createdDate")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectMixedServiceCreatedDateTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCreatedDates")
    @DisplayName("accepts valid value")
    void testAcceptsCreatedDate(String description, LocalDate createdDate) {
      assertThat(
              validator
                  .forExecutables()
                  .validateConstructorParameters(
                      java.util.Arrays.stream(ObjectMixedService.class.getDeclaredConstructors())
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
                        "Harvey Dach",
                        new BigDecimal("-1.00"),
                        createdDate,
                        "nathalie.mcclure@hotmail.com"
                      }))
          .isEmpty();
      ObjectMixedService obj =
          new ObjectMixedService(
              "Harvey Dach", new BigDecimal("-1.00"), createdDate, "nathalie.mcclure@hotmail.com");
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validCreatedDates() {
      return Stream.of(Arguments.of("valid createdDate 1", LocalDate.of(2024, 1, 15)));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCreatedDates")
    @DisplayName("rejects invalid value")
    void testRejectsCreatedDate(String description, LocalDate createdDate) {
      var violations =
          validator
              .forExecutables()
              .validateConstructorParameters(
                  java.util.Arrays.stream(ObjectMixedService.class.getDeclaredConstructors())
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
                    "Harvey Dach",
                    new BigDecimal("-1.00"),
                    createdDate,
                    "nathalie.mcclure@hotmail.com"
                  });
      // the violation must be on parameter 'createdDate', not on the other (valid) ones
      assertThat(violations)
          .anyMatch(
              v ->
                  v.getPropertyPath().toString().endsWith(".createdDate")
                      || v.getPropertyPath().toString().endsWith(".arg2"));
    }

    static Stream<Arguments> invalidCreatedDates() {
      return Stream.of(Arguments.of("null createdDate", (LocalDate) null));
    }
  }

  @Nested
  @DisplayName("ObjectMixedService Field email")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectMixedServiceEmailTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validEmails")
    @DisplayName("accepts valid value")
    void testAcceptsEmail(String description, String email) {
      ObjectMixedService obj =
          new ObjectMixedService(
              "Harvey Dach", new BigDecimal("-1.00"), LocalDate.of(2024, 1, 15), email);
      assertThat(obj.getEmail()).isEqualTo(email);
    }

    static Stream<Arguments> validEmails() {
      return Stream.of(Arguments.of("email 0", "nathalie.mcclure@hotmail.com"));
    }
  }

  @Nested
  @DisplayName("ObjectMixedService Method copyFrom()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectMixedServiceMethodCopyFromTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCopyFromInputs")
    @DisplayName("copyFrom accepts valid input")
    void testAcceptsValidInput(String description, ObjectMixedService source, String reason) {
      assertThatCode(() -> objectMixedService.copyFrom(source, reason)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCopyFromInputs")
    @DisplayName("copyFrom rejects invalid input")
    void testRejectsInvalidInput(String description, ObjectMixedService source, String reason) {
      // Enforce @NotNull: DataValidation.notNull(source);
      assertThatThrownBy(() -> objectMixedService.copyFrom(source, reason))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validCopyFromInputs() {
      return Stream.of(
          Arguments.of(
              "valid copyFrom",
              new ObjectMixedService(
                  "Myron McDermott",
                  new BigDecimal("-1.00"),
                  LocalDate.of(2024, 1, 15),
                  "cedric.barrows@yahoo.com"),
              (String) null));
    }

    static Stream<Arguments> invalidCopyFromInputs() {
      return Stream.of(Arguments.of("invalid source", (ObjectMixedService) null, (String) null));
    }
  }

  @Nested
  @DisplayName("ObjectMixedService Method formatDisplay()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectMixedServiceMethodFormatDisplayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFormatDisplayInputs")
    @DisplayName("formatDisplay accepts valid input")
    void testAcceptsValidInput(String description, String prefix) {
      String result = objectMixedService.formatDisplay(prefix);
      assertThat(result).isNotNull();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidFormatDisplayInputs")
    @DisplayName("formatDisplay rejects invalid input")
    void testRejectsInvalidInput(String description, String prefix) {
      // Enforce @NotBlank: DataValidation.notBlank(prefix);
      assertThatThrownBy(() -> objectMixedService.formatDisplay(prefix))
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
  @DisplayName("ObjectMixedService Method merge()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectMixedServiceMethodMergeTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validMergeInputs")
    @DisplayName("merge accepts valid input")
    void testAcceptsValidInput(String description, ObjectMixedService other, String optionalLabel) {
      ObjectMixedService result = objectMixedService.merge(other, optionalLabel);
      assertThat(validator.validate(result)).isEmpty();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidMergeInputs")
    @DisplayName("merge rejects invalid input")
    void testRejectsInvalidInput(
        String description, ObjectMixedService other, String optionalLabel) {
      // Enforce @NotNull: DataValidation.notNull(other);
      assertThatThrownBy(() -> objectMixedService.merge(other, optionalLabel))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validMergeInputs() {
      return Stream.of(
          Arguments.of(
              "valid merge",
              new ObjectMixedService(
                  "Darcel Kuhn",
                  new BigDecimal("-1.00"),
                  LocalDate.of(2024, 1, 15),
                  "tamika.douglas@yahoo.com"),
              (String) null));
    }

    static Stream<Arguments> invalidMergeInputs() {
      return Stream.of(Arguments.of("invalid other", (ObjectMixedService) null, (String) null));
    }
  }

  @Nested
  @DisplayName("ObjectMixedService Method reset()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectMixedServiceMethodResetTest {

    @Test
    @DisplayName("executes without exception")
    void testExecutesWithoutException() {
      assertThatCode(() -> objectMixedService.reset()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("object remains valid after execution")
    void testObjectRemainsValidAfterExecution() {
      objectMixedService.reset();
      assertThat(validator.validate(objectMixedService)).isEmpty();
    }
  }

  @Nested
  @DisplayName("ObjectMixedService Method resolvedPrice()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectMixedServiceMethodResolvedPriceTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validResolvedPriceInputs")
    @DisplayName("resolvedPrice accepts valid input")
    void testAcceptsValidInput(String description, BigDecimal fallback) {
      BigDecimal result = objectMixedService.resolvedPrice(fallback);
      DataValidation.notNull(result);
    }

    static Stream<Arguments> validResolvedPriceInputs() {
      return Stream.of(Arguments.of("valid resolvedPrice", (BigDecimal) null));
    }
  }
}
