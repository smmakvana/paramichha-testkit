package com.paramichha.testkit.regression;

import com.paramichha.testkit.ProjectType;
import com.paramichha.testkit.ReflectionClassReader;
import com.paramichha.testkit.TestKit;
import com.paramichha.testkit.fixture.ctor.Circle;
import com.paramichha.testkit.fixture.ctor.ImpossibleRange;
import com.paramichha.testkit.model.FieldInfo;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.Positive;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

/**
 * Regression tests for the TestBuddy bug report: constraints on <em>constructor parameters</em>
 * ({@code Circle(@Positive int x, @Negative int y, @Max(5) @Min(1) int z)}) were ignored, giving
 * invalid fixtures such as {@code new Circle(146, 146, 146)} and "rejects" tests that only
 * asserted {@code isNotNull()}.
 *
 * <p>Generated tests are compiled and run for real, including against a broken copy of the class
 * they were generated for, so the tests prove they check something.
 */
class ConstructorConstraintRegressionTest {

    private static final Path CIRCLE_SOURCE =
            Path.of("src/test/java/com/paramichha/testkit/fixture/ctor/Circle.java");

    // ── Reading constraints ───────────────────────────────────────────────

    @Nested
    @DisplayName("reading constraints")
    class Reading {

        @Test
        void constructorParameterConstraintsAreReadAndKeptApartFromFieldConstraints() {
            List<FieldInfo> fields = ReflectionClassReader.INSTANCE.read(Circle.class).fields();
            FieldInfo x = fields.get(0), y = fields.get(1), z = fields.get(2);

            assertThat(x.constructorValidations()).singleElement().isInstanceOf(Positive.class);
            assertThat(y.constructorValidations()).singleElement().isInstanceOf(Negative.class);
            assertThat(z.constructorValidations()).hasSize(2);
            // the fields and getters themselves carry no constraints
            assertThat(fields).allSatisfy(f -> assertThat(f.propertyValidations()).isEmpty());
            assertThat(List.of(x.constructorIndex(), y.constructorIndex(), z.constructorIndex()))
                    .containsExactly(0, 1, 2);
        }
    }

    // ── Generated values ──────────────────────────────────────────────────

    @Nested
    @DisplayName("generated values")
    class Values {

        private final String src = TestKit.of(Circle.class).generateTest().asString();

        @Test
        void baselineSatisfiesEveryConstructorConstraint() {
            Matcher m = Pattern.compile("new Circle\\((-?\\d+), (-?\\d+), (-?\\d+)\\)").matcher(src);
            assertThat(m.find()).as("baseline fixture").isTrue();
            int x = Integer.parseInt(m.group(1)), y = Integer.parseInt(m.group(2)), z = Integer.parseInt(m.group(3));
            assertThat(x).as("@Positive x").isPositive();
            assertThat(y).as("@Negative y").isNegative();
            assertThat(z).as("@Min(1) @Max(5) z").isBetween(1, 5);
        }

        @Test
        void positiveRejectsZeroAndNegativeValues() {
            assertThat(values("invalidXes")).contains(0).allSatisfy(v -> assertThat(v).isLessThanOrEqualTo(0))
                    .anySatisfy(v -> assertThat(v).isNegative());
            assertThat(values("validXes")).isNotEmpty().allSatisfy(v -> assertThat(v).isPositive());
        }

        @Test
        void negativeRejectsZeroAndPositiveValues() {
            assertThat(values("invalidYs")).contains(0).allSatisfy(v -> assertThat(v).isGreaterThanOrEqualTo(0))
                    .anySatisfy(v -> assertThat(v).isPositive());
            assertThat(values("validYs")).isNotEmpty().allSatisfy(v -> assertThat(v).isNegative());
        }

        @Test
        void minOneMaxFiveGivesOnlyOneToFiveAsValid() {
            assertThat(values("validZes")).isNotEmpty().allSatisfy(v -> assertThat(v).isBetween(1, 5))
                    .contains(1, 5);
            assertThat(values("invalidZes")).contains(0, 6).noneSatisfy(v -> assertThat(v).isBetween(1, 5));
        }

