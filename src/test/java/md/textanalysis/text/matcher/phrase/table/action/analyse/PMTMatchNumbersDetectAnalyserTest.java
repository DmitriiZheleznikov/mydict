package md.textanalysis.text.matcher.phrase.table.action.analyse;

import md.textanalysis.text.matcher.phrase.table.action.AbstractPMTActionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PMTMatchNumbersDetectAnalyserTest extends AbstractPMTActionTest {

    @BeforeEach
    protected void setUp() throws IOException, URISyntaxException {
        super.setUp();
        super.setUpSkips();
        super.setUpMatches();
    }

    @Test
    void findNumbersInLine2() {
        List<Integer> expected = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        List<Integer> actual = PMTMatchNumbersDetectAnalyser.findNumbersInLine2(matchingTable, 0);
        Collections.sort(actual);
        assertEquals(expected, actual);

        expected = Arrays.asList(3,4,5,6,7,8,9,10,11,12);
        actual = PMTMatchNumbersDetectAnalyser.findNumbersInLine2(matchingTable, 3);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }
}