package md.textanalysis.text.element.phrase;

import heli.component.shape.text.htext.HString;
import heli.component.shape.text.htext.HStringFlow;
import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.element.word.Number;
import md.textanalysis.text.element.word.Separator;
import md.textanalysis.text.element.word.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhraseTest {
    Phrase phraseBig;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        TextAnalyserHelper.init();
        phraseBig = new Phrase(0);
        phraseBig.addEntity(new Word("Test"));
        phraseBig.addEntity(new Word("Phrase"));
        phraseBig.addEntity(new Word("where"));
        phraseBig.addEntity(new Word("search"));
        phraseBig.addEntity(new Word("will"));
        phraseBig.addEntity(new Word("be"));
        phraseBig.addEntity(new Separator(","));
        phraseBig.addEntity(new Word("also"));
        phraseBig.addEntity(new Number("123"));
        phraseBig.addEntity(new Separator("&"));
        phraseBig.addEntity(new Number("666"));
        phraseBig.addEntity(new Separator("."));
        phraseBig.init();
    }

    @Test
    void contains() {
        Phrase phrase = new Phrase(0);
        phrase.addEntity(new Word("be"));
        phrase.addEntity(new Separator(","));
        phrase.addEntity(new Word("also"));
        phrase.addEntity(new Number("123"));
        phrase.init();

        assertEquals(Arrays.asList(5, 6, 7, 8), phraseBig.contains(phrase, 5));
        assertEquals(Phrase.EMPTY_LIST_INT, phraseBig.contains(phrase, 6));

        phrase = new Phrase(0);
        phrase.addEntity(new Word("be"));
        phrase.addEntity(new Separator(","));
        phrase.addEntity(new Word("also"));
        phrase.addEntity(new Number("777"));
        phrase.init();
        assertEquals(Phrase.EMPTY_LIST_INT, phraseBig.contains(phrase, 0));
    }

    @Test
    void tString() {
        assertEquals("Test Phrase where search will be, also 123 & 666.", phraseBig.toString());
    }

    @Test
    void toStringFlow() {
        Set<Integer> boldNums = new HashSet<>();
        boldNums.add(2);
        boldNums.add(4);
        boldNums.add(5);
        HStringFlow flow = phraseBig.toStringFlow(boldNums);

        assertEquals(5, flow.getData().size());
        assertEquals("Test Phrase ", flow.getData().get(0).toString());
        assertEquals("where ", flow.getData().get(1).toString());
        assertEquals("search ", flow.getData().get(2).toString());
        assertEquals("will be", flow.getData().get(3).toString());
        assertEquals(", also 123 & 666.", flow.getData().get(4).toString());

        assertEquals(true, flow.getData().get(0).getFontWeight() == HString.FontWeight.REGULAR);
        assertEquals(true, flow.getData().get(1).getFontWeight() == HString.FontWeight.BOLD);
        assertEquals(true, flow.getData().get(2).getFontWeight() == HString.FontWeight.REGULAR);
        assertEquals(true, flow.getData().get(3).getFontWeight() == HString.FontWeight.BOLD);
        assertEquals(true, flow.getData().get(4).getFontWeight() == HString.FontWeight.REGULAR);
    }

    @Test
    void countLenLeftOf() {
        assertEquals(0, phraseBig.countLenLeftOf(0));
        assertEquals(12, phraseBig.countLenLeftOf(2));
    }

    @Test
    void countLenRightOf() {
        assertEquals(0, phraseBig.countLenRightOf(11));
        assertEquals(7, phraseBig.countLenRightOf(8));
    }
}