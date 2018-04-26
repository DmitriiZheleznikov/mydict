package md.textanalysis.helper.root;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple 'root; finder, it's not real root of word, but it's common part, that will identify particular word in all (or most) forms, like:
 * purchase and purchased will be converted to "purchas" and will be equal. This helps to decrease number of words in text and effectively
 * remove them from list in cas presence in MyDictionary file
 */
public class RootFinderHelper {
    private static final int MIN_WORD_LENGTH = 3;
    private static final List<String> ENDS = new ArrayList<>();
    private static final List<String> BEGINS = new ArrayList<>();
    private static final List<String> ENDS_CAUSES_NEXT_ITERATION = new ArrayList<>();

    static {
        ENDS.add("iness");
        ENDS.add("ness");
        ENDS.add("less");
        ENDS.add("full");
        ENDS.add("est");
        ENDS.add("ing");
        ENDS.add("'ll");
        ENDS.add("'ve");
        ENDS.add("'em");
        ENDS.add("n't");
        ENDS.add("'re");
        ENDS.add("ed");
        ENDS.add("er");
        ENDS.add("es");
        ENDS.add("ly");
        ENDS.add("'s");
        ENDS.add("'d");
        ENDS.add("e");
        ENDS.add("s");

        ENDS_CAUSES_NEXT_ITERATION.add("'s");
        ENDS_CAUSES_NEXT_ITERATION.add("'d");
        ENDS_CAUSES_NEXT_ITERATION.add("s");
        ENDS_CAUSES_NEXT_ITERATION.add("e");

        BEGINS.add("un");
        BEGINS.add("dis");
    }

    /**
     * Takes word and convert it to some common form 'root', has special cases, irregular verb dict and common rules
     * @param word in lower case
     * @return 'root' of word
     */
    public static String get(String word) {
        String root = getRootRecursive(word, 0);
        if (root.startsWith("'") || root.startsWith("-")) return root.substring(1);
        if (root.endsWith("'")) return root.substring(0, root.length()-1);
        return root;
    }

    private static String getRootRecursive(String word, int level) {
        String end = findEnd(word);
        if (canBeRemoved(word, end)) {
            word = word.substring(0, word.length()-end.length());
            if (level == 0 && ENDS_CAUSES_NEXT_ITERATION.contains(end)) {
                return getRootRecursive(word, level+1);
            }
        }

        String begin = findBegin(word);
        if (canBeRemoved(word, begin)) {
            word = word.substring(begin.length());
        }

        return word;
    }

    private static boolean canBeRemoved(String word, String part) {
        if (part == null) return false;
        if (part.contains("'")) return true;
        if ((word.length() - part.length()) < MIN_WORD_LENGTH) return false;

        return true;
    }

    private static String findEnd(String word) {
        for (String end : ENDS) {
            if (word.endsWith(end)) return end;
        }
        return null;
    }

    private static String findBegin(String word) {
        for (String end : BEGINS) {
            if (word.startsWith(end)) return end;
        }
        return null;
    }
}
