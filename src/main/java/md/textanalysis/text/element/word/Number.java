package md.textanalysis.text.element.word;

import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.enums.State;

public class Number extends AbstractWord {
    public Number(String original) {
        super(original);
        this.state = State.SKIPPED;
    }

    public Number(String original, Phrase phrase) {
        super(original, phrase);
        this.state = State.SKIPPED;
    }

    @Override
    public String getSeparatorBefore() {
        return " ";
    }

    @Override
    public boolean isSeparatorAfterRequired() {
        return true;
    }
}
