package md.textanalysis.totextconverter.impl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyDictLinesToTextConverterTest {
    private static final List<String> INPUT_LIST = new ArrayList<>(3);
    private static final String EXPECTED = "test ; ssf ;sfsdfsd;snsf'kk sflkjsf;;;;;;;;;;";

    static {
        INPUT_LIST.add(" test , ssf ;sfsdfsd.");
        INPUT_LIST.add("   snsf'kk sflkjsf");
        INPUT_LIST.add("0923092349");
    }

    @Test
    void perform() {
        MyDictLinesToTextConverter converter = new MyDictLinesToTextConverter(INPUT_LIST);
        assertEquals(EXPECTED, converter.perform());
    }

}