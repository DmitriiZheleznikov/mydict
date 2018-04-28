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

    public static String get(String word, String text) throws IOException, URISyntaxException {
        String key = IrregularVerbHelper.get(word);
        if (key == null) key = TextAnalyserHelper.getRoot(key);
        return get(key, word, text);
    }

    public static String get(String key, String word, String text) throws IOException, URISyntaxException {
        init();
        List<PhVerb> phVerbs = PH_VERB_MAP.get(key);

        if (phVerbs != null) {
            PhVerb phVerbToUse = null;
            String line = calcLine(word, text);//TODO find with start point!!!
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

    /** Finds line with pointed word in text as: word + 3 more words */
    private static String calcLine(String word, String text) {
        int wordStart = text.indexOf(word);
        int spacesCount = 0;
        StringBuilder line = new StringBuilder();

        for (int i = wordStart; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') spacesCount++;
            if (spacesCount == WORDS_IN_LINE_COUNT) break;
            line.append(ch);
        }

        return line.toString();
    }

    private static void init() throws IOException, URISyntaxException {
        if (PH_VERB_MAP.isEmpty()) {
            List<String> phVerbs = ResourceHelper.readTextFile(PhrasalVerbHelper.class, "/resources/data/PhrasalVerbs.list");//Files.readAllLines(Paths.get(), StandardCharsets.UTF_8);
            for (String phVerb : phVerbs) {
                initPhVerb(findVerb(phVerb), findPreposition(phVerb));
            }
        }
    }

    private static void initPhVerb(String verb, String preposition) {
        System.out.println("\""+verb+"\", \""+preposition+"\"");
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
