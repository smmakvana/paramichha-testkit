# Idea: TestKit reads a raw `.java` source file

**Status:** not started. A design note to come back to; nothing here is built yet.

## Goal

Give TestKit a Java **source** file (or source string) and get tests back, without compiling the
class first. The same engine would then serve:

1. **devboost**: it writes source from project.yml and could generate class-level tests straight
   away, with no compile step in between.
2. **TestBuddy** (IDE): tests for the file being edited, even before it compiles.
3. **Any Jakarta project**: standalone, on source.

## Where it plugs in today

```
TestKit.of(Class<?>)   ──► ReflectionClassReader ──► ClassInfo ──► generator ──► test source
TestKit.of(ClassInfo)  ──────────────────────────────┘   (already public: the entry point to use)
```

- `ClassReader` (`ClassInfo read(Class<?> type)`) is the extension point. Today its only
  implementation, `ReflectionClassReader`, reads a **compiled** class.
- `TestKit.of(ClassInfo)` already exists, so a source reader can feed the generator without
  touching `TestKit`'s API.

## What has to change

| Piece | Today | Needed for source |
|---|---|---|
| `ClassReader` | `read(Class<?>)` | add a source variant, e.g. `SourceClassReader.read(String source)` (JavaParser or the JDK's `com.sun.source` tree API) |
| `ClassInfo.type()` | returns `Class<?>` | must become optional: a source file has no `Class` yet |
| `FieldInfo.rawType()` / `genericType()` | `Class<?>` / `java.lang.reflect.Type` | a type *name* model (simple name, package, type arguments) |
| `FieldInfo.validations()` | `List<Annotation>` instances | annotation *descriptions* (name + attributes). DataFactory already parses annotation strings: `FieldValue.with("@Size(max=100)")` |
| Values from DataFactory | `DataFactory.of(Class)` for nested domain types | `DataFactory.field(name, type, annotations)` / `.with(String)` per field; nested types need their own source read |
| Seed | `Seeds.stableHash(type)` | `Seeds.stableHash(qualifiedName)` (same value: it hashes the name) |

Users of `classInfo.type()` to revisit: `ClassDataFactory` (valid/invalid values, seed) and
`ReflectionClassReader` itself.

## Suggested order

1. Introduce the name-based type model next to the reflection one; keep reflection the default.
2. `SourceClassReader` for records and simple classes (fields + annotations only).
3. Run `CatalogSweepTest` through the source reader too: same 141 classes, same expected results.
   That is the acceptance test.
4. Lombok, all-args and JavaBeans styles from source; then method tests.

## History

- `ClassDataCoder` (deleted when this note was written) was **not** source-reading code: it was an
  older string-assembly coder replaced by `ClassDataTransformer` + `ClassCoder`. Nothing on any
  branch contained source-reading work at that time. If needed, it is in git history.
