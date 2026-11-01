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

@DisplayName("Eclipse Field y")
@Tag("field")
@TestMethodOrder(MethodOrderer.MethodName.class)
class EclipseFieldYTest {

  Eclipse eclipse = new Eclipse(791, 791, -1.0f);

  @ParameterizedTest(name = "{0}")
  @MethodSource("validYs")
  @DisplayName("accepts valid value")
  void testAcceptsY(String description, int y) {
    Eclipse obj = new Eclipse(791, y, -1.0f);
    assertThat(obj.getY()).isEqualTo(y);
  }

  static Stream<Arguments> validYs() {
    return Stream.of(
        Arguments.of("valid y 1", 791),
        Arguments.of("valid y 2", -2147483648),
        Arguments.of("valid y 3", -2147483647),
        Arguments.of("valid y 4", 0),
        Arguments.of("valid y 5", 2147483646),
        Arguments.of("valid y 6", 2147483647));
  }
}
