package md.textanalysis.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextAnalyserHelperTest {
    @Test
    void convertToLettersOnly() {
        assertEquals("a   b cd' s ", TextAnalyserHelper.convertToLettersOnly("a111b&cd'\ts\n"));
    }

    @Test
    void convertToSingleSpaces() {
        assertEquals("a b cd' s ", TextAnalyserHelper.convertToSingleSpaces("a   b cd' s   "));
    }

    @Test
    void containsUpper() {
        assertEquals(true, TextAnalyserHelper.containsUpper("dfgfgT sdfsdf    df"));
        assertEquals(true, TextAnalyserHelper.containsUpper("Wdfgfg sdfsdf    df"));
        assertEquals(true, TextAnalyserHelper.containsUpper("dfgfg sdfsdf    dfS"));
        assertEquals(false, TextAnalyserHelper.containsUpper("dfgfg sdfsdf    df"));
    }

    @Test
    void containsExtraSpaces() {
        assertEquals(true, TextAnalyserHelper.containsExtraSpaces("dfgfgT sdfsdf    df"));
        assertEquals(true, TextAnalyserHelper.containsExtraSpaces("  dfgfg sdfsdf df "));
        assertEquals(true, TextAnalyserHelper.containsExtraSpaces("dfgfg sdfsdf df  "));
        assertEquals(false, TextAnalyserHelper.containsExtraSpaces("dfgfg sdfsdf df "));
    }

    @Test
    void trimIfNeeded() {
        String str = "ttt";
        assertEquals(str, TextAnalyserHelper.trimIfNeeded(str));
        assertEquals(str, TextAnalyserHelper.trimIfNeeded("  ttt"));
    }

    @Test
    void lowerCaseIfNeeded() {
        String str = "ttt";
        assertEquals(str, TextAnalyserHelper.lowerCaseIfNeeded(str));
        assertEquals(str, TextAnalyserHelper.lowerCaseIfNeeded("tTt"));
    }

    @Test
    void singleSpacesIfNeeded() {
        String str = "ttt ttt";
        assertEquals(str, TextAnalyserHelper.singleSpacesIfNeeded(str));
        assertEquals(str, TextAnalyserHelper.singleSpacesIfNeeded("ttt  ttt"));
    }
}