package com.paramichha.testkit.generator;

final class DefaultReturnData implements ReturnData {

    private final ClassData type;
    private final boolean   voidReturn;
    private final boolean   constrained;
    private final boolean   needsWrapperRecord;
    private final String    assertionCode;
    private final String    wrapperRecord;

    DefaultReturnData(ClassData type, boolean voidReturn, boolean constrained,
                      boolean needsWrapperRecord, String assertionCode, String wrapperRecord) {
        this.type               = type;
        this.voidReturn         = voidReturn;
        this.constrained        = constrained;
        this.needsWrapperRecord = needsWrapperRecord;
        this.assertionCode      = assertionCode;
        this.wrapperRecord      = wrapperRecord;
    }

    public ClassData type()                { return type;               }
    public boolean   isVoid()              { return voidReturn;         }
    public boolean   isConstrained()       { return constrained;        }
    public boolean   needsWrapperRecord()  { return needsWrapperRecord; }
    public String    assertionCode()       { return assertionCode;      }
    public String    wrapperRecord()       { return wrapperRecord;      }
}
