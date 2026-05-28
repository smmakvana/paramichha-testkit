package com.paramichha.testkit.generator;

/**
 * Single source of truth for all Java source templates.
 *
 * Four templates — one per Java construct.
 * Placeholders use {name} syntax.
 * Coders replace placeholders and return the result.
 * No logic here — just constants.
 */
final class CoderTemplates {

    private CoderTemplates() {}

    // ── Parameter ─────────────────────────────────────────────────────────

    static final String PARAMETER = "{annotations}{type} {name}";

    // ── Field ─────────────────────────────────────────────────────────────

    static final String FIELD = "{annotations}{modifiers}{type} {name}{initializer};\n";

    // ── Method ────────────────────────────────────────────────────────────

    static final String METHOD =
            "{annotations}{modifiers}{returnType} {name}({params}) {\n{body}\n}\n";

    // ── Class ─────────────────────────────────────────────────────────────

    static final String CLASS =
            "{package}{imports}{annotations}{modifiers}class {name} {\n\n{fields}{methods}{nestedClasses}}\n";
}
