package md.textanalysis.text.matcher.phrase;

import md.textanalysis.text.analyser.AnalyserFacade;
import md.textanalysis.text.element.phrase.Idiom;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IdiomToPhraseMatcherTest {
    IdiomToPhraseMatcher matcher;

    Idiom idiom1;
    Idiom idiom2;
    Idiom idiom3;
    Idiom idiom4;
    Idiom idiom5;
    Phrase phrase1;
    Phrase phrase2;
    Phrase phrase21;
    Phrase phrase3;
    Phrase phrase4;
    Phrase phrase5;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        AnalyserFacade.init();
        matcher = AnalyserFacade.getIdiomToPhraseMatcher();

        idiom1 = new Idiom();
        idiom1.addEntity(new Word("throw"));
        idiom1.addEntity(new Word("dust"));
        idiom1.addEntity(new Word("in"));
        idiom1.addEntity(new Word("eyes"));
        idiom1.init();

        phrase1 = new Phrase();
        phrase1.addEntity(new Word("test"));
        phrase1.addEntity(new Word("test"));
        phrase1.addEntity(new Word("threw"));
        phrase1.addEntity(new Word("dust"));
        phrase1.addEntity(new Word("in"));
        phrase1.addEntity(new Word("the"));
        phrase1.addEntity(new Word("old"));
        phrase1.addEntity(new Word("lady's"));
        phrase1.addEntity(new Word("eyes"));
        phrase1.init();

        idiom2 = new Idiom();
        idiom2.addEntity(new Word("take"));
        idiom2.addEntity(new Word("a"));
        idiom2.addEntity(new Word("load"));
        idiom2.addEntity(new Word("off"));
        idiom2.addEntity(new Word("one's"));
        idiom2.addEntity(new Word("mind"));
        idiom2.init();

        phrase2 = new Phrase();
        phrase2.addEntity(new Word("took"));
        phrase2.addEntity(new Word("a"));
        phrase2.addEntity(new Word("load"));
        phrase2.addEntity(new Word("off"));
        phrase2.addEntity(new Word("Tom's"));
        phrase2.addEntity(new Word("mind"));
        phrase2.init();

        phrase21 = new Phrase();
        phrase21.addEntity(new Word("took"));
        phrase21.addEntity(new Word("a"));
        phrase21.addEntity(new Word("load"));
        phrase21.addEntity(new Word("off"));
        phrase21.addEntity(new Word("your"));
        phrase21.addEntity(new Word("mind"));
        phrase21.init();

        idiom3 = new Idiom();
        idiom3.addEntity(new Word("pull"));
        idiom3.addEntity(new Word("strings"));
        idiom3.init();

        phrase3 = new Phrase();
        phrase3.addEntity(new Word("pull"));
        phrase3.addEntity(new Word("a"));
        phrase3.addEntity(new Word("few"));
        phrase3.addEntity(new Word("strings"));
        phrase3.init();

        idiom4 = new Idiom();
        idiom4.addEntity(new Word("lick"));
        idiom4.addEntity(new Word("_"));
        idiom4.addEntity(new Word("boots"));
        idiom4.init();

        phrase4 = new Phrase();
        phrase4.addEntity(new Word("lick"));
        phrase4.addEntity(new Word("the"));
        phrase4.addEntity(new Word("old"));
        phrase4.addEntity(new Word("lady's"));
        phrase4.addEntity(new Word("boots"));
        phrase4.init();

        idiom5 = new Idiom();
        idiom5.addEntity(new Word("ants"));
        idiom5.addEntity(new Word("in"));
        idiom5.addEntity(new Word("my"));
        idiom5.addEntity(new Word("pants"));
        idiom5.init();

        phrase5 = new Phrase();
        phrase5.addEntity(new Word("Test"));
        phrase5.addEntity(new Word("aNts"));
        phrase5.addEntity(new Word("in"));
        phrase5.addEntity(new Word("her"));
        phrase5.addEntity(new Word("pants"));
        phrase5.addEntity(new Word("Test"));
        phrase5.init();
    }

    @Test
    void findMatchingNumbersInPhrase() {
        List<Integer> expected;
        List<Integer> actual;

        expected = Phrase.EMPTY_LIST_INT;
        actual = matcher.findMatchingNumbersInPhrase(idiom2, phrase3, 0);
        assertEquals(expected, actual);

        expected = Arrays.asList(2,3,4,5,6,7,8);
        actual = matcher.findMatchingNumbersInPhrase(idiom1, phrase1, 2);
        Collections.sort(actual);
        assertEquals(expected, actual);

        expected = Arrays.asList(0,1,2,3,4,5);
        actual = matcher.findMatchingNumbersInPhrase(idiom2, phrase2, 0);
        Collections.sort(actual);
        assertEquals(expected, actual);

        expected = Arrays.asList(0,1,2,3,4,5);
        actual = matcher.findMatchingNumbersInPhrase(idiom2, phrase21, 0);
        Collections.sort(actual);
        assertEquals(expected, actual);

        expected = Arrays.asList(0,1,2,3);
        actual = matcher.findMatchingNumbersInPhrase(idiom3, phrase3, 0);
        Collections.sort(actual);
        assertEquals(expected, actual);

        expected = Arrays.asList(0,1,2,3,4);
        actual = matcher.findMatchingNumbersInPhrase(idiom4, phrase4, 0);
        Collections.sort(actual);
        assertEquals(expected, actual);

        expected = Phrase.EMPTY_LIST_INT;
        actual = matcher.findMatchingNumbersInPhrase(idiom3, phrase3, 2);
        assertEquals(expected, actual);

        expected = Phrase.EMPTY_LIST_INT;
        actual = matcher.findMatchingNumbersInPhrase(idiom1, phrase3, 0);
        assertEquals(expected, actual);

        expected = Arrays.asList(1,2,3,4);
        actual = matcher.findMatchingNumbersInPhrase(idiom5, phrase5, 1);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }
}