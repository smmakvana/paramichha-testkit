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

@DisplayName("SplitGodRecord Field shortWrapper")
@Tag("field")
@TestMethodOrder(MethodOrderer.MethodName.class)
class SplitGodRecordFieldShortWrapperTest {

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
  @MethodSource("validShortWrappers")
  @DisplayName("accepts valid value")
  void testAcceptsShortWrapper(String description, Short shortWrapper) {
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
            shortWrapper,
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
    assertThat(obj.shortWrapper()).isEqualTo(shortWrapper);
  }

  static Stream<Arguments> validShortWrappers() {
    return Stream.of(
        Arguments.of("valid shortWrapper 1", (short) 330),
        Arguments.of("valid shortWrapper 2", (short) -32768),
        Arguments.of("valid shortWrapper 3", (short) -32767),
        Arguments.of("valid shortWrapper 4", (short) 0),
        Arguments.of("valid shortWrapper 5", (short) 32766),
        Arguments.of("valid shortWrapper 6", (short) 32767));
  }
}
