package md.textanalysis.text.matcher.phrase.table;

import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.AbstractWord;

import java.util.Arrays;

public class PMTLine {
    public static final int CAPACITY = 300;

    private PMTElement[] elements;
    private int maxPos;

    public PMTLine() {
        this.elements = new PMTElement[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            this.elements[i] = new PMTElement();
        }
        clear();
    }

    public void init(Phrase phrase, int startPos) {
        maxPos = -1;
        int i = -1;
        for (AbstractWord word : phrase.getEntities()) {
            i++;
            if (i >= startPos) {
                elements[++maxPos].setWord(word);
            }
        }
    }

    public void clear() {
        this.maxPos = -1;
    }

    public PMTElement getElement(int i) {
        return i <= maxPos ? elements[i] : null;
    }

    public int getMaxPos() {
        return maxPos;
    }

    public boolean analyseAreAllWordsFound() {
        for (int i = 0; i <= getMaxPos(); i++) {
            if (getElement(i).isNotMatched()) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PMTLine{" +
                "maxPos=" + maxPos
                + ", elements=" + Arrays.toString(elements) +
                '}';
    }
}
