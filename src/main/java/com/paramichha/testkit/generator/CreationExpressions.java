package com.paramichha.testkit.generator;

import com.paramichha.utils.Texts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The one place that decides how a generated test creates an instance of the class under test.
 *
 * <p>Records and classes with an all-args constructor are created with {@code new X(...)}. A
 * JavaBeans class (no-args constructor and setters) has no such constructor, so the test class gets
 * a small factory method, {@code newX(...)}, that calls the setters, and every creation calls it.
 */
final class CreationExpressions {

    private static final String INDENT = "    ";

    private CreationExpressions() {
    }

    /** Creates the instance with every member at its valid baseline value. */
    static String baseline(ClassData data, ClassDataConfig config) {
        return call(data, config, null);
    }

    /** Creates the instance with {@code target} taken from the variable of the same name. */
    static String withVariable(ClassData data, ClassDataConfig config, MemberData target) {
        return call(data, config, target);
    }

    /** The {@code newX(...)} factory a JavaBeans class needs, or empty for any other class. */
    static Optional<MethodCoderInfo> factoryMethod(ClassData data, ClassDataConfig config) {
        if (!config.isSetterStyle() || data.members().isEmpty()) return Optional.empty();
        String type = config.sourceClassName();
        List<ParameterCoderInfo> params = new ArrayList<>();
        StringBuilder body = new StringBuilder(type).append(" obj = new ").append(type).append("();\n");
        for (MemberData m : data.members()) {
            params.add(ParameterCoderInfo.of(m.type().className(), m.name()));
            body.append("obj.set").append(Texts.capitalize(m.name())).append("(").append(m.name()).append(");\n");
        }
        body.append("return obj;");
        return Optional.of(new MethodCoderInfo(List.of(), List.of("private", "static"),
                type, factoryName(config), params, body.toString()));
    }

    private static String call(ClassData data, ClassDataConfig config, MemberData target) {
        List<Object> parts = data.validValues();
        List<MemberData> members = data.members();
        StringBuilder sb = new StringBuilder(config.isSetterStyle()
                ? factoryName(config) + "("
                : "new " + config.sourceClassName() + "(");
        sb.append("\n");
        for (int i = 0; i < members.size(); i++) {
            if (i > 0) sb.append(",\n");
            MemberData m = members.get(i);
            sb.append(INDENT).append(target != null && m.name().equals(target.name())
                    ? m.name()
                    : literal(i < parts.size() ? parts.get(i) : null, m));
        }
        return sb.append(")").toString();
    }

    private static String literal(Object value, MemberData member) {
        return value != null && LiteralConverter.canSerialize(value)
                ? LiteralConverter.toLiteral(value)
                : "(" + member.type().className() + ") null";
    }

    private static String factoryName(ClassDataConfig config) {
        return "new" + config.sourceClassName();
    }
}
