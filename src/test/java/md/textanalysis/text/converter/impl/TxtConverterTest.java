package md.textanalysis.text.converter.impl;

import md.textanalysis.text.analyser.text.TextOrderNumberSetter;
import md.textanalysis.text.analyser.text.TextPhrasesCreator;
import md.textanalysis.text.converter.AbstractTextConverter;
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

        expected.add(new Word("This"));
        expected.add(new Word("father’s"));
        expected.add(new Word("text"));
        expected.add(new Separator(","));

        expected.add(new Word("for"));
        expected.add(new Word("tests"));
        expected.add(new Separator("."));

        expected.add(new Number("1"));
        expected.add(new Separator("."));

        expected.add(new Word("convert"));
        expected.add(new Word("it"));
        expected.add(new Word("into"));
        expected.add(new Word("a"));
        expected.add(new Word("list"));
        expected.add(new Number("2"));
        expected.add(new Separator("."));

        expected.add(new Word("Filter"));
        expected.add(new Word("out"));
        expected.add(new Word("\"MyDictionary\""));
        expected.add(new Word("file"));
        expected.add(new Separator("("));
        expected.add(new Word("words"));
        expected.add(new Word("you"));
        expected.add(new Word("know"));
        expected.add(new Separator(")"));

        new TextPhrasesCreator().perform(expected);
        new TextOrderNumberSetter().perform(expected);

        return expected;
    }
}