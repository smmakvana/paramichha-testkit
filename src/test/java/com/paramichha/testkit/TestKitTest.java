package com.paramichha.testkit;

import com.paramichha.datafactory.catalog.annotation.combined.CombinedRecord;
import com.paramichha.datafactory.catalog.annotation.combined.CombinedAllArgs;
import com.paramichha.datafactory.catalog.annotation.email.EmailRecord;
import com.paramichha.datafactory.catalog.annotation.numeric.NumericConstraintRecord;
import com.paramichha.datafactory.catalog.annotation.numeric.NumericConstraintAllArgs;
import com.paramichha.datafactory.catalog.nested.NestedRecord;
import com.paramichha.datafactory.catalog.realworld.registration.UserRegistration;
import com.paramichha.testkit.fixture.PlainRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("TestKit")
class TestKitTest {

    @Nested
    @DisplayName("of()")
    class OfMethod {

        @Test
        void nullTypeThrows() {
            assertThatThrownBy(() -> TestKit.of((Class<?>) null))
                    .isInstanceOf(TestKitException.class);
        }

        @Test
        void returnsTestKitInstance() {
            assertThat(TestKit.of(UserRegistration.class)).isNotNull();
        }
    }

    @Nested
    @DisplayName("generate()")
    class GenerateMethod {

        @Test
        void generatesSourceForRecord() {
            String src = TestKit.of(UserRegistration.class)
                    .withPackage("com.example.test").generateTest().asString();
            assertThat(src).contains("package com.example.test");
            assertThat(src).contains("class UserRegistrationTest");
            assertThat(src).contains("UserRegistration userRegistration");
            assertThat(src).contains("UserRegistrationCreationTest");
        }

        @Test
        void generatesSourceForAllArgs() {
            String src = TestKit.of(CombinedAllArgs.class)
                    .withPackage("com.example.test").generateTest().asString();
            assertThat(src).contains("CombinedAllArgs combinedAllArgs");
            assertThat(src).contains("CombinedAllArgsCreationTest");
        }

        @Test
        void defaultPackageIsClassPackage() {
            GenerationResult result = TestKit.of(UserRegistration.class).generateTest();
            assertThat(result.packageName())
                    .isEqualTo(UserRegistration.class.getPackageName());
        }

        @Test
        void classNameIsTypeNamePlusSuffix() {
            GenerationResult result = TestKit.of(UserRegistration.class).generateTest();
            assertThat(result.className()).isEqualTo("UserRegistrationTest");
        }

        @Test
        void annotatedClassHasMemberNestedClasses() {
            String src = TestKit.of(NumericConstraintRecord.class)
                    .withPackage("com.example").generateTest().asString();
            assertThat(src).contains("@Nested");
            assertThat(src).contains("NumericConstraintRecord Field");
        }

        @Test
        void annotatedClassHasValidator() {
            String src = TestKit.of(UserRegistration.class)
                    .withPackage("com.example").generateTest().asString();
            assertThat(src).contains("Validator validator");
            assertThat(src).contains("setUpValidator");
        }

        @Test
        void unannotatedClassHasNoValidator() {
            String src = TestKit.of(PlainRecord.class)
                    .withPackage("com.example").generateTest().asString();
            assertThat(src).doesNotContain("setUpValidator");
            assertThat(src).doesNotContain("Validation.buildDefaultValidatorFactory");
        }

        @Test
        void annotatedFieldHasRejectTest() {
            String src = TestKit.of(EmailRecord.class)
                    .withPackage("com.example").generateTest().asString();
            assertThat(src).contains("testRejectsEmail");
        }

        @Test
        void unannotatedFieldHasNoRejectTest() {
            // Nothing can reject a value of an unconstrained field, so a "rejects" test could only
            // be a false positive (it used to assert isNotNull()).
            String src = TestKit.of(PlainRecord.class)
                    .withPackage("com.example").generateTest().asString();
            assertThat(src).doesNotContain("testRejects").doesNotContain("isNotNull()).isTrue")
                    .doesNotContain("assertThat(obj).isNotNull()").doesNotContain("TODO");
            assertThat(src).contains("testAcceptsCount");
        }

