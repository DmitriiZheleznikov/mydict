package md.textanalysis.helper.root;

import heli.helper.ResourceHelper;
import md.textanalysis.helper.TextAnalyserHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Detects phrasal verbs by word and text<br>
 * Logic:<ul>
 *     <li>Calculate 'key' by using RootFinderHelper</li>
 *     <li>Check if map contains such key</li>
 *     <li>If so, define a few words further and check every preposition for found word</li>
 * </ul>
 */
public class PhrasalVerbHelper {
    private static final int WORDS_IN_LINE_COUNT = 5;
    public static final HashMap<String, List<PhVerb>> PH_VERB_MAP = new HashMap<>();

    public static String get(int wordPos, String root, String word, String text) {
        root = ((root == null) ? findRoot(word) : root);
        List<PhVerb> phVerbs = PH_VERB_MAP.get(root);

        if (phVerbs != null) {
            PhVerb phVerbToUse = null;
            String line = calcLine(wordPos, text);
            for (PhVerb phVerb : phVerbs) {
                if (line.contains(phVerb.getPreposition())) {
                    if (phVerbToUse == null || phVerbToUse.getPreposition().length() < phVerb.getPreposition().length()) {
                        phVerbToUse = phVerb;
                    }
                }
            }
            return phVerbToUse == null ? null : phVerbToUse.toString();
        }

        return null;
    }

    private static String findRoot(String word) {
        String root = IrregularVerbHelper.get(word);
        if (root == null) root = TextAnalyserHelper.getRoot(word);
        return root;
    }

    /** Finds line with pointed word in text as: word + few more words */
    private static String calcLine(int wordPos, String text) {
        int spacesCount = 0;
        StringBuilder line = new StringBuilder();

        for (int i = wordPos; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') spacesCount++;
            if (spacesCount == WORDS_IN_LINE_COUNT) break;
            line.append(ch);
        }

        return line.toString();
    }

    public static void init() throws IOException, URISyntaxException {
        if (PH_VERB_MAP.isEmpty()) {
            List<String> phVerbs = ResourceHelper.readTextFile(PhrasalVerbHelper.class, "/resources/data/PhrasalVerbs.list");//Files.readAllLines(Paths.get(), StandardCharsets.UTF_8);
            for (String phVerb : phVerbs) {
                initPhVerb(findVerb(phVerb), findPreposition(phVerb));
            }
        }
    }

    private static void initPhVerb(String verb, String preposition) {
        String key = TextAnalyserHelper.getRoot(verb);
        List<PhVerb> phVerbs = PH_VERB_MAP.computeIfAbsent(key, k -> new ArrayList<>());
        phVerbs.add(new PhVerb(verb, preposition));
    }

    private static String findVerb(String phVerb) {
        return phVerb.substring(0, phVerb.indexOf(" "));
    }

    private static String findPreposition(String phVerb) {
        return phVerb.substring(phVerb.indexOf(" ") + 1);
    }

    static class PhVerb {
        private String verb;
        private String preposition;

        public PhVerb(String verb, String preposition) {
            this.verb = verb;
            this.preposition = preposition;
        }

        public String getVerb() {
            return verb;
        }

        public String getPreposition() {
            return preposition;
        }

        @Override
        public String toString() {
            return verb + " " + preposition;
        }
    }
}
