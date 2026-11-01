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
          Arguments.of("valid byteValue 5", (byte) 126));
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
          Arguments.of("valid byteWrapper 5", (byte) 126));
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
  }

  @Nested
  @DisplayName("GodRecord Field map")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodRecordMapTest {}
}
