package md.textanalysis.text.analyser.word.impl;

import md.textanalysis.ctrl.AContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeautifyAnalyser extends AbstractWordAnalyser {
    private static final String KEY_NAME_CUT_BEGIN = "CUT_BEGIN=";
    private static final String KEY_NAME_CUT_END = "CUT_END=";
    private static final String KEY_NAME_END_REPLACE = "END_REPLACE=";

    private final List<String> DATA_CUT_BEGIN = new ArrayList<>(16);
    private final List<String> DATA_CUT_END = new ArrayList<>(16);
    private final Map<String, String> DATA_END_REPLACE = new HashMap<>();

    @Override
    protected String iniFileName() {
        return "/resources/data/word/Beautify.data";
    }

    @Override
    protected void initLine(String line) {
        initListByKey(DATA_CUT_BEGIN, KEY_NAME_CUT_BEGIN, line);
        initListByKey(DATA_CUT_END, KEY_NAME_CUT_END, line);
        initMapByKey(DATA_END_REPLACE, KEY_NAME_END_REPLACE, line);
    }

    public void process(AContext context) {
        context.setLower(get(context.getLower()));
    }

    @Override
    public String get(String word) {
        return replaceEnd(processBegins(processEnds(word)));
    }

    private String processBegins(String word) {
        for (String start : DATA_CUT_BEGIN) {
            if (word.startsWith(start)) return word.substring(start.length());
        }
        return word;
    }

    private String processEnds(String word) {
        for (String end : DATA_CUT_END) {
            if (word.endsWith(end)) return word.substring(0, word.length()-end.length());
        }
        return word;
    }

    private String replaceEnd(String word) {
        for (String key : DATA_END_REPLACE.keySet()) {
            if (word.endsWith(key)) {
                word = word.substring(0, word.length()-key.length()) + DATA_END_REPLACE.get(key);
                break;
            }
        }
        return word;
    }
}
