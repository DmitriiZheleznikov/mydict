package md.textanalysis.text.analyse.word.impl;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RootFinderAnalyserTest {
    @Test
    void get() throws IOException, URISyntaxException {
        RootFinderAnalyser analyser = new RootFinderAnalyser();
        analyser.init();

        assertNotEquals("do", analyser.get("didn't"));
        assertNotEquals("bind", analyser.get("bound"));
        assertEquals("riv", analyser.get("rivers"));
        assertEquals("happ", analyser.get("unhappiness"));
        assertEquals("gust", analyser.get("disgusting"));
        assertEquals("print", analyser.get("printer"));
        assertEquals("hamburger", analyser.get("hamburger's"));
        assertEquals("una", analyser.get("unaness"));
        assertEquals("aaa", analyser.get("unaaaness"));
        assertEquals("i", analyser.get("i've"));
        assertEquals("model", analyser.get("-model"));
        assertEquals("thomas", analyser.get("thomas'"));
        assertEquals("fals", analyser.get("false"));
        assertEquals("applicability", analyser.get("applicability"));
        assertEquals("applicability", analyser.get("'applicability'"));
    }

}