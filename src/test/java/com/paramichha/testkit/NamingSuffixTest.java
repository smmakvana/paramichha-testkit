package com.paramichha.testkit;

import com.paramichha.testkit.examples.Circle;
import com.paramichha.testkit.examples.SampleService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NamingSuffixTest {

    @Test
    void defaultSuffixKeepsTheHistoricalNames() {
        var files = TestKit.of(SampleService.class).withSeed(1).generateTests().files();
        assertThat(files).extracting(JavaSourceCode::className)
                .contains("SampleServiceTest", "SampleServiceCreationTest", "SampleServiceFieldNameTest");
    }

    @Test
    void integrationTestSuffixRenamesEveryGeneratedClass() {
        var template = TestKitTemplate.builder().testSuffix("IT").build();
        var files = TestKit.of(SampleService.class).withTemplate(template).withSeed(1)
                .generateTests().files();

        assertThat(files).extracting(JavaSourceCode::className)
                .contains("SampleServiceIT", "SampleServiceCreationIT", "SampleServiceFieldNameIT")
                .noneMatch(n -> n.endsWith("Test"));
        assertThat(files).allSatisfy(f -> assertThat(f.source()).contains("class " + f.className()));
    }

    @Test
    void singleFileModeUsesTheSuffixToo() {
        var template = TestKitTemplate.builder().testSuffix("Spec").build();
        String src = TestKit.of(Circle.class).withTemplate(template).withSeed(1).generateTest().asString();
        assertThat(src).contains("class CircleSpec").doesNotContain("class CircleTest");
    }

    @Test
    void suffixMustBeAJavaIdentifier() {
        assertThatThrownBy(() -> TestKitTemplate.builder().testSuffix("")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> TestKitTemplate.builder().testSuffix("I T")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> TestKitTemplate.builder().testSuffix("9IT")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> TestKitTemplate.builder().testSuffix(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void builderLeavesOtherDefaultsAlone() {
        var template = TestKitTemplate.builder().testSuffix("IT").build();
        assertThat(template.validatorField).isEqualTo(TestKitTemplate.DEFAULT.validatorField);
        assertThat(TestKitTemplate.DEFAULT.testSuffix).isEqualTo("Test");
    }
}
