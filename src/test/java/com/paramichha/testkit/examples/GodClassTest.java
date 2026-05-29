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

@DisplayName("GodClass")
class GodClassTest {

  GodClass godClass =
      new GodClass(
          (byte) 127,
          (short) 791,
          791,
          791L,
          -1.0f,
          -1.0d,
          true,
          'I',
          (byte) 127,
          (short) 791,
          791,
          791L,
          -1.0f,
          -1.0d,
          true,
          'I',
          new byte[] {(byte) 127, (byte) 127},
          new short[] {(short) 791, (short) 858},
          new int[] {791, 858},
          new long[] {791L, 858L},
          new float[] {-1.0f, -1.0f},
          new double[] {-1.0d, -1.0d},
          new boolean[] {true, true},
          new char[] {'I', 'Y'},
          new Byte[] {(byte) 127, (byte) 127},
          new Short[] {(short) 791, (short) 858},
          new Integer[] {791, 858},
          new Long[] {791L, 858L},
          new Float[] {-1.0f, -1.0f},
          new Double[] {-1.0d, -1.0d},
          new Boolean[] {true, true},
          new Character[] {'I', 'Y'},
          DayOfWeek.MONDAY,
          List.of("voluptates"),
          Set.of("voluptates", "consectetur"),
          (Map) null);

  @Nested
  @DisplayName("GodClass Creation")
  @Tag("creation")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassCreationTest {

    @Test
    @DisplayName("can be created")
    void testCanBeCreated() {
      assertThat(godClass).isNotNull();
    }

    @Test
    @DisplayName("holds expected values")
    void testHoldsExpectedValues() {
      assertThat(godClass.getByteValue()).isEqualTo((byte) 127);
      assertThat(godClass.getShortValue()).isEqualTo((short) 791);
      assertThat(godClass.getIntValue()).isEqualTo(791);
      assertThat(godClass.getLongValue()).isEqualTo(791L);
      assertThat(godClass.getFloatValue()).isEqualTo(-1.0f);
      assertThat(godClass.getDoubleValue()).isEqualTo(-1.0d);
      assertThat(godClass.isBooleanValue()).isEqualTo(true);
      assertThat(godClass.getCharValue()).isEqualTo('I');
      assertThat(godClass.getByteWrapper()).isEqualTo((byte) 127);
      assertThat(godClass.getShortWrapper()).isEqualTo((short) 791);
      assertThat(godClass.getIntWrapper()).isEqualTo(791);
      assertThat(godClass.getLongWrapper()).isEqualTo(791L);
      assertThat(godClass.getFloatWrapper()).isEqualTo(-1.0f);
      assertThat(godClass.getDoubleWrapper()).isEqualTo(-1.0d);
      assertThat(godClass.getBooleanWrapper()).isEqualTo(true);
      assertThat(godClass.getCharWrapper()).isEqualTo('I');
      assertThat(godClass.getByteArray()).isEqualTo(new byte[] {(byte) 127, (byte) 127});
      assertThat(godClass.getShortArray()).isEqualTo(new short[] {(short) 791, (short) 858});
      assertThat(godClass.getIntArray()).isEqualTo(new int[] {791, 858});
      assertThat(godClass.getLongArray()).isEqualTo(new long[] {791L, 858L});
      assertThat(godClass.getFloatArray()).isEqualTo(new float[] {-1.0f, -1.0f});
      assertThat(godClass.getDoubleArray()).isEqualTo(new double[] {-1.0d, -1.0d});
      assertThat(godClass.getBooleanArray()).isEqualTo(new boolean[] {true, true});
      assertThat(godClass.getCharArray()).isEqualTo(new char[] {'I', 'Y'});
      assertThat(godClass.getByteWrapperArray()).isEqualTo(new Byte[] {(byte) 127, (byte) 127});
      assertThat(godClass.getShortWrapperArray()).isEqualTo(new Short[] {(short) 791, (short) 858});
      assertThat(godClass.getIntWrapperArray()).isEqualTo(new Integer[] {791, 858});
      assertThat(godClass.getLongWrapperArray()).isEqualTo(new Long[] {791L, 858L});
      assertThat(godClass.getFloatWrapperArray()).isEqualTo(new Float[] {-1.0f, -1.0f});
      assertThat(godClass.getDoubleWrapperArray()).isEqualTo(new Double[] {-1.0d, -1.0d});
      assertThat(godClass.getBooleanWrapperArray()).isEqualTo(new Boolean[] {true, true});
      assertThat(godClass.getCharWrapperArray()).isEqualTo(new Character[] {'I', 'Y'});
      assertThat(godClass.getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
      assertThat(godClass.getList()).isEqualTo(List.of("voluptates"));
      assertThat(godClass.getSet()).isEqualTo(Set.of("voluptates", "consectetur"));
    }
  }

  @Nested
  @DisplayName("GodClass Field byteValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassByteValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validByteValues")
    @DisplayName("accepts valid value")
    void testAcceptsByteValue(String description, byte byteValue) {
      GodClass obj =
          new GodClass(
              byteValue,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getByteValue()).isEqualTo(byteValue);
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
      GodClass obj =
          new GodClass(
              byteValue,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidByteValues() {
      // TODO: add constraints to byteValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", (byte) 0));
    }
  }

  @Nested
  @DisplayName("GodClass Field shortValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassShortValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validShortValues")
    @DisplayName("accepts valid value")
    void testAcceptsShortValue(String description, short shortValue) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              shortValue,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getShortValue()).isEqualTo(shortValue);
    }

