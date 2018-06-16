package md.textanalysis.text.element.word;

import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.analyse.AnalyserFacade;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.enums.State;

public class Word extends AbstractWord {
    public Word(String original) {
        super(original);
        this.state = State.PLANNED;
    }

    public Word(String original, Phrase phrase) {
        super(original, phrase);
        this.state = State.PLANNED;
    }

    public void init() {
        super.init();
        this.setLower(TextAnalyserHelper.convertToLowerCase(this.getOriginal()));
        this.setRoot(AnalyserFacade.getWordRoot(this.getLower()));
    }

    @Override
    public String getSeparatorBefore() {
        return " ";
    }

    @Override
    public boolean isSeparatorAfterRequired() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;

        Word word = (Word) o;
        //System.out.println("this=" + original + "("+root+"), that=" + word.original+"("+word.root+")");

        return root != null ? root.equals(word.root) : original.equals(word.original);
    }

    @Override
    public int hashCode() {
        return root != null ? root.hashCode() : 0;
    }

    @Override
    public String toString() {
        return original;
    }
}
