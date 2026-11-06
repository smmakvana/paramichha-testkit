package com.paramichha.testkit.generator;

/**
 * Transforms a {@link ParamData} into a {@link ParameterCoderInfo}.
 * Knows: parameter test data.
 * Produces: parameter syntax description.
 */
final class ParameterTransformer {

    private ParameterTransformer() {}

    static ParameterCoderInfo transform(ParamData param) {
        return ParameterCoderInfo.of(param.type().className(), param.name());
    }
}
