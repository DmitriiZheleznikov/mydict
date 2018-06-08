package md.textanalysis.text.element.phrase;

import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.element.word.Separator;
import md.textanalysis.text.element.word.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhrasalVerbTest {
    Phrase phraseBig;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        TextAnalyserHelper.init();
        phraseBig = new Phrase(0);
        phraseBig.addEntity(new Word("How"));
        phraseBig.addEntity(new Word("did"));
        phraseBig.addEntity(new Word("you"));
        phraseBig.addEntity(new Word("find"));
        phraseBig.addEntity(new Word("this"));
        phraseBig.addEntity(new Word("BroKen"));
        phraseBig.addEntity(new Word("aaa"));
        phraseBig.addEntity(new Word("BroKen"));
        phraseBig.addEntity(new Word("thing"));
        phraseBig.addEntity(new Word("down"));
        phraseBig.addEntity(new Word("sir"));
        phraseBig.addEntity(new Word("miss"));
        phraseBig.addEntity(new Separator("."));
        phraseBig.init();
    }

    @Test
    void contains() {
        PhrasalVerb phVerb = new PhrasalVerb(0);
        phVerb.addEntity(new Word("break"));
        phVerb.addEntity(new Word("down"));
        phVerb.init();
        assertEquals(false, phraseBig.contains(phVerb, 5));
        assertEquals(true, phraseBig.contains(phVerb, 7));
        assertEquals(false, phraseBig.contains(phVerb, 8));

        phVerb = new PhrasalVerb(0);
        phVerb.addEntity(new Word("break"));
        phVerb.addEntity(new Word("test"));
        phVerb.init();
        assertEquals(false, phraseBig.contains(phVerb, 0));

        phVerb = new PhrasalVerb(0);
        phVerb.addEntity(new Word("bring"));
        phVerb.addEntity(new Word("along"));
        phVerb.init();
        Phrase phrase = new Phrase(2);
        phrase.addEntity(new Word("bring"));
        phrase.addEntity(new Word("him"));
        phrase.addEntity(new Word("along"));
        phrase.init();
        assertEquals(true, phrase.contains(phVerb, 0));
    }

    @Test
    void toStringTest() {
        PhrasalVerb phVerb = new PhrasalVerb(0);
        phVerb.addEntity(new Word("break"));
        phVerb.addEntity(new Word("down"));
        phVerb.init();

        assertEquals("break down", phVerb.toString());
    }

}