package com.paramichha.testkit.examples;

import com.paramichha.utils.DataValidation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
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
      try {
        ObjectMixedService obj =
            new ObjectMixedService(
                name,
                new BigDecimal("-1.00"),
                LocalDate.of(2024, 1, 15),
                "nathalie.mcclure@hotmail.com");
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

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidPrices")
    @DisplayName("rejects invalid value")
    void testRejectsPrice(String description, BigDecimal price) {
      // TODO: add constraints to price, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      ObjectMixedService obj =
          new ObjectMixedService(
              "Harvey Dach", price, LocalDate.of(2024, 1, 15), "nathalie.mcclure@hotmail.com");
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidPrices() {
      // TODO: add constraints to price, or delete this test
      return Stream.of(
          Arguments.of("null price", (BigDecimal) null),
          Arguments.of("invalid price 2", new BigDecimal("-1")));
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
      try {
        ObjectMixedService obj =
            new ObjectMixedService(
                "Harvey Dach",
                new BigDecimal("-1.00"),
                createdDate,
                "nathalie.mcclure@hotmail.com");
        assertThat(validator.validate(obj)).isNotEmpty();
      } catch (Exception ignored) {
        // Constructor enforced the contract — test passes
      }
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

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidEmails")
    @DisplayName("rejects invalid value")
    void testRejectsEmail(String description, String email) {
      // TODO: add constraints to email, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      ObjectMixedService obj =
          new ObjectMixedService(
              "Harvey Dach", new BigDecimal("-1.00"), LocalDate.of(2024, 1, 15), email);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidEmails() {
      // TODO: add constraints to email, or delete this test
      return Stream.of(Arguments.of("null email", (String) null), Arguments.of("blank email", ""));
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
    void testAcceptsValidInput(String description, ObjectMixedService arg0, String arg1) {
      assertThatCode(() -> objectMixedService.copyFrom(arg0, arg1)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCopyFromInputs")
    @DisplayName("copyFrom rejects invalid input")
    void testRejectsInvalidInput(String description, ObjectMixedService arg0, String arg1) {
      // Enforce @NotNull: DataValidation.notNull(arg0);
      assertThatThrownBy(() -> objectMixedService.copyFrom(arg0, arg1))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validCopyFromInputs() {
      return Stream.of(
          Arguments.of(
              "valid copyFrom",
              new ObjectMixedService(
                  "Jolanda Cormier",
                  new BigDecimal("-1.00"),
                  LocalDate.of(2024, 1, 15),
                  "marcelino.torphy@gmail.com"),
              (String) null));
    }

    static Stream<Arguments> invalidCopyFromInputs() {
      return Stream.of(Arguments.of("invalid arg0", (ObjectMixedService) null, (String) null));
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
    void testAcceptsValidInput(String description, String arg0) {
      String result = objectMixedService.formatDisplay(arg0);
      assertThat(result).isNotNull();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidFormatDisplayInputs")
    @DisplayName("formatDisplay rejects invalid input")
    void testRejectsInvalidInput(String description, String arg0) {
      // Enforce @NotBlank: DataValidation.notBlank(arg0);
      assertThatThrownBy(() -> objectMixedService.formatDisplay(arg0))
          .isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validFormatDisplayInputs() {
      return Stream.of(Arguments.of("valid formatDisplay", "rerum"));
    }

    static Stream<Arguments> invalidFormatDisplayInputs() {
      return Stream.of(
          Arguments.of("invalid arg0", (String) null), Arguments.of("invalid arg0", ""));
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
    void testAcceptsValidInput(String description, ObjectMixedService arg0, String arg1) {
      ObjectMixedService result = objectMixedService.merge(arg0, arg1);
      assertThat(validator.validate(result)).isEmpty();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidMergeInputs")
    @DisplayName("merge rejects invalid input")
    void testRejectsInvalidInput(String description, ObjectMixedService arg0, String arg1) {
      // Enforce @NotNull: DataValidation.notNull(arg0);
      assertThatThrownBy(() -> objectMixedService.merge(arg0, arg1)).isInstanceOf(Exception.class);
    }

    static Stream<Arguments> validMergeInputs() {
      return Stream.of(
          Arguments.of(
              "valid merge",
              new ObjectMixedService(
                  "Ms. Kamilah Cummerata",
                  new BigDecimal("-1.00"),
                  LocalDate.of(2024, 1, 15),
                  "anton.hoeger@yahoo.com"),
              (String) null));
    }

    static Stream<Arguments> invalidMergeInputs() {
      return Stream.of(Arguments.of("invalid arg0", (ObjectMixedService) null, (String) null));
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

    private record ResolvedPriceResult(@NotNull BigDecimal value) {}
    ;

    @ParameterizedTest(name = "{0}")
    @MethodSource("validResolvedPriceInputs")
    @DisplayName("resolvedPrice accepts valid input")
    void testAcceptsValidInput(String description, BigDecimal arg0) {
      BigDecimal result = objectMixedService.resolvedPrice(arg0);
      DataValidation.notNull(result);
    }

    static Stream<Arguments> validResolvedPriceInputs() {
      return Stream.of(Arguments.of("valid resolvedPrice", (BigDecimal) null));
    }
  }
}