        @Test
        void bracesAreBalanced() {
            String src = TestKit.of(CombinedRecord.class)
                    .withPackage("com.example").generateTest().asString();
            long opens  = src.chars().filter(c -> c == '{').count();
            long closes = src.chars().filter(c -> c == '}').count();
            assertThat(opens).isEqualTo(closes);
        }

        @Test
        void nestedObjectClassGenerates() {
            String src = TestKit.of(NestedRecord.class)
                    .withPackage("com.example").generateTest().asString();
            assertThat(src).contains("NestedRecordTest");
        }

        @Test
        void numericBoundaryValuesPresent() {
            String src = TestKit.of(NumericConstraintAllArgs.class)
                    .withPackage("com.example").generateTest().asString();
            // quantity is @Min(1) @Max(100): the max and the first value above it
            assertThat(src).contains("100").contains("101");
            // primitive constrained fields must get real invalid values, not a placeholder
            assertThat(src).contains("(invalid)").doesNotContain("no constraints defined");
        }
    }

    @Nested
    @DisplayName("withPackage()")
    class WithPackage {

        @Test
        void blankPackageThrows() {
            assertThatThrownBy(() -> TestKit.of(UserRegistration.class).withPackage(""))
                    .isInstanceOf(TestKitException.class);
        }

        @Test
        void packageAppearsInOutput() {
            String src = TestKit.of(UserRegistration.class)
                    .withPackage("com.custom.pkg").generateTest().asString();
            assertThat(src).contains("package com.custom.pkg;");
        }
    }

    @Nested
    @DisplayName("GeneratedTestClass")
    class GenerationResultTests {

        @Test
        void asStringReturnsSource() {
            GenerationResult result = TestKit.of(UserRegistration.class).generateTest();
            assertThat(result.asString()).isEqualTo(result.source());
        }

        @Test
        void writeToCreatesFile(@TempDir Path tmp) {
            GenerationResult result = TestKit.of(UserRegistration.class)
                    .withPackage("com.example.out").generateTest();
            Path written = result.writeTo(tmp);
            assertThat(written).exists();
            assertThat(written.getFileName().toString())
                    .isEqualTo("UserRegistrationTest.java");
        }
    }

    @Nested
    @DisplayName("split() — generateAll()")
    class SplitTests {

        @Test
        void generateAllProducesMultipleFiles() {
            var files = TestKit.of(UserRegistration.class)
                    .withPackage("com.example.split")
                    .generateTests();
            assertThat(files.files()).hasSizeGreaterThan(1);
        }

        @Test
        void baseClassIsFirst() {
            var files = TestKit.of(UserRegistration.class)
                    .withPackage("com.example.split")
                    .generateTests();
            assertThat(files.files().get(0).className()).isEqualTo("UserRegistrationTest");
        }

        @Test
        void creationFileIsSecond() {
            var files = TestKit.of(UserRegistration.class)
                    .withPackage("com.example.split")
                    .generateTests();
            assertThat(files.files().get(1).className()).isEqualTo("UserRegistrationCreationTest");
        }

        @Test
        void allFilesHaveCorrectPackage() {
            var files = TestKit.of(UserRegistration.class)
                    .withPackage("com.example.split")
                    .generateTests();
            files.files().forEach(f ->
                    assertThat(f.packageName()).isEqualTo("com.example.split"));
        }

        @Test
        void bracesBalancedInAllFiles() {
            var files = TestKit.of(CombinedRecord.class)
                    .withPackage("com.example.split")
                    .generateTests();
            files.files().forEach(f -> {
                String src = f.asString();
                long opens  = src.chars().filter(c -> c == '{').count();
                long closes = src.chars().filter(c -> c == '}').count();
                assertThat(opens)
                        .as("unbalanced braces in %s", f.className())
                        .isEqualTo(closes);
            });
        }
    }
}