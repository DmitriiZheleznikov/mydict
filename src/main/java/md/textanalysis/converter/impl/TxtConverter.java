package md.textanalysis.converter.impl;

import md.textanalysis.converter.AbstractTextConverter;
import md.textanalysis.text.element.word.AbstractWord;

import java.util.List;

public class TxtConverter extends AbstractTextConverter<AbstractWord> {
    private AbstractWord wordWrap = null;

    public TxtConverter(List<String> rawLinesToAnalyse) {
        super(rawLinesToAnalyse);
    }

    @Override
    protected boolean beforeLineProcess(String line) {
        return false;
    }

    @Override
    protected String beforeElementProcess(String wordText, boolean isFirstWordInLine) {
        if (isFirstWordInLine && processWordWrapCase(wordText)) {
            return null;
        }

        return wordText;
    }

    @Override
    protected boolean afterElementProcess(AbstractWord wordElement, boolean isFirstWordInLine) {
        return false;
    }

    @Override
    protected boolean afterLineProcess(AbstractWord wordElement) {
        wordWrap = identifyWordWrapCase(wordElement);
        return false;
    }

    private boolean processWordWrapCase(String wordText) {
        if (wordWrap != null) {
            wordWrap.setOriginal(wordWrap.getOriginal() + wordText);
            return true;
        }
        return false;
    }

    private AbstractWord identifyWordWrapCase(AbstractWord wordElement) {
        if (wordElement != null && wordElement.getOriginal().endsWith("-")) {
            wordWrap = wordElement;
            wordElement.setOriginal(wordElement.getOriginal().substring(0, wordElement.getOriginal().length()-1));
        } else {
            wordWrap = null;
        }

        return wordWrap;
    }

    @Override
    public List<AbstractWord> getResult() {
        return resultWords;
    }
}
