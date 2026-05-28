package com.paramichha.testkit.examples;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SplitGodRecord Field charWrapperArray")
@Tag("field")
@TestMethodOrder(MethodOrderer.MethodName.class)
class SplitGodRecordFieldCharWrapperArrayTest {

  SplitGodRecord splitGodRecord =
      new SplitGodRecord(
          (byte) 127,
          (short) 330,
          330,
          330L,
          -1.0f,
          -1.0d,
          true,
          'U',
          (byte) 127,
          (short) 330,
          330,
          330L,
          -1.0f,
          -1.0d,
          true,
          'U',
          new byte[] {(byte) 127, (byte) 127},
          new short[] {(short) 330, (short) 189},
          new int[] {330, 189},
          new long[] {330L, 189L},
          new float[] {-1.0f, -1.0f},
          new double[] {-1.0d, -1.0d},
          new boolean[] {true, true},
          new char[] {'U', 'P'},
          new Byte[] {(byte) 127, (byte) 127},
          new Short[] {(short) 330, (short) 189},
          new Integer[] {330, 189},
          new Long[] {330L, 189L},
          new Float[] {-1.0f, -1.0f},
          new Double[] {-1.0d, -1.0d},
          new Boolean[] {true, true},
          new Character[] {'U', 'P'},
          DayOfWeek.MONDAY,
          List.of("suscipit"),
          Set.of("labore", "suscipit"),
          (Map) null);

  @ParameterizedTest(name = "{0}")
  @MethodSource("validCharWrapperArrays")
  @DisplayName("accepts valid value")
  void testAcceptsCharWrapperArray(String description, Character[] charWrapperArray) {
    SplitGodRecord obj =
        new SplitGodRecord(
            (byte) 127,
            (short) 330,
            330,
            330L,
            -1.0f,
            -1.0d,
            true,
            'U',
            (byte) 127,
            (short) 330,
            330,
            330L,
            -1.0f,
            -1.0d,
            true,
            'U',
            new byte[] {(byte) 127, (byte) 127},
            new short[] {(short) 330, (short) 189},
            new int[] {330, 189},
            new long[] {330L, 189L},
            new float[] {-1.0f, -1.0f},
            new double[] {-1.0d, -1.0d},
            new boolean[] {true, true},
            new char[] {'U', 'P'},
            new Byte[] {(byte) 127, (byte) 127},
            new Short[] {(short) 330, (short) 189},
            new Integer[] {330, 189},
            new Long[] {330L, 189L},
            new Float[] {-1.0f, -1.0f},
            new Double[] {-1.0d, -1.0d},
            new Boolean[] {true, true},
            charWrapperArray,
            DayOfWeek.MONDAY,
            List.of("suscipit"),
            Set.of("labore", "suscipit"),
            (Map) null);
    assertThat(obj.charWrapperArray()).isEqualTo(charWrapperArray);
  }

  static Stream<Arguments> validCharWrapperArrays() {
    return Stream.of(Arguments.of("valid charWrapperArray 1", new Character[] {'U', 'P'}));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("invalidCharWrapperArrays")
  @DisplayName("rejects invalid value")
  void testRejectsCharWrapperArray(String description, Character[] charWrapperArray) {
    // TODO: add constraints to charWrapperArray, then replace assertThat(obj).isNotNull()
    //       with assertThat(validator.validate(obj)).isNotEmpty()
    SplitGodRecord obj =
        new SplitGodRecord(
            (byte) 127,
            (short) 330,
            330,
            330L,
            -1.0f,
            -1.0d,
            true,
            'U',
            (byte) 127,
            (short) 330,
            330,
            330L,
            -1.0f,
            -1.0d,
            true,
            'U',
            new byte[] {(byte) 127, (byte) 127},
            new short[] {(short) 330, (short) 189},
            new int[] {330, 189},
            new long[] {330L, 189L},
            new float[] {-1.0f, -1.0f},
            new double[] {-1.0d, -1.0d},
            new boolean[] {true, true},
            new char[] {'U', 'P'},
            new Byte[] {(byte) 127, (byte) 127},
            new Short[] {(short) 330, (short) 189},
            new Integer[] {330, 189},
            new Long[] {330L, 189L},
            new Float[] {-1.0f, -1.0f},
            new Double[] {-1.0d, -1.0d},
            new Boolean[] {true, true},
            charWrapperArray,
            DayOfWeek.MONDAY,
            List.of("suscipit"),
            Set.of("labore", "suscipit"),
            (Map) null);
    assertThat(obj).isNotNull();
  }

  static Stream<Arguments> invalidCharWrapperArrays() {
    // TODO: add constraints to charWrapperArray, or delete this test
    return Stream.of(Arguments.of("null charWrapperArray", (Character[]) null));
  }
}
