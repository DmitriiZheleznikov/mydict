package md.textanalysis.ctrl;

import heli.component.shape.text.htext.HStringFlow;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.AbstractWord;

import java.util.HashSet;
import java.util.Set;

public class AContext {
    private AbstractWord currentWord;
    private String lower;
    private String root;
    private HStringFlow example;
    private Set<Integer> wNumBold;

    public AContext() {
        this.wNumBold = new HashSet<>();
    }

    public void nextWord(AbstractWord currentWord) {
        this.currentWord = currentWord;
        this.lower = currentWord.getLower();
        this.root = currentWord.getRoot();
        this.example = null;
        this.wNumBold.clear();
        this.wNumBold.add(currentWord.getOrderNumber());
    }

    public AbstractWord getCurrentWord() {
        return currentWord;
    }

    public Phrase getCurrentPhrase() {
        return getCurrentWord().getPhrase();
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public HStringFlow getExample() {
        return example;
    }

    public void setExample(HStringFlow example) {
        this.example = example;
    }

    public int getCurWordNumber() {
        return currentWord.getOrderNumber();
    }

    public String getLower() {
        return lower;
    }

    public void setLower(String lower) {
        this.lower = lower;
    }

    public void boldWordByGlobalNum(int num) {
        wNumBold.add(num);
    }

    public Set<Integer> getWNumBold() {
        return wNumBold;
    }

    @Override
    public String toString() {
        return "AContext{" +
                "word='" + currentWord + '\'' +
                ", lower='" + lower + '\'' +
                ", root='" + root + '\'' +
                '}';
    }
}
