package md.textanalysis.text.analyser.word.impl;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialCasesAnalyserTest {
    @Test
    void get() throws IOException, URISyntaxException {
        SpecialCasesAnalyser analyser = new SpecialCasesAnalyser();
        analyser.init();

        assertEquals("do", analyser.get("doesn't"));
    }

}