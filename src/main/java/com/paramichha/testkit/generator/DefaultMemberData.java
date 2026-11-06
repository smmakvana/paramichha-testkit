package com.paramichha.testkit.generator;

import java.util.List;

final class DefaultMemberData implements MemberData {

    private final String       name;
    private final ClassData    type;
    private final List<Object> validValues;
    private final List<Object> invalidValues;
    private final boolean      propertyConstrained;
    private final boolean      constructorConstrained;
    private final int          constructorIndex;
    private final List<String> conflicts;

    DefaultMemberData(String name, ClassData type,
                      List<Object> validValues, List<Object> invalidValues,
                      boolean propertyConstrained, boolean constructorConstrained,
                      int constructorIndex, List<String> conflicts) {
        this.name                   = name;
        this.type                   = type;
        this.validValues            = validValues;
        this.invalidValues          = invalidValues;
        this.propertyConstrained    = propertyConstrained;
        this.constructorConstrained = constructorConstrained;
        this.constructorIndex       = constructorIndex;
        this.conflicts              = List.copyOf(conflicts);
    }

    /** A field whose values could not be worked out: no values, no constraints. */
    static DefaultMemberData unresolved(String name, ClassData type) {
        return new DefaultMemberData(name, type, List.of(), List.of(), false, false, -1, List.of());
    }

    public boolean      isPropertyConstrained()    { return propertyConstrained;    }
    public boolean      isConstructorConstrained() { return constructorConstrained; }
    public int          constructorIndex()         { return constructorIndex;       }
    public List<String> conflicts()                { return conflicts;              }

    public String       name()          { return name;          }
    public ClassData    type()          { return type;          }
    public List<Object> validValues()   { return validValues;   }
    public List<Object> invalidValues() { return invalidValues; }
}
