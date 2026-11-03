package com.paramichha.testkit;

import static org.assertj.core.api.Assertions.assertThat;

import com.paramichha.testkit.fixture.coverage.Shape;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Every public method gets a test unless folding is chosen")
class AllPublicMethodsTest {

    private static List<String> files(TestKitTemplate t) {
        return TestKit.of(Shape.class).withTemplate(t).generateTests().files().stream()
                .map(f -> f.className()).collect(Collectors.toList());
    }

    @Test
    void defaultKeepsEveryPublicMethod() {
        List<String> names = files(TestKitTemplate.DEFAULT);
        assertThat(names).contains("ShapeMethodAreaTest", "ShapeMethodDiameterTest", "ShapeMethodGetAreaTest",
                "ShapeMethodGetDiameterTest", "ShapeMethodGetRadiusTest", "ShapeMethodSetRadiusTest",
                "ShapeMethodContainsTest", "ShapeMethodScaleTest", "ShapeMethodResetTest");
        assertThat(names).contains("ShapeFieldRadiusTest");
    }

    @Test
    void foldingRemovesOnlyAccessorsThatHaveAField() {
        List<String> names = files(TestKitTemplate.builder().foldAccessors(true).build());
        assertThat(names).doesNotContain("ShapeMethodGetRadiusTest", "ShapeMethodSetRadiusTest");
        assertThat(names).contains("ShapeMethodGetAreaTest", "ShapeMethodGetDiameterTest", "ShapeMethodAreaTest");
    }

    @Test
    void setterWithConstraintIsNotDroppedAndRejectsWhenItAccepts() {
        String src = TestKit.of(Shape.class).generateTests().files().stream()
                .filter(f -> f.className().equals("ShapeMethodSetRadiusTest")).findFirst().orElseThrow().source();
        assertThat(src).contains("setRadius");
    }
}
