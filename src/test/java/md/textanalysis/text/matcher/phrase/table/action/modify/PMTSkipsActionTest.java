package md.textanalysis.text.matcher.phrase.table.action.modify;

import md.textanalysis.text.element.phrase.Idiom;
import md.textanalysis.text.matcher.phrase.table.action.AbstractPMTActionTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PMTSkipsActionTest extends AbstractPMTActionTest {

    @Test
    void process() {
        matchingTable.modifyMarkSkips(Idiom.TO_SKIP);

        //This
        assertTrue(!matchingTable.getLine1().getElement(0).isSkipped());
        assertTrue(!matchingTable.getLine1().getElement(0).isMatched());
        assertTrue( matchingTable.getLine1().getElement(0).isNotMatched());

        //is
        assertTrue(!matchingTable.getLine1().getElement(1).isSkipped());
        assertTrue(!matchingTable.getLine1().getElement(1).isMatched());
        assertTrue( matchingTable.getLine1().getElement(1).isNotMatched());

        //the
        assertTrue( matchingTable.getLine1().getElement(2).isSkipped());
        assertTrue(!matchingTable.getLine1().getElement(2).isMatched());
        assertTrue(!matchingTable.getLine1().getElement(2).isNotMatched());

        //test
        assertTrue(!matchingTable.getLine1().getElement(3).isSkipped());
        assertTrue(!matchingTable.getLine1().getElement(3).isMatched());
        assertTrue( matchingTable.getLine1().getElement(3).isNotMatched());

        //,
        assertTrue( matchingTable.getLine1().getElement(4).isSkipped());
        assertTrue(!matchingTable.getLine1().getElement(4).isMatched());
        assertTrue(!matchingTable.getLine1().getElement(4).isNotMatched());

        //test
        assertTrue(!matchingTable.getLine1().getElement(5).isSkipped());
        assertTrue(!matchingTable.getLine1().getElement(5).isMatched());
        assertTrue( matchingTable.getLine1().getElement(5).isNotMatched());

        //1
        assertTrue( matchingTable.getLine1().getElement(6).isSkipped());
        assertTrue(!matchingTable.getLine1().getElement(6).isMatched());
        assertTrue(!matchingTable.getLine1().getElement(6).isNotMatched());


        //This
        assertTrue(!matchingTable.getLine2().getElement(0).isSkipped());
        assertTrue(!matchingTable.getLine2().getElement(0).isMatched());
        assertTrue( matchingTable.getLine2().getElement(0).isNotMatched());

        //.
        assertTrue( matchingTable.getLine2().getElement(9).isSkipped());
        assertTrue(!matchingTable.getLine2().getElement(9).isMatched());
        assertTrue(!matchingTable.getLine2().getElement(9).isNotMatched());
    }
}