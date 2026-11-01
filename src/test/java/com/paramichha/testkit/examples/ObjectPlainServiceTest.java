package com.paramichha.testkit.examples;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;
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

@DisplayName("ObjectPlainService")
class ObjectPlainServiceTest {

  ObjectPlainService objectPlainService =
      new ObjectPlainService(
          "Judi Gaylord",
          new BigDecimal("-1.00"),
          LocalDate.of(2024, 1, 15),
          "hans.moen@gmail.com");

  @Nested
  @DisplayName("ObjectPlainService Creation")
  @Tag("creation")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectPlainServiceCreationTest {

    @Test
    @DisplayName("can be created")
    void testCanBeCreated() {
      assertThat(objectPlainService).isNotNull();
    }

    @Test
    @DisplayName("holds expected values")
    void testHoldsExpectedValues() {
      assertThat(objectPlainService.getName()).isEqualTo("Judi Gaylord");
      assertThat(objectPlainService.getPrice()).isEqualTo(new BigDecimal("-1.00"));
      assertThat(objectPlainService.getCreatedDate()).isEqualTo(LocalDate.of(2024, 1, 15));
      assertThat(objectPlainService.getEmail()).isEqualTo("hans.moen@gmail.com");
    }
  }

  @Nested
  @DisplayName("ObjectPlainService Field name")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectPlainServiceNameTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validNames")
    @DisplayName("accepts valid value")
    void testAcceptsName(String description, String name) {
      ObjectPlainService obj =
          new ObjectPlainService(
              name, new BigDecimal("-1.00"), LocalDate.of(2024, 1, 15), "hans.moen@gmail.com");
      assertThat(obj.getName()).isEqualTo(name);
    }

