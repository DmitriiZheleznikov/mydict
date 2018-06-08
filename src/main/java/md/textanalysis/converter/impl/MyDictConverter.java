package md.textanalysis.converter.impl;

import md.textanalysis.converter.ITextConverter;
import md.textanalysis.helper.TextAnalyserHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDictConverter implements ITextConverter<String> {
    protected List<String> rawLinesToAnalyse;
    protected List<String> resultLines;

    public MyDictConverter(List<String> rawLinesToAnalyse) {
        this.rawLinesToAnalyse = rawLinesToAnalyse;
    }

    @Override
    public void perform() {
        resultLines = new ArrayList<>(rawLinesToAnalyse.size());
        for (String rawLine : rawLinesToAnalyse) {
            String line = TextAnalyserHelper.trimIfNeeded(rawLine);
            line = TextAnalyserHelper.lowerCaseIfNeeded(line);
            line = TextAnalyserHelper.singleSpacesIfNeeded(line);
            resultLines.add(line);
        }
    }

    @Override
    public List<String> getResult() {
        return resultLines;
    }


}
