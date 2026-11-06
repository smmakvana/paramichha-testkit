package com.paramichha.testkit.regression;

import com.paramichha.datafactory.catalog.number.NumberPojo;
import com.paramichha.datafactory.catalog.number.NumberRecord;
import com.paramichha.testkit.GenerationResult;
import com.paramichha.testkit.TestKit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A JavaBeans class (no-args constructor and setters) has no all-args constructor. Generated tests
 * used to call one anyway and did not compile; they now create it through a newX(...) factory
 * that calls the setters. CatalogSweepTest compiles and runs these tests for every catalog POJO.
 */
class JavaBeansCreationRegressionTest {

    @Test
    void javaBeansClassIsCreatedThroughAFactoryThatCallsTheSetters() {
        // whitespace-insensitive: the output may or may not have been through the formatter
        String src = TestKit.of(NumberPojo.class).generateTest().asString().replaceAll("\\s+", " ")
                .replace("( ", "(");

        assertThat(src)
                .contains("private static NumberPojo newNumberPojo(byte tier, short port, int quantity,")
                .contains("NumberPojo obj = new NumberPojo();")
                .contains("obj.setTier(tier);")
                .contains("obj.setAdjustment(adjustment);")
                .contains("NumberPojo obj = newNumberPojo(tier,")
                .doesNotContainPattern("new NumberPojo\\([^)]");
    }

    @Test
    void everySplitFileHasItsOwnFactory() {
        GenerationResult all = TestKit.of(NumberPojo.class).generateTests();

        assertThat(all.files()).hasSizeGreaterThan(1).allSatisfy(file -> {
            String src = file.asString();
            if (src.contains("newNumberPojo(")) {
                assertThat(src).contains("private static NumberPojo newNumberPojo(");
            }
        });
    }

    @Test
    void recordsAndAllArgsClassesKeepTheirConstructor() {
        String src = TestKit.of(NumberRecord.class).generateTest().asString();

        assertThat(src).contains("new NumberRecord(").doesNotContain("newNumberRecord(");
    }
}
