package md.textanalysis.text.analyser.word.impl;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IrregularVerbsAnalyserTest {
    @Test
    void get() throws IOException, URISyntaxException {
        IrregularVerbsAnalyser analyser = new IrregularVerbsAnalyser();
        analyser.init();

        assertEquals("arise", analyser.get("arise"));
        assertEquals("arise", analyser.get("arose"));
        assertEquals("arise", analyser.get("arisen"));
    }

}