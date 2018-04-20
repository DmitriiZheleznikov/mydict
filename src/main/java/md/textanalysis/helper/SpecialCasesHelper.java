package md.textanalysis.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * During finding 'root' (common part of word) there are some special cases
 */
public class SpecialCasesHelper {
    private static final Map<String, String> MAP = new HashMap<>();

    public static String get(String word) {
        return MAP.get(word);
    }

    static {
        MAP.put("does", "do");
        MAP.put("don't", "do");
        MAP.put("doesn't", "do");
        MAP.put("did", "do");
        MAP.put("didn't", "do");
        MAP.put("hasn't", "have");
        MAP.put("haven't", "have");
        MAP.put("has", "have");
        MAP.put("shouldn't", "should");
        MAP.put("couldn't", "can");
        MAP.put("can't", "can");
        MAP.put("cannot", "can");
        MAP.put("won't", "will");
        MAP.put("i've", "i");
        MAP.put("i'm", "i");
        MAP.put("i'd", "i");
        MAP.put("you're", "you");
        MAP.put("you've", "you");
        MAP.put("you'd", "you");
        MAP.put("he's", "he");
        MAP.put("he'd", "he");
        MAP.put("it's", "it");
        MAP.put("we're", "we");
        MAP.put("we've", "we");
        MAP.put("wasn't", "was");
        MAP.put("weren't", "were");
        MAP.put("hadn't", "have");
        MAP.put("'em", "they");
        MAP.put("'cause", "because");
    }
}
