package com.paramichha.testkit.examples;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Eclipse Field r")
@Tag("field")
@TestMethodOrder(MethodOrderer.MethodName.class)
class EclipseFieldRTest {

  Eclipse eclipse = new Eclipse(791, 791, -1.0f);

  @ParameterizedTest(name = "{0}")
  @MethodSource("validRs")
  @DisplayName("accepts valid value")
  void testAcceptsR(String description, float r) {
    Eclipse obj = new Eclipse(791, 791, r);
    assertThat(obj.getR()).isEqualTo(r);
  }

  static Stream<Arguments> validRs() {
    return Stream.of(
        Arguments.of("valid r 1", -1.0f),
        Arguments.of("valid r 2", 0.0f),
        Arguments.of("valid r 3", 351.46f));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("invalidRs")
  @DisplayName("rejects invalid value")
  void testRejectsR(String description, float r) {
    // TODO: add constraints to r, then replace assertThat(obj).isNotNull()
    //       with assertThat(validator.validate(obj)).isNotEmpty()
    Eclipse obj = new Eclipse(791, 791, r);
    assertThat(obj).isNotNull();
  }

  static Stream<Arguments> invalidRs() {
    // TODO: add constraints to r, or delete this test
    return Stream.of(Arguments.of("invalid r 1", -1.0f));
  }
}
