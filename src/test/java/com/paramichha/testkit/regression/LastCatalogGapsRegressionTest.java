package com.paramichha.testkit.regression;

import com.paramichha.datafactory.catalog.enumtype.OrderStatus;
import com.paramichha.datafactory.catalog.nested.NestedPojo;
import com.paramichha.datafactory.catalog.optional.OptionalAllArgs;
import com.paramichha.datafactory.catalog.realworld.trading.TradeWithLegs;
import com.paramichha.datafactory.catalog.unsupported.CircularReference;
import com.paramichha.testkit.TestKit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/** The last catalog gaps the sweep found; CatalogSweepTest compiles and runs the output. */
class LastCatalogGapsRegressionTest {

    private static String generated(Class<?> type) {
        return TestKit.of(type).generateTest().asString().replaceAll("\\s+", " ").replace("( ", "(");
    }

    @Test
    @DisplayName("Optional fields get a present value of their type argument, not null")
    void optionalFields() {
        assertThat(generated(OptionalAllArgs.class))
                .containsPattern("java\\.util\\.Optional\\.of\\(\"[^\"]+\"\\), java\\.util\\.Optional\\.of\\(-?\\d+\\)\\)")
                .doesNotContain("(Optional) null)");
    }

    @Test
    @DisplayName("types used only inside nested values are imported (BigDecimal in a List<TradeLeg>)")
    void nestedValueImports() {
        assertThat(generated(TradeWithLegs.class)).contains("import java.math.BigDecimal;");
    }

    @Test
    @DisplayName("a setter whose parameter type merely has constraints is not expected to throw")
    void setterOfConstrainedTypeDoesNotReject() {
        assertThat(generated(NestedPojo.class)).doesNotContain("setAddress rejects invalid input");
    }

    @Test
    @DisplayName("an enum gets a test of its constants")
    void enumTest() {
        assertThat(generated(OrderStatus.class))
                .contains("class OrderStatusTest")
                .contains("@EnumSource(OrderStatus.class)")
                .contains("assertThat(OrderStatus.valueOf(constant.name())).isSameAs(constant);")
                .doesNotContain("Generation failed");
    }

    @Test
    @DisplayName("a class TestKit can't create is reported with the reason, not turned into broken code")
    void uncreatableClassIsReported() {
        assertThat(TestKit.of(CircularReference.class).generateTest().asString())
                .doesNotContainPattern("(?m)^\\s*class\\s")
                .contains("// Generation failed for: CircularReference")
                .contains("field 'child' has no setter, and no constructor takes its one field");
    }
}
