package md.textanalysis.text.analyser.phrase;

import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.analyser.AnalyserFacade;
import md.textanalysis.utils.WordsTokenizer;

public class PhraseRootFinderAnalyser {
    public String getRoot(String phrase) {
        if (phrase == null || phrase.isEmpty()) return "";

        StringBuilder root = new StringBuilder();
        WordsTokenizer wt = new WordsTokenizer(phrase);
        while (wt.hasMoreTokens()) {
            String nxt = wt.nextToken();
            if (TextAnalyserHelper.isWord(nxt)) {
                if (root.length() > 0) root.append(" ");
                root.append(AnalyserFacade.getWordRoot(nxt));
            }
        }

        return root.toString();
    }
}
