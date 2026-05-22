package com.paramichha.testkit.generator;

import java.util.List;

final class DefaultParamData implements ParamData {

    private final String       name;
    private final ClassData    type;
    private final boolean      constrained;
    private final List<Object> validValues;
    private final List<Object> invalidValues;
    private final List<String> enforcementHints;

    DefaultParamData(String name, ClassData type, boolean constrained,
                     List<Object> validValues, List<Object> invalidValues,
                     List<String> enforcementHints) {
        this.name             = name;
        this.type             = type;
        this.constrained      = constrained;
        this.validValues      = validValues;
        this.invalidValues    = invalidValues;
        this.enforcementHints = enforcementHints;
    }

    public String       name()             { return name;             }
    public ClassData    type()             { return type;             }
    public boolean      isConstrained()    { return constrained;      }
    public List<Object> validValues()      { return validValues;      }
    public List<Object> invalidValues()    { return invalidValues;    }
    public List<String> enforcementHints() { return enforcementHints; }
}
