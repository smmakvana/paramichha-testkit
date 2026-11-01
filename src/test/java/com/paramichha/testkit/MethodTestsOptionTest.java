package com.paramichha.testkit;

import com.paramichha.testkit.examples.SampleService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MethodTestsOptionTest {

    @Test
    void methodTestsAreOnByDefault() {
        String src = TestKit.of(SampleService.class).withSeed(1).generateTest().asString();
        assertThat(src).contains("SampleServiceMethod");
    }

    @Test
    void defaultIsUnchangedByTheOption() {
        String byDefault = TestKit.of(SampleService.class).withSeed(1).generateTest().asString();
        String explicit  = TestKit.of(SampleService.class).withSeed(1).withMethodTests(true).generateTest().asString();
        assertThat(explicit).isEqualTo(byDefault);
    }

    @Test
    void withoutMethodTestsKeepsCreationAndFieldTests() {
        String src = TestKit.of(SampleService.class).withSeed(1).withMethodTests(false).generateTest().asString();
        assertThat(src).doesNotContain("SampleServiceMethod");
        assertThat(src).contains("SampleServiceCreationTest").contains("testRejectsQuantity");
    }

    @Test
    void splitModeHasNoMethodFilesWhenOff() {
        var files = TestKit.of(SampleService.class).withSeed(1).withMethodTests(false).generateTests().files();
        assertThat(files).extracting(JavaSourceCode::className).noneMatch(n -> n.contains("Method"));
        assertThat(files).isNotEmpty();
    }
}
