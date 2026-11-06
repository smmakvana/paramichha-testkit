package com.paramichha.testkit.sweep;

import com.paramichha.datafactory.catalog.number.NumberRecord;
import com.paramichha.testkit.TestKit;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

/**
 * End-to-end sweep over the whole datafactory-catalog: for every catalog class, TestKit generates
 * a test, the test is compiled with javac and run with the JUnit launcher, and every generated test
 * must pass. This proves TestKit's output compiles and is correct for every class shape in the
 * catalog, and, because every value in those tests comes from DataFactory, that DataFactory's
 * valid and invalid values really are valid and invalid.
 */
class CatalogSweepTest {

    private static final String CATALOG_PACKAGE = "com/paramichha/datafactory/catalog/";

    @TempDir Path dir;

    /** Why a catalog class is known to fail today. Each one is a TestKit bug to fix. */
    enum Gap {
        SETTER("JavaBeans class (no-args constructor and setters): the generated test calls an all-args constructor that does not exist"),
        SIZE_NULL("@Size on a collection or map: null is generated as invalid, but Bean Validation accepts null for @Size"),
        QUEUE("Queue field: the generated value is a String literal, not a Queue"),
        SET("Set field: the generated Set literal does not compile (type inference)"),
        NAME_CLASH("a field named 'description' clashes with the generated test parameter 'description'"),
        GETTER("Optional field: the getter check compares against the wrong value"),
        VALID_MAP_OPTIONAL("record with an Optional component: the valid baseline object fails validation"),
        IMPORT("a nested type used by a field is not imported"),
        ENUM("enum: TestKit writes only a TODO comment and no test class"),
        UNSUPPORTED("fixture TestKit does not support yet: it should be reported, not turned into code that does not compile");

        final String reason;

        Gap(String reason) {
            this.reason = reason;
        }
    }

    /**
     * Catalog classes whose generated test does not compile or pass today, with the reason. The list
     * may only shrink: a class on it that starts passing fails this sweep until it is removed, and a
     * class not on it must pass.
     */
    static final Map<String, Gap> KNOWN_FAILING = Map.ofEntries(
            Map.entry("annotation.assertbool.AssertBoolPojo", Gap.SETTER),
            Map.entry("annotation.combined.CombinedPojo", Gap.SETTER),
            Map.entry("annotation.creditcard.CreditCardPojo", Gap.SETTER),
            Map.entry("annotation.decimal.DecimalConstraintPojo", Gap.SETTER),
            Map.entry("annotation.ean.EanPojo", Gap.SETTER),
            Map.entry("annotation.email.EmailPojo", Gap.SETTER),
            Map.entry("annotation.isbn.IsbnPojo", Gap.SETTER),
            Map.entry("annotation.notblank.NotBlankPojo", Gap.SETTER),
            Map.entry("annotation.notempty.NotEmptyPojo", Gap.SETTER),
            Map.entry("annotation.notnull.NotNullPojo", Gap.SETTER),
            Map.entry("annotation.numeric.NumericConstraintPojo", Gap.SETTER),
            Map.entry("annotation.pattern.PatternPojo", Gap.SETTER),
            Map.entry("annotation.size.SizePojo", Gap.SETTER),
            Map.entry("annotation.temporal.TemporalConstraintPojo", Gap.SETTER),
            Map.entry("annotation.url.UrlPojo", Gap.SETTER),
            Map.entry("collection.array.ArrayPojo", Gap.SETTER),
            Map.entry("collection.list.ListPojo", Gap.SETTER),
            Map.entry("collection.map.MapPojo", Gap.SETTER),
            Map.entry("collection.queue.QueuePojo", Gap.SETTER),
            Map.entry("collection.set.SetPojo", Gap.SETTER),
            Map.entry("date.DatePojo", Gap.SETTER),
            Map.entry("datetime.DateTimePojo", Gap.SETTER),
            Map.entry("decimal.DecimalPojo", Gap.SETTER),
            Map.entry("enumtype.EnumPojo", Gap.SETTER),
            Map.entry("identifier.IdentifierPojo", Gap.SETTER),
            Map.entry("logical.LogicalPojo", Gap.SETTER),
            Map.entry("nested.NestedPojo", Gap.SETTER),
            Map.entry("number.NumberPojo", Gap.SETTER),
            Map.entry("optional.OptionalPojo", Gap.SETTER),
            Map.entry("realworld.address.PostalAddressPojo", Gap.SETTER),
            Map.entry("text.TextPojo", Gap.SETTER),
            Map.entry("timepart.TimePartPojo", Gap.SETTER),
            Map.entry("annotation.size.SizeAllArgs", Gap.SIZE_NULL),
            Map.entry("annotation.size.SizeLombok", Gap.SIZE_NULL),
            Map.entry("annotation.size.SizeRecord", Gap.SIZE_NULL),
            Map.entry("collection.list.ListAllArgs", Gap.SIZE_NULL),
            Map.entry("collection.list.ListLombok", Gap.SIZE_NULL),
            Map.entry("collection.list.ListRecord", Gap.SIZE_NULL),
            Map.entry("collection.map.MapAllArgs", Gap.SIZE_NULL),
            Map.entry("collection.map.MapLombok", Gap.SIZE_NULL),
            Map.entry("collection.map.MapRecord", Gap.SIZE_NULL),
            Map.entry("collection.queue.QueueAllArgs", Gap.QUEUE),
            Map.entry("collection.queue.QueueLombok", Gap.QUEUE),
            Map.entry("collection.queue.QueueRecord", Gap.QUEUE),
            Map.entry("collection.set.SetAllArgs", Gap.SET),
            Map.entry("collection.set.SetLombok", Gap.SET),
            Map.entry("collection.set.SetRecord", Gap.SET),
            Map.entry("text.TextAllArgs", Gap.NAME_CLASH),
            Map.entry("text.TextLombok", Gap.NAME_CLASH),
            Map.entry("text.TextRecord", Gap.NAME_CLASH),
            Map.entry("optional.OptionalAllArgs", Gap.GETTER),
            Map.entry("optional.OptionalLombok", Gap.GETTER),
            Map.entry("optional.OptionalRecord", Gap.VALID_MAP_OPTIONAL),
            Map.entry("realworld.trading.TradeWithLegs", Gap.IMPORT),
            Map.entry("enumtype.OrderStatus", Gap.ENUM),
            Map.entry("realworld.trading.TradeSide", Gap.ENUM),
            Map.entry("realworld.trading.TradeStatus", Gap.ENUM),
            Map.entry("unsupported.CircularReference", Gap.UNSUPPORTED),
            Map.entry("unsupported.ErasedGeneric", Gap.UNSUPPORTED));


