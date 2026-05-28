package com.paramichha.testkit.examples;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

@DisplayName("GodRecord")
class GodRecordTest {

  GodRecord godRecord =
      new GodRecord(
          (byte) 127,
          (short) 669,
          669,
          669L,
          -1.0f,
          -1.0d,
          true,
          'D',
          (byte) 127,
          (short) 669,
          669,
          669L,
          -1.0f,
          -1.0d,
          true,
          'D',
          new byte[] {(byte) 127, (byte) 127},
          new short[] {(short) 669, (short) 761},
          new int[] {669, 761},
          new long[] {669L, 761L},
          new float[] {-1.0f, -1.0f},
          new double[] {-1.0d, -1.0d},
          new boolean[] {true, true},
          new char[] {'D', 'P'},
          new Byte[] {(byte) 127, (byte) 127},
          new Short[] {(short) 669, (short) 761},
          new Integer[] {669, 761},
          new Long[] {669L, 761L},
          new Float[] {-1.0f, -1.0f},
          new Double[] {-1.0d, -1.0d},
          new Boolean[] {true, true},
          new Character[] {'D', 'P'},
          DayOfWeek.MONDAY,
          List.of("ut"),
          Set.of("debitis", "ut"),
          (Map) null);

  @Nested
  @DisplayName("GodRecord Creation")
  @Tag("creation")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordCreationTest {

    @Test
    @DisplayName("can be created")
    void testCanBeCreated() {
      assertThat(godRecord).isNotNull();
    }

    @Test
    @DisplayName("holds expected values")
    void testHoldsExpectedValues() {
      assertThat(godRecord.byteValue()).isEqualTo((byte) 127);
      assertThat(godRecord.shortValue()).isEqualTo((short) 669);
      assertThat(godRecord.intValue()).isEqualTo(669);
      assertThat(godRecord.longValue()).isEqualTo(669L);
      assertThat(godRecord.floatValue()).isEqualTo(-1.0f);
      assertThat(godRecord.doubleValue()).isEqualTo(-1.0d);
      assertThat(godRecord.booleanValue()).isEqualTo(true);
      assertThat(godRecord.charValue()).isEqualTo('D');
      assertThat(godRecord.byteWrapper()).isEqualTo((byte) 127);
      assertThat(godRecord.shortWrapper()).isEqualTo((short) 669);
      assertThat(godRecord.intWrapper()).isEqualTo(669);
      assertThat(godRecord.longWrapper()).isEqualTo(669L);
      assertThat(godRecord.floatWrapper()).isEqualTo(-1.0f);
      assertThat(godRecord.doubleWrapper()).isEqualTo(-1.0d);
      assertThat(godRecord.booleanWrapper()).isEqualTo(true);
      assertThat(godRecord.charWrapper()).isEqualTo('D');
      assertThat(godRecord.byteArray()).isEqualTo(new byte[] {(byte) 127, (byte) 127});
      assertThat(godRecord.shortArray()).isEqualTo(new short[] {(short) 669, (short) 761});
      assertThat(godRecord.intArray()).isEqualTo(new int[] {669, 761});
      assertThat(godRecord.longArray()).isEqualTo(new long[] {669L, 761L});
      assertThat(godRecord.floatArray()).isEqualTo(new float[] {-1.0f, -1.0f});
      assertThat(godRecord.doubleArray()).isEqualTo(new double[] {-1.0d, -1.0d});
      assertThat(godRecord.booleanArray()).isEqualTo(new boolean[] {true, true});
      assertThat(godRecord.charArray()).isEqualTo(new char[] {'D', 'P'});
      assertThat(godRecord.byteWrapperArray()).isEqualTo(new Byte[] {(byte) 127, (byte) 127});
      assertThat(godRecord.shortWrapperArray()).isEqualTo(new Short[] {(short) 669, (short) 761});
      assertThat(godRecord.intWrapperArray()).isEqualTo(new Integer[] {669, 761});
      assertThat(godRecord.longWrapperArray()).isEqualTo(new Long[] {669L, 761L});
      assertThat(godRecord.floatWrapperArray()).isEqualTo(new Float[] {-1.0f, -1.0f});
      assertThat(godRecord.doubleWrapperArray()).isEqualTo(new Double[] {-1.0d, -1.0d});
      assertThat(godRecord.booleanWrapperArray()).isEqualTo(new Boolean[] {true, true});
      assertThat(godRecord.charWrapperArray()).isEqualTo(new Character[] {'D', 'P'});
      assertThat(godRecord.dayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
      assertThat(godRecord.list()).isEqualTo(List.of("ut"));
      assertThat(godRecord.set()).isEqualTo(Set.of("debitis", "ut"));
    }
  }

  @Nested
  @DisplayName("GodRecord Field byteValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordByteValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validByteValues")
    @DisplayName("accepts valid value")
    void testAcceptsByteValue(String description, byte byteValue) {
      GodRecord obj =
          new GodRecord(
              byteValue,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
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
      GodRecord obj =
          new GodRecord(
              byteValue,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidByteValues() {
      // TODO: add constraints to byteValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", (byte) 0));
    }
  }

  @Nested
  @DisplayName("GodRecord Field shortValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordShortValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validShortValues")
    @DisplayName("accepts valid value")
    void testAcceptsShortValue(String description, short shortValue) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              shortValue,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.shortValue()).isEqualTo(shortValue);
    }

    static Stream<Arguments> validShortValues() {
      return Stream.of(
          Arguments.of("valid shortValue 1", (short) 669),
          Arguments.of("valid shortValue 2", (short) -32768),
          Arguments.of("valid shortValue 3", (short) -32767),
          Arguments.of("valid shortValue 4", (short) 0),
          Arguments.of("valid shortValue 5", (short) 32766),
          Arguments.of("valid shortValue 6", (short) 32767));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidShortValues")
    @DisplayName("rejects invalid value")
    void testRejectsShortValue(String description, short shortValue) {
      // TODO: add constraints to shortValue, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              shortValue,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidShortValues() {
      // TODO: add constraints to shortValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", (short) 0));
    }
  }

  @Nested
  @DisplayName("GodRecord Field intValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordIntValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validIntValues")
    @DisplayName("accepts valid value")
    void testAcceptsIntValue(String description, int intValue) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              intValue,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.intValue()).isEqualTo(intValue);
    }

    static Stream<Arguments> validIntValues() {
      return Stream.of(
          Arguments.of("valid intValue 1", 669),
          Arguments.of("valid intValue 2", -2147483648),
          Arguments.of("valid intValue 3", -2147483647),
          Arguments.of("valid intValue 4", 0),
          Arguments.of("valid intValue 5", 2147483646),
          Arguments.of("valid intValue 6", 2147483647));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidIntValues")
    @DisplayName("rejects invalid value")
    void testRejectsIntValue(String description, int intValue) {
      // TODO: add constraints to intValue, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              intValue,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidIntValues() {
      // TODO: add constraints to intValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", 0));
    }
  }

  @Nested
  @DisplayName("GodRecord Field longValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordLongValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLongValues")
    @DisplayName("accepts valid value")
    void testAcceptsLongValue(String description, long longValue) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              longValue,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.longValue()).isEqualTo(longValue);
    }

    static Stream<Arguments> validLongValues() {
      return Stream.of(
          Arguments.of("valid longValue 1", 669L),
          Arguments.of("valid longValue 2", -9223372036854775808L),
          Arguments.of("valid longValue 3", -9223372036854775807L),
          Arguments.of("valid longValue 4", 0L),
          Arguments.of("valid longValue 5", 9223372036854775806L),
          Arguments.of("valid longValue 6", 9223372036854775807L));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLongValues")
    @DisplayName("rejects invalid value")
    void testRejectsLongValue(String description, long longValue) {
      // TODO: add constraints to longValue, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              longValue,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidLongValues() {
      // TODO: add constraints to longValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", 0L));
    }
  }

  @Nested
  @DisplayName("GodRecord Field floatValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordFloatValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFloatValues")
    @DisplayName("accepts valid value")
    void testAcceptsFloatValue(String description, float floatValue) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              floatValue,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.floatValue()).isEqualTo(floatValue);
    }

    static Stream<Arguments> validFloatValues() {
      return Stream.of(
          Arguments.of("valid floatValue 1", -1.0f),
          Arguments.of("valid floatValue 2", 0.0f),
          Arguments.of("valid floatValue 3", 48.85f));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidFloatValues")
    @DisplayName("rejects invalid value")
    void testRejectsFloatValue(String description, float floatValue) {
      // TODO: add constraints to floatValue, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              floatValue,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidFloatValues() {
      // TODO: add constraints to floatValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", 0.0f));
    }
  }

  @Nested
  @DisplayName("GodRecord Field doubleValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordDoubleValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validDoubleValues")
    @DisplayName("accepts valid value")
    void testAcceptsDoubleValue(String description, double doubleValue) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              doubleValue,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.doubleValue()).isEqualTo(doubleValue);
    }

    static Stream<Arguments> validDoubleValues() {
      return Stream.of(
          Arguments.of("valid doubleValue 1", -1.0d),
          Arguments.of("valid doubleValue 2", 0.0d),
          Arguments.of("valid doubleValue 3", 48.85d));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidDoubleValues")
    @DisplayName("rejects invalid value")
    void testRejectsDoubleValue(String description, double doubleValue) {
      // TODO: add constraints to doubleValue, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              doubleValue,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidDoubleValues() {
      // TODO: add constraints to doubleValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", 0.0d));
    }
  }

  @Nested
  @DisplayName("GodRecord Field booleanValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordBooleanValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validBooleanValues")
    @DisplayName("accepts valid value")
    void testAcceptsBooleanValue(String description, boolean booleanValue) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              booleanValue,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.booleanValue()).isEqualTo(booleanValue);
    }

    static Stream<Arguments> validBooleanValues() {
      return Stream.of(
          Arguments.of("valid booleanValue 1", true), Arguments.of("valid booleanValue 2", false));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidBooleanValues")
    @DisplayName("rejects invalid value")
    void testRejectsBooleanValue(String description, boolean booleanValue) {
      // TODO: add constraints to booleanValue, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              booleanValue,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidBooleanValues() {
      // TODO: add constraints to booleanValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", false));
    }
  }

  @Nested
  @DisplayName("GodRecord Field charValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordCharValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCharValues")
    @DisplayName("accepts valid value")
    void testAcceptsCharValue(String description, char charValue) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              charValue,
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.charValue()).isEqualTo(charValue);
    }

    static Stream<Arguments> validCharValues() {
      return Stream.of(Arguments.of("valid charValue 1", 'D'));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCharValues")
    @DisplayName("rejects invalid value")
    void testRejectsCharValue(String description, char charValue) {
      // TODO: add constraints to charValue, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              charValue,
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidCharValues() {
      // TODO: add constraints to charValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", '\0'));
    }
  }

  @Nested
  @DisplayName("GodRecord Field byteWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordByteWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validByteWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsByteWrapper(String description, Byte byteWrapper) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              byteWrapper,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.byteWrapper()).isEqualTo(byteWrapper);
    }

    static Stream<Arguments> validByteWrappers() {
      return Stream.of(
          Arguments.of("valid byteWrapper 1", (byte) 127),
          Arguments.of("valid byteWrapper 2", (byte) -128),
          Arguments.of("valid byteWrapper 3", (byte) -127),
          Arguments.of("valid byteWrapper 4", (byte) 0),
          Arguments.of("valid byteWrapper 5", (byte) 126),
          Arguments.of("valid byteWrapper 6", (byte) 127));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidByteWrappers")
    @DisplayName("rejects invalid value")
    void testRejectsByteWrapper(String description, Byte byteWrapper) {
      // TODO: add constraints to byteWrapper, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              byteWrapper,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidByteWrappers() {
      // TODO: add constraints to byteWrapper, or delete this test
      return Stream.of(
          Arguments.of("null byteWrapper", (Byte) null),
          Arguments.of("invalid byteWrapper 2", (byte) -1));
    }
  }

  @Nested
  @DisplayName("GodRecord Field shortWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordShortWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validShortWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsShortWrapper(String description, Short shortWrapper) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              shortWrapper,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.shortWrapper()).isEqualTo(shortWrapper);
    }

    static Stream<Arguments> validShortWrappers() {
      return Stream.of(
          Arguments.of("valid shortWrapper 1", (short) 669),
          Arguments.of("valid shortWrapper 2", (short) -32768),
          Arguments.of("valid shortWrapper 3", (short) -32767),
          Arguments.of("valid shortWrapper 4", (short) 0),
          Arguments.of("valid shortWrapper 5", (short) 32766),
          Arguments.of("valid shortWrapper 6", (short) 32767));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidShortWrappers")
    @DisplayName("rejects invalid value")
    void testRejectsShortWrapper(String description, Short shortWrapper) {
      // TODO: add constraints to shortWrapper, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              shortWrapper,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidShortWrappers() {
      // TODO: add constraints to shortWrapper, or delete this test
      return Stream.of(
          Arguments.of("null shortWrapper", (Short) null),
          Arguments.of("invalid shortWrapper 2", (short) -1));
    }
  }

  @Nested
  @DisplayName("GodRecord Field intWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordIntWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validIntWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsIntWrapper(String description, Integer intWrapper) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              intWrapper,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.intWrapper()).isEqualTo(intWrapper);
    }

    static Stream<Arguments> validIntWrappers() {
      return Stream.of(
          Arguments.of("valid intWrapper 1", 669),
          Arguments.of("valid intWrapper 2", -2147483648),
          Arguments.of("valid intWrapper 3", -2147483647),
          Arguments.of("valid intWrapper 4", 0),
          Arguments.of("valid intWrapper 5", 2147483646),
          Arguments.of("valid intWrapper 6", 2147483647));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidIntWrappers")
    @DisplayName("rejects invalid value")
    void testRejectsIntWrapper(String description, Integer intWrapper) {
      // TODO: add constraints to intWrapper, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              intWrapper,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidIntWrappers() {
      // TODO: add constraints to intWrapper, or delete this test
      return Stream.of(
          Arguments.of("null intWrapper", (Integer) null),
          Arguments.of("invalid intWrapper 2", -1));
    }
  }

  @Nested
  @DisplayName("GodRecord Field longWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordLongWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLongWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsLongWrapper(String description, Long longWrapper) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              longWrapper,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.longWrapper()).isEqualTo(longWrapper);
    }

    static Stream<Arguments> validLongWrappers() {
      return Stream.of(
          Arguments.of("valid longWrapper 1", 669L),
          Arguments.of("valid longWrapper 2", -9223372036854775808L),
          Arguments.of("valid longWrapper 3", -9223372036854775807L),
          Arguments.of("valid longWrapper 4", 0L),
          Arguments.of("valid longWrapper 5", 9223372036854775806L),
          Arguments.of("valid longWrapper 6", 9223372036854775807L));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLongWrappers")
    @DisplayName("rejects invalid value")
    void testRejectsLongWrapper(String description, Long longWrapper) {
      // TODO: add constraints to longWrapper, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              longWrapper,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidLongWrappers() {
      // TODO: add constraints to longWrapper, or delete this test
      return Stream.of(
          Arguments.of("null longWrapper", (Long) null),
          Arguments.of("invalid longWrapper 2", -1L));
    }
  }

  @Nested
  @DisplayName("GodRecord Field floatWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordFloatWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFloatWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsFloatWrapper(String description, Float floatWrapper) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              floatWrapper,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.floatWrapper()).isEqualTo(floatWrapper);
    }

    static Stream<Arguments> validFloatWrappers() {
      return Stream.of(
          Arguments.of("valid floatWrapper 1", -1.0f),
          Arguments.of("valid floatWrapper 2", 0.0f),
          Arguments.of("valid floatWrapper 3", 48.85f));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidFloatWrappers")
    @DisplayName("rejects invalid value")
    void testRejectsFloatWrapper(String description, Float floatWrapper) {
      // TODO: add constraints to floatWrapper, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              floatWrapper,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidFloatWrappers() {
      // TODO: add constraints to floatWrapper, or delete this test
      return Stream.of(
          Arguments.of("null floatWrapper", (Float) null),
          Arguments.of("invalid floatWrapper 2", -1.0f));
    }
  }

  @Nested
  @DisplayName("GodRecord Field doubleWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordDoubleWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validDoubleWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsDoubleWrapper(String description, Double doubleWrapper) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              doubleWrapper,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.doubleWrapper()).isEqualTo(doubleWrapper);
    }

    static Stream<Arguments> validDoubleWrappers() {
      return Stream.of(
          Arguments.of("valid doubleWrapper 1", -1.0d),
          Arguments.of("valid doubleWrapper 2", 0.0d),
          Arguments.of("valid doubleWrapper 3", 48.85d));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidDoubleWrappers")
    @DisplayName("rejects invalid value")
    void testRejectsDoubleWrapper(String description, Double doubleWrapper) {
      // TODO: add constraints to doubleWrapper, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              doubleWrapper,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidDoubleWrappers() {
      // TODO: add constraints to doubleWrapper, or delete this test
      return Stream.of(
          Arguments.of("null doubleWrapper", (Double) null),
          Arguments.of("invalid doubleWrapper 2", -1.0d));
    }
  }

  @Nested
  @DisplayName("GodRecord Field booleanWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordBooleanWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validBooleanWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsBooleanWrapper(String description, Boolean booleanWrapper) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              booleanWrapper,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.booleanWrapper()).isEqualTo(booleanWrapper);
    }

    static Stream<Arguments> validBooleanWrappers() {
      return Stream.of(
          Arguments.of("valid booleanWrapper 1", true),
          Arguments.of("valid booleanWrapper 2", false));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidBooleanWrappers")
    @DisplayName("rejects invalid value")
    void testRejectsBooleanWrapper(String description, Boolean booleanWrapper) {
      // TODO: add constraints to booleanWrapper, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              booleanWrapper,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidBooleanWrappers() {
      // TODO: add constraints to booleanWrapper, or delete this test
      return Stream.of(
          Arguments.of("null booleanWrapper", (Boolean) null),
          Arguments.of("invalid booleanWrapper 2", false));
    }
  }

  @Nested
  @DisplayName("GodRecord Field charWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordCharWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCharWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsCharWrapper(String description, Character charWrapper) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              charWrapper,
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.charWrapper()).isEqualTo(charWrapper);
    }

    static Stream<Arguments> validCharWrappers() {
      return Stream.of(Arguments.of("valid charWrapper 1", 'D'));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCharWrappers")
    @DisplayName("rejects invalid value")
    void testRejectsCharWrapper(String description, Character charWrapper) {
      // TODO: add constraints to charWrapper, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              charWrapper,
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidCharWrappers() {
      // TODO: add constraints to charWrapper, or delete this test
      return Stream.of(Arguments.of("null charWrapper", (Character) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field byteArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordByteArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validByteArrays")
    @DisplayName("accepts valid value")
    void testAcceptsByteArray(String description, byte[] byteArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              byteArray,
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.byteArray()).isEqualTo(byteArray);
    }

    static Stream<Arguments> validByteArrays() {
      return Stream.of(Arguments.of("valid byteArray 1", new byte[] {(byte) 127, (byte) 127}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidByteArrays")
    @DisplayName("rejects invalid value")
    void testRejectsByteArray(String description, byte[] byteArray) {
      // TODO: add constraints to byteArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              byteArray,
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidByteArrays() {
      // TODO: add constraints to byteArray, or delete this test
      return Stream.of(Arguments.of("null byteArray", (byte[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field shortArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordShortArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validShortArrays")
    @DisplayName("accepts valid value")
    void testAcceptsShortArray(String description, short[] shortArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              shortArray,
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.shortArray()).isEqualTo(shortArray);
    }

    static Stream<Arguments> validShortArrays() {
      return Stream.of(Arguments.of("valid shortArray 1", new short[] {(short) 669, (short) 761}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidShortArrays")
    @DisplayName("rejects invalid value")
    void testRejectsShortArray(String description, short[] shortArray) {
      // TODO: add constraints to shortArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              shortArray,
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidShortArrays() {
      // TODO: add constraints to shortArray, or delete this test
      return Stream.of(Arguments.of("null shortArray", (short[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field intArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordIntArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validIntArrays")
    @DisplayName("accepts valid value")
    void testAcceptsIntArray(String description, int[] intArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              intArray,
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.intArray()).isEqualTo(intArray);
    }

    static Stream<Arguments> validIntArrays() {
      return Stream.of(Arguments.of("valid intArray 1", new int[] {669, 761}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidIntArrays")
    @DisplayName("rejects invalid value")
    void testRejectsIntArray(String description, int[] intArray) {
      // TODO: add constraints to intArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              intArray,
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidIntArrays() {
      // TODO: add constraints to intArray, or delete this test
      return Stream.of(Arguments.of("null intArray", (int[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field longArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordLongArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLongArrays")
    @DisplayName("accepts valid value")
    void testAcceptsLongArray(String description, long[] longArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              longArray,
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.longArray()).isEqualTo(longArray);
    }

    static Stream<Arguments> validLongArrays() {
      return Stream.of(Arguments.of("valid longArray 1", new long[] {669L, 761L}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLongArrays")
    @DisplayName("rejects invalid value")
    void testRejectsLongArray(String description, long[] longArray) {
      // TODO: add constraints to longArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              longArray,
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidLongArrays() {
      // TODO: add constraints to longArray, or delete this test
      return Stream.of(Arguments.of("null longArray", (long[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field floatArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordFloatArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFloatArrays")
    @DisplayName("accepts valid value")
    void testAcceptsFloatArray(String description, float[] floatArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              floatArray,
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.floatArray()).isEqualTo(floatArray);
    }

    static Stream<Arguments> validFloatArrays() {
      return Stream.of(Arguments.of("valid floatArray 1", new float[] {-1.0f, -1.0f}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidFloatArrays")
    @DisplayName("rejects invalid value")
    void testRejectsFloatArray(String description, float[] floatArray) {
      // TODO: add constraints to floatArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              floatArray,
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidFloatArrays() {
      // TODO: add constraints to floatArray, or delete this test
      return Stream.of(Arguments.of("null floatArray", (float[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field doubleArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordDoubleArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validDoubleArrays")
    @DisplayName("accepts valid value")
    void testAcceptsDoubleArray(String description, double[] doubleArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              doubleArray,
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.doubleArray()).isEqualTo(doubleArray);
    }

    static Stream<Arguments> validDoubleArrays() {
      return Stream.of(Arguments.of("valid doubleArray 1", new double[] {-1.0d, -1.0d}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidDoubleArrays")
    @DisplayName("rejects invalid value")
    void testRejectsDoubleArray(String description, double[] doubleArray) {
      // TODO: add constraints to doubleArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              doubleArray,
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidDoubleArrays() {
      // TODO: add constraints to doubleArray, or delete this test
      return Stream.of(Arguments.of("null doubleArray", (double[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field booleanArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordBooleanArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validBooleanArrays")
    @DisplayName("accepts valid value")
    void testAcceptsBooleanArray(String description, boolean[] booleanArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              booleanArray,
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.booleanArray()).isEqualTo(booleanArray);
    }

    static Stream<Arguments> validBooleanArrays() {
      return Stream.of(Arguments.of("valid booleanArray 1", new boolean[] {true, true}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidBooleanArrays")
    @DisplayName("rejects invalid value")
    void testRejectsBooleanArray(String description, boolean[] booleanArray) {
      // TODO: add constraints to booleanArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              booleanArray,
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidBooleanArrays() {
      // TODO: add constraints to booleanArray, or delete this test
      return Stream.of(Arguments.of("null booleanArray", (boolean[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field charArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordCharArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCharArrays")
    @DisplayName("accepts valid value")
    void testAcceptsCharArray(String description, char[] charArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              charArray,
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.charArray()).isEqualTo(charArray);
    }

    static Stream<Arguments> validCharArrays() {
      return Stream.of(Arguments.of("valid charArray 1", new char[] {'D', 'P'}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCharArrays")
    @DisplayName("rejects invalid value")
    void testRejectsCharArray(String description, char[] charArray) {
      // TODO: add constraints to charArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              charArray,
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidCharArrays() {
      // TODO: add constraints to charArray, or delete this test
      return Stream.of(Arguments.of("null charArray", (char[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field byteWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordByteWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validByteWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsByteWrapperArray(String description, Byte[] byteWrapperArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              byteWrapperArray,
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.byteWrapperArray()).isEqualTo(byteWrapperArray);
    }

    static Stream<Arguments> validByteWrapperArrays() {
      return Stream.of(
          Arguments.of("valid byteWrapperArray 1", new Byte[] {(byte) 127, (byte) 127}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidByteWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsByteWrapperArray(String description, Byte[] byteWrapperArray) {
      // TODO: add constraints to byteWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              byteWrapperArray,
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidByteWrapperArrays() {
      // TODO: add constraints to byteWrapperArray, or delete this test
      return Stream.of(Arguments.of("null byteWrapperArray", (Byte[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field shortWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordShortWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validShortWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsShortWrapperArray(String description, Short[] shortWrapperArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              shortWrapperArray,
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.shortWrapperArray()).isEqualTo(shortWrapperArray);
    }

    static Stream<Arguments> validShortWrapperArrays() {
      return Stream.of(
          Arguments.of("valid shortWrapperArray 1", new Short[] {(short) 669, (short) 761}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidShortWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsShortWrapperArray(String description, Short[] shortWrapperArray) {
      // TODO: add constraints to shortWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              shortWrapperArray,
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidShortWrapperArrays() {
      // TODO: add constraints to shortWrapperArray, or delete this test
      return Stream.of(Arguments.of("null shortWrapperArray", (Short[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field intWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordIntWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validIntWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsIntWrapperArray(String description, Integer[] intWrapperArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              intWrapperArray,
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.intWrapperArray()).isEqualTo(intWrapperArray);
    }

    static Stream<Arguments> validIntWrapperArrays() {
      return Stream.of(Arguments.of("valid intWrapperArray 1", new Integer[] {669, 761}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidIntWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsIntWrapperArray(String description, Integer[] intWrapperArray) {
      // TODO: add constraints to intWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              intWrapperArray,
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidIntWrapperArrays() {
      // TODO: add constraints to intWrapperArray, or delete this test
      return Stream.of(Arguments.of("null intWrapperArray", (Integer[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field longWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordLongWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLongWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsLongWrapperArray(String description, Long[] longWrapperArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              longWrapperArray,
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.longWrapperArray()).isEqualTo(longWrapperArray);
    }

    static Stream<Arguments> validLongWrapperArrays() {
      return Stream.of(Arguments.of("valid longWrapperArray 1", new Long[] {669L, 761L}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLongWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsLongWrapperArray(String description, Long[] longWrapperArray) {
      // TODO: add constraints to longWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              longWrapperArray,
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidLongWrapperArrays() {
      // TODO: add constraints to longWrapperArray, or delete this test
      return Stream.of(Arguments.of("null longWrapperArray", (Long[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field floatWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordFloatWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFloatWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsFloatWrapperArray(String description, Float[] floatWrapperArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              floatWrapperArray,
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.floatWrapperArray()).isEqualTo(floatWrapperArray);
    }

    static Stream<Arguments> validFloatWrapperArrays() {
      return Stream.of(Arguments.of("valid floatWrapperArray 1", new Float[] {-1.0f, -1.0f}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidFloatWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsFloatWrapperArray(String description, Float[] floatWrapperArray) {
      // TODO: add constraints to floatWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              floatWrapperArray,
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidFloatWrapperArrays() {
      // TODO: add constraints to floatWrapperArray, or delete this test
      return Stream.of(Arguments.of("null floatWrapperArray", (Float[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field doubleWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordDoubleWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validDoubleWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsDoubleWrapperArray(String description, Double[] doubleWrapperArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              doubleWrapperArray,
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.doubleWrapperArray()).isEqualTo(doubleWrapperArray);
    }

    static Stream<Arguments> validDoubleWrapperArrays() {
      return Stream.of(Arguments.of("valid doubleWrapperArray 1", new Double[] {-1.0d, -1.0d}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidDoubleWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsDoubleWrapperArray(String description, Double[] doubleWrapperArray) {
      // TODO: add constraints to doubleWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              doubleWrapperArray,
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidDoubleWrapperArrays() {
      // TODO: add constraints to doubleWrapperArray, or delete this test
      return Stream.of(Arguments.of("null doubleWrapperArray", (Double[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field booleanWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordBooleanWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validBooleanWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsBooleanWrapperArray(String description, Boolean[] booleanWrapperArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              booleanWrapperArray,
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.booleanWrapperArray()).isEqualTo(booleanWrapperArray);
    }

    static Stream<Arguments> validBooleanWrapperArrays() {
      return Stream.of(Arguments.of("valid booleanWrapperArray 1", new Boolean[] {true, true}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidBooleanWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsBooleanWrapperArray(String description, Boolean[] booleanWrapperArray) {
      // TODO: add constraints to booleanWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              booleanWrapperArray,
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidBooleanWrapperArrays() {
      // TODO: add constraints to booleanWrapperArray, or delete this test
      return Stream.of(Arguments.of("null booleanWrapperArray", (Boolean[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field charWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordCharWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCharWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsCharWrapperArray(String description, Character[] charWrapperArray) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              charWrapperArray,
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.charWrapperArray()).isEqualTo(charWrapperArray);
    }

    static Stream<Arguments> validCharWrapperArrays() {
      return Stream.of(Arguments.of("valid charWrapperArray 1", new Character[] {'D', 'P'}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCharWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsCharWrapperArray(String description, Character[] charWrapperArray) {
      // TODO: add constraints to charWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              charWrapperArray,
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidCharWrapperArrays() {
      // TODO: add constraints to charWrapperArray, or delete this test
      return Stream.of(Arguments.of("null charWrapperArray", (Character[]) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field dayOfWeek")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordDayOfWeekTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validDayOfWeeks")
    @DisplayName("accepts valid value")
    void testAcceptsDayOfWeek(String description, DayOfWeek dayOfWeek) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              dayOfWeek,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.dayOfWeek()).isEqualTo(dayOfWeek);
    }

    static Stream<Arguments> validDayOfWeeks() {
      return Stream.of(
          Arguments.of("valid dayOfWeek 1", DayOfWeek.MONDAY),
          Arguments.of("valid dayOfWeek 2", DayOfWeek.TUESDAY),
          Arguments.of("valid dayOfWeek 3", DayOfWeek.WEDNESDAY),
          Arguments.of("valid dayOfWeek 4", DayOfWeek.THURSDAY),
          Arguments.of("valid dayOfWeek 5", DayOfWeek.FRIDAY),
          Arguments.of("valid dayOfWeek 6", DayOfWeek.SATURDAY),
          Arguments.of("valid dayOfWeek 7", DayOfWeek.SUNDAY));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidDayOfWeeks")
    @DisplayName("rejects invalid value")
    void testRejectsDayOfWeek(String description, DayOfWeek dayOfWeek) {
      // TODO: add constraints to dayOfWeek, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              dayOfWeek,
              List.of("ut"),
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidDayOfWeeks() {
      // TODO: add constraints to dayOfWeek, or delete this test
      return Stream.of(Arguments.of("null dayOfWeek", (DayOfWeek) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field list")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordListTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLists")
    @DisplayName("accepts valid value")
    void testAcceptsList(String description, List list) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              list,
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj.list()).isEqualTo(list);
    }

    static Stream<Arguments> validLists() {
      return Stream.of(Arguments.of("valid list 1", List.of("ut")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLists")
    @DisplayName("rejects invalid value")
    void testRejectsList(String description, List list) {
      // TODO: add constraints to list, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              list,
              Set.of("debitis", "ut"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidLists() {
      // TODO: add constraints to list, or delete this test
      return Stream.of(
          Arguments.of("null list", (List) null), Arguments.of("invalid list 2", List.of()));
    }
  }

  @Nested
  @DisplayName("GodRecord Field set")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordSetTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validSets")
    @DisplayName("accepts valid value")
    void testAcceptsSet(String description, Set set) {
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              set,
              (Map) null);
      assertThat(obj.set()).isEqualTo(set);
    }

    static Stream<Arguments> validSets() {
      return Stream.of(Arguments.of("valid set 1", Set.of("debitis", "ut")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidSets")
    @DisplayName("rejects invalid value")
    void testRejectsSet(String description, Set set) {
      // TODO: add constraints to set, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              set,
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidSets() {
      // TODO: add constraints to set, or delete this test
      return Stream.of(Arguments.of("null set", (Set) null));
    }
  }

  @Nested
  @DisplayName("GodRecord Field map")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordMapTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidMaps")
    @DisplayName("rejects invalid value")
    void testRejectsMap(String description, Map map) {
      // TODO: add constraints to map, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodRecord obj =
          new GodRecord(
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              (byte) 127,
              (short) 669,
              669,
              669L,
              -1.0f,
              -1.0d,
              true,
              'D',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 669, (short) 761},
              new int[] {669, 761},
              new long[] {669L, 761L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'D', 'P'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 669, (short) 761},
              new Integer[] {669, 761},
              new Long[] {669L, 761L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'D', 'P'},
              DayOfWeek.MONDAY,
              List.of("ut"),
              Set.of("debitis", "ut"),
              map);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidMaps() {
      // TODO: add constraints to map, or delete this test
      return Stream.of(Arguments.of("null map", (Map) null));
    }
  }
}
