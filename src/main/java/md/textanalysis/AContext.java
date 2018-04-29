package md.textanalysis;

public class AContext {
    private TextToAnalyse textToAnalyse;
    private String wordOriginal;
    private String wordRoot;
    private String wordToUse;
    private String example;
    private int curPosInOriginalText;

    public AContext(TextToAnalyse textToAnalyse) {
        this.textToAnalyse = textToAnalyse;
        this.curPosInOriginalText = 0;
    }

    public void newWord(String wordOriginal) {
        this.wordOriginal = wordOriginal;
        this.wordRoot = null;
        this.wordToUse = wordOriginal;
        this.example = null;
        curPosInOriginalText = textToAnalyse.getTextLowerNoDelimiters().indexOf(wordOriginal, curPosInOriginalText+1);
    }

    public String getWordOriginal() {
        return wordOriginal;
    }

    public String getWordRoot() {
        return wordRoot;
    }

    public String getWordToUse() {
        return wordToUse;
    }

    public int getCurPosInOriginalText() {
        return curPosInOriginalText;
    }

    public String getExample() {
        return example;
    }

    public TextToAnalyse getTextToAnalyse() {
        return textToAnalyse;
    }

    public void setWordRoot(String wordRoot) {
        this.wordRoot = wordRoot;
    }

    public void setWordToUse(String wordToUse) {
        this.wordToUse = wordToUse;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return "AContext{" +
                "wordOriginal='" + wordOriginal + '\'' +
                ", wordRoot='" + wordRoot + '\'' +
                ", wordToUse='" + wordToUse + '\'' +
                ", example='" + example + '\'' +
                ", curPosInOriginalText=" + curPosInOriginalText +
                '}';
    }
}
