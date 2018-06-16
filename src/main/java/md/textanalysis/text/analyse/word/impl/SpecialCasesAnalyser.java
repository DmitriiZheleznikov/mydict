package md.textanalysis.text.analyse.word.impl;

import md.textanalysis.ctrl.AContext;

import java.util.HashMap;
import java.util.Map;

public class SpecialCasesAnalyser extends AbstractWordAnalyser {
    private final Map<String, String> MAP = new HashMap<>();

    @Override
    protected String iniFileName() {
        return "/resources/data/SpecialCases.list";
    }

    @Override
    protected void initLine(String line) {
        String[] form = line.split(" +");
        MAP.put(form[0], form[1]);
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
