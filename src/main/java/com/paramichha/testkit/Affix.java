package com.paramichha.testkit;

/**
 * A prefix and a suffix wrapped around a generated name: {@code prefix + base + suffix}.
 *
 * <p>Both parts are plain Java identifier text (letters, digits, {@code _} or {@code $}), so a
 * generated name is always a valid Java identifier. Either part may be empty.
 */
public record Affix(String prefix, String suffix) {

    public Affix {
        check("prefix", prefix, true);
        check("suffix", suffix, true);
    }

    /** {@code prefix + base + suffix} */
    public String apply(String base) {
        return prefix + base + suffix;
    }

    /** Validates one part; throws {@link IllegalArgumentException} with the reason. */
    static void check(String what, String text, boolean mayBeEmpty) {
        if (text == null) throw new IllegalArgumentException(what + " must not be null");
        if (text.isEmpty()) {
            if (mayBeEmpty) return;
            throw new IllegalArgumentException(what + " must not be empty");
        }
        if (!text.chars().allMatch(Character::isJavaIdentifierPart)) {
            throw new IllegalArgumentException(what + " must contain only identifier characters, got: " + text);
        }
    }
}
