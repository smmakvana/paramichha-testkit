package com.paramichha.testkit.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RawFieldReadTest {

    @Test
    @DisplayName("a field is read through a record accessor, a getter or an is-getter")
    void accessorForms() {
        assertThat(SolidCodeGenerator.readsField("obj.items()", "items")).isTrue();
        assertThat(SolidCodeGenerator.readsField("obj.getItems()", "items")).isTrue();
        assertThat(SolidCodeGenerator.readsField("obj.isItems()", "items")).isTrue();
        assertThat(SolidCodeGenerator.readsField("obj.itemsCount()", "items")).isFalse();
    }
}
