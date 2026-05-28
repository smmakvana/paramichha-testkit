package com.paramichha.testkit.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Eclipse Method area()")
@Tag("method")
@TestMethodOrder(MethodOrderer.MethodName.class)
class EclipseMethodAreaTest {

  Eclipse eclipse = new Eclipse(791, 791, -1.0f);

  @Test
  @DisplayName("area returns a result")
  void testReturnsResult() {
    float result = eclipse.area();
  }

  @Test
  @DisplayName("area returns consistent result")
  void testReturnsConsistentResult() {
    assertThat(eclipse.area()).isEqualTo(eclipse.area());
  }
}
