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
          Arguments.of("valid byteValue 5", (byte) 126));
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
          Arguments.of("valid byteWrapper 5", (byte) 126));
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
  }

  @Nested
  @DisplayName("GodClass Field map")
  @Tag("field")
  @TestMethodOrder(MethodOrderer.MethodName.class)
  class GodClassMapTest {}
}
