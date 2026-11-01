package com.paramichha.testkit.examples;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record GodRecord(
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
) {}