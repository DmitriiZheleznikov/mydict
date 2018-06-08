package md.textanalysis.converter.impl;

import md.textanalysis.converter.AbstractTextConverter;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.element.word.Number;
import md.textanalysis.text.element.word.Separator;
import md.textanalysis.text.element.word.Word;

import java.util.ArrayList;
import java.util.List;

class TxtConverterTest extends AbstractConverterTest {

    @Override
    AbstractTextConverter getConverter(List<String> original) {
        return new TxtConverter(original);
    }

    @Override
    List<String> getOriginal() {
        List<String> original = new ArrayList<>();
        original.add("This father’s text, for tests.");
        original.add("1. conv-");
        original.add("ert it into a list");
        original.add("   ");
        original.add("2. Filter out \"MyDictionary\" file (words you know)");

        return original;
    }

    @Override
    List<AbstractWord> getExpected() {
        List<AbstractWord> expected = new ArrayList<>();

        Phrase phrase = new Phrase(0);
        expected.add(new Word("This", phrase));
        expected.add(new Word("father’s", phrase));
        expected.add(new Word("text", phrase));
        expected.add(new Separator(",", phrase));

        phrase = new Phrase(4);
        expected.add(new Word("for", phrase));
        expected.add(new Word("tests", phrase));
        expected.add(new Separator(".", phrase));

        phrase = new Phrase(7);
        expected.add(new Number("1", phrase));
        expected.add(new Separator(".", phrase));

        phrase = new Phrase(9);
        expected.add(new Word("convert", phrase));
        expected.add(new Word("it", phrase));
        expected.add(new Word("into", phrase));
        expected.add(new Word("a", phrase));
        expected.add(new Word("list", phrase));
        expected.add(new Number("2", phrase));
        expected.add(new Separator(".", phrase));

        phrase = new Phrase(16);
        expected.add(new Word("Filter", phrase));
        expected.add(new Word("out", phrase));
        expected.add(new Word("\"MyDictionary\"", phrase));
        expected.add(new Word("file", phrase));
        expected.add(new Separator("(", phrase));
        expected.add(new Word("words", phrase));
        expected.add(new Word("you", phrase));
        expected.add(new Word("know", phrase));
        expected.add(new Separator(")", phrase));

        return expected;
    }
}