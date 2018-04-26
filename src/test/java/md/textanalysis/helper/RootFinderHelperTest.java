package md.textanalysis.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RootFinderHelperTest {
    @Test
    void get() {
        assertEquals("do", TextAnalyserHelper.getRoot("didn't"));
        assertEquals("bind", TextAnalyserHelper.getRoot("bound"));
        assertEquals("riv", TextAnalyserHelper.getRoot("rivers"));
        assertEquals("happ", TextAnalyserHelper.getRoot("unhappiness"));
        assertEquals("gust", TextAnalyserHelper.getRoot("disgusting"));
        assertEquals("print", TextAnalyserHelper.getRoot("printer"));
        assertEquals("hamburg", TextAnalyserHelper.getRoot("hamburger's"));
        assertEquals("una", TextAnalyserHelper.getRoot("unaness"));
        assertEquals("aaa", TextAnalyserHelper.getRoot("unaaaness"));
        assertEquals("i", TextAnalyserHelper.getRoot("i've"));
        assertEquals("model", TextAnalyserHelper.getRoot("-model"));
        assertEquals("thomas", TextAnalyserHelper.getRoot("thomas'"));
    }

}