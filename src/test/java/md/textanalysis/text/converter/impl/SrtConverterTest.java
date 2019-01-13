package md.textanalysis.text.converter.impl;

import md.textanalysis.text.analyser.text.TextOrderNumberSetter;
import md.textanalysis.text.analyser.text.TextPhrasesCreator;
import md.textanalysis.text.converter.AbstractTextConverter;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.element.word.Separator;
import md.textanalysis.text.element.word.Word;

import java.util.ArrayList;
import java.util.List;

class SrtConverterTest extends AbstractConverterTest {

    @Override
    AbstractTextConverter getConverter(List<String> original) {
        return new SrtConverter(original);
    }

    @Override
    List<String> getOriginal() {
        List<String> original = new ArrayList<>();
        original.add("");
        original.add("19");
        original.add("00:02:58,428 --> 00:02:59,720");
        original.add("MARTY: Hey, Doc?");
        original.add("");
        original.add("20");
        original.add("00:03:02,307 --> 00:03:03,432");
        original.add("Doc?");
        original.add("");
        original.add("21");
        original.add("00:03:04,601 --> 00:03:06,352");
        original.add("Hello! Any-");
        original.add("body home?");
        original.add("");
        original.add("22");
        original.add("00:03:06,728 --> 00:03:07,687");
        original.add("Einstein, come here, boy.");

        return original;
    }

    @Override
    List<AbstractWord> getExpected() {
        List<AbstractWord> expected = new ArrayList<>();

        expected.add(new Word("MARTY"));
        expected.add(new Separator(":"));

        expected.add(new Word("Hey"));
        expected.add(new Separator(","));

        expected.add(new Word("Doc"));
        expected.add(new Separator("?"));

        expected.add(new Word("Doc"));
        expected.add(new Separator("?"));

        expected.add(new Word("Hello"));
        expected.add(new Separator("!"));

        expected.add(new Word("Anybody"));
        expected.add(new Word("home"));
        expected.add(new Separator("?"));

        expected.add(new Word("Einstein"));
        expected.add(new Separator(","));

        expected.add(new Word("come"));
        expected.add(new Word("here"));
        expected.add(new Separator(","));

        expected.add(new Word("boy"));
        expected.add(new Separator("."));

        new TextPhrasesCreator().perform(expected);
        new TextOrderNumberSetter().perform(expected);

        return expected;
    }
}