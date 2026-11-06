package com.paramichha.testkit.generator;

import com.paramichha.testkit.model.MethodKind;

import java.util.List;

/**
 * A class method with kind classification, parameters, and return type —
 * all DataFactory values pre-resolved.
 */
public interface MethodData {

    String          name();
    MethodKind      kind();        // COMMAND, QUERY, MUTATOR, TRANSFORMER, OPERATION

    List<ParamData> params();
    ReturnData      returnData();

    // ── Template-resolved names ───────────────────────────────────────────

    String  nestedClassName();     // e.g. "ObjectAnnotatedServiceMethodMergeTest"
    String  validSourceName();     // e.g. "validMergeInputs"
    String  invalidSourceName();   // e.g. "invalidMergeInputs"

    // ── Convenience ───────────────────────────────────────────────────────

    default boolean anyParamConstrained(){ return params().stream().anyMatch(ParamData::isConstrained); }
}
