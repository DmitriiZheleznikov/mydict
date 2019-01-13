package md.textanalysis.text.element.phrase;

import md.textanalysis.text.element.word.AbstractWord;

import java.util.ArrayList;
import java.util.List;

public class PhrasalVerb extends Phrase {
    public static final int NUM_WORDS_IN_BETWEEN = 3;

    public PhrasalVerb() {//int pos) {
        super();
    }

    @Override
    protected List<Integer> isContainedIn(Phrase phrase, int startPos) {
        if (phrase.entities.size() < this.entities.size()) return EMPTY_LIST_INT;

        AbstractWord verb = entities.get(0);

        List<Integer> numbers = new ArrayList<>();
        boolean isVerbFound = false;
        int i = startPos;
        int foundWordsCount = 0;
        for (AbstractWord thisEntity : this.entities) {
            for (; i < phrase.entities.size(); i++) {
                AbstractWord thatEntity = phrase.entities.get(i);

                //Same verb has been found again
                //(this should be an any verb, but it requires a dictionary... may be later,
                // for now I'll add limitation of 3 extra words in between verb and preposition)
                if (isVerbFound && thatEntity.equals(verb)) return EMPTY_LIST_INT;

                //Next word found
                if (thatEntity.equals(thisEntity)) {
                    numbers.add(i);
                    isVerbFound = true;
                    i++;
                    foundWordsCount++;
                    break;
                }

                //Limitation should cover 90% of cases (I hope it's so :) )
                if (i >= NUM_WORDS_IN_BETWEEN+startPos+this.entities.size()) {
                    return EMPTY_LIST_INT;
                }
            }
        }

        return foundWordsCount == this.entities.size() ? numbers : EMPTY_LIST_INT;
    }
}
