package md.textanalysis.text.element.phrase;

import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.element.word.Number;
import md.textanalysis.text.element.word.Separator;
import md.textanalysis.text.element.word.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

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
        assertEquals(true, phraseBig.contains(phrase, 5));
        assertEquals(false, phraseBig.contains(phrase, 6));

        phrase = new Phrase(0);
        phrase.addEntity(new Word("be"));
        phrase.addEntity(new Separator(","));
        phrase.addEntity(new Word("also"));
        phrase.addEntity(new Number("777"));
        phrase.init();
        assertEquals(false, phraseBig.contains(phrase, 0));
    }

    @Test
    void tString() {
        assertEquals("Test Phrase where search will be, also 123 & 666.", phraseBig.toString());
    }

}