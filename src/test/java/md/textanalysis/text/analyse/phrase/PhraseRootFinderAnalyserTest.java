package md.textanalysis.text.analyse.phrase;

import md.textanalysis.text.analyse.AnalyserFacade;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhraseRootFinderAnalyserTest {
    @Test
    void getRoot() throws IOException, URISyntaxException {
        AnalyserFacade.init();

        String expected = "hide break a poll test";
        PhraseRootFinderAnalyser analyser = new PhraseRootFinderAnalyser();

        assertEquals(expected, analyser.getRoot("hidden broken a 9 poll, tested"));
    }

}