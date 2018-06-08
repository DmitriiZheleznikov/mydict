package md.textanalysis.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhrasesComparatorTest {
    @Test
    void compare() {
        PhrasesComparator comparator = new PhrasesComparator();

        assertEquals(1, comparator.compare("zzz", "aaa") > 0 ? 1 : 0);
        assertEquals(-1, comparator.compare("zzz", "aaa bbb"));
    }

    @Test
    void countWords() {
        PhrasesComparator comparator = new PhrasesComparator();

        assertEquals(1, comparator.countWords("  test "));
        assertEquals(2, comparator.countWords("  test  test "));
    }

}