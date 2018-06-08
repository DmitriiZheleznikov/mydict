package md.textanalysis.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhrasesTreeSetTest {
    @Test
    void toArray() {
        PhrasesTreeSet set = new PhrasesTreeSet();
        set.add("zzz");
        set.add("bbb aaa");
        set.add("aaa");
        set.add("bbb");
        set.add("ddd fff bbb");

        String[] expected = new String[5];
        expected[0] = "aaa";
        expected[1] = "bbb";
        expected[2] = "zzz";
        expected[3] = "bbb aaa";
        expected[4] = "ddd fff bbb";

        int i = 0;
        for (String line : set) {
            assertEquals(expected[i++], line);
        }
    }

}