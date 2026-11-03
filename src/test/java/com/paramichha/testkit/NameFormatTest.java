package com.paramichha.testkit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.paramichha.testkit.examples.Circle;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Naming and display settings")
class NameFormatTest {

    @Nested
    @DisplayName("defaults keep the existing names")
    class Defaults {
        private final TestKitTemplate t = TestKitTemplate.DEFAULT;

        @Test
        void splitFileNames() {
            assertThat(NameFormat.splitFileName(t, "Circle", "Creation", "")).isEqualTo("CircleCreationTest");
            assertThat(NameFormat.splitFileName(t, "Circle", "Field", "r")).isEqualTo("CircleFieldRTest");
            assertThat(NameFormat.splitFileName(t, "Circle", "Method", "area")).isEqualTo("CircleMethodAreaTest");
        }

        @Test
        void treeLabels() {
            assertThat(NameFormat.display(t, "Circle", "Creation", "")).isEqualTo("Circle Creation");
            assertThat(NameFormat.display(t, "Circle", "Field", "r")).isEqualTo("Circle Field r");
            assertThat(NameFormat.display(t, "Circle", "Method", "area()")).isEqualTo("Circle Method area()");
        }
    }

    @Nested
    @DisplayName("display settings")
    class Display {
        @Test
        void prefixAndSuffix() {
            TestKitTemplate t = TestKitTemplate.builder()
                    .displayPrefix("[unit]").showSuffixInDisplay(true).build();
            assertThat(NameFormat.display(t, "Circle", "Field", "r")).isEqualTo("[unit] Circle Field r (Test)");
        }

        @Test
        void arrowStyle() {
            TestKitTemplate t = TestKitTemplate.builder().nestedStyle(true).build();
            assertThat(NameFormat.display(t, "Circle", "Field", "r")).isEqualTo("Circle › field r");
        }
    }

    @Nested
    @DisplayName("split file patterns")
    class SplitFiles {
        @Test
        void classPrefixAndSuffixApply() {
            TestKitTemplate t = TestKitTemplate.builder().classPrefix("Unit").testSuffix("IT").build();
            assertThat(NameFormat.splitFileName(t, "Circle", "Field", "radius")).isEqualTo("UnitCircleFieldRadiusIT");
        }

        @Test
        void customOrder() {
            TestKitTemplate t = TestKitTemplate.builder().splitFilePattern("{Kind}{Name}Of{Class}{Suffix}").build();
            assertThat(NameFormat.splitFileName(t, "Circle", "Field", "r")).isEqualTo("FieldROfCircleTest");
        }

        @Test
        void generatedFilesUseThePattern() {
            TestKitTemplate t = TestKitTemplate.builder().splitFilePattern("{Class}{Kind}{Name}{Suffix}").testSuffix("IT").build();
            List<String> names = TestKit.of(Circle.class).withTemplate(t).generateTests().files().stream()
                    .map(f -> f.className()).toList();
            assertThat(names).contains("CircleFieldRIT", "CircleCreationIT", "CircleMethodAreaIT");
        }
    }

    @Nested
    @DisplayName("invalid patterns are refused")
    class Validation {
        @Test
        void unknownTokenIsRefused() {
            assertThatThrownBy(() -> TestKitTemplate.builder().splitFilePattern("{Class}{Kind}{Nope}{Suffix}"))
                    .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("{Nope}");
        }

        @Test
        void patternWithoutKindIsRefused() {
            assertThatThrownBy(() -> TestKitTemplate.builder().splitFilePattern("{Class}{Name}{Suffix}"))
                    .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("{Kind}");
        }

        @Test
        void invalidCharacterIsRefused() {
            assertThatThrownBy(() -> TestKitTemplate.builder().splitFilePattern("{Class}-{Kind}{Suffix}"))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void unknownDisplayTokenIsRefused() {
            assertThatThrownBy(() -> TestKitTemplate.builder().displayPattern("{Class} {Suffix}"))
                    .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("{Suffix}");
        }
    }
}