        @Test
        void invalidCasesKeepTheOtherParametersValid() {
            assertThat(src).contains("new Object[] {x, -").contains("new Object[] {2, y, ")
                    .containsPattern("new Object\\[\\] \\{\\d+, -\\d+, z\\}");
        }

        @Test
        void constructorConstraintsUseExecutableValidation() {
            assertThat(src).contains(".forExecutables()").contains(".validateConstructorParameters(");
            // validate(obj) can't see constructor-parameter constraints
            assertThat(src).doesNotContain(".validate(obj)").doesNotContain("testIsValid");
        }

        @Test
        void noFalsePositivesOrMisleadingTodos() {
            assertThat(src).doesNotContain("assertThat(obj).isNotNull()")
                    .doesNotContain("TODO")
                    .doesNotContain("no constraints defined")
                    .doesNotContain("catch (Exception");
        }

        @Test
        void settersWithoutConstraintsAreNotTreatedAsValidated() {
            String withMethods = TestKit.of(Circle.class).withMethodTests(true).generateTest().asString();
            assertThat(withMethods).doesNotContain("setX(").doesNotContain("setY(").doesNotContain("setZ(");
        }

        private List<Integer> values(String source) {
            Matcher block = Pattern.compile("Stream<Arguments> " + source + "\\(\\) \\{(.*?)\\);", Pattern.DOTALL)
                    .matcher(src);
            assertThat(block.find()).as(source).isTrue();
            List<Integer> out = new ArrayList<>();
            Matcher v = Pattern.compile("Arguments\\.of\\(\"[^\"]*\", (-?\\d+)\\)").matcher(block.group(1));
            while (v.find()) out.add(Integer.parseInt(v.group(1)));
            return out;
        }
    }

    // ── Contradictions and projects without a validator ───────────────────

    @Test
    @DisplayName("contradictory constraints are reported, not turned into fixtures")
    void contradictoryConstraintsAreExplicit() {
        String src = TestKit.of(ImpossibleRange.class).generateTest().asString();
        assertThat(src).contains("testConstraintsAreSatisfiable").contains("impossible range")
                .doesNotContain("testAcceptsQuantity").doesNotContain("testRejectsQuantity");
    }

    @Test
    @DisplayName("without a validation provider the constructor must reject invalid values")
    void noProviderConstructorMustReject() {
        String src = TestKit.of(Circle.class).withProjectType(ProjectType.PLAIN_JAVA).generateTest().asString();
        assertThat(src).doesNotContain("validator")
                .contains("testRejects")
                .contains("assertThatThrownBy(() -> new Circle(")
                .contains("isInstanceOf(IllegalArgumentException.class)")
                .contains("in the constructor and throw IllegalArgumentException")
                .contains("assertThat(obj.getX()).isEqualTo(x)");
    }

    // ── Compiled and run for real ─────────────────────────────────────────

    @Nested
    @DisplayName("generated tests compile and run")
    class CompiledAndRun {

        @TempDir Path dir;

        @Test
        void generatedTestsCompileAndPass() throws Exception {
            Path classes = compileCircle("gen.ok", circleSource("gen.ok"), false);
            String test = generateFor(classes, "gen.ok.Circle");
            TestExecutionSummary summary = compileAndRun(classes, "gen.ok", test);

            assertThat(summary.getTestsFoundCount()).isGreaterThanOrEqualTo(10);
            assertThat(summary.getTotalFailureCount()).as(failures(summary)).isZero();
        }

        @Test
        void generatedTestsFailWhenTheConstraintIsRemoved() throws Exception {
            // Tests generated for the real Circle, run against a copy without @Positive on x:
            // the x rejection tests must fail, everything else must still pass.
            Path okClasses = compileCircle("gen.ok2", circleSource("gen.ok2"), false);
            String test = generateFor(okClasses, "gen.ok2.Circle").replace("package gen.ok2;", "package gen.mut;");
            String mutant = circleSource("gen.mut").replace("@Positive int x", "int x");
            Path mutantClasses = compileCircle("gen.mut", mutant, false);

            TestExecutionSummary summary = compileAndRun(mutantClasses, "gen.mut", test);
            assertThat(summary.getTotalFailureCount()).isPositive();
            assertThat(summary.getFailures()).allSatisfy(f ->
                    assertThat(f.getTestIdentifier().getUniqueId()).contains("testRejectsX"));
        }

