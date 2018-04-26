package md.textanalysis.helper;

import md.textanalysis.helper.root.RootFinderHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RootFinderHelperTest {
    @Test
    void get() {
        assertNotEquals("do", RootFinderHelper.get("didn't"));
        assertNotEquals("bind", RootFinderHelper.get("bound"));
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