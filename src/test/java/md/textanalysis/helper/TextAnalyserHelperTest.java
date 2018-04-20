package md.textanalysis.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextAnalyserHelperTest {
    @Test
    void convertToLettersOnly() {
        assertEquals("a   b cd' s ", TextAnalyserHelper.convertToLettersOnly("a111b&cd'\ts\n"));
    }

    @Test
    void convertToSingleSpaces() {
        assertEquals("a b cd' s ", TextAnalyserHelper.convertToSingleSpaces("a   b cd' s   "));
    }

}