package md.textanalysis.text.modifier;

import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.element.word.Number;
import md.textanalysis.text.element.word.Separator;
import md.textanalysis.text.element.word.Word;
import md.textanalysis.text.modifier.impl.TextSpecialCase;
import md.textanalysis.text.modifier.impl.TextSpecialCasesList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class TextSpecialCasesModifierTest {
    TextSpecialCasesModifier modifier;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        modifier = new TextSpecialCasesModifier();
        modifier.init();
    }

    @Test
    void init() {
        String modStr = modifier.toString();
        assertTrue(modStr.contains("do not"));
        assertTrue(modStr.contains("shall not"));
        assertFalse(modStr.contains("wasn't"));
        assertFalse(modStr.contains("wasn’t"));
    }

    @Test
    void getPeaceOfOriginalText() {
        List<AbstractWord> text = new ArrayList<>();
        text.add(new Word("This"));
        text.add(new Word("is"));
        text.add(new Word("text"));
        text.add(new Number("2"));
        text.add(new Word("check"));

        ListIterator<AbstractWord> it = text.listIterator();
        it.next();
        AbstractWord word = it.next();

        List<AbstractWord> peaceOfText = modifier.getPeaceOfOriginalText(word, it, 3);

        assertEquals("[is, text, 2]", peaceOfText.toString());
        assertEquals("text", it.next().getOriginal());
    }

    @Test
    void isCasePresented() {
        List<AbstractWord> text = new ArrayList<>();
        text.add(new Word("We"));
        text.add(new Word("cannot"));
        text.add(new Word("and"));
        text.add(new Word("must"));
        text.add(new Word("not"));
        text.add(new Word("stop"));
        text.add(new Word("there"));
        text.add(new Separator("."));

        List<AbstractWord> sCaseText = new ArrayList<>();
        sCaseText.add(new Word("must"));
        sCaseText.add(new Word("not"));
        sCaseText.add(new Word("stop"));

        TextSpecialCase sCase = new TextSpecialCase(sCaseText);

        assertFalse(modifier.isCasePresented(text, sCase));

        text = new ArrayList<>();
        text.add(new Word("must"));
        text.add(new Word("not"));
        text.add(new Word("stop"));
        text.add(new Word("there"));
        text.add(new Separator("."));

        assertTrue(modifier.isCasePresented(text, sCase));
    }

    @Test
    void process() {
        TextSpecialCasesList specialCasesList = new TextSpecialCasesList();

        List<AbstractWord> from = new ArrayList<>();
        from.add(new Word("must"));
        from.add(new Word("not"));
        specialCasesList.add(from);

        from = new ArrayList<>();
        from.add(new Word("must"));
        from.add(new Word("not"));
        from.add(new Word("test"));
        specialCasesList.add(from);

        from = new ArrayList<>();
        from.add(new Word("test"));
        from.add(new Word("test"));
        from.add(new Word("test"));
        specialCasesList.add(from);

        List<AbstractWord> text = new ArrayList<>();
        text.add(new Word("We"));
        text.add(new Word("must"));
        text.add(new Word("not"));
        text.add(new Word("test"));
        text.add(new Word("it"));
        text.add(new Separator("."));

        ListIterator<AbstractWord> it = text.listIterator();
        it.next();
        AbstractWord word = it.next();

        modifier.process(word, it, specialCasesList);

        assertEquals(4, text.size());
        assertEquals("We", text.get(0).getOriginal());
        assertEquals("must not test", text.get(1).getOriginal());
        assertEquals("it", text.get(2).getOriginal());
    }

    @Test
    void perform() {
        List<AbstractWord> text = new ArrayList<>();
        text.add(new Word("We"));
        text.add(new Word("cAn"));
        text.add(new Word("not"));
        text.add(new Word("and"));
        text.add(new Word("must"));
        text.add(new Word("not"));
        text.add(new Word("stop"));
        text.add(new Word("there"));
        text.add(new Separator("."));

        TextAnalyserHelper.initWords(text);

        modifier.perform(text);

        assertEquals(7, text.size());
        assertEquals("We", text.get(0).getOriginal());
        assertEquals("cAn not", text.get(1).getOriginal());
        assertEquals("and", text.get(2).getOriginal());
        assertEquals("must not", text.get(3).getOriginal());
    }
}