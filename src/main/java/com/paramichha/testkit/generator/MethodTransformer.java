package com.paramichha.testkit.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms a {@link MethodData} into a nested {@link ClassCoderInfo}.
 * Delegates to {@link CommandTransformer}, {@link QueryTransformer}, {@link MutatorTransformer}
 * based on method kind.
 */
final class MethodTransformer {

    static ClassCoderInfo transform(MethodData method, ClassDataConfig config) {
        List<MethodCoderInfo> methods = switch (method.kind()) {
            case COMMAND     -> CommandTransformer.transform(method, config);
            case QUERY       -> QueryTransformer.transform(method, config);
            case MUTATOR,
                 TRANSFORMER,
                 OPERATION   -> MutatorTransformer.transform(method, config);
        };

        List<FieldCoderInfo> fields = new ArrayList<>();
        if (method.returnData().needsWrapperRecord()
                && method.returnData().wrapperRecord() != null) {
            fields.add(new FieldCoderInfo(
                    List.of(), List.of(),
                    "", method.returnData().wrapperRecord(), null));
        }

        return ClassCoderInfo.nested(
                List.of("@Nested",
                        "@DisplayName(\"" + method.displayName() + "\")"),
                List.of(),
                method.nestedClassName(),
                List.copyOf(fields),
                methods,
                List.of());
    }
}
