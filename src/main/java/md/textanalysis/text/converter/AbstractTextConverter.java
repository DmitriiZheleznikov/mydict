package md.textanalysis.text.converter;

import md.textanalysis.text.element.TextElementFactory;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.utils.WordsTokenizer;

import java.util.LinkedList;
import java.util.List;

abstract public class AbstractTextConverter implements ITextConverter<AbstractWord> {
    protected List<String> rawLinesToAnalyse;
    protected List<AbstractWord> resultWords;

    public AbstractTextConverter(List<String> rawLinesToAnalyse) {
        this.rawLinesToAnalyse = rawLinesToAnalyse;
    }

    @Override
    public void perform() {
        perform(new LinkedList<>());
    }

    @Override
    public void perform(List<AbstractWord> initialResultList) {
        resultWords = initialResultList;
        AbstractWord wordElement = null;
        for (String line : rawLinesToAnalyse) {
            if (beforeLineProcess(line)) continue;

            WordsTokenizer wt = createWordsTokenizer(line);
            boolean isFirstWordInLine = true;
            while (wt.hasMoreTokens()) {
                String wordText = wt.nextToken();

                if ((wordText = beforeElementProcess(wordText, isFirstWordInLine)) == null) {
                    isFirstWordInLine = false;
                    continue;
                }

                wordElement = TextElementFactory.create(wordText);
                resultWords.add(wordElement);

                if (afterElementProcess(wordElement, isFirstWordInLine)) break;

                if (isFirstWordInLine) isFirstWordInLine = false;
            }

            if (afterLineProcess(wordElement)) break;
        }
    }

    abstract protected WordsTokenizer createWordsTokenizer(String line);
    abstract protected boolean beforeLineProcess(String line);
    abstract protected String beforeElementProcess(String wordText, boolean isFirstWordInLine);
    abstract protected boolean afterElementProcess(AbstractWord wordElement, boolean isFirstWordInLine);
    abstract protected boolean afterLineProcess(AbstractWord wordElement);

    public List<String> getRawLinesToAnalyse() {
        return rawLinesToAnalyse;
    }
}
