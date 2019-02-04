package md.textanalysis.text.analyser.word;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordAnalyserTest {
    @Test
    void getRoot() throws IOException, URISyntaxException {
        WordAnalyser analyser = new WordAnalyser();
        analyser.init();

        assertEquals("do", analyser.getRoot("doesn't"));
        assertEquals("be", analyser.getRoot("is"));
        assertEquals("arise", analyser.getRoot("arise"));
        assertEquals("arise", analyser.getRoot("arose"));
        assertEquals("arise", analyser.getRoot("arisen"));
        assertEquals("riv", analyser.getRoot("rivers"));
        assertEquals("happ", analyser.getRoot("\"unhappiness"));
        assertEquals("gust", analyser.getRoot("disgusting"));
        assertEquals("print", analyser.getRoot("printer"));
        assertEquals("hamburger", analyser.getRoot("hamburger's"));
        assertEquals("una", analyser.getRoot("unaness"));
        assertEquals("aaa", analyser.getRoot("unaaaness"));
        assertEquals("i", analyser.getRoot("i've"));
        assertEquals("model", analyser.getRoot("-model"));
        assertEquals("thomas", analyser.getRoot("\"thomas'"));
    }

}