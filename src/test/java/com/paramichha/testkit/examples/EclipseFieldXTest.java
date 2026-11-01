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

@DisplayName("Eclipse Field x")
@Tag("field")
@TestMethodOrder(MethodOrderer.MethodName.class)
class EclipseFieldXTest {

  Eclipse eclipse = new Eclipse(791, 791, -1.0f);

  @ParameterizedTest(name = "{0}")
  @MethodSource("validXes")
  @DisplayName("accepts valid value")
  void testAcceptsX(String description, int x) {
    Eclipse obj = new Eclipse(x, 791, -1.0f);
    assertThat(obj.getX()).isEqualTo(x);
  }

  static Stream<Arguments> validXes() {
    return Stream.of(
        Arguments.of("valid x 1", 791),
        Arguments.of("valid x 2", -2147483648),
        Arguments.of("valid x 3", -2147483647),
        Arguments.of("valid x 4", 0),
        Arguments.of("valid x 5", 2147483646),
        Arguments.of("valid x 6", 2147483647));
  }
}
