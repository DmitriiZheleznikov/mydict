package md.textanalysis.text.element.phrase;

import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.element.word.Separator;
import md.textanalysis.text.element.word.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdiomTest {
    Phrase phraseBig;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        TextAnalyserHelper.init();
        phraseBig = new Phrase();
        phraseBig.addEntity(new Word("Test"));
        phraseBig.addEntity(new Word("aNts"));
        phraseBig.addEntity(new Word("in"));
        phraseBig.addEntity(new Word("her"));
        phraseBig.addEntity(new Word("pants"));
        phraseBig.addEntity(new Word("Test"));
        phraseBig.init();
    }

    @Test
    void contains() {
        Idiom idiom = new Idiom();
        idiom.addEntity(new Word("ants"));
        idiom.addEntity(new Word("in"));
        idiom.addEntity(new Word("my"));
        idiom.addEntity(new Word("pants"));
        idiom.init();

        assertEquals(Arrays.asList(1, 2, 3, 4), phraseBig.contains(idiom, 1));
        assertEquals(Phrase.EMPTY_LIST_INT, phraseBig.contains(idiom, 2));
    }

    @Test
    void contains_() {
        Idiom idiom = new Idiom();
        idiom.addEntity(new Word("ants"));
        idiom.addEntity(new Word("in"));
        idiom.addEntity(new Separator("_"));
        idiom.addEntity(new Word("pants"));
        idiom.init();

        assertEquals(Arrays.asList(1, 2, 3, 4), phraseBig.contains(idiom, 1));
        assertEquals(Phrase.EMPTY_LIST_INT, phraseBig.contains(idiom, 2));

        phraseBig = new Phrase();
        phraseBig.addEntity(new Word("Test"));
        phraseBig.addEntity(new Word("as"));
        phraseBig.addEntity(new Word("slippery"));
        phraseBig.addEntity(new Word("as"));
        phraseBig.addEntity(new Word("frog's"));
        phraseBig.addEntity(new Word("hair"));
        phraseBig.init();

        idiom = new Idiom();
        idiom.addEntity(new Word("as"));
        idiom.addEntity(new Separator("_"));
        idiom.addEntity(new Word("as"));
        idiom.addEntity(new Word("frog's"));
        idiom.addEntity(new Word("hair"));
        idiom.init();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), phraseBig.contains(idiom, 1));
    }

    @Test
    void containsSpecialCases() {
        Idiom idiom = new Idiom();
        idiom.addEntity(new Word("ants"));
        idiom.addEntity(new Word("at"));
        idiom.addEntity(new Separator("my"));
        idiom.addEntity(new Word("pants"));
        idiom.init();

        assertEquals(Arrays.asList(1, 2, 3, 4), phraseBig.contains(idiom, 1));
        assertEquals(Phrase.EMPTY_LIST_INT, phraseBig.contains(idiom, 2));
    }

    @Test
    void containsSkipArticles() {
        phraseBig = new Phrase();
        phraseBig.addEntity(new Word("Test"));
        phraseBig.addEntity(new Word("Finding"));
        phraseBig.addEntity(new Word("the"));
        phraseBig.addEntity(new Word("needle"));
        phraseBig.addEntity(new Word("in"));
        //phraseBig.addEntity(new Word("a"));
        phraseBig.addEntity(new Word("Haystack"));
        phraseBig.addEntity(new Word("Test"));
        phraseBig.init();

        Idiom idiom = new Idiom();
        idiom.addEntity(new Word("Finding"));
        //idiom.addEntity(new Word("a"));
        idiom.addEntity(new Word("needle"));
        idiom.addEntity(new Word("in"));
        idiom.addEntity(new Word("a"));
        idiom.addEntity(new Word("Haystack"));
        idiom.init();

        assertEquals(Arrays.asList(1, 2, 3, 4, 5), phraseBig.contains(idiom, 1));
        assertEquals(Phrase.EMPTY_LIST_INT, phraseBig.contains(idiom, 2));
    }
}