package md.textanalysis.text.element.phrase;

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

    public Idiom() {
        super();
    }

    private int calcSizeOfPhaseWithoutSKIPs(Phrase phrase) {
        if (phrase.getEntities().size() == 0) return 0;

        int size = 0;
        for (AbstractWord word : phrase.getEntities()) {
            if (!TO_SKIP.contains(word.getLower())) size++;
        }

        return size;
    }

    private boolean checkIfPhraseContainsSeparatorOnly(Phrase phrase, int startPos) {
        for (int i = startPos; i < phrase.getEntities().size(); i++) {
            if (!(phrase.getEntities().get(i) instanceof Separator)) return false;
        }

        return true;
    }

    @Override
    protected List<Integer> isContainedIn(Phrase phrase, int startPos) {
        if (calcSizeOfPhaseWithoutSKIPs(phrase) < calcSizeOfPhaseWithoutSKIPs(this)) return EMPTY_LIST_INT;

        List<Integer> numbers = new ArrayList<>();
        int idiomI = 0;
        int phraseI = startPos;

        for (int i = startPos; i < phrase.entities.size(); i++) {
            if (this.entities.size() <= idiomI) return numbers;
            if (phrase.entities.size() <= phraseI) {
                return checkIfPhraseContainsSeparatorOnly(this, idiomI) ? numbers : EMPTY_LIST_INT;
            }

            if (TO_SKIP.contains(this.entities.get(idiomI).getLower())) idiomI++;
            if (TO_SKIP.contains(phrase.entities.get(phraseI).getLower())) {
                numbers.add(phraseI);
                phraseI++;
            }

            if (this.entities.size() <= idiomI) return numbers;
            if (phrase.entities.size() <= phraseI) {
                return checkIfPhraseContainsSeparatorOnly(this, idiomI) ? numbers : EMPTY_LIST_INT;
            }

            if (!compareWords(this.entities.get(idiomI), phrase.entities.get(phraseI))) {
                return EMPTY_LIST_INT;
            }
            numbers.add(phraseI);
            if (this.entities.size() == (idiomI + 1)) return numbers;
            if (i >= NUM_LIMIT_DURING_CONTAINS + startPos) return EMPTY_LIST_INT;

            idiomI++;
            phraseI++;
        }

        return numbers;
    }

    private boolean compareWords(AbstractWord word1, AbstractWord word2) {
        if (POSSESSIVE_PRONOUNS.contains(word1.getRoot()) && POSSESSIVE_PRONOUNS.contains(word2.getRoot())) return true;
        if (POSSESSIVE_PRONOUNS_2.contains(word1.getRoot()) && POSSESSIVE_PRONOUNS_2.contains(word2.getRoot())) return true;
        if (PRONOUNS.contains(word1.getRoot()) && PRONOUNS.contains(word2.getRoot())) return true;
        if (ARTICLES.contains(word1.getRoot()) && ARTICLES.contains(word2.getRoot())) return true;
        if (PREPOSITIONS.contains(word1.getRoot()) && PREPOSITIONS.contains(word2.getRoot())) return true;

        if ("_".equals(word1.getOriginal()) || "_".equals(word2.getOriginal())) return true;

        return word1.equals(word2);
    }

    static {
        ARTICLES.add("a");
        ARTICLES.add("an");
        ARTICLES.add("the");

        TO_SKIP.addAll(ARTICLES);
        TO_SKIP.add(",");
        TO_SKIP.add(".");
        TO_SKIP.add("!");
        TO_SKIP.add(";");
        TO_SKIP.add(":");
        TO_SKIP.add("?");

        PREPOSITIONS.add("on");
        PREPOSITIONS.add("upon");
        PREPOSITIONS.add("in");
        PREPOSITIONS.add("at");

        PRONOUNS.add("i");
        PRONOUNS.add("me");
        PRONOUNS.add("you");
        PRONOUNS.add("he");
        PRONOUNS.add("she");
        PRONOUNS.add("it");
        PRONOUNS.add("they");
        PRONOUNS.add("we");

        POSSESSIVE_PRONOUNS.add("my");
        POSSESSIVE_PRONOUNS.add("your");
        POSSESSIVE_PRONOUNS.add("his");
        POSSESSIVE_PRONOUNS.add("her");
        POSSESSIVE_PRONOUNS.add("its");
        POSSESSIVE_PRONOUNS.add("our");
        POSSESSIVE_PRONOUNS.add("their");

        POSSESSIVE_PRONOUNS_2.add("mine");
        POSSESSIVE_PRONOUNS_2.add("yours");
        POSSESSIVE_PRONOUNS_2.add("hers");
        POSSESSIVE_PRONOUNS_2.add("ours");
        POSSESSIVE_PRONOUNS_2.add("theirs");
    }
}
