package md.textanalysis.ctrl;

import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.AbstractWord;

public class AContext {
    private TextToAnalyse textToAnalyse;
    private String original;
    private String lower;
    private String root;
    private String example;
    private int curWordNumber;

    public AContext(TextToAnalyse textToAnalyse) {
        this.textToAnalyse = textToAnalyse;
        this.curWordNumber = -1;
    }

    public void nextWord(int curWordNumber) {
        this.curWordNumber = curWordNumber;
        this.original = textToAnalyse.getEntities().get(curWordNumber).getOriginal();
        this.lower = textToAnalyse.getEntities().get(curWordNumber).getLower();
        this.root = textToAnalyse.getEntities().get(curWordNumber).getRoot();
        this.example = null;
    }

    public AbstractWord getCurrentWord() {
        return textToAnalyse.getEntities().get(curWordNumber);
    }

    public Phrase getCurrentPhrase() {
        return getCurrentWord().getPhrase();
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
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

    @Override
    public String toString() {
        return "AContext{" +
                "original='" + original + '\'' +
                ", lower='" + lower + '\'' +
                ", root='" + root + '\'' +
                '}';
    }
}
