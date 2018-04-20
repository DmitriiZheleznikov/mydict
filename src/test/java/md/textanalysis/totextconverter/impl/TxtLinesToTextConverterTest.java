package md.textanalysis.totextconverter.impl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TxtLinesToTextConverterTest {
    private static final List<String> INPUT_LIST = new ArrayList<>();
    private static final String EXPECTED =
            " A test text! It'll help us to    \"test\" the" +
            " functionality. We will use walkie-" +
            "talkei and line bre" +
            "aks to test how it's    preformed. " +
            "How the line with some mess...23wfw w.w32wed32r<test></test>";

    static {
        INPUT_LIST.add("A test text! It'll help us to    \"test\" the");
        INPUT_LIST.add("functionality. We will use walkie-");
        INPUT_LIST.add("-talkei and line bre-");
        INPUT_LIST.add("aks to test how it's    preformed.");
        INPUT_LIST.add("How the line with some mess...23wfw w.w32wed32r<test></test>");
    }

    @Test
    void perform() {
        TxtLinesToTextConverter converter = new TxtLinesToTextConverter(INPUT_LIST);
        assertEquals(EXPECTED, converter.perform());
    }

}