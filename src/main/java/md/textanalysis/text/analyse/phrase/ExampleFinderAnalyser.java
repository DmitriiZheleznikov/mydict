package md.textanalysis.text.analyse.phrase;

import md.textanalysis.ctrl.AContext;
import md.textanalysis.text.analyse.AbstractAnalyser;
import md.textanalysis.text.element.phrase.Phrase;

public class ExampleFinderAnalyser extends AbstractAnalyser {
    private static final int COUNT_SYMBOLS_SIDE = 125;
    private static final int MAX_SYMBOLS = COUNT_SYMBOLS_SIDE*2;

    @Override
    protected String iniFileName() {
        return null;
    }

    @Override
    protected void initLine(String line) {

    }

    public void process(AContext context) {
        Phrase phrase = context.getCurrentPhrase();
        String example = phrase.toString();
        if (example.length() > MAX_SYMBOLS) {
            int wordPosInText = phrase.getWordPosInText(context.getCurWordNumber()-phrase.getNum());
            example = example.substring(
                    getLeftBoundary(wordPosInText-COUNT_SYMBOLS_SIDE),
                    getRightBoundary(wordPosInText+COUNT_SYMBOLS_SIDE, example.length())
            );
        }

        context.setExample(example.replaceAll("([\r\n])", " "));
    }

    private static int getLeftBoundary(int indx) {
        if (indx > COUNT_SYMBOLS_SIDE) {
            return indx - COUNT_SYMBOLS_SIDE;
        }
        return 0;
    }

    private static int getRightBoundary(int indx, int textLen) {
        if ((indx + COUNT_SYMBOLS_SIDE) > textLen) {
            return textLen;
        }
        return indx + COUNT_SYMBOLS_SIDE;
    }
}