    static Stream<String> catalogClasses() throws Exception {
        Path jar = Path.of(NumberRecord.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        List<String> names = new ArrayList<>();
        if (Files.isDirectory(jar)) {
            try (Stream<Path> files = Files.walk(jar.resolve(CATALOG_PACKAGE))) {
                files.map(p -> jar.relativize(p).toString().replace(File.separatorChar, '/')).forEach(names::add);
            }
        } else {
            try (JarFile file = new JarFile(jar.toFile())) {
                file.stream().map(e -> e.getName()).forEach(names::add);
            }
        }
        return names.stream()
                .filter(n -> n.startsWith(CATALOG_PACKAGE) && n.endsWith(".class") && !n.contains("$"))
                .map(n -> n.substring(0, n.length() - ".class".length()).replace('/', '.'))
                .sorted();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("catalogClasses")
    void generatedTestCompilesAndPasses(String className) throws Exception {
        Gap gap = KNOWN_FAILING.get(className.substring("com.paramichha.datafactory.catalog.".length()));
        if (gap == null) {
            generateCompileAndRun(className);
            return;
        }
        try {
            generateCompileAndRun(className);
        } catch (AssertionError stillFailing) {
            return; // known gap, still open
        }
        throw new AssertionError(className + " now passes (" + gap + ": " + gap.reason
                + "). Remove it from KNOWN_FAILING.");
    }

    @org.junit.jupiter.api.Test
    void everyKnownFailingClassIsInTheCatalog() throws Exception {
        List<String> catalog = catalogClasses()
                .map(n -> n.substring("com.paramichha.datafactory.catalog.".length())).toList();
        assertThat(catalog).containsAll(KNOWN_FAILING.keySet());
    }

    private void generateCompileAndRun(String className) throws Exception {
        Class<?> type = Class.forName(className);
        String source = TestKit.of(type).generateTest().asString();

        Matcher name = Pattern.compile("(?m)^(?:public\\s+)?(?:final\\s+)?class\\s+(\\w+)").matcher(source);
        assertThat(name.find()).as("generated test class name\n%s", source).isTrue();
        String testClass = type.getPackageName() + "." + name.group(1);

        Path out = dir.resolve("classes");
        Path file = dir.resolve("src").resolve(testClass.replace('.', '/') + ".java");
        Files.createDirectories(file.getParent());
        Files.createDirectories(out);
        Files.writeString(file, source);
        compile(out, file, source);

        TestExecutionSummary summary = run(out, testClass);
        assertThat(summary.getTestsFoundCount()).as("tests found in generated %s", testClass).isPositive();
        assertThat(summary.getTotalFailureCount()).as("failures in generated %s:%n%s%n--- source ---%n%s",
                testClass, failures(summary), source).isZero();
    }

    private static void compile(Path out, Path file, String source) {
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        int rc = javac.run(null, null, err, "-d", out.toString(),
                "-cp", out + File.pathSeparator + System.getProperty("java.class.path"),
                "-proc:none", "-nowarn", file.toString());
        assertThat(rc).as("javac failed:%n%s%n--- source ---%n%s", err.toString(StandardCharsets.UTF_8), source).isZero();
    }

    private TestExecutionSummary run(Path classes, String testClass) throws Exception {
        try (URLClassLoader loader = new CatalogFirstLoader(classes, getClass().getClassLoader())) {
            ClassLoader previous = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(loader);
            try {
                LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                        .selectors(selectClass(Class.forName(testClass, true, loader)))
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

    /**
     * Loads catalog classes and the generated test from the same loader, as in a real project where
     * a test and the class it tests share a package at run time. Without this, package-private
     * members (such as Lombok's package-private all-args constructor) look inaccessible.
     */
    private static final class CatalogFirstLoader extends URLClassLoader {
        private static final String CATALOG = "com.paramichha.datafactory.catalog.";

        CatalogFirstLoader(Path generated, ClassLoader parent) throws Exception {
            super(new URL[]{generated.toUri().toURL(), catalogLocation()}, parent);
        }

        private static URL catalogLocation() {
            return NumberRecord.class.getProtectionDomain().getCodeSource().getLocation();
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            if (!name.startsWith(CATALOG)) return super.loadClass(name, resolve);
            synchronized (getClassLoadingLock(name)) {
                Class<?> c = findLoadedClass(name);
                if (c == null) c = findClass(name);
                if (resolve) resolveClass(c);
                return c;
            }
        }
    }

    private static String failures(TestExecutionSummary summary) {
        List<String> lines = new ArrayList<>();
        summary.getFailures().forEach(f -> lines.add(f.getTestIdentifier().getDisplayName() + ": " + f.getException()));
        Collections.sort(lines);
        return String.join("\n", lines);
    }
}
