package com.paramichha.testkit.regression;

import com.paramichha.datafactory.catalog.collection.map.MapRecord;
import com.paramichha.datafactory.catalog.collection.queue.QueueRecord;
import com.paramichha.datafactory.catalog.collection.set.SetRecord;
import com.paramichha.datafactory.catalog.text.TextRecord;
import com.paramichha.testkit.TestKit;
import com.paramichha.testkit.utils.TestNames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Bugs the catalog sweep found in generated tests (CatalogSweepTest compiles and runs them):
 * a field named "description", containers without typed values, a baseline wiped by one field
 * without a value, and Queue equality.
 */
class ContainersAndNamesRegressionTest {

    private static String generated(Class<?> type) {
        // whitespace-insensitive: the output may or may not have been through the formatter
        return TestKit.of(type).generateTest().asString().replaceAll("\\s+", " ").replace("( ", "(");
    }

    /** The baseline instance line: "MapRecord mapRecord = new MapRecord(...);". */
    private static String baseline(Class<?> type) {
        String src = generated(type);
        String start = type.getSimpleName() + " " + Character.toLowerCase(type.getSimpleName().charAt(0))
                + type.getSimpleName().substring(1) + " = ";
        int i = src.indexOf(start);
        assertThat(i).as("baseline field in generated test").isNotNegative();
        return src.substring(i, src.indexOf(';', i) + 1);
    }

    @Test
    @DisplayName("a field named 'description' does not clash with the case-label parameter")
    void labelParameterAvoidsFieldNames() {
        assertThat(generated(TextRecord.class))
                .contains("void testAcceptsDescription(String caseDescription, String description)")
                .doesNotContain("(String description, String description)");
        assertThat(TestNames.labelParameter(List.of("name"))).isEqualTo("description");
        assertThat(TestNames.labelParameter(List.of("description"))).isEqualTo("caseDescription");
        assertThat(TestNames.labelParameter(List.of("description", "caseDescription"))).isEqualTo("caseDescription2");
    }

    @Test
    @DisplayName("maps get typed entries, and one field without a value does not wipe the others")
    void mapBaselineIsTypedAndKept() {
        // MapRecord(Map<String,String> headers, Map<String,Integer> scores, Map<String,Object> metadata)
        assertThat(baseline(MapRecord.class))
                .containsPattern("new MapRecord\\(Map\\.ofEntries\\(Map\\.entry\\(\"[^\"]+\", \"[^\"]+\"\\)\\), "
                        + "Map\\.ofEntries\\(Map\\.entry\\(\"[^\"]+\", -?\\d+\\)\\), "
                        + "Map\\.ofEntries\\(Map\\.entry\\(\"[^\"]+\", \"[^\"]+\"\\)\\)\\)")
                .doesNotContain("(Map) null");
    }

    @Test
    @DisplayName("sets and queues get one element of their declared type")
    void setAndQueueBaselines() {
        assertThat(baseline(SetRecord.class)).contains("new SetRecord(Set.of(").doesNotContain("null");
        assertThat(baseline(QueueRecord.class)).contains("new QueueRecord(new java.util.ArrayDeque<>(List.of(")
                .doesNotContain("null");
    }

    @Test
    @DisplayName("a Queue is compared by its elements: ArrayDeque has no equals")
    void queueHoldsCheckComparesElements() {
        assertThat(generated(QueueRecord.class))
                .containsPattern("\\) ?\\.containsExactlyElementsOf\\(new java\\.util\\.ArrayDeque<>\\(List\\.of\\(");
    }
}
