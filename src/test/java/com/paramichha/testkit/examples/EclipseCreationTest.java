package com.paramichha.testkit.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Eclipse Creation")
@Tag("creation")
@TestMethodOrder(MethodOrderer.MethodName.class)
class EclipseCreationTest {

  Eclipse eclipse = new Eclipse(791, 791, -1.0f);

  @Test
  @DisplayName("can be created")
  void testCanBeCreated() {
    assertThat(eclipse).isNotNull();
  }

  @Test
  @DisplayName("holds expected values")
  void testHoldsExpectedValues() {
    assertThat(eclipse.getX()).isEqualTo(791);
    assertThat(eclipse.getY()).isEqualTo(791);
    assertThat(eclipse.getR()).isEqualTo(-1.0f);
  }
}