    static Stream<Arguments> validShortValues() {
      return Stream.of(
          Arguments.of("valid shortValue 1", (short) 791),
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              shortValue,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidShortValues() {
      // TODO: add constraints to shortValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", (short) 0));
    }
  }

  @Nested
  @DisplayName("GodClass Field intValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassIntValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validIntValues")
    @DisplayName("accepts valid value")
    void testAcceptsIntValue(String description, int intValue) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              intValue,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getIntValue()).isEqualTo(intValue);
    }

    static Stream<Arguments> validIntValues() {
      return Stream.of(
          Arguments.of("valid intValue 1", 791),
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              intValue,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidIntValues() {
      // TODO: add constraints to intValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", 0));
    }
  }

  @Nested
  @DisplayName("GodClass Field longValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassLongValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLongValues")
    @DisplayName("accepts valid value")
    void testAcceptsLongValue(String description, long longValue) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              longValue,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getLongValue()).isEqualTo(longValue);
    }

    static Stream<Arguments> validLongValues() {
      return Stream.of(
          Arguments.of("valid longValue 1", 791L),
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              longValue,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidLongValues() {
      // TODO: add constraints to longValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", 0L));
    }
  }

  @Nested
  @DisplayName("GodClass Field floatValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassFloatValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFloatValues")
    @DisplayName("accepts valid value")
    void testAcceptsFloatValue(String description, float floatValue) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              floatValue,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getFloatValue()).isEqualTo(floatValue);
    }

    static Stream<Arguments> validFloatValues() {
      return Stream.of(
          Arguments.of("valid floatValue 1", -1.0f),
          Arguments.of("valid floatValue 2", 0.0f),
          Arguments.of("valid floatValue 3", 448.52f));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidFloatValues")
    @DisplayName("rejects invalid value")
    void testRejectsFloatValue(String description, float floatValue) {
      // TODO: add constraints to floatValue, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              floatValue,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidFloatValues() {
      // TODO: add constraints to floatValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", 0.0f));
    }
  }

  @Nested
  @DisplayName("GodClass Field doubleValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassDoubleValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validDoubleValues")
    @DisplayName("accepts valid value")
    void testAcceptsDoubleValue(String description, double doubleValue) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              doubleValue,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getDoubleValue()).isEqualTo(doubleValue);
    }

    static Stream<Arguments> validDoubleValues() {
      return Stream.of(
          Arguments.of("valid doubleValue 1", -1.0d),
          Arguments.of("valid doubleValue 2", 0.0d),
          Arguments.of("valid doubleValue 3", 448.52d));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidDoubleValues")
    @DisplayName("rejects invalid value")
    void testRejectsDoubleValue(String description, double doubleValue) {
      // TODO: add constraints to doubleValue, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              doubleValue,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidDoubleValues() {
      // TODO: add constraints to doubleValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", 0.0d));
    }
  }

  @Nested
  @DisplayName("GodClass Field booleanValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassBooleanValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validBooleanValues")
    @DisplayName("accepts valid value")
    void testAcceptsBooleanValue(String description, boolean booleanValue) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              booleanValue,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.isBooleanValue()).isEqualTo(booleanValue);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              booleanValue,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidBooleanValues() {
      // TODO: add constraints to booleanValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", false));
    }
  }

  @Nested
  @DisplayName("GodClass Field charValue")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassCharValueTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCharValues")
    @DisplayName("accepts valid value")
    void testAcceptsCharValue(String description, char charValue) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              charValue,
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getCharValue()).isEqualTo(charValue);
    }

    static Stream<Arguments> validCharValues() {
      return Stream.of(Arguments.of("valid charValue 1", 'I'));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCharValues")
    @DisplayName("rejects invalid value")
    void testRejectsCharValue(String description, char charValue) {
      // TODO: add constraints to charValue, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              charValue,
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidCharValues() {
      // TODO: add constraints to charValue, or delete this test
      return Stream.of(Arguments.of("no constraints defined — add some!", '\0'));
    }
  }

  @Nested
  @DisplayName("GodClass Field byteWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassByteWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validByteWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsByteWrapper(String description, Byte byteWrapper) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              byteWrapper,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getByteWrapper()).isEqualTo(byteWrapper);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              byteWrapper,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
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
  @DisplayName("GodClass Field shortWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassShortWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validShortWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsShortWrapper(String description, Short shortWrapper) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              shortWrapper,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getShortWrapper()).isEqualTo(shortWrapper);
    }

    static Stream<Arguments> validShortWrappers() {
      return Stream.of(
          Arguments.of("valid shortWrapper 1", (short) 791),
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              shortWrapper,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
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
  @DisplayName("GodClass Field intWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassIntWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validIntWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsIntWrapper(String description, Integer intWrapper) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              intWrapper,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getIntWrapper()).isEqualTo(intWrapper);
    }

    static Stream<Arguments> validIntWrappers() {
      return Stream.of(
          Arguments.of("valid intWrapper 1", 791),
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              intWrapper,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
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
  @DisplayName("GodClass Field longWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassLongWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLongWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsLongWrapper(String description, Long longWrapper) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              longWrapper,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getLongWrapper()).isEqualTo(longWrapper);
    }

    static Stream<Arguments> validLongWrappers() {
      return Stream.of(
          Arguments.of("valid longWrapper 1", 791L),
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              longWrapper,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
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
  @DisplayName("GodClass Field floatWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassFloatWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFloatWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsFloatWrapper(String description, Float floatWrapper) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              floatWrapper,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getFloatWrapper()).isEqualTo(floatWrapper);
    }

    static Stream<Arguments> validFloatWrappers() {
      return Stream.of(
          Arguments.of("valid floatWrapper 1", -1.0f),
          Arguments.of("valid floatWrapper 2", 0.0f),
          Arguments.of("valid floatWrapper 3", 448.52f));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidFloatWrappers")
    @DisplayName("rejects invalid value")
    void testRejectsFloatWrapper(String description, Float floatWrapper) {
      // TODO: add constraints to floatWrapper, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              floatWrapper,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
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
  @DisplayName("GodClass Field doubleWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassDoubleWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validDoubleWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsDoubleWrapper(String description, Double doubleWrapper) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              doubleWrapper,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getDoubleWrapper()).isEqualTo(doubleWrapper);
    }

    static Stream<Arguments> validDoubleWrappers() {
      return Stream.of(
          Arguments.of("valid doubleWrapper 1", -1.0d),
          Arguments.of("valid doubleWrapper 2", 0.0d),
          Arguments.of("valid doubleWrapper 3", 448.52d));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidDoubleWrappers")
    @DisplayName("rejects invalid value")
    void testRejectsDoubleWrapper(String description, Double doubleWrapper) {
      // TODO: add constraints to doubleWrapper, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              doubleWrapper,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
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
  @DisplayName("GodClass Field booleanWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassBooleanWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validBooleanWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsBooleanWrapper(String description, Boolean booleanWrapper) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              booleanWrapper,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getBooleanWrapper()).isEqualTo(booleanWrapper);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              booleanWrapper,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
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
  @DisplayName("GodClass Field charWrapper")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassCharWrapperTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCharWrappers")
    @DisplayName("accepts valid value")
    void testAcceptsCharWrapper(String description, Character charWrapper) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              charWrapper,
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getCharWrapper()).isEqualTo(charWrapper);
    }

    static Stream<Arguments> validCharWrappers() {
      return Stream.of(Arguments.of("valid charWrapper 1", 'I'));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCharWrappers")
    @DisplayName("rejects invalid value")
    void testRejectsCharWrapper(String description, Character charWrapper) {
      // TODO: add constraints to charWrapper, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              charWrapper,
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidCharWrappers() {
      // TODO: add constraints to charWrapper, or delete this test
      return Stream.of(Arguments.of("null charWrapper", (Character) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field byteArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassByteArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validByteArrays")
    @DisplayName("accepts valid value")
    void testAcceptsByteArray(String description, byte[] byteArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              byteArray,
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getByteArray()).isEqualTo(byteArray);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              byteArray,
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidByteArrays() {
      // TODO: add constraints to byteArray, or delete this test
      return Stream.of(Arguments.of("null byteArray", (byte[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field shortArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassShortArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validShortArrays")
    @DisplayName("accepts valid value")
    void testAcceptsShortArray(String description, short[] shortArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              shortArray,
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getShortArray()).isEqualTo(shortArray);
    }

    static Stream<Arguments> validShortArrays() {
      return Stream.of(Arguments.of("valid shortArray 1", new short[] {(short) 791, (short) 858}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidShortArrays")
    @DisplayName("rejects invalid value")
    void testRejectsShortArray(String description, short[] shortArray) {
      // TODO: add constraints to shortArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              shortArray,
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidShortArrays() {
      // TODO: add constraints to shortArray, or delete this test
      return Stream.of(Arguments.of("null shortArray", (short[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field intArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassIntArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validIntArrays")
    @DisplayName("accepts valid value")
    void testAcceptsIntArray(String description, int[] intArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              intArray,
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getIntArray()).isEqualTo(intArray);
    }

    static Stream<Arguments> validIntArrays() {
      return Stream.of(Arguments.of("valid intArray 1", new int[] {791, 858}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidIntArrays")
    @DisplayName("rejects invalid value")
    void testRejectsIntArray(String description, int[] intArray) {
      // TODO: add constraints to intArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              intArray,
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidIntArrays() {
      // TODO: add constraints to intArray, or delete this test
      return Stream.of(Arguments.of("null intArray", (int[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field longArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassLongArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLongArrays")
    @DisplayName("accepts valid value")
    void testAcceptsLongArray(String description, long[] longArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              longArray,
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getLongArray()).isEqualTo(longArray);
    }

    static Stream<Arguments> validLongArrays() {
      return Stream.of(Arguments.of("valid longArray 1", new long[] {791L, 858L}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLongArrays")
    @DisplayName("rejects invalid value")
    void testRejectsLongArray(String description, long[] longArray) {
      // TODO: add constraints to longArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              longArray,
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidLongArrays() {
      // TODO: add constraints to longArray, or delete this test
      return Stream.of(Arguments.of("null longArray", (long[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field floatArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassFloatArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFloatArrays")
    @DisplayName("accepts valid value")
    void testAcceptsFloatArray(String description, float[] floatArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              floatArray,
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getFloatArray()).isEqualTo(floatArray);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              floatArray,
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidFloatArrays() {
      // TODO: add constraints to floatArray, or delete this test
      return Stream.of(Arguments.of("null floatArray", (float[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field doubleArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassDoubleArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validDoubleArrays")
    @DisplayName("accepts valid value")
    void testAcceptsDoubleArray(String description, double[] doubleArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              doubleArray,
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getDoubleArray()).isEqualTo(doubleArray);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              doubleArray,
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidDoubleArrays() {
      // TODO: add constraints to doubleArray, or delete this test
      return Stream.of(Arguments.of("null doubleArray", (double[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field booleanArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassBooleanArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validBooleanArrays")
    @DisplayName("accepts valid value")
    void testAcceptsBooleanArray(String description, boolean[] booleanArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              booleanArray,
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getBooleanArray()).isEqualTo(booleanArray);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              booleanArray,
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidBooleanArrays() {
      // TODO: add constraints to booleanArray, or delete this test
      return Stream.of(Arguments.of("null booleanArray", (boolean[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field charArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassCharArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCharArrays")
    @DisplayName("accepts valid value")
    void testAcceptsCharArray(String description, char[] charArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              charArray,
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getCharArray()).isEqualTo(charArray);
    }

    static Stream<Arguments> validCharArrays() {
      return Stream.of(Arguments.of("valid charArray 1", new char[] {'I', 'Y'}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCharArrays")
    @DisplayName("rejects invalid value")
    void testRejectsCharArray(String description, char[] charArray) {
      // TODO: add constraints to charArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              charArray,
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidCharArrays() {
      // TODO: add constraints to charArray, or delete this test
      return Stream.of(Arguments.of("null charArray", (char[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field byteWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassByteWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validByteWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsByteWrapperArray(String description, Byte[] byteWrapperArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              byteWrapperArray,
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getByteWrapperArray()).isEqualTo(byteWrapperArray);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              byteWrapperArray,
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidByteWrapperArrays() {
      // TODO: add constraints to byteWrapperArray, or delete this test
      return Stream.of(Arguments.of("null byteWrapperArray", (Byte[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field shortWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassShortWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validShortWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsShortWrapperArray(String description, Short[] shortWrapperArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              shortWrapperArray,
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getShortWrapperArray()).isEqualTo(shortWrapperArray);
    }

    static Stream<Arguments> validShortWrapperArrays() {
      return Stream.of(
          Arguments.of("valid shortWrapperArray 1", new Short[] {(short) 791, (short) 858}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidShortWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsShortWrapperArray(String description, Short[] shortWrapperArray) {
      // TODO: add constraints to shortWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              shortWrapperArray,
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidShortWrapperArrays() {
      // TODO: add constraints to shortWrapperArray, or delete this test
      return Stream.of(Arguments.of("null shortWrapperArray", (Short[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field intWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassIntWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validIntWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsIntWrapperArray(String description, Integer[] intWrapperArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              intWrapperArray,
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getIntWrapperArray()).isEqualTo(intWrapperArray);
    }

    static Stream<Arguments> validIntWrapperArrays() {
      return Stream.of(Arguments.of("valid intWrapperArray 1", new Integer[] {791, 858}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidIntWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsIntWrapperArray(String description, Integer[] intWrapperArray) {
      // TODO: add constraints to intWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              intWrapperArray,
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidIntWrapperArrays() {
      // TODO: add constraints to intWrapperArray, or delete this test
      return Stream.of(Arguments.of("null intWrapperArray", (Integer[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field longWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassLongWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLongWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsLongWrapperArray(String description, Long[] longWrapperArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              longWrapperArray,
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getLongWrapperArray()).isEqualTo(longWrapperArray);
    }

    static Stream<Arguments> validLongWrapperArrays() {
      return Stream.of(Arguments.of("valid longWrapperArray 1", new Long[] {791L, 858L}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLongWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsLongWrapperArray(String description, Long[] longWrapperArray) {
      // TODO: add constraints to longWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              longWrapperArray,
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidLongWrapperArrays() {
      // TODO: add constraints to longWrapperArray, or delete this test
      return Stream.of(Arguments.of("null longWrapperArray", (Long[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field floatWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassFloatWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validFloatWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsFloatWrapperArray(String description, Float[] floatWrapperArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              floatWrapperArray,
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getFloatWrapperArray()).isEqualTo(floatWrapperArray);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              floatWrapperArray,
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidFloatWrapperArrays() {
      // TODO: add constraints to floatWrapperArray, or delete this test
      return Stream.of(Arguments.of("null floatWrapperArray", (Float[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field doubleWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassDoubleWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validDoubleWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsDoubleWrapperArray(String description, Double[] doubleWrapperArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              doubleWrapperArray,
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getDoubleWrapperArray()).isEqualTo(doubleWrapperArray);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              doubleWrapperArray,
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidDoubleWrapperArrays() {
      // TODO: add constraints to doubleWrapperArray, or delete this test
      return Stream.of(Arguments.of("null doubleWrapperArray", (Double[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field booleanWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassBooleanWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validBooleanWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsBooleanWrapperArray(String description, Boolean[] booleanWrapperArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              booleanWrapperArray,
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getBooleanWrapperArray()).isEqualTo(booleanWrapperArray);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              booleanWrapperArray,
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidBooleanWrapperArrays() {
      // TODO: add constraints to booleanWrapperArray, or delete this test
      return Stream.of(Arguments.of("null booleanWrapperArray", (Boolean[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field charWrapperArray")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassCharWrapperArrayTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validCharWrapperArrays")
    @DisplayName("accepts valid value")
    void testAcceptsCharWrapperArray(String description, Character[] charWrapperArray) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              charWrapperArray,
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getCharWrapperArray()).isEqualTo(charWrapperArray);
    }

    static Stream<Arguments> validCharWrapperArrays() {
      return Stream.of(Arguments.of("valid charWrapperArray 1", new Character[] {'I', 'Y'}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidCharWrapperArrays")
    @DisplayName("rejects invalid value")
    void testRejectsCharWrapperArray(String description, Character[] charWrapperArray) {
      // TODO: add constraints to charWrapperArray, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              charWrapperArray,
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidCharWrapperArrays() {
      // TODO: add constraints to charWrapperArray, or delete this test
      return Stream.of(Arguments.of("null charWrapperArray", (Character[]) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field dayOfWeek")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassDayOfWeekTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validDayOfWeeks")
    @DisplayName("accepts valid value")
    void testAcceptsDayOfWeek(String description, DayOfWeek dayOfWeek) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              dayOfWeek,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getDayOfWeek()).isEqualTo(dayOfWeek);
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
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              dayOfWeek,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidDayOfWeeks() {
      // TODO: add constraints to dayOfWeek, or delete this test
      return Stream.of(Arguments.of("null dayOfWeek", (DayOfWeek) null));
    }
  }

  @Nested
  @DisplayName("GodClass Field list")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassListTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validLists")
    @DisplayName("accepts valid value")
    void testAcceptsList(String description, List list) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              list,
              Set.of("voluptates", "consectetur"),
              (Map) null);
      assertThat(obj.getList()).isEqualTo(list);
    }

    static Stream<Arguments> validLists() {
      return Stream.of(Arguments.of("valid list 1", List.of("voluptates")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidLists")
    @DisplayName("rejects invalid value")
    void testRejectsList(String description, List list) {
      // TODO: add constraints to list, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              list,
              Set.of("voluptates", "consectetur"),
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
  @DisplayName("GodClass Field set")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassSetTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("validSets")
    @DisplayName("accepts valid value")
    void testAcceptsSet(String description, Set set) {
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              set,
              (Map) null);
      assertThat(obj.getSet()).isEqualTo(set);
    }

    static Stream<Arguments> validSets() {
      return Stream.of(Arguments.of("valid set 1", Set.of("voluptates", "consectetur")));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidSets")
    @DisplayName("rejects invalid value")
    void testRejectsSet(String description, Set set) {
      // TODO: add constraints to set, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
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
  @DisplayName("GodClass Field map")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassMapTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidMaps")
    @DisplayName("rejects invalid value")
    void testRejectsMap(String description, Map map) {
      // TODO: add constraints to map, then replace assertThat(obj).isNotNull()
      //       with assertThat(validator.validate(obj)).isNotEmpty()
      GodClass obj =
          new GodClass(
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              (byte) 127,
              (short) 791,
              791,
              791L,
              -1.0f,
              -1.0d,
              true,
              'I',
              new byte[] {(byte) 127, (byte) 127},
              new short[] {(short) 791, (short) 858},
              new int[] {791, 858},
              new long[] {791L, 858L},
              new float[] {-1.0f, -1.0f},
              new double[] {-1.0d, -1.0d},
              new boolean[] {true, true},
              new char[] {'I', 'Y'},
              new Byte[] {(byte) 127, (byte) 127},
              new Short[] {(short) 791, (short) 858},
              new Integer[] {791, 858},
              new Long[] {791L, 858L},
              new Float[] {-1.0f, -1.0f},
              new Double[] {-1.0d, -1.0d},
              new Boolean[] {true, true},
              new Character[] {'I', 'Y'},
              DayOfWeek.MONDAY,
              List.of("voluptates"),
              Set.of("voluptates", "consectetur"),
              map);
      assertThat(obj).isNotNull();
    }

    static Stream<Arguments> invalidMaps() {
      // TODO: add constraints to map, or delete this test
      return Stream.of(Arguments.of("null map", (Map) null));
    }
  }
}
