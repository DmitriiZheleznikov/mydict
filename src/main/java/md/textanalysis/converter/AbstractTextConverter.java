package md.textanalysis.converter;

import md.textanalysis.text.element.TextElementFactory;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.utils.WordsTokenizer;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractTextConverter<T> implements ITextConverter<T> {
    protected List<String> rawLinesToAnalyse;
    protected List<AbstractWord> resultWords;

    public AbstractTextConverter(List<String> rawLinesToAnalyse) {
        this.rawLinesToAnalyse = rawLinesToAnalyse;
        this.resultWords = new ArrayList<>(100000);
    }

    @Override
    public void perform() {
        AbstractWord wordElement = null;
        Phrase phrase = new Phrase(0);
        int i = -1;
        for (String line : rawLinesToAnalyse) {

            if (beforeLineProcess(line)) continue;

            WordsTokenizer wt = new WordsTokenizer(line);
            boolean isFirstWordInLine = true;
            while (wt.hasMoreTokens()) {
                String wordText = wt.nextToken();

                if ((wordText = beforeElementProcess(wordText, isFirstWordInLine)) == null) {
                    isFirstWordInLine = false;
                    continue;
                }

                i++;
                wordElement = TextElementFactory.create(wordText, phrase);
                resultWords.add(wordElement);
                if (wordElement.isPhraseBreak()) {
                    phrase = new Phrase(i+1/*, phrase*/);
                }

                if (afterElementProcess(wordElement, isFirstWordInLine)) break;

                if (isFirstWordInLine) isFirstWordInLine = false;
            }

            if (afterLineProcess(wordElement)) break;
        }
    }

    abstract protected boolean beforeLineProcess(String line);
    abstract protected String beforeElementProcess(String wordText, boolean isFirstWordInLine);
    abstract protected boolean afterElementProcess(AbstractWord wordElement, boolean isFirstWordInLine);
    abstract protected boolean afterLineProcess(AbstractWord wordElement);

    public List<String> getRawLinesToAnalyse() {
        return rawLinesToAnalyse;
    }
}
