package md.textanalysis.text.matcher.phrase.table;

import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.matcher.phrase.table.enums.PMTElementState;

public class PMTElement {
    private AbstractWord word;
    private PMTElementState state;
    private int numMatchingElement;

    public PMTElement() {
        setWord(null);
    }

    public AbstractWord getWord() {
        return word;
    }

    public void setWord(AbstractWord word) {
        this.word = word;
        setNotMatch();
        this.numMatchingElement = -1;
    }

    public PMTElementState getState() {
        return state;
    }

    public boolean isSkipped() {
        return PMTElementState.SKIP == this.state;
    }

    public boolean isMatched() {
        return PMTElementState.MATCH == this.state;
    }

    public boolean isNotMatched() {
        return PMTElementState.NOT_MATCH == this.state;
    }

    private void setState(PMTElementState state) {
        this.state = state;
    }

    public void setNotMatch() {
        setState(PMTElementState.NOT_MATCH);
    }

    public void setSkip() {
        setState(PMTElementState.SKIP);
    }

    public void setMatch(int numMatchingElement) {
        setState(PMTElementState.MATCH);
        this.numMatchingElement = numMatchingElement;
    }

    public int getNumMatchingElement() {
        return numMatchingElement;
    }

    @Override
    public String toString() {
        return "PMTElement{" + word +
                ", " + state +
                ", numMatchingElement=" + numMatchingElement +
                '}';
    }
}
