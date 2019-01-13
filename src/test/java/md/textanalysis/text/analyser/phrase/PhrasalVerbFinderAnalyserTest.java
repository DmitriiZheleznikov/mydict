package md.textanalysis.text.analyser.phrase;

import md.textanalysis.ctrl.AContext;
import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.analyser.text.TextOrderNumberSetter;
import md.textanalysis.text.analyser.text.TextPhrasesCreator;
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

        List<AbstractWord> entities = new ArrayList<>();
        entities.add(new Word("How"));
        entities.add(new Word("did"));
        entities.add(new Word("you"));
        entities.add(new Word("find"));
        entities.add(new Word("this"));
        entities.add(new Word("bRoken"));
        entities.add(new Word("thing"));
        entities.add(new Word("dowN"));
        entities.add(new Word("sir"));
        entities.add(new Word("miss"));
        new TextPhrasesCreator().perform(entities);
        entities.get(0).getPhrase().init();
        new TextOrderNumberSetter().perform(entities);

        AContext context = new AContext();
        context.nextWord(entities.get(5));

        analyser.process(context);

        assertEquals(expected, context.getLower());
    }
}