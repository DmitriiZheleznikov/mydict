package md.textanalysis.text.matcher.phrase.table.action.analyse;

import md.textanalysis.text.matcher.phrase.table.action.AbstractPMTActionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class PMTGapsAnalyserTest extends AbstractPMTActionTest {

    @BeforeEach
    protected void setUp() throws IOException, URISyntaxException {
        super.setUp();
        super.setUpSkips();
        super.setUpMatches();
    }

    @Test
    void isThereGap() {
        assertTrue(PMTGapsAnalyser.isThereGap(matchingTable.getLine2(), 3, 8));
        assertTrue(PMTGapsAnalyser.isThereGap(matchingTable.getLine2(), 4, 8));
        assertTrue(PMTGapsAnalyser.isThereGap(matchingTable.getLine2(), 2, 8));
        assertTrue(PMTGapsAnalyser.isThereGap(matchingTable.getLine2(), 4, 9));

        assertFalse(PMTGapsAnalyser.isThereGap(matchingTable.getLine2(), 1, 1));
        assertFalse(PMTGapsAnalyser.isThereGap(matchingTable.getLine2(), 2, 1));
        assertFalse(PMTGapsAnalyser.isThereGap(matchingTable.getLine2(), 1, 3));
        assertFalse(PMTGapsAnalyser.isThereGap(matchingTable.getLine2(), 0, 4));
    }

    @Test
    void calcGapSize() {
        assertEquals(2, PMTGapsAnalyser.calcGapSize(matchingTable.getLine2(), 3, 8, 5));
        assertEquals(1, PMTGapsAnalyser.calcGapSize(matchingTable.getLine2(), 3, 8, 0));
    }

    @Test
    void calcMaxGapSize() {
        assertEquals(2, PMTGapsAnalyser.calcMaxGapSize(matchingTable, 5));
        assertEquals(1, PMTGapsAnalyser.calcMaxGapSize(matchingTable, 0));
    }

    @Test
    void countGapsInLine2ByLine1() {
        assertEquals(1, PMTGapsAnalyser.countGapsInLine2ByLine1(matchingTable));
    }
}