package md.textanalysis.ctrl;

import heli.component.shape.text.htext.HStringFlow;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.AbstractWord;

import java.util.HashSet;
import java.util.Set;

public class AContext {
    private TextToAnalyse textToAnalyse;
    private String lower;
    private String root;
    private HStringFlow example;
    private int curWordNumber;
    private Set<Integer> wNumBold;

    public AContext(TextToAnalyse textToAnalyse) {
        this.textToAnalyse = textToAnalyse;
        this.curWordNumber = -1;
        this.wNumBold = new HashSet<>();
    }

    public void nextWord(int curWordNumber) {
        this.curWordNumber = curWordNumber;
        this.lower = textToAnalyse.getEntities().get(curWordNumber).getLower();
        this.root = textToAnalyse.getEntities().get(curWordNumber).getRoot();
        this.example = null;
        this.wNumBold.clear();
        this.wNumBold.add(curWordNumber);
    }

    public AbstractWord getCurrentWord() {
        return textToAnalyse.getEntities().get(curWordNumber);
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
        return curWordNumber;
    }

    public void setCurWordNumber(int curWordNumber) {
        this.curWordNumber = curWordNumber;
    }

    public String getLower() {
        return lower;
    }

    public void setLower(String lower) {
        this.lower = lower;
    }

    public TextToAnalyse getTextToAnalyse() {
        return textToAnalyse;
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
                ", lower='" + lower + '\'' +
                ", root='" + root + '\'' +
                '}';
    }
}
