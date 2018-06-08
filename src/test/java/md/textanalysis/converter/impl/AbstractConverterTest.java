package md.textanalysis.converter.impl;

import md.textanalysis.converter.AbstractTextConverter;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.AbstractWord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

abstract public class AbstractConverterTest {

    @Test
    void performWords() {
        List<String> original = getOriginal();
        List<AbstractWord> expected = getExpected();

        AbstractTextConverter converter = getConverter(original);
        converter.perform();
        assertArrayEquals(expected.toArray(new Object[]{}), converter.getResult().toArray(new Object[]{}));
    }

    @Test
    void performPhrases() {
        List<String> original = getOriginal();
        List<AbstractWord> expected = getExpected();

        AbstractTextConverter converter = getConverter(original);
        converter.perform();

        int i = -1;
        List<Phrase> phrasesExpected = new ArrayList<>();
        for (AbstractWord word : expected) {
            i++;
            if (i == word.getPhrase().getNum()) {
                phrasesExpected.add(word.getPhrase());
            }
        }

        i = -1;
        List<Phrase> phrasesOriginal = new ArrayList<>();
        for (AbstractWord word : (List<AbstractWord>)converter.getResult()) {
            i++;
            if (i == word.getPhrase().getNum()) {
                phrasesOriginal.add(word.getPhrase());
            }
        }

        assertArrayEquals(phrasesExpected.toArray(new Object[]{}), phrasesOriginal.toArray(new Object[]{}));
    }

    abstract AbstractTextConverter getConverter(List<String> original);
    abstract List<String> getOriginal();
    abstract List<AbstractWord> getExpected();
}
