package com.paramichha.testkit.generator;

import java.util.List;

final class DefaultClassData implements ClassData {

    private final String           className;
    private final String           packageName;
    private final boolean          primitive;
    private final boolean          voidType;
    private final boolean          enumType;
    private final boolean          constrained;
    private final List<MemberData> members;
    private final List<MethodData> methods;
    private final List<Object>     validValues;
    private final List<Object>     invalidValues;

    DefaultClassData(String className, String packageName,
                     boolean primitive, boolean voidType, boolean enumType, boolean constrained,
                     List<MemberData> members, List<MethodData> methods,
                     List<Object> validValues, List<Object> invalidValues) {
        this.className    = className;
        this.packageName  = packageName;
        this.primitive    = primitive;
        this.voidType     = voidType;
        this.enumType     = enumType;
        this.constrained  = constrained;
        this.members      = members;
        this.methods      = methods;
        this.validValues  = validValues;
        this.invalidValues= invalidValues;
    }

    public String           className()    { return className;    }
    public String           packageName()  { return packageName;  }
    public boolean          isPrimitive()  { return primitive;    }
    public boolean          isVoid()       { return voidType;     }
    public boolean          isEnum()       { return enumType;     }
    public boolean          isConstrained(){ return constrained;  }
    public List<MemberData> members()      { return members;      }
    public List<MethodData> methods()      { return methods;      }
    public List<Object>     validValues()  { return validValues;  }
    public List<Object>     invalidValues(){ return invalidValues; }
}
