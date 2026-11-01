package com.paramichha.testkit.generator;

import java.util.List;

final class DefaultMemberData implements MemberData {

    private final String       name;
    private final ClassData    type;
    private final boolean      constrained;
    private final boolean      required;
    private final List<Object> validValues;
    private final List<Object> invalidValues;
    private final boolean      propertyConstrained;
    private final boolean      constructorConstrained;
    private final int          constructorIndex;
    private final List<String> conflicts;

    DefaultMemberData(String name, ClassData type, boolean constrained, boolean required,
                      List<Object> validValues, List<Object> invalidValues) {
        this.name          = name;
        this.type          = type;
        this.constrained   = constrained;
        this.required      = required;
        this.validValues   = validValues;
        this.invalidValues = invalidValues;
        this.propertyConstrained    = constrained;
        this.constructorConstrained = false;
        this.constructorIndex       = -1;
        this.conflicts              = List.of();
    }

    DefaultMemberData(String name, ClassData type, boolean required,
                      List<Object> validValues, List<Object> invalidValues,
                      boolean propertyConstrained, boolean constructorConstrained,
                      int constructorIndex, List<String> conflicts) {
        this.name          = name;
        this.type          = type;
        this.constrained   = propertyConstrained || constructorConstrained;
        this.required      = required;
        this.validValues   = validValues;
        this.invalidValues = invalidValues;
        this.propertyConstrained    = propertyConstrained;
        this.constructorConstrained = constructorConstrained;
        this.constructorIndex       = constructorIndex;
        this.conflicts              = List.copyOf(conflicts);
    }

    public boolean      isPropertyConstrained()    { return propertyConstrained;    }
    public boolean      isConstructorConstrained() { return constructorConstrained; }
    public int          constructorIndex()         { return constructorIndex;       }
    public List<String> conflicts()                { return conflicts;              }

    public String       name()          { return name;          }
    public ClassData    type()          { return type;          }
    public boolean      isConstrained() { return constrained;   }
    public boolean      isRequired()    { return required;      }
    public List<Object> validValues()   { return validValues;   }
    public List<Object> invalidValues() { return invalidValues; }
}
