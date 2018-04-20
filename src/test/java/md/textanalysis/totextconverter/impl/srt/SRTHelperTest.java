package md.textanalysis.totextconverter.impl.srt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SRTHelperTest {
    @Test
    void isLineTime() {
        assertNotEquals(true, SRTHelper.isLineTime("1"));
        assertEquals(true, SRTHelper.isLineTime("00:00:23,565 --> 00:00:25,233"));
        assertNotEquals(true, SRTHelper.isLineTime("(CLOCKS TICKING)"));
        assertNotEquals(true, SRTHelper.isLineTime("  "));
        assertNotEquals(true, SRTHelper.isLineTime(""));
        assertNotEquals(true, SRTHelper.isLineTime(null));
    }

    @Test
    void isLineValid() {
        assertNotEquals(true, SRTHelper.isLineValid("1"));
        assertEquals(true, SRTHelper.isLineValid("00:00:23,565 --> 00:00:25,233"));
        assertEquals(true, SRTHelper.isLineValid("(CLOCKS TICKING)"));
        assertEquals(true, SRTHelper.isLineValid("  "));
        assertNotEquals(true, SRTHelper.isLineValid(""));
        assertNotEquals(true, SRTHelper.isLineValid(null));
    }

}