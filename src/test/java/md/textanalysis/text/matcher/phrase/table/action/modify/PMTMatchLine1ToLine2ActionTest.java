package md.textanalysis.text.matcher.phrase.table.action.modify;

import md.textanalysis.text.element.phrase.Idiom;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.Word;
import md.textanalysis.text.matcher.phrase.table.action.AbstractPMTActionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class PMTMatchLine1ToLine2ActionTest extends AbstractPMTActionTest {
    @BeforeEach
    protected void setUp() throws IOException, URISyntaxException {
        super.setUp();
        super.setUpSkips();
    }

    @Test
    void findMatchIn() {
        //findMatchIn(PMTLine lineToSearchIn, PMTElement whatLookFor, int startFrom)
        assertEquals(8,
                PMTMatchLine1ToLine2Action.findMatchIn(
                        matchingTable.getLine2(), matchingTable.getLine1().getElement(5), 2));

        assertEquals(-1,
                PMTMatchLine1ToLine2Action.findMatchIn(
                        matchingTable.getLine2(), matchingTable.getLine1().getElement(4), 1));
    }

    @Test
    void process() {
        PMTMatchLine1ToLine2Action.process(matchingTable);

        assertEquals(0,  matchingTable.getLine1().getElement(0).getNumMatchingElement());
        assertEquals(1,  matchingTable.getLine1().getElement(1).getNumMatchingElement());
        assertEquals(3,  matchingTable.getLine1().getElement(3).getNumMatchingElement());
        assertEquals(-1, matchingTable.getLine1().getElement(4).getNumMatchingElement());
        assertEquals(8,  matchingTable.getLine1().getElement(5).getNumMatchingElement());

        assertTrue(matchingTable.getLine2().getElement(1).isMatched());
    }

    @Test
    void process2() {
        Idiom idiom5 = new Idiom();
        idiom5.addEntity(new Word("ants"));
        idiom5.addEntity(new Word("in"));
        idiom5.addEntity(new Word("my"));
        idiom5.addEntity(new Word("pants"));
        idiom5.init();

        Phrase phrase5 = new Phrase();
        phrase5.addEntity(new Word("Test"));
        phrase5.addEntity(new Word("aNts"));
        phrase5.addEntity(new Word("in"));
        phrase5.addEntity(new Word("her"));
        phrase5.addEntity(new Word("pants"));
        phrase5.addEntity(new Word("Test"));
        phrase5.init();

        matchingTable.init(idiom5, phrase5, 0, 1);

        PMTMatchLine1ToLine2Action.process(matchingTable);

        assertEquals(0,  matchingTable.getLine1().getElement(0).getNumMatchingElement());
        assertEquals(1,  matchingTable.getLine1().getElement(1).getNumMatchingElement());
        assertEquals(3, matchingTable.getLine1().getElement(3).getNumMatchingElement());

        assertTrue(matchingTable.getLine2().getElement(1).isMatched());
    }
}