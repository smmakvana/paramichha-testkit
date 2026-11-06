package com.paramichha.testkit;

import com.paramichha.testkit.scan.TestableScanner;
import com.paramichha.testkit.scan.fixture.ScanTarget;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** Public entry points and their less common paths. */
class PublicApiEdgesTest {

    private static final String FIXTURE_PACKAGE = "com.paramichha.testkit.scan.fixture";

    @Test
    @DisplayName("the project type follows what is on the classpath")
    void projectTypeDetection() {
        assertThat(ProjectType.detect(name -> name.contains("springframework"))).isEqualTo(ProjectType.SPRING);
        assertThat(ProjectType.detect(name -> name.contains("hibernate"))).isEqualTo(ProjectType.JAKARTA);
        assertThat(ProjectType.detect(name -> false)).isEqualTo(ProjectType.PLAIN_JAVA);
        assertThat(ProjectType.SPRING.isSpring()).isTrue();
        assertThat(ProjectType.PLAIN_JAVA.isPlainJava()).isTrue();
        assertThat(ProjectType.JAKARTA.isPlainJava()).isFalse();
    }

    @Test
    @DisplayName("template builder: validator field, display pattern, token resolution")
    void templateBuilder() {
        TestKitTemplate t = TestKitTemplate.builder().validatorField("checker").displayPattern("{Name} of {Class}").build();
        assertThat(t.validatorField).isEqualTo("checker");
        assertThat(t.displayPattern).isEqualTo("{Name} of {Class}");
        assertThat(t.resolve("{ClassName}/{className}/{MemberName}/{memberName}", "Circle", "radius"))
                .isEqualTo("Circle/circle/Radius/radius");
        assertThatThrownBy(() -> TestKitTemplate.builder().displayPattern("{Unknown}"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("an affix part that must not be empty is rejected")
    void emptyAffix() {
        assertThatThrownBy(() -> Affix.check("classPrefix", "", false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("classPrefix must not be empty");
    }

    @Test
    @DisplayName("test data for a type uses its stable seed")
    void dataProviderDefaultSeed() {
        assertThat(DefaultTestDataProvider.INSTANCE.provide(ScanTarget.class).type()).isEqualTo(ScanTarget.class);
    }

    @Test
    @DisplayName("a file that can't be read as text is treated as hand-owned")
    void unreadableFileIsKept(@TempDir Path dir) {
        assertThat(JavaSourceCode.isHandOwned(dir)).isTrue();   // a directory: exists, can't be read
    }

    @Test
    @DisplayName("@Testable classes are found in directories and jars, with their target package")
    void scanning(@TempDir Path dir) throws Exception {
        assertThat(TestableScanner.scan(FIXTURE_PACKAGE))
                .contains(new TestableScanner.ScanResult(ScanTarget.class, "com.example.generated"));

        Path jar = jarWith(dir.resolve("fixtures.jar"), ScanTarget.class);
        ClassLoader app = getClass().getClassLoader();
        ClassLoader jarOnly = new ClassLoader(app) {
            @Override
            public Enumeration<URL> getResources(String name) throws IOException {
                return Collections.enumeration(List.of(URI.create("jar:" + jar.toUri() + "!/" + name).toURL()));
            }
        };
        assertThat(withContextLoader(jarOnly, () -> TestableScanner.scan(FIXTURE_PACKAGE)))
                .containsExactly(new TestableScanner.ScanResult(ScanTarget.class, "com.example.generated"));
    }

    @Test
    @DisplayName("a package that can't be listed fails with the package name")
    void scanFailure() {
        ClassLoader broken = new ClassLoader(getClass().getClassLoader()) {
            @Override
            public Enumeration<URL> getResources(String name) throws IOException {
                throw new IOException("disk error");
            }
        };
        assertThatThrownBy(() -> withContextLoader(broken, () -> TestableScanner.scan(FIXTURE_PACKAGE)))
                .isInstanceOf(TestKitException.class)
                .hasMessage("Failed to scan package: " + FIXTURE_PACKAGE);
    }

    private static Path jarWith(Path jar, Class<?> type) throws IOException {
        String entry = type.getName().replace('.', '/') + ".class";
        try (OutputStream out = Files.newOutputStream(jar); JarOutputStream j = new JarOutputStream(out)) {
            StringBuilder dirs = new StringBuilder();
            for (String part : entry.substring(0, entry.lastIndexOf('/')).split("/")) {
                dirs.append(part).append('/');
                j.putNextEntry(new JarEntry(dirs.toString()));
                j.closeEntry();
            }
            j.putNextEntry(new JarEntry(entry));
            try (var in = type.getClassLoader().getResourceAsStream(entry)) {
                in.transferTo(j);
            }
            j.closeEntry();
        }
        return jar;
    }

    private static <T> T withContextLoader(ClassLoader loader, java.util.function.Supplier<T> action) {
        Thread t = Thread.currentThread();
        ClassLoader old = t.getContextClassLoader();
        t.setContextClassLoader(loader);
        try {
            return action.get();
        } finally {
            t.setContextClassLoader(old);
        }
    }
}
