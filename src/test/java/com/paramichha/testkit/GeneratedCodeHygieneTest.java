package com.paramichha.testkit;

import com.paramichha.testkit.examples.Circle;
import com.paramichha.testkit.examples.GodClass;
import com.paramichha.testkit.examples.GodRecord;
import com.paramichha.testkit.examples.ObjectAnnotatedService;
import com.paramichha.testkit.examples.SplitGodRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** Generated tests compile without warnings, and files are written safely. */
class GeneratedCodeHygieneTest {

    private static final String SUPPRESS = "@SuppressWarnings({\"rawtypes\", \"unchecked\"})";

    @Test
    @DisplayName("a test that reads a raw List/Set/Map field says so once, at the class")
    void rawFieldsGetOneSuppression() {
        assertThat(TestKit.of(GodRecord.class).generateTest().asString()).containsOnlyOnce(SUPPRESS);
        assertThat(TestKit.of(GodClass.class).generateTest().asString()).containsOnlyOnce(SUPPRESS);
        assertThat(TestKit.of(Circle.class).generateTest().asString()).doesNotContain("@SuppressWarnings");
    }

    @Test
    @DisplayName("split files get the suppression only when they read a raw field")
    void onlySplitFilesThatReadARawFieldAreMarked() {
        List<JavaSourceCode> files = TestKit.of(SplitGodRecord.class).generateTests().files();
        assertThat(file(files, "SplitGodRecordFieldListTest")).contains(SUPPRESS);
        assertThat(file(files, "SplitGodRecordFieldIntValueTest")).doesNotContain("@SuppressWarnings");
    }

    @Test
    @DisplayName("a constrained return value is checked in place, with no unused holder record")
    void noUnusedReturnRecords() {
        String src = TestKit.of(ObjectAnnotatedService.class).generateTest().asString();
        assertThat(src).doesNotContain("private record").contains("DataValidation.");
        assertThat(src).doesNotContain("import jakarta.validation.constraints.Positive;");
    }

    @Test
    @DisplayName("an array value is labelled by its contents, so the label is readable and the same on every run")
    void arrayLabelsAreStable() {
        String src = TestKit.of(GodRecord.class).generateTest().asString();
        assertThat(src).containsPattern("intArray \\[[-0-9, ]+\\] \\(valid\\)").doesNotContainPattern("\\[I@");
        assertThat(TestKit.of(GodRecord.class).generateTest().asString()).isEqualTo(src);
    }

    @Test
    @DisplayName("an unchanged file is left alone, and a write failure names the class")
    void writing(@TempDir Path dir) throws IOException {
        JavaSourceCode code = new JavaSourceCode("demo", "DemoTest",
                "// " + JavaSourceCode.OWNERSHIP_MARKER + "\nclass DemoTest {}");
        Path file = code.writeTo(dir);
        var modified = Files.getLastModifiedTime(file);
        assertThat(code.writeTo(dir)).isEqualTo(file);
        assertThat(Files.getLastModifiedTime(file)).isEqualTo(modified);

        Path notADirectory = Files.writeString(dir.resolve("plain-file"), "x");
        assertThatThrownBy(() -> code.writeTo(notADirectory))
                .isInstanceOf(TestKitException.class)
                .hasMessage("Failed to write DemoTest.java")
                .hasCauseInstanceOf(IOException.class);
    }

    @Test
    @DisplayName("an empty result is rejected")
    void emptyResult() {
        assertThatThrownBy(() -> new GenerationResult(List.of()))
                .isInstanceOf(TestKitException.class)
                .hasMessageContaining("at least one file");
    }

    private static String file(List<JavaSourceCode> files, String className) {
        return files.stream().filter(f -> f.className().equals(className)).findFirst().orElseThrow().source();
    }
}
