package com.paramichha.testkit.generator;

import com.paramichha.testkit.fixture.Unbuildable;
import com.paramichha.testkit.fixture.Wallet;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class LiteralConverterConstructedTest {

    @Test
    void plainClassBecomesConstructorCall() {
        Object w = new Wallet("Ada", 3, new BigDecimal("1.50"));
        assertThat(LiteralConverter.canSerialize(w)).isTrue();
        assertThat(LiteralConverter.toLiteral(w)).isEqualTo("new Wallet(\"Ada\", 3, new BigDecimal(\"1.50\"))");
        assertThat(LiteralConverter.importsFor(w))
                .contains("com.paramichha.testkit.fixture.Wallet", "java.math.BigDecimal");
    }

    @Test
    void classWithoutFieldMatchingConstructorIsNotSerializable() {
        Object u = new Unbuildable("x");
        assertThat(LiteralConverter.canSerialize(u)).isFalse();
        assertThat(LiteralConverter.toLiteralOrNull(u)).isNull();
    }
}
