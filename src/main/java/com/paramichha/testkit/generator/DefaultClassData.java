package com.paramichha.testkit.generator;

import java.util.List;

final class DefaultClassData implements ClassData {

    private final String           className;
    private final String           packageName;
    private final boolean          primitive;
    private final boolean          voidType;
    private final boolean          enumType;
    private final boolean          array;
    private final boolean          collection;
    private final boolean          map;
    private final List<MemberData> members;
    private final List<MethodData> methods;
    private final List<Object>     validValues;

    DefaultClassData(String className, String packageName,
                     boolean primitive, boolean voidType, boolean enumType,
                     boolean array, boolean collection, boolean map,
                     List<MemberData> members, List<MethodData> methods,
                     List<Object> validValues) {
        this.className   = className;
        this.packageName = packageName;
        this.primitive   = primitive;
        this.voidType    = voidType;
        this.enumType    = enumType;
        this.array       = array;
        this.collection  = collection;
        this.map         = map;
        this.members     = members;
        this.methods     = methods;
        this.validValues = validValues;
    }

    public String           className()    { return className;   }
    public String           packageName()  { return packageName; }
    public boolean          isPrimitive()  { return primitive;   }
    public boolean          isVoid()       { return voidType;    }
    public boolean          isEnum()       { return enumType;    }
    public boolean          isArray()      { return array;       }
    public boolean          isCollection() { return collection;  }
    public boolean          isMap()        { return map;         }
    public List<MemberData> members()      { return members;     }
    public List<MethodData> methods()      { return methods;     }
    public List<Object>     validValues()  { return validValues; }
}