        @Test
        void sourceEditsAreReflectedInTheNextGeneration() throws Exception {
            // As in the IDE: edit, rebuild, fresh class loader, generate again.
            Path v1 = compileCircle("gen.edit", circleSource("gen.edit"), true);
            String before = generateFor(v1, "gen.edit.Circle");

            Path v2 = compileCircle("gen.edit", circleSource("gen.edit").replace("@Max(5)", "@Max(9)"), true);
            String after = generateFor(v2, "gen.edit.Circle");

            assertThat(before).contains("Arguments.of(\"invalid z 2\", 6)").doesNotContain("\"valid z 9\"");
            assertThat(after).contains("Arguments.of(\"invalid z 2\", 10)")
                    .containsPattern("Arguments\\.of\\(\"valid z \\d+\", 9\\)");
            // compiled with -parameters: the violation path uses the real parameter name
            assertThat(after).contains(".endsWith(\".z\")");
        }

        // ── helpers ──

        private String circleSource(String pkg) throws Exception {
            return Files.readString(CIRCLE_SOURCE)
                    .replace("package com.paramichha.testkit.fixture.ctor;", "package " + pkg + ";");
        }

        private int compiles;

        private Path compileCircle(String pkg, String source, boolean parameters) throws Exception {
            Path src = dir.resolve("src" + compiles).resolve(pkg.replace('.', '/'));
            Path out = dir.resolve("classes" + compiles++);
            Files.createDirectories(src);
            Files.createDirectories(out);
            Files.writeString(src.resolve("Circle.java"), source);
            compile(out, out, parameters, src.resolve("Circle.java"));
            return out;
        }

        private String generateFor(Path classes, String className) throws Exception {
            try (URLClassLoader loader = loader(classes)) {
                return TestKit.of(Class.forName(className, true, loader)).generateTest().asString();
            }
        }

        private TestExecutionSummary compileAndRun(Path classes, String pkg, String testSource) throws Exception {
            Path src = dir.resolve("test-src" + compiles).resolve(pkg.replace('.', '/'));
            Files.createDirectories(src);
            Path file = src.resolve("CircleTest.java");
            Files.writeString(file, testSource);
            compile(classes, classes, false, file);

            try (URLClassLoader loader = loader(classes)) {
                ClassLoader previous = Thread.currentThread().getContextClassLoader();
                Thread.currentThread().setContextClassLoader(loader);
                try {
                    LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                            .selectors(selectClass(Class.forName(pkg + ".CircleTest", true, loader)))
                            .build();
                    Launcher launcher = LauncherFactory.create();
                    SummaryGeneratingListener listener = new SummaryGeneratingListener();
                    launcher.execute(request, listener);
                    return listener.getSummary();
                } finally {
                    Thread.currentThread().setContextClassLoader(previous);
                }
            }
        }

        private void compile(Path out, Path extraClasspath, boolean parameters, Path... files) {
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            List<String> args = new ArrayList<>(List.of(
                    "-d", out.toString(),
                    "-cp", extraClasspath + java.io.File.pathSeparator + System.getProperty("java.class.path"),
                    "-proc:none", "-nowarn"));
            if (parameters) args.add("-parameters");
            for (Path f : files) args.add(f.toString());
            ByteArrayOutputStream err = new ByteArrayOutputStream();
            int rc = javac.run(null, null, err, args.toArray(String[]::new));
            assertThat(rc).as("javac:\n" + err.toString(StandardCharsets.UTF_8)).isZero();
        }

        private URLClassLoader loader(Path classes) throws Exception {
            return new URLClassLoader(new URL[]{classes.toUri().toURL()}, getClass().getClassLoader());
        }

        private String failures(TestExecutionSummary summary) {
            StringBuilder sb = new StringBuilder();
            summary.getFailures().forEach(f -> sb.append(f.getTestIdentifier().getDisplayName())
                    .append(": ").append(f.getException()).append('\n'));
            return sb.toString();
        }
    }
}
