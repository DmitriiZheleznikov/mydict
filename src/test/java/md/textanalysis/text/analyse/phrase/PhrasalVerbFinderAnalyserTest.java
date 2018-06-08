package md.textanalysis.text.analyse.phrase;

import md.textanalysis.ctrl.AContext;
import md.textanalysis.ctrl.TextToAnalyse;
import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.element.word.Word;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhrasalVerbFinderAnalyserTest {
    @Test
    void process() throws IOException, URISyntaxException {
        TextAnalyserHelper.init();
        PhrasalVerbFinderAnalyser analyser = new PhrasalVerbFinderAnalyser();
        analyser.init();

        String expected = "break down";

        Phrase phrase1 = new Phrase(0);
        Phrase phrase2 = new Phrase(2);
        List<AbstractWord> entities = new ArrayList<>();
        entities.add(new Word("How", phrase1));
        entities.add(new Word("did", phrase1));
        entities.add(new Word("you", phrase2));
        entities.add( new Word("find", phrase2));
        entities.add(new Word("this", phrase2));
        entities.add(new Word("bRoken", phrase2));
        entities.add(new Word("thing", phrase2));
        entities.add(new Word("dowN", phrase2));
        entities.add(new Word("sir", phrase2));
        entities.add(new Word("miss", phrase2));
        phrase1.init();
        phrase2.init();

        TextToAnalyseMock textToAnalyse = new TextToAnalyseMock(entities);
        AContext context = new AContext(textToAnalyse);
        context.nextWord(5);

        analyser.process(context);

        assertEquals(expected, context.getLower());
    }

    private static class TextToAnalyseMock extends TextToAnalyse {
        List<AbstractWord> entities;

        public TextToAnalyseMock(List<AbstractWord> entities) {
            super(null);
            this.entities = entities;
        }

        @Override
        public List<AbstractWord> getEntities() {
            return entities;
        }
    }
}