package md.textanalysis.converter.impl;

import md.textanalysis.converter.AbstractTextConverter;
import md.textanalysis.text.element.phrase.Phrase;
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

        Phrase phrase = new Phrase(0);
        expected.add(new Word("MARTY", phrase));
        expected.add(new Separator(":", phrase));

        phrase = new Phrase(2);
        expected.add(new Word("Hey", phrase));
        expected.add(new Separator(",", phrase));

        phrase = new Phrase(4);
        expected.add(new Word("Doc", phrase));
        expected.add(new Separator("?", phrase));

        phrase = new Phrase(6);
        expected.add(new Word("Doc", phrase));
        expected.add(new Separator("?", phrase));

        phrase = new Phrase(8);
        expected.add(new Word("Hello", phrase));
        expected.add(new Separator("!", phrase));

        phrase = new Phrase(10);
        expected.add(new Word("Anybody", phrase));
        expected.add(new Word("home", phrase));
        expected.add(new Separator("?", phrase));

        phrase = new Phrase(13);
        expected.add(new Word("Einstein", phrase));
        expected.add(new Separator(",", phrase));

        phrase = new Phrase(15);
        expected.add(new Word("come", phrase));
        expected.add(new Word("here", phrase));
        expected.add(new Separator(",", phrase));

        phrase = new Phrase(18);
        expected.add(new Word("boy", phrase));
        expected.add(new Separator(".", phrase));

        return expected;
    }
}