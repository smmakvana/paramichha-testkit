package com.paramichha.testkit;

import com.paramichha.testkit.examples.SampleService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NamingAffixTest {

    @Test
    void classPrefixAndSuffixWrapEveryClassName() {
        var template = TestKitTemplate.builder().classPrefix("Unit").testSuffix("IT").build();
        var names = TestKit.of(SampleService.class).withTemplate(template).withSeed(1)
                .generateTests().files().stream().map(JavaSourceCode::className).toList();

        assertThat(names).contains("UnitSampleServiceIT", "UnitSampleServiceCreationIT", "UnitSampleServiceFieldNameIT");
    }

    @Test
    void methodSuffixWrapsTestMethodNames() {
        var template = TestKitTemplate.builder().methodSuffix("_Spec").build();
        String src = TestKit.of(SampleService.class).withTemplate(template).withSeed(1).generateTest().asString();

        assertThat(src).contains("void testAcceptsQuantity_Spec(").contains("void testRejectsQuantity_Spec(");
        // helper streams and setup keep their names
        assertThat(src).contains("static Stream<Arguments> validQuantityValues()");
        assertThat(src).doesNotContain("validQuantities_Spec");
    }

    @Test
    void methodPrefixAndSuffixCombine() {
        var template = TestKitTemplate.builder().methodPrefix("check").methodSuffix("_Spec").build();
        String src = TestKit.of(SampleService.class).withTemplate(template).withSeed(1).generateTest().asString();

        assertThat(src).contains("void checktestRejectsQuantity_Spec(");
    }

    @Test
    void defaultsChangeNothing() {
        String byDefault = TestKit.of(SampleService.class).withSeed(1).generateTest().asString();
        String explicit = TestKit.of(SampleService.class).withSeed(1)
                .withTemplate(TestKitTemplate.builder().classPrefix("").methodPrefix("").methodSuffix("").build())
                .generateTest().asString();
        assertThat(explicit).isEqualTo(byDefault);
    }

    @Test
    void invalidAffixesAreRefused() {
        assertThatThrownBy(() -> TestKitTemplate.builder().classPrefix("Unit-Tests"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> TestKitTemplate.builder().methodSuffix("_bad name"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> TestKitTemplate.builder().methodPrefix(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void affixAppliesPrefixBaseSuffix() {
        assertThat(new Affix("Unit", "IT").apply("Circle")).isEqualTo("UnitCircleIT");
        assertThat(new Affix("", "").apply("Circle")).isEqualTo("Circle");
    }
}
