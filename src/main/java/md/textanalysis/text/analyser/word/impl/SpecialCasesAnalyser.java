package md.textanalysis.text.analyser.word.impl;

import md.textanalysis.ctrl.AContext;
import md.textanalysis.helper.TextAnalyserHelper;

import java.util.HashMap;
import java.util.Map;

public class SpecialCasesAnalyser extends AbstractWordAnalyser {
    private final Map<String, String> MAP = new HashMap<>();

    @Override
    protected String iniFileName() {
        return "/resources/data/word/SpecialCases.list";
    }

    @Override
    protected void initLine(String line) {
        if (!line.contains("=")) return;

        String lineFrom = TextAnalyserHelper.singleSpacesIfNeeded(line.substring(0, line.indexOf("=")).trim().toLowerCase());
        String lineTo = TextAnalyserHelper.singleSpacesIfNeeded(line.substring(line.indexOf("=") + 1).trim().toLowerCase());
        if (lineFrom != null && lineFrom.length() > 0 && lineTo != null && lineTo.length() > 0) {
            MAP.put(lineFrom, lineTo);
        }
    }

    @Override
    public String get(String word) {
        return MAP.get(word);
    }

    public void process(AContext context) {
        String found = get(context.getLower());
        if (found != null) {
            context.setLower(found);
            context.setRoot(found);
        }
    }
}
