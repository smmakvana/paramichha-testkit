# TestKit

Generates JUnit 5 tests for your Java classes from their Jakarta Validation annotations.
Point it at a class; it writes a test file that checks the class can be built, that every
field accepts its boundary values and rejects values that break each constraint, and that
each public method accepts valid input and rejects invalid input.

Built on [DataFactory](https://github.com/smmakvana/paramichha-datafactory), which supplies all the data.

---

## Install

```xml
<dependency>
    <groupId>com.paramichha</groupId>
    <artifactId>testkit</artifactId>
    <version>0.2.0</version>
</dependency>
```

Requires Java 21. The **generated** tests need JUnit 5 (`junit-jupiter`, including `junit-jupiter-params`),
AssertJ and a Jakarta Validation implementation (e.g. Hibernate Validator) on the test classpath.

---

## Quick start

```java
public record UserRequest(
    @NotBlank @Email            String  email,
    @NotNull @Min(18) @Max(120) Integer age
) {}
```

```java
// One test file
TestKit.of(UserRequest.class)
       .withPackage("com.example")
       .generateTest()
       .writeTo(Path.of("src/test/java"));   // → src/test/java/com/example/UserRequestTest.java

// Split mode — one file per concern (creation, each field, each method)
TestKit.of(UserRequest.class).generateTests().writeTo(Path.of("src/test/java"));

// Or keep the source in memory
String source = TestKit.of(UserRequest.class).generateTest().asString();
```

### What gets generated

```java
@DisplayName("UserRequest")
class UserRequestTest {

  @Nested @DisplayName("UserRequest Creation") @Tag("creation")
  class UserRequestCreationTest {
    @Test void testCanBeCreated()         { … }
    @Test void testHoldsExpectedValues()  { … }
    @Test void testIsValid()              { … }   // validator finds no violations
  }

  @Nested @DisplayName("UserRequest Field age") @Tag("field")
  class UserRequestAgeTest {
    @ParameterizedTest @MethodSource("validAges")   void testAcceptsAge(…) { … }  // boundary values within 18..120
    @ParameterizedTest @MethodSource("invalidAges") void testRejectsAge(…) { … }  // null, 17, 121
  }
  …
}
```

Classes with public methods also get method tests (`testAcceptsValidInput` / `testRejectsInvalidInput`) per
method, grouped by kind: command, mutator, query, transformer, operation.

---

## Features

- **Field tests from annotations** — boundary values that must pass, one value per constraint that must fail.
- **Creation tests** — the class can be built, holds the values it was given, and validates cleanly.
- **Method tests** — public methods called with valid arguments and with each invalid argument.
- **Single or split output** — `generateTest()` for one file, `generateTests()` for one file per concern.
- **Batch generation** — `TestKitRunner.forClasses(A.class, B.class)` or `TestKitRunner.scanPackage("com.example")`
  for classes marked `@Testable` / `@Testables({A.class, B.class})`.
- **Deterministic output** — values are seeded from the class name, so regenerating gives the same file and
  generated tests are safe to commit. Override with `withSeed(n)`.
- **Records, Lombok, all-args constructors, JavaBeans** — the constructor style is detected; objects passed as
  arguments are written as `new Foo(…)` calls.
- **Unannotated fields aren't skipped** — they get a test with a `// TODO: add constraints` note, so gaps are visible.
- **Method tests on or off** — `withMethodTests(false)` generates only creation and field tests.
- **Generation modes** — `withMode(GenerationMode.PRODUCTION)` (default) or `DEV`, see DataFactory.
- **Pluggable** — `withTemplate(...)` controls naming; `withProjectType(SPRING | JAKARTA | PLAIN_JAVA)` is detected
  from the classpath when not set.
- **Embeds safely** — formatting with google-java-format is optional and never breaks generation (see below).

---

## Formatting

Output is formatted with google-java-format when it can run. google-java-format needs `jdk.compiler`
internals, so the JVM must start with:

```
--add-exports jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED
```

Without them, TestKit logs one warning and returns the source unformatted — it is still valid Java.
Set `-Dtestkit.format=false` to skip formatting entirely, for example in an IDE that applies its own code style.

---

## Limitations

- **Works on compiled classes.** Input is a `Class<?>` (or a `ClassInfo`); TestKit reads it by reflection.
  Source that hasn't been compiled yet can't be used directly.
- **Generated tests are a starting point, not a specification.** They check constraints and that methods
  accept or reject input; they don't know your business rules or expected return values. Review them.
- **How a reject test checks a constraint depends on where the constraint is.**
  - On a **constructor parameter** (`Circle(@Positive int x)`): checked with
    `validator.forExecutables().validateConstructorParameters(...)` — the constructor itself isn't called, and isn't
    assumed to enforce anything. The violation must be on that parameter.
  - On a **field or getter**: the object is built and `validator.validate(obj)` must report a violation on that
    field. If the constructor rejects the value itself (`IllegalArgumentException` or `ValidationException`), that
    counts as rejected too; any other exception fails the test.
- **Unconstrained fields get accept tests only.** Nothing can reject their values, so a reject test would be a false
  positive.
- **Contradictory constraints** (e.g. `@Min(5) @Max(3)`) produce a failing `testConstraintsAreSatisfiable` naming the
  problem, instead of fixtures that can't be valid.
- **Without a Jakarta Validation provider** on the test classpath, no validation or reject tests are generated.
- **Jakarta Validation only.** `javax.validation` annotations are not read.
- **Objects as arguments** are written as `new Foo(…)` only when `Foo` is a record, or has a public constructor
  whose parameters match its declared fields in order. Otherwise the argument is `null` and the test needs editing.
- **Whatever DataFactory can't express, TestKit can't test** — custom `@Constraint` annotations, class-level and
  cross-field rules, validation groups and element annotations (`List<@NotBlank String>`) are not covered.
  See [DataFactory's limitations](https://github.com/smmakvana/paramichha-datafactory#limitations).
- **Failures produce a placeholder file.** If a class can't be processed, the output is a test file containing
  only comments with the reason; check the console for `✗ TestKit failed for …`.
- **`TestKitRunner.writeBothModesTo(...)` overwrites.** PRODUCTION and DEV output use the same file name, so the
  DEV file replaces the PRODUCTION one.
- **Parameter names come from the compiled class.** Compile the classes under test with `-parameters`
  (`<parameters>true</parameters>` in maven-compiler-plugin) to get `merge(other, newName)` in generated method
  tests; without it Java only keeps `arg0`, `arg1`, ….
- **JUnit 5 + AssertJ only.** No TestNG, JUnit 4 or Hamcrest output.

---

## License

Apache 2.0
