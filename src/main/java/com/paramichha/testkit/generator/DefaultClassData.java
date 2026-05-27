package com.paramichha.testkit.generator;

import java.util.List;

final class DefaultClassData implements ClassData {

    private final String           className;
    private final String           packageName;
    private final boolean          primitive;
    private final boolean          voidType;
    private final boolean          enumType;
    private final boolean          record;
    private final boolean          array;
    private final boolean          collection;
    private final boolean          map;
    private final boolean          constrained;
    private final ClassData        componentType;
    private final List<MemberData> members;
    private final List<MethodData> methods;
    private final List<Object>     validValues;
    private final List<Object>     invalidValues;

    DefaultClassData(String className, String packageName,
                     boolean primitive, boolean voidType, boolean enumType,
                     boolean record, boolean array, boolean collection, boolean map,
                     boolean constrained,
                     ClassData componentType,
                     List<MemberData> members, List<MethodData> methods,
                     List<Object> validValues, List<Object> invalidValues) {
        this.className     = className;
        this.packageName   = packageName;
        this.primitive     = primitive;
        this.voidType      = voidType;
        this.enumType      = enumType;
        this.record        = record;
        this.array         = array;
        this.collection    = collection;
        this.map           = map;
        this.constrained   = constrained;
        this.componentType = componentType;
        this.members       = members;
        this.methods       = methods;
        this.validValues   = validValues;
        this.invalidValues = invalidValues;
    }

    /** Convenience constructor for simple/primitive types with no component, no members. */
    DefaultClassData(String className, String packageName,
                     boolean primitive, boolean voidType, boolean enumType, boolean constrained,
                     List<MemberData> members, List<MethodData> methods,
                     List<Object> validValues, List<Object> invalidValues) {
        this(className, packageName, primitive, voidType, enumType,
                false, false, false, false, constrained,
                null, members, methods, validValues, invalidValues);
    }

    public String           className()     { return className;     }
    public String           packageName()   { return packageName;   }
    public boolean          isPrimitive()   { return primitive;     }
    public boolean          isVoid()        { return voidType;      }
    public boolean          isEnum()        { return enumType;      }
    public boolean          isRecord()      { return record;        }
    public boolean          isArray()       { return array;         }
    public boolean          isCollection()  { return collection;    }
    public boolean          isMap()         { return map;           }
    public boolean          isConstrained() { return constrained;   }
    public ClassData        componentType() { return componentType; }
    public List<MemberData> members()       { return members;       }
    public List<MethodData> methods()       { return methods;       }
    public List<Object>     validValues()   { return validValues;   }
    public List<Object>     invalidValues() { return invalidValues; }
}