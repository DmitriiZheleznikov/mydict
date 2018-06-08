package md.textanalysis.text.analyse.word.impl;

import java.util.*;

public class RootFinderAnalyser extends AbstractWordAnalyser {
    private static final int MIN_WORD_LENGTH = 3;

    private static final String KEY_NAME_END = "END=";
    private static final String KEY_NAME_END_REPLACE = "END_REPLACE=";
    private static final String KEY_NAME_END_CAUSES_ANOTHER_ITERATION = "END_CAUSES_ANOTHER_ITERATION=";
    private static final String KEY_NAME_BEGIN = "BEGIN=";

    private final List<String> DATA_END = new ArrayList<>();
    private final List<String> DATA_BEGIN = new ArrayList<>();
    private final Set<String> DATA_END_CAUSES_ANOTHER_ITERATION = new HashSet<>();
    private final Map<String, String> DATA_END_REPLACE = new HashMap<>();

    @Override
    protected String iniFileName() {
        return "/resources/data/RootFinder.data";
    }

    @Override
    protected void initLine(String line) {
        initListByKey(DATA_END, KEY_NAME_END, line);
        initListByKey(DATA_END_CAUSES_ANOTHER_ITERATION, KEY_NAME_END_CAUSES_ANOTHER_ITERATION, line);
        initListByKey(DATA_BEGIN, KEY_NAME_BEGIN, line);
        initMapByKey(DATA_END_REPLACE, KEY_NAME_END_REPLACE, line);
    }

    /**
     * Takes word and convert it to some common form 'impl', has special cases, irregular verb dict and common rules
     * @param word in lower case
     * @return 'impl' of word
     */
    public String get(String word) {
        String root = getRootRecursive(word, 0);
        if (root.startsWith("\"") || root.startsWith("‘") || root.startsWith("’") || root.startsWith("'") || root.startsWith("-")) root = root.substring(1);
        if (root.endsWith("\"") || root.endsWith("’") || root.endsWith("'")) root = root.substring(0, root.length()-1);
        return replaceEnd(root);
    }

    private String getRootRecursive(String word, int level) {
        String end = findEnd(word);
        if (canBeRemoved(word, end)) {
            word = word.substring(0, word.length()-end.length());
            if (level == 0 && DATA_END_CAUSES_ANOTHER_ITERATION.contains(end)) {
                return getRootRecursive(word, level+1);
            }
        }

        String begin = findBegin(word);
        if (canBeRemoved(word, begin)) {
            word = word.substring(begin.length());
        }

        return word;
    }

    private boolean canBeRemoved(String word, String part) {
        if (part == null) return false;
        if (part.contains("'")) return true;
        if ((word.length() - part.length()) < MIN_WORD_LENGTH) return false;

        return true;
    }

    private String findEnd(String word) {
        for (String end : DATA_END) {
            if (word.endsWith(end)) return end;
        }
        return null;
    }

    private String findBegin(String word) {
        for (String end : DATA_BEGIN) {
            if (word.startsWith(end)) return end;
        }
        return null;
    }

    private String replaceEnd(String word) {
        for (String key : DATA_END_REPLACE.keySet()) {
            if (word.endsWith(key) && canBeRemoved(word, key)) {
                word = word.substring(0, word.length()-key.length()) + DATA_END_REPLACE.get(key);
            }
        }
        return word;
    }
}
