package com.paramichha.testkit.generator;

import com.paramichha.testkit.utils.JavaFormatting;
import com.paramichha.testkit.CodeGenerator;
import com.paramichha.testkit.GenerationResult;
import com.paramichha.testkit.TestCodeData;
import com.paramichha.testkit.model.ClassInfo;
import com.paramichha.testkit.model.ConstructorStyle;
import com.paramichha.testkit.model.FieldInfo;
import com.paramichha.testkit.utils.TestNames;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Production code generator using the FreshCoder pipeline.
 *
 * <pre>
 * TestCodeData
 *   → ClassDataFactory      (ALL DataFactory calls)
 *   → ClassData             (pure data)
 *   → ClassDataConfig       (template-resolved config)
 *   → ClassDataTransformer  (ClassData → ClassCoderInfo)
 *   → ClassCoder            (ClassCoderInfo → String)
 *   → google-java-format    (String → formatted String)
 *   → GenerationResult
 * </pre>
 */
public final class SolidCodeGenerator implements CodeGenerator {

    public static final SolidCodeGenerator INSTANCE = new SolidCodeGenerator();

    private final FreshCoder<ClassCoderInfo> coder      = new ClassCoder();

    private SolidCodeGenerator() {}

    @Override
    public GenerationResult generate(TestCodeData payload) {
        if (payload.classInfo().type() != null && payload.classInfo().type().isEnum())
            return enumTest(payload);
        var problem = Creatability.problem(payload.classInfo());
        if (problem.isPresent()) return placeholder(payload, problem.get());
        if (payload.fixture() != null && payload.fixture().hasError())
            return placeholder(payload, payload.fixture().error());
        try {
            ClassData       data   = ClassDataFactory.build(payload);
            ClassDataConfig config = buildConfig(payload, data);
            ClassCoderInfo  info   = rawTypesAllowed(ClassDataTransformer.transform(data, config), payload.classInfo());
            String          source = cleanCode(config.header() + coder.code(info));
            return GenerationResult.of(data.className(), config.targetPackage(), source);
        } catch (Exception e) {
            return placeholder(payload, e.getMessage());
        }
    }

    @Override
    public GenerationResult generateAll(TestCodeData payload) {
        if (payload.classInfo().type() != null && payload.classInfo().type().isEnum())
            return enumTest(payload);
        var problem = Creatability.problem(payload.classInfo());
        if (problem.isPresent()) return placeholder(payload, problem.get());
        if (payload.fixture() != null && payload.fixture().hasError())
            return placeholder(payload, payload.fixture().error());
        try {
            ClassData            data    = ClassDataFactory.build(payload);
            ClassDataConfig      config  = buildConfig(payload, data);
            List<ClassCoderInfo> infos   = ClassDataTransformer.transformSplit(data, config).stream()
                    .map(info -> rawTypesAllowed(info, payload.classInfo()))
                    .toList();
            List<GenerationResult> results = infos.stream()
                    .map(info -> GenerationResult.of(
                            info.name(),
                            config.targetPackage(),
                            cleanCode(config.header() + coder.code(info))))
                    .collect(Collectors.toList());
            return GenerationResult.ofAll(results);
        } catch (Exception e) {
            return placeholder(payload, e.getMessage());
        }
    }

    // ── Raw types ─────────────────────────────────────────────────────────

    /**
     * A class with a raw field ({@code List} instead of {@code List<String>}) makes a test that
     * reads that field use the same raw type, and the compiler warns on every use. The warning
     * belongs to the class under test, so such a test file says it knows, once. Files that never
     * read a raw field get nothing (an unneeded suppression is itself flagged by IDEs).
     */
    private ClassCoderInfo rawTypesAllowed(ClassCoderInfo info, ClassInfo classInfo) {
        String code = coder.code(info);
        boolean readsRawField = classInfo.fields().stream()
                .filter(f -> f.genericType() instanceof Class<?> c && c.getTypeParameters().length > 0)
                .map(FieldInfo::name)
                .anyMatch(name -> readsField(code, name));
        if (!readsRawField) return info;
        List<String> annotations = new ArrayList<>(info.annotations());
        annotations.addFirst("@SuppressWarnings({\"rawtypes\", \"unchecked\"})");
        return new ClassCoderInfo(info.packageName(), info.imports(), annotations, info.modifiers(),
                info.name(), info.fields(), info.methods(), info.nestedClasses());
    }

    /** True when the code calls the field's accessor: {@code .name()}, {@code .getName()} or {@code .isName()}. */
    static boolean readsField(String code, String name) {
        String cap = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        return code.contains("." + name + "()") || code.contains(".get" + cap + "()")
                || code.contains(".is" + cap + "()");
    }

    // ── Formatting ────────────────────────────────────────────────────────

    private String cleanCode(String source) {
        // Never breaks generation — falls back to unformatted source when the formatter
        // can't run in this JVM (see JavaFormatting).
        return JavaFormatting.format(source);
    }

    // ── Config assembly ───────────────────────────────────────────────────

