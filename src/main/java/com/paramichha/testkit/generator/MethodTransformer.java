package com.paramichha.testkit.generator;

import java.util.List;

/**
 * Transforms a {@link MethodData} into a nested {@link ClassCoderInfo}.
 * Delegates to {@link CommandTransformer}, {@link QueryTransformer}, {@link MutatorTransformer}.
 */
final class MethodTransformer {

    private MethodTransformer() {}

    static ClassCoderInfo transform(MethodData method, ClassDataConfig config) {
        String className   = config.sourceClassName();
        String displayName = com.paramichha.testkit.NameFormat.display(
                config.template(), className, "Method", method.name() + "()");

        List<MethodCoderInfo> methods = switch (method.kind()) {
            case COMMAND     -> CommandTransformer.transform(method, config);
            case QUERY       -> QueryTransformer.transform(method, config);
            case MUTATOR,
                 TRANSFORMER,
                 OPERATION   -> MutatorTransformer.transform(method, config);
        };

        return ClassCoderInfo.nested(
                List.of("@Nested",
                        "@DisplayName(\"" + displayName + "\")",
                        "@Tag(\"method\")",
                        "@TestMethodOrder(MethodOrderer.MethodName.class)"),
                List.of(),
                method.nestedClassName(),
                List.of(),
                methods,
                List.of());
    }
}