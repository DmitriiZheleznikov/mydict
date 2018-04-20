package md.textanalysis.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RootFinderHelperTest {
    @Test
    void get() {
        assertEquals("do", RootFinderHelper.get("didn't"));
        assertEquals("bind", RootFinderHelper.get("bound"));
        assertEquals("riv", RootFinderHelper.get("rivers"));
        assertEquals("happ", RootFinderHelper.get("unhappiness"));
        assertEquals("gust", RootFinderHelper.get("disgusting"));
        assertEquals("print", RootFinderHelper.get("printer"));
        assertEquals("hamburg", RootFinderHelper.get("hamburger's"));
        assertEquals("una", RootFinderHelper.get("unaness"));
        assertEquals("aaa", RootFinderHelper.get("unaaaness"));
        assertEquals("i", RootFinderHelper.get("i've"));
        assertEquals("model", RootFinderHelper.get("-model"));
        assertEquals("thomas", RootFinderHelper.get("thomas'"));
    }

}