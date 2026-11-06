package com.paramichha.testkit.generator;

import com.paramichha.testkit.model.MethodKind;

import java.util.List;

final class DefaultMethodData implements MethodData {

    private final String          name;
    private final MethodKind      kind;
    private final List<ParamData> params;
    private final ReturnData      returnData;
    private final String          nestedClassName;
    private final String          validSourceName;
    private final String          invalidSourceName;

    DefaultMethodData(String name, MethodKind kind, List<ParamData> params,
                      ReturnData returnData, String nestedClassName,
                      String validSourceName, String invalidSourceName) {
        this.name             = name;
        this.kind             = kind;
        this.params           = params;
        this.returnData       = returnData;
        this.nestedClassName  = nestedClassName;
        this.validSourceName  = validSourceName;
        this.invalidSourceName= invalidSourceName;
    }

    public String          name()             { return name;             }
    public MethodKind      kind()             { return kind;             }
    public List<ParamData> params()           { return params;           }
    public ReturnData      returnData()       { return returnData;       }
    public String          nestedClassName()  { return nestedClassName;  }
    public String          validSourceName()  { return validSourceName;  }
    public String          invalidSourceName(){ return invalidSourceName; }
}
