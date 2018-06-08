package md.textanalysis.text.analyse.word.impl;

import java.util.HashMap;
import java.util.Map;

public class IrregularVerbsAnalyser extends AbstractWordAnalyser {
    private final Map<String, String> MAP = new HashMap<>(1000);

    @Override
    protected String iniFileName() {
        return "/resources/data/IrregularVerbs.list";
    }

    @Override
    protected void initLine(String line) {
        String[] form = line.split(" +");
        MAP.put(form[0], form[0]);
        MAP.put(form[1], form[0]);
        MAP.put(form[2], form[0]);
    }

    @Override
    public String get(String word) {
        return MAP.get(word);
    }
}
