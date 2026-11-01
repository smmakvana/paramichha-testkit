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

@DisplayName("SplitGodRecord Field byteValue")
@Tag("field")
@TestMethodOrder(MethodOrderer.MethodName.class)
class SplitGodRecordFieldByteValueTest {

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
  @MethodSource("validByteValues")
  @DisplayName("accepts valid value")
  void testAcceptsByteValue(String description, byte byteValue) {
    SplitGodRecord obj =
        new SplitGodRecord(
            byteValue,
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
    assertThat(obj.byteValue()).isEqualTo(byteValue);
  }

  static Stream<Arguments> validByteValues() {
    return Stream.of(
        Arguments.of("valid byteValue 1", (byte) 127),
        Arguments.of("valid byteValue 2", (byte) -128),
        Arguments.of("valid byteValue 3", (byte) -127),
        Arguments.of("valid byteValue 4", (byte) 0),
        Arguments.of("valid byteValue 5", (byte) 126),
        Arguments.of("valid byteValue 6", (byte) 127));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("invalidByteValues")
  @DisplayName("rejects invalid value")
  void testRejectsByteValue(String description, byte byteValue) {
    // TODO: add constraints to byteValue, then replace assertThat(obj).isNotNull()
    //       with assertThat(validator.validate(obj)).isNotEmpty()
    SplitGodRecord obj =
        new SplitGodRecord(
            byteValue,
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
    assertThat(obj).isNotNull();
  }

  static Stream<Arguments> invalidByteValues() {
    // TODO: add constraints to byteValue, or delete this test
    return Stream.of(Arguments.of("invalid byteValue 1", (byte) -1));
  }
}
