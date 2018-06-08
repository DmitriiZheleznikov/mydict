package md.textanalysis.converter.impl;

import md.textanalysis.converter.state.Fb2ConverterStateMachine;
import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.element.ctrl.HTMLSymbol;

import java.util.List;

public class Fb2Converter extends TxtConverter {
    private Fb2ConverterStateMachine state = new Fb2ConverterStateMachine();

    public Fb2Converter(List<String> rawLinesToAnalyse) {
        super(rawLinesToAnalyse);
    }

    @Override
    protected String beforeElementProcess(String wordText, boolean isFirstWordInLine) {
        boolean shouldProcessWord = state.step(wordText);

        HTMLSymbol symbol = state.getSymbol();
        if (symbol.isComplete()) {
            return super.beforeElementProcess(symbol.get(), isFirstWordInLine);
        }
        if (!symbol.isEmpty() && !symbol.isValid()) {
            return super.beforeElementProcess((TextAnalyserHelper.isNumber(wordText) ? "&#" : "&") + wordText, isFirstWordInLine);
        }

        if (shouldProcessWord) {
            return super.beforeElementProcess(wordText, isFirstWordInLine);
        }
        return null; //continue cycle without processing current word
    }
}
