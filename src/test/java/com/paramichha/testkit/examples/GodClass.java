package com.paramichha.testkit.examples;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Raw List, Set and Map on purpose: TestKit must handle classes that use raw types.
@SuppressWarnings("rawtypes")
public final class GodClass {
    private final byte byteValue;
    private final short shortValue;
    private final int intValue;
    private final long longValue;
    private final float floatValue;
    private final double doubleValue;
    private final boolean booleanValue;
    private final char charValue;
    private final Byte byteWrapper;
    private final Short shortWrapper;
    private final Integer intWrapper;
    private final Long longWrapper;
    private final Float floatWrapper;
    private final Double doubleWrapper;
    private final Boolean booleanWrapper;
    private final Character charWrapper;
    private final byte[] byteArray;
    private final short[] shortArray;
    private final int[] intArray;
    private final long[] longArray;
    private final float[] floatArray;
    private final double[] doubleArray;
    private final boolean[] booleanArray;
    private final char[] charArray;
    private final Byte[] byteWrapperArray;
    private final Short[] shortWrapperArray;
    private final Integer[] intWrapperArray;
    private final Long[] longWrapperArray;
    private final Float[] floatWrapperArray;
    private final Double[] doubleWrapperArray;
    private final Boolean[] booleanWrapperArray;
    private final Character[] charWrapperArray;
    private final DayOfWeek dayOfWeek;
    private final List list;
    private final Set set;
    private final Map map;

    public GodClass(
            // Primitives
            byte byteValue,
            short shortValue,
            int intValue,
            long longValue,
            float floatValue,
            double doubleValue,
            boolean booleanValue,
            char charValue,

            // Wrappers
            Byte byteWrapper,
            Short shortWrapper,
            Integer intWrapper,
            Long longWrapper,
            Float floatWrapper,
            Double doubleWrapper,
            Boolean booleanWrapper,
            Character charWrapper,

            // Primitive Arrays
            byte[] byteArray,
            short[] shortArray,
            int[] intArray,
            long[] longArray,
            float[] floatArray,
            double[] doubleArray,
            boolean[] booleanArray,
            char[] charArray,

            // Wrapper Arrays
            Byte[] byteWrapperArray,
            Short[] shortWrapperArray,
            Integer[] intWrapperArray,
            Long[] longWrapperArray,
            Float[] floatWrapperArray,
            Double[] doubleWrapperArray,
            Boolean[] booleanWrapperArray,
            Character[] charWrapperArray,

            // Enum
            DayOfWeek dayOfWeek,

            // Collections
            List list,
            Set set,
            Map map
    ) {
        this.byteValue = byteValue;
        this.shortValue = shortValue;
        this.intValue = intValue;
        this.longValue = longValue;
        this.floatValue = floatValue;
        this.doubleValue = doubleValue;
        this.booleanValue = booleanValue;
        this.charValue = charValue;
        this.byteWrapper = byteWrapper;
        this.shortWrapper = shortWrapper;
        this.intWrapper = intWrapper;
        this.longWrapper = longWrapper;
        this.floatWrapper = floatWrapper;
        this.doubleWrapper = doubleWrapper;
        this.booleanWrapper = booleanWrapper;
        this.charWrapper = charWrapper;
        this.byteArray = byteArray;
        this.shortArray = shortArray;
        this.intArray = intArray;
        this.longArray = longArray;
        this.floatArray = floatArray;
        this.doubleArray = doubleArray;
        this.booleanArray = booleanArray;
        this.charArray = charArray;
        this.byteWrapperArray = byteWrapperArray;
        this.shortWrapperArray = shortWrapperArray;
        this.intWrapperArray = intWrapperArray;
        this.longWrapperArray = longWrapperArray;
        this.floatWrapperArray = floatWrapperArray;
        this.doubleWrapperArray = doubleWrapperArray;
        this.booleanWrapperArray = booleanWrapperArray;
        this.charWrapperArray = charWrapperArray;
        this.dayOfWeek = dayOfWeek;
        this.list = list;
        this.set = set;
        this.map = map;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public short getShortValue() {
        return shortValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public char getCharValue() {
        return charValue;
    }

    public Byte getByteWrapper() {
        return byteWrapper;
    }

    public Short getShortWrapper() {
        return shortWrapper;
    }

    public Integer getIntWrapper() {
        return intWrapper;
    }

    public Long getLongWrapper() {
        return longWrapper;
    }

    public Float getFloatWrapper() {
        return floatWrapper;
    }

    public Double getDoubleWrapper() {
        return doubleWrapper;
    }

    public Boolean getBooleanWrapper() {
        return booleanWrapper;
    }

    public Character getCharWrapper() {
        return charWrapper;
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public short[] getShortArray() {
        return shortArray;
    }

    public int[] getIntArray() {
        return intArray;
    }

    public long[] getLongArray() {
        return longArray;
    }

    public float[] getFloatArray() {
        return floatArray;
    }

    public double[] getDoubleArray() {
        return doubleArray;
    }

    public boolean[] getBooleanArray() {
        return booleanArray;
    }

    public char[] getCharArray() {
        return charArray;
    }

    public Byte[] getByteWrapperArray() {
        return byteWrapperArray;
    }

    public Short[] getShortWrapperArray() {
        return shortWrapperArray;
    }

    public Integer[] getIntWrapperArray() {
        return intWrapperArray;
    }

    public Long[] getLongWrapperArray() {
        return longWrapperArray;
    }

    public Float[] getFloatWrapperArray() {
        return floatWrapperArray;
    }

    public Double[] getDoubleWrapperArray() {
        return doubleWrapperArray;
    }

    public Boolean[] getBooleanWrapperArray() {
        return booleanWrapperArray;
    }

    public Character[] getCharWrapperArray() {
        return charWrapperArray;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public List getList() {
        return list;
    }

    public Set getSet() {
        return set;
    }

    public Map getMap() {
        return map;
    }
}