package com.paramichha.testkit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.paramichha.testkit.utils.FormatJavaFile.removeUnUsedImport;

/**
 * A single generated Java source file.
 */
public record JavaSourceCode(
        String packageName,
        String className,
        String source
) {
    public JavaSourceCode {
        source = removeUnUsedImport(source);
    }

    public Path writeTo(Path sourceRoot) {
        try {
            Path dir = sourceRoot;
            for (String part : packageName.split("\\.")) dir = dir.resolve(part);
            Files.createDirectories(dir);
            Path file = dir.resolve(className + ".java");
            if (Files.exists(file) && Files.readString(file).equals(source))
                return file;
            Files.writeString(file, source);
            return file;
        } catch (IOException e) {
            throw new TestKitException("Failed to write " + className + ".java", e);
        }
    }

    public String asString() { return source; }
}