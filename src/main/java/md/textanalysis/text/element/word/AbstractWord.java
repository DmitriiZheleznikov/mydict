package md.textanalysis.text.element.word;

import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.enums.State;

abstract public class AbstractWord {
    private static final int MIN_WORD_LENGTH = 3;

    protected String original;
    protected String lower;
    protected String root;
    protected State state;
    protected Phrase phrase;

    public AbstractWord(String original) {
        this.original = original;
        this.state = State.PLANNED;
    }

    public AbstractWord(String original, Phrase phrase) {
        this.original = original;
        this.state = State.PLANNED;
        this.phrase = phrase;
        if (phrase != null) phrase.addEntity(this);
    }

    public void init() {
        lower = original;
        root = original;
    }

    public boolean isPhraseBreak() {
        return false;
    }

    abstract public String getSeparatorBefore();

    abstract public boolean isSeparatorAfterRequired();

    public boolean isValid() {
        return original != null
                && original.length() >= MIN_WORD_LENGTH
                && state == State.PLANNED;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Phrase getPhrase() {
        return phrase;
    }

    public void setPhrase(Phrase phrase) {
        this.phrase = phrase;
    }

    public String getLower() {
        return lower;
    }

    public void setLower(String lower) {
        this.lower = lower;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractWord)) return false;

        AbstractWord that = (AbstractWord) o;

        return original.equals(that.original);
    }

    @Override
    public int hashCode() {
        return original.hashCode();
    }

    @Override
    public String toString() {
        return original;
    }
}
