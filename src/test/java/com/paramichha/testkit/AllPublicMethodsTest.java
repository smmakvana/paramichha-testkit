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

    private static String setterSource(ProjectType type) {
        return TestKit.of(Shape.class).withProjectType(type).generateTests().files().stream()
                .filter(f -> f.className().equals("ShapeMethodSetRadiusTest")).findFirst().orElseThrow().source();
    }

    @Test
    void jakartaSetterRejectsAreCheckedByTheValidator() {
        String src = setterSource(ProjectType.JAKARTA);
        assertThat(src).contains("validateParameters")
                .doesNotContain("isInstanceOf(Exception.class)");
    }

    @Test
    void coreSetterRejectsExpectAnException() {
        String src = setterSource(ProjectType.PLAIN_JAVA);
        assertThat(src).contains("isInstanceOf(Exception.class)")
                .doesNotContain("validateParameters");
    }
}
