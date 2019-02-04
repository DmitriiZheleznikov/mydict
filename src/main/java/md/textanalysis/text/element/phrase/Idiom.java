package md.textanalysis.text.element.phrase;

import md.textanalysis.text.analyser.AnalyserFacade;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.element.word.Separator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Idiom extends Phrase {
    public static final Set<String> POSSESSIVE_PRONOUNS = new HashSet<>();
    public static final Set<String> POSSESSIVE_PRONOUNS_2 = new HashSet<>();
    public static final Set<String> PRONOUNS = new HashSet<>();
    public static final Set<String> ARTICLES = new HashSet<>();
    public static final Set<String> PREPOSITIONS = new HashSet<>();

    public static final Set<String> TO_SKIP = new HashSet<>();
    public static final Set<String> CAUSE_OF_GAP = new HashSet<>();

    static {
        ARTICLES.add("a");
        ARTICLES.add("an");
        ARTICLES.add("the");

        PREPOSITIONS.add("on");
        PREPOSITIONS.add("upon");
        PREPOSITIONS.add("in");
        PREPOSITIONS.add("at");
        PREPOSITIONS.add("by");
        PREPOSITIONS.add("to");

        PRONOUNS.add("i");
        PRONOUNS.add("me");
        PRONOUNS.add("you");
        PRONOUNS.add("he");
        PRONOUNS.add("she");
        PRONOUNS.add("it");
        PRONOUNS.add("they");
        PRONOUNS.add("we");
        PRONOUNS.add("someone");
        PRONOUNS.add("somebody");
        PRONOUNS.add("oneself");
        PRONOUNS.add("myself");
        PRONOUNS.add("yourself");
        PRONOUNS.add("himself");
        PRONOUNS.add("herself");
        PRONOUNS.add("itself");
        PRONOUNS.add("ourselves");
        PRONOUNS.add("themselves");

        POSSESSIVE_PRONOUNS.add("my");
        POSSESSIVE_PRONOUNS.add("your");
        POSSESSIVE_PRONOUNS.add("his");
        POSSESSIVE_PRONOUNS.add("her");
        POSSESSIVE_PRONOUNS.add("its");
        POSSESSIVE_PRONOUNS.add("our");
        POSSESSIVE_PRONOUNS.add("their");
        POSSESSIVE_PRONOUNS.add("one's");

        POSSESSIVE_PRONOUNS_2.add("mine");
        POSSESSIVE_PRONOUNS_2.add("yours");
        POSSESSIVE_PRONOUNS_2.add("hers");
        POSSESSIVE_PRONOUNS_2.add("ours");
        POSSESSIVE_PRONOUNS_2.add("theirs");

        TO_SKIP.addAll(ARTICLES);
        TO_SKIP.addAll(PREPOSITIONS);
        TO_SKIP.addAll(PRONOUNS);
        TO_SKIP.addAll(POSSESSIVE_PRONOUNS);
        TO_SKIP.addAll(POSSESSIVE_PRONOUNS_2);
        TO_SKIP.add("_");

        CAUSE_OF_GAP.addAll(PRONOUNS);
        CAUSE_OF_GAP.addAll(POSSESSIVE_PRONOUNS);
        CAUSE_OF_GAP.addAll(POSSESSIVE_PRONOUNS_2);
        CAUSE_OF_GAP.add("_");
    }

    private int cntOfGapsMaxCache = 0;

    public Idiom() {
        super();
    }

    public int getMaxCntOfGaps() {
        if (cntOfGapsMaxCache <= 0) {
            cntOfGapsMaxCache = 1; // One additional gap
            for (AbstractWord word : entities) {
                if (CAUSE_OF_GAP.contains(word.getLower())) {
                    cntOfGapsMaxCache++;
                }
            }
        }
        return cntOfGapsMaxCache;
    }

    @Override
    protected List<Integer> isContainedIn(Phrase phrase, int startPos) {
        return AnalyserFacade.getIdiomToPhraseMatcher().findMatchingNumbersInPhrase(this, phrase, startPos);
    }

    @Override
    public String toString(int startPos, boolean lower) {
        String result = super.toString(startPos, lower);
        result = result.replaceAll(" _", " [word]");
        result = result.replaceAll("_ ", " [word]");

        return result;
    }
}
