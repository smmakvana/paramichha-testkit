package com.paramichha.testkit.generator;

final class DefaultReturnData implements ReturnData {

    private final ClassData type;
    private final boolean   voidReturn;
    private final boolean   constrained;
    private final String    assertionCode;

    DefaultReturnData(ClassData type, boolean voidReturn, boolean constrained, String assertionCode) {
        this.type          = type;
        this.voidReturn    = voidReturn;
        this.constrained   = constrained;
        this.assertionCode = assertionCode;
    }

    public ClassData type()          { return type;          }
    public boolean   isVoid()        { return voidReturn;    }
    public boolean   isConstrained() { return constrained;   }
    public String    assertionCode() { return assertionCode; }
}
