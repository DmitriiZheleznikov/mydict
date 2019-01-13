package md.textanalysis.text.analyser.phrase;

import md.textanalysis.ctrl.AContext;
import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.analyser.text.TextOrderNumberSetter;
import md.textanalysis.text.analyser.text.TextPhrasesCreator;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.element.word.Separator;
import md.textanalysis.text.element.word.Word;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class IdiomFinderAnalyserTest {
    @Test
    void process() throws IOException, URISyntaxException {
        TextAnalyserHelper.init();
        IdiomFinderAnalyser analyser = new IdiomFinderAnalyser();
        analyser.init();

        String expected = "make like a tree and leave";

        List<AbstractWord> entities = new ArrayList<>();
        entities.add(new Word("Why"));
        entities.add(new Word("don't"));
        entities.add(new Word("you"));
        entities.add(new Word("make"));
        entities.add(new Word("like"));
        entities.add(new Word("a"));
        entities.add(new Word("tree"));
        entities.add(new Word("and"));
        entities.add(new Word("get"));
        entities.add(new Word("out"));
        entities.add(new Word("of"));
        entities.add(new Word("here"));
        new TextPhrasesCreator().perform(entities);
        entities.get(0).getPhrase().init();

        new TextOrderNumberSetter().perform(entities);
        AContext context = new AContext();
        context.nextWord(entities.get(3));

        analyser.process(context);

        assertEquals(expected, context.getLower());
    }

    @Test
    void process2() throws IOException, URISyntaxException {
        TextAnalyserHelper.init();
        IdiomFinderAnalyser analyser = new IdiomFinderAnalyser();
        analyser.init();

        String expected = "the big apple";

        Phrase phrase1 = new Phrase();
        List<AbstractWord> entities = new ArrayList<>();
        entities.add(new Word("test"));
        entities.add(new Word("the"));
        entities.add(new Word("big"));
        entities.add(new Word("apple"));
        entities.add(new Word("test"));
        new TextPhrasesCreator().perform(entities);
        entities.get(0).getPhrase().init();

        new TextOrderNumberSetter().perform(entities);
        AContext context = new AContext();
        context.nextWord(entities.get(1));
        analyser.process(context);
        assertNotEquals(expected, context.getLower());

        context.nextWord(entities.get(2));
        analyser.process(context);
        assertEquals(expected, context.getLower());
    }

    @Test
    void process3() throws IOException, URISyntaxException {
        TextAnalyserHelper.init();
        IdiomFinderAnalyser analyser = new IdiomFinderAnalyser();
        analyser.init();

        String expected = "the big apple";

        List<AbstractWord> entities = new ArrayList<>();
        entities.add(new Word("test"));
        entities.add(new Word("big"));
        entities.add(new Word("apple"));
        entities.add(new Word("test"));
        new TextPhrasesCreator().perform(entities);
        entities.get(0).getPhrase().init();

        new TextOrderNumberSetter().perform(entities);
        AContext context = new AContext();
        context.nextWord(entities.get(1));

        analyser.process(context);

        assertEquals(expected, context.getLower());
    }

    @Test
    void processABBR() throws IOException, URISyntaxException {
        TextAnalyserHelper.init();
        IdiomFinderAnalyser analyser = new IdiomFinderAnalyser();
        analyser.init();

        String expected = "gentlemen only, ladies forbidden";

        List<AbstractWord> entities = new ArrayList<>();
        entities.add(new Word("test"));
        entities.add(new Word("GOLF"));
        entities.add(new Word("test"));
        new TextPhrasesCreator().perform(entities);
        entities.get(0).getPhrase().init();
        new TextOrderNumberSetter().perform(entities);
        AContext context = new AContext();
        context.nextWord(entities.get(1));

        analyser.process(context);

        assertEquals(expected, context.getLower());
    }

    @Test
    void process4() throws IOException, URISyntaxException {
        TextAnalyserHelper.init();
        IdiomFinderAnalyser analyser = new IdiomFinderAnalyser();
        analyser.init();

        String expected = "while you live, tell truth and shame the devil!";

        List<AbstractWord> entities = new ArrayList<>();
        entities.add(new Word("while"));
        entities.add(new Word("you"));
        entities.add(new Word("live"));
        entities.add(new Separator(","));
        entities.add(new Word("tell"));
        entities.add(new Word("truth"));
        entities.add(new Word("and"));
        entities.add(new Word("shame"));
        entities.add(new Word("the"));
        entities.add(new Word("devil"));
        entities.add(new Separator("!"));

        new TextPhrasesCreator().perform(entities);
        entities.get(0).getPhrase().init();

        new TextOrderNumberSetter().perform(entities);
        AContext context = new AContext();
        context.nextWord(entities.get(0));

        analyser.process(context);

        assertEquals(expected, context.getLower());
    }

    @Test
    void process4_1() throws IOException, URISyntaxException {
        TextAnalyserHelper.init();
        IdiomFinderAnalyser analyser = new IdiomFinderAnalyser();
        analyser.init();

        String expected = "while you live, tell truth and shame the devil!";

        List<AbstractWord> entities = new ArrayList<>();
        entities.add(new Word("while"));
        entities.add(new Word("you"));
        entities.add(new Word("live"));
        entities.add(new Separator(","));
        entities.add(new Word("tell"));
        entities.add(new Word("truth"));
        entities.add(new Word("and"));
        entities.add(new Word("shame"));
        entities.add(new Word("the"));
        entities.add(new Word("devil"));

        new TextPhrasesCreator().perform(entities);
        entities.get(0).getPhrase().init();

        new TextOrderNumberSetter().perform(entities);
        AContext context = new AContext();
        context.nextWord(entities.get(0));

        analyser.process(context);

        assertEquals(expected, context.getLower());
    }

    @Test
    void process4_2() throws IOException, URISyntaxException {
        TextAnalyserHelper.init();
        IdiomFinderAnalyser analyser = new IdiomFinderAnalyser();
        analyser.init();

        String expected = "while you live, tell truth and shame the devil!";

        List<AbstractWord> entities = new ArrayList<>();
        entities.add(new Word("while"));
        entities.add(new Word("you"));
        entities.add(new Word("live"));
        entities.add(new Separator(","));
        entities.add(new Word("tell"));
        entities.add(new Word("truth"));
        entities.add(new Word("and"));
        entities.add(new Word("shame"));
        entities.add(new Word("the"));
        entities.add(new Word("devil"));
        entities.add(new Word("test"));

        new TextPhrasesCreator().perform(entities);
        entities.get(0).getPhrase().init();

        new TextOrderNumberSetter().perform(entities);
        AContext context = new AContext();
        context.nextWord(entities.get(0));

        analyser.process(context);

        assertEquals(expected, context.getLower());
    }
}