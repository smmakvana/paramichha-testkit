package com.paramichha.testkit.generator;

import java.util.List;

final class DefaultMemberData implements MemberData {

    private final String       name;
    private final ClassData    type;
    private final boolean      constrained;
    private final boolean      required;
    private final List<Object> validValues;
    private final List<Object> invalidValues;

    DefaultMemberData(String name, ClassData type, boolean constrained, boolean required,
                      List<Object> validValues, List<Object> invalidValues) {
        this.name          = name;
        this.type          = type;
        this.constrained   = constrained;
        this.required      = required;
        this.validValues   = validValues;
        this.invalidValues = invalidValues;
    }

    public String       name()          { return name;          }
    public ClassData    type()          { return type;          }
    public boolean      isConstrained() { return constrained;   }
    public boolean      isRequired()    { return required;      }
    public List<Object> validValues()   { return validValues;   }
    public List<Object> invalidValues() { return invalidValues; }
}