    static Stream<Arguments> validNames() {
      return Stream.of(Arguments.of("name 0", "Judi Gaylord"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidNames")
    @DisplayName("rejects invalid value")
    void testRejectsName(String description, String name) {
      // TODO: add constraints to name, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      ObjectPlainService obj =
          new ObjectPlainService(
              name, new BigDecimal("-1.00"), LocalDate.of(2024, 1, 15), "hans.moen@gmail.com");
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidNames() {
      // TODO: add constraints to name, or delete this test
      return Stream.of(Arguments.of("null name", (String) null), Arguments.of("blank name", ""));
    }
  }

  @Nested
  @DisplayName("ObjectPlainService Field price")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectPlainServicePriceTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validPrices")
    @DisplayName("accepts valid value")
    void testAcceptsPrice(String description, BigDecimal price) {
      ObjectPlainService obj =
          new ObjectPlainService(
              "Judi Gaylord", price, LocalDate.of(2024, 1, 15), "hans.moen@gmail.com");
      assertThat(obj.getPrice()).isEqualTo(price);
    }

    static Stream<Arguments> validPrices() {
      return Stream.of(
          Arguments.of("valid price 1", new BigDecimal("-1.00")),
          Arguments.of("valid price 2", new BigDecimal("0.00")),
          Arguments.of("valid price 3", new BigDecimal("847.35")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidPrices")
    @DisplayName("rejects invalid value")
    void testRejectsPrice(String description, BigDecimal price) {
      // TODO: add constraints to price, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      ObjectPlainService obj =
          new ObjectPlainService(
              "Judi Gaylord", price, LocalDate.of(2024, 1, 15), "hans.moen@gmail.com");
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
  @DisplayName("ObjectPlainService Field createdDate")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectPlainServiceCreatedDateTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCreatedDates")
    @DisplayName("accepts valid value")
    void testAcceptsCreatedDate(String description, LocalDate createdDate) {
      ObjectPlainService obj =
          new ObjectPlainService(
              "Judi Gaylord", new BigDecimal("-1.00"), createdDate, "hans.moen@gmail.com");
      assertThat(obj.getCreatedDate()).isEqualTo(createdDate);
    }

    static Stream<Arguments> validCreatedDates() {
      return Stream.of(Arguments.of("valid createdDate 1", LocalDate.of(2024, 1, 15)));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCreatedDates")
    @DisplayName("rejects invalid value")
    void testRejectsCreatedDate(String description, LocalDate createdDate) {
      // TODO: add constraints to createdDate, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      ObjectPlainService obj =
          new ObjectPlainService(
              "Judi Gaylord", new BigDecimal("-1.00"), createdDate, "hans.moen@gmail.com");
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidCreatedDates() {
      // TODO: add constraints to createdDate, or delete this test
      return Stream.of(Arguments.of("null createdDate", (LocalDate) null));
    }
  }

  @Nested
  @DisplayName("ObjectPlainService Field email")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectPlainServiceEmailTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validEmails")
    @DisplayName("accepts valid value")
    void testAcceptsEmail(String description, String email) {
      ObjectPlainService obj =
          new ObjectPlainService(
              "Judi Gaylord", new BigDecimal("-1.00"), LocalDate.of(2024, 1, 15), email);
      assertThat(obj.getEmail()).isEqualTo(email);
    }

    static Stream<Arguments> validEmails() {
      return Stream.of(Arguments.of("email 0", "hans.moen@gmail.com"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidEmails")
    @DisplayName("rejects invalid value")
    void testRejectsEmail(String description, String email) {
      // TODO: add constraints to email, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      ObjectPlainService obj =
          new ObjectPlainService(
              "Judi Gaylord", new BigDecimal("-1.00"), LocalDate.of(2024, 1, 15), email);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidEmails() {
      // TODO: add constraints to email, or delete this test
      return Stream.of(Arguments.of("null email", (String) null), Arguments.of("blank email", ""));
    }
  }

  @Nested
  @DisplayName("ObjectPlainService Method applyTax()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectPlainServiceMethodApplyTaxTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validApplyTaxInputs")
    @DisplayName("applyTax accepts valid input")
    void testAcceptsValidInput(String description, BigDecimal arg0) {
      BigDecimal result = objectPlainService.applyTax(arg0);
      assertThat(result).isNotNull();
    }

    static Stream<Arguments> validApplyTaxInputs() {
      return Stream.of(Arguments.of("valid applyTax", (BigDecimal) null));
    }
  }

  @Nested
  @DisplayName("ObjectPlainService Method copyFrom()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectPlainServiceMethodCopyFromTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCopyFromInputs")
    @DisplayName("copyFrom accepts valid input")
    void testAcceptsValidInput(String description, ObjectPlainService arg0, String arg1) {
      assertThatCode(() -> objectPlainService.copyFrom(arg0, arg1)).doesNotThrowAnyException();
    }

    static Stream<Arguments> validCopyFromInputs() {
      return Stream.of(
          Arguments.of(
              "valid copyFrom",
              new ObjectPlainService(
                  "Basil Hermiston",
                  new BigDecimal("-1.00"),
                  LocalDate.of(2024, 1, 15),
                  "eleonora.halvorson@yahoo.com"),
              (String) null));
    }
  }

  @Nested
  @DisplayName("ObjectPlainService Method formatDisplay()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectPlainServiceMethodFormatDisplayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFormatDisplayInputs")
    @DisplayName("formatDisplay accepts valid input")
    void testAcceptsValidInput(String description, String arg0) {
      String result = objectPlainService.formatDisplay(arg0);
      assertThat(result).isNotNull();
    }

    static Stream<Arguments> validFormatDisplayInputs() {
      return Stream.of(Arguments.of("valid formatDisplay", (String) null));
    }
  }

  @Nested
  @DisplayName("ObjectPlainService Method merge()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectPlainServiceMethodMergeTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validMergeInputs")
    @DisplayName("merge accepts valid input")
    void testAcceptsValidInput(String description, ObjectPlainService arg0, String arg1) {
      ObjectPlainService result = objectPlainService.merge(arg0, arg1);
      assertThat(result).isNotNull();
    }

    static Stream<Arguments> validMergeInputs() {
      return Stream.of(
          Arguments.of(
              "valid merge",
              new ObjectPlainService(
                  "Clemencia Cartwright MD",
                  new BigDecimal("-1.00"),
                  LocalDate.of(2024, 1, 15),
                  "karlyn.dare@hotmail.com"),
              (String) null));
    }
  }

  @Nested
  @DisplayName("ObjectPlainService Method reset()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class ObjectPlainServiceMethodResetTest {

    @Test
    @DisplayName("executes without exception")
    void testExecutesWithoutException() {
      assertThatCode(() -> objectPlainService.reset()).doesNotThrowAnyException();
    }
  }
}
