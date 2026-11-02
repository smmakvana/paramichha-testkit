package com.paramichha.testkit.generator;

import com.paramichha.testkit.Affix;

import java.util.List;

/**
 * Template-resolved configuration passed to {@link ClassDataCoder}.
 * No DataFactory, no ClassInfo — pure config strings.
 */
final class ClassDataConfig {

    private final String       sourceClassName;   // e.g. "ObjectAnnotatedService"
    private final String       targetPackage;
    private final String       instanceName;      // e.g. "objectAnnotatedService"
    private final String       header;
    private final List<String> imports;
    private final boolean      hasJakarta;
    private final boolean      isUtilityClass;
    private final boolean      isRecord;
    private final String       validatorField;    // e.g. "validator"
    private final String       validatorSetup;    // e.g. "setUpValidator"
    private final Affix        classAffix;        // class names: prefix + name + suffix
    private final Affix        methodAffix;       // test method names: prefix + name + suffix

    ClassDataConfig(String sourceClassName, String targetPackage, String instanceName,
                    String header, List<String> imports,
                    boolean hasJakarta, boolean isUtilityClass, boolean isRecord,
                    String validatorField, String validatorSetup,
                    Affix classAffix, Affix methodAffix) {
        this.sourceClassName = sourceClassName;
        this.targetPackage   = targetPackage;
        this.instanceName    = instanceName;
        this.header          = header;
        this.imports         = imports;
        this.hasJakarta      = hasJakarta;
        this.isUtilityClass  = isUtilityClass;
        this.isRecord        = isRecord;
        this.validatorField  = validatorField;
        this.validatorSetup  = validatorSetup;
        this.classAffix      = classAffix;
        this.methodAffix     = methodAffix;
    }

    String       sourceClassName() { return sourceClassName; }
    String       targetPackage()   { return targetPackage;   }
    String       instanceName()    { return instanceName;    }
    String       header()          { return header;          }
    List<String> imports()         { return imports;         }
    boolean      hasJakarta()      { return hasJakarta;      }
    boolean      isUtilityClass()  { return isUtilityClass;  }
    boolean      isRecord()        { return isRecord;        }
    String       validatorField()  { return validatorField;  }
    String       validatorSetup()  { return validatorSetup;  }
    Affix        classAffix()      { return classAffix;      }
    Affix        methodAffix()     { return methodAffix;     }
}
