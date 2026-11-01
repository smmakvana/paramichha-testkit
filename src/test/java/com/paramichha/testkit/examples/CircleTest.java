package com.paramichha.testkit.examples;

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

@DisplayName("Circle")
class CircleTest {

  private static Validator validator;
  Circle circle = new Circle(780, 780, 1.5f);

  @BeforeAll
  static void setUpValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Nested
  @DisplayName("Circle Creation")
  @Tag("creation")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class CircleCreationTest {

    @Test
    @DisplayName("can be created")
    void testCanBeCreated() {
      assertThat(circle).isNotNull();
    }

    @Test
    @DisplayName("holds expected values")
    void testHoldsExpectedValues() {
      assertThat(circle.getX()).isEqualTo(780);
      assertThat(circle.getY()).isEqualTo(780);
      assertThat(circle.getR()).isEqualTo(1.5f);
    }

    @Test
    @DisplayName("is valid (no constraint violations)")
    void testIsValid() {
      assertThat(validator.validate(circle)).isEmpty();
    }
  }

  @Nested
  @DisplayName("Circle Field x")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class CircleXTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validXes")
    @DisplayName("accepts valid value")
    void testAcceptsX(String description, int x) {
      Circle obj = new Circle(x, 780, 1.5f);
      assertThat(obj.getX()).isEqualTo(x);
    }

    static Stream<Arguments> validXes() {
      return Stream.of(
          Arguments.of("valid x 1", 780),
          Arguments.of("valid x 2", -2147483648),
          Arguments.of("valid x 3", -2147483647),
          Arguments.of("valid x 4", 0),
          Arguments.of("valid x 5", 2147483646),
          Arguments.of("valid x 6", 2147483647));
    }
  }

  @Nested
  @DisplayName("Circle Field y")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class CircleYTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validYs")
    @DisplayName("accepts valid value")
    void testAcceptsY(String description, int y) {
      Circle obj = new Circle(780, y, 1.5f);
      assertThat(obj.getY()).isEqualTo(y);
    }

    static Stream<Arguments> validYs() {
      return Stream.of(
          Arguments.of("valid y 1", 780),
          Arguments.of("valid y 2", -2147483648),
          Arguments.of("valid y 3", -2147483647),
          Arguments.of("valid y 4", 0),
          Arguments.of("valid y 5", 2147483646),
          Arguments.of("valid y 6", 2147483647));
    }
  }

  @Nested
  @DisplayName("Circle Field r")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class CircleRTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validRs")
    @DisplayName("accepts valid value")
    void testAcceptsR(String description, float r) {
      Circle obj = new Circle(780, 780, r);
      assertThat(validator.validate(obj)).isEmpty();
    }

    static Stream<Arguments> validRs() {
      return Stream.of(
          Arguments.of("valid r 1", 1.5f),
          Arguments.of("valid r 2", 1.0f),
          Arguments.of("valid r 3", 2.0f));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidRs")
    @DisplayName("rejects invalid value")
    void testRejectsR(String description, float r) {
      Circle obj;
      try {
        obj = new Circle(780, 780, r);
      } catch (IllegalArgumentException | jakarta.validation.ValidationException e) {
        return; // the constructor itself rejected the value
      }
      assertThat(validator.validate(obj))
          .anyMatch(
              v -> {
                String path = v.getPropertyPath().toString();
                return path.equals("r") || path.startsWith("r.") || path.startsWith("r[");
              });
    }

    static Stream<Arguments> invalidRs() {
      return Stream.of(Arguments.of("invalid r 1", 0.0f));
    }
  }

  @Nested
  @DisplayName("Circle Method area()")
  @Tag("method")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class CircleMethodAreaTest {

    @Test
    @DisplayName("area returns a result")
    void testReturnsResult() {
      float result = circle.area();
    }

    @Test
    @DisplayName("area returns consistent result")
    void testReturnsConsistentResult() {
      assertThat(circle.area()).isEqualTo(circle.area());
    }
  }
}
