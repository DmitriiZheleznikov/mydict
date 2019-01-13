package md.textanalysis.text.analyser.text;

import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.AbstractWord;

import java.util.List;

public class TextPhrasesCreator {
    public void perform(List<AbstractWord> wordsList) {
        Phrase phrase = new Phrase();
        for (AbstractWord word : wordsList) {
            word.addToPhrase(phrase);
            if (word.isPhraseBreak()) {
                phrase = new Phrase();
            }
        }
    }
}