    private ClassDataConfig buildConfig(TestCodeData payload, ClassData data) {
        ClassInfo classInfo    = payload.classInfo();
        boolean   hasJakarta   = payload.projectType().hasValidator()
                && classInfo.fields().stream().anyMatch(f -> !f.validations().isEmpty());
        boolean   isRecord     = classInfo.constructorStyle() == ConstructorStyle.RECORD;
        String    instanceName = TestNames.toVariable(classInfo.simpleName());

        ImportCollector imports = buildImports(classInfo, payload);
        // Values can bring types the field types don't show, e.g. a BigDecimal inside a nested
        // record in a List<TradeLeg>: import what the literals will use.
        imports.addValueImports(data.validValues());
        for (MemberData m : data.members()) {
            imports.addValueImports(m.validValues());
            imports.addValueImports(m.invalidValues());
        }

        String header = "// Generated by TestKit. Do not edit by hand. Add your own tests in "
                + classInfo.simpleName() + "ExtraTest.java.\n"
                + "// Regenerate: TestKit.of(" + classInfo.simpleName()
                + ".class).generateTest().writeTo(...)\n";

        return new ClassDataConfig(
                classInfo.simpleName(),
                payload.targetPackage(),
                instanceName,
                header,
                imports.collect().stream().sorted().collect(Collectors.toList()),
                hasJakarta,
                classInfo.utilityClass(),
                isRecord,
                classInfo.constructorStyle() == ConstructorStyle.SETTER,
                payload.template().validatorField,
                payload.template().validatorSetup,
                payload.template().classAffix(),
                payload.template().methodAffix(),
                payload.template());
    }

    private ImportCollector buildImports(ClassInfo classInfo, TestCodeData payload) {
        ImportCollector ic = new ImportCollector();
        ic.addTypeImport(classInfo.type());
        ic.addFieldTypeImports(classInfo.fields());

        String targetPkg = payload.targetPackage();
        classInfo.methods().forEach(m -> {
            m.parameters().forEach(p -> {
                String pkg = p.type().isPrimitive() ? "" : p.type().getPackageName();
                if (!pkg.equals(targetPkg)
                        && !pkg.equals("java.lang")
                        && !pkg.startsWith("java.util")) {
                    ic.addTypeImport(p.type());
                }
            });
        });

        boolean needsDataValidation = classInfo.methods().stream().anyMatch(m ->
                m.returnType().isConstrained() ||
                        m.parameters().stream().anyMatch(p -> !p.validations().isEmpty()));
        if (needsDataValidation) ic.addRawImport("com.paramichha.utils.DataValidation");

        return ic;
    }

    // ── Placeholder ───────────────────────────────────────────────────────

    /**
     * An enum has no fields to build: check that it has constants and that each one is found again
     * by its name (catches a constant renamed without its uses, or a broken valueOf override).
     */
    private GenerationResult enumTest(TestCodeData payload) {
        String type = payload.classInfo().simpleName();
        String name = payload.template().classAffix().apply(type);
        String pkg  = payload.targetPackage();
        String source = "// Generated by TestKit. Do not edit by hand. Add your own tests in " + type + "ExtraTest.java.\n"
                + "// Regenerate: TestKit.of(" + type + ".class).generateTest().writeTo(...)\n"
                + "package " + pkg + ";\n\n"
                + "import org.junit.jupiter.api.DisplayName;\n"
                + "import org.junit.jupiter.api.Test;\n"
                + "import org.junit.jupiter.params.ParameterizedTest;\n"
                + "import org.junit.jupiter.params.provider.EnumSource;\n\n"
                + "import static org.assertj.core.api.Assertions.assertThat;\n\n"
                + "@DisplayName(\"" + type + "\")\n"
                + "class " + name + " {\n\n"
                + "    @Test\n"
                + "    @DisplayName(\"has constants\")\n"
                + "    void testHasConstants() {\n"
                + "        assertThat(" + type + ".values()).isNotEmpty();\n"
                + "    }\n\n"
                + "    @ParameterizedTest(name = \"{0}\")\n"
                + "    @EnumSource(" + type + ".class)\n"
                + "    @DisplayName(\"every constant is found by its name\")\n"
                + "    void testValueOfName(" + type + " constant) {\n"
                + "        assertThat(" + type + ".valueOf(constant.name())).isSameAs(constant);\n"
                + "    }\n"
                + "}\n";
        return GenerationResult.of(name, pkg, cleanCode(source));
    }

    private GenerationResult placeholder(TestCodeData payload, String error) {
        String name = payload.template().classAffix().apply(payload.classInfo().simpleName());
        String pkg  = payload.targetPackage();
        System.err.println("✗ TestKit failed for "
                + payload.classInfo().simpleName() + ": " + error);
        return GenerationResult.of(name, pkg,
                "package " + pkg + ";\n\n"
                        + "// TODO: " + name + "\n"
                        + "// Generation failed for: " + payload.classInfo().simpleName() + "\n"
                        + "// Reason: " + error + "\n"
                        + "// Fix the class and regenerate.\n");
    }
}