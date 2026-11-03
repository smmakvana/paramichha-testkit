package com.paramichha.testkit;

import static org.assertj.core.api.Assertions.assertThat;

import com.paramichha.testkit.examples.Circle;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class OwnershipAndTodoTest {

    @Test
    @DisplayName("generated files carry the ownership marker")
    void generatedFileCarriesMarker() {
        String src = TestKit.of(Circle.class).generateTest().asString();
        assertThat(src).contains(JavaSourceCode.OWNERSHIP_MARKER);
    }

    @Test
    @DisplayName("a query without a known expected value fails until a person types it")
    void queryWithoutExpectationIsTodo() {
        String src = TestKit.of(Circle.class).generateTest().asString();
        assertThat(src).contains("TODO: define the expected result of area()");
    }

    @Test
    @DisplayName("a hand-owned file is not overwritten")
    void handOwnedFileIsKept(@TempDir Path dir) throws IOException {
        Path file = dir.resolve("demo").resolve("CircleTest.java");
        Files.createDirectories(file.getParent());
        Files.writeString(file, "class Mine {}");

        JavaSourceCode generated = new JavaSourceCode("demo", "CircleTest",
                "// " + JavaSourceCode.OWNERSHIP_MARKER + "\nclass CircleTest {}");

        assertThat(JavaSourceCode.isHandOwned(file)).isTrue();
        generated.writeTo(dir);
        assertThat(Files.readString(file)).isEqualTo("class Mine {}");
    }
}
