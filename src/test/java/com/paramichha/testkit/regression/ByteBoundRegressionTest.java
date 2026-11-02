package com.paramichha.testkit.regression;

import com.paramichha.testkit.TestKit;
import com.paramichha.testkit.fixture.bounds.ByteBound;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Regression: "one above @Max(127)" on a byte wrapped to -128. That value is invalid for
 * {@code @Min(1)} too, so it has to be checked as a VALID value: every value marked valid must
 * satisfy 1 <= level <= 127.
 */
class ByteBoundRegressionTest {

    @Test
    void everyValidValueIsWithinTheConstraint() {
        String src = TestKit.of(ByteBound.class).withSeed(1).generateTest().asString();

        Matcher m = Pattern.compile("Arguments\\.of\\(\"valid level \\d+\", \\(byte\\) (-?\\d+)\\)").matcher(src);
        int count = 0;
        while (m.find()) {
            count++;
            int v = Integer.parseInt(m.group(1));
            assertThat(v).as("valid level value").isBetween(1, 127);
        }
        assertThat(count).as("valid values found").isGreaterThan(1);
    }
}
