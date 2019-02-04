package md.textanalysis.text.matcher.phrase.table.action.modify;

import md.textanalysis.text.element.phrase.Idiom;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.matcher.phrase.table.PMTElement;
import md.textanalysis.text.matcher.phrase.table.PMTLine;
import md.textanalysis.text.matcher.phrase.table.PhraseMatchingTable;

/**
 * Goes by line 1 and searches matching word in line 2, not vise versa
 */
public class PMTMatchLine1ToLine2Action {
    public static void process(PhraseMatchingTable matchingTable) {
        int i1 = 0;
        int i2 = -1;

        PMTLine line1 = matchingTable.getLine1();
        PMTLine line2 = matchingTable.getLine2();

        for (i1 = 0; i1 <= line1.getMaxPos(); i1++) {
            PMTElement currentElement = line1.getElement(i1);

            if (currentElement.isSkipped()) continue;

            int i2_found = findMatchIn(line2, currentElement, i2+1);
            if (i2_found < 0) continue;
            i2 = i2_found;

            currentElement.setMatch(i2);
            line2.getElement(i2).setMatch(i1);
        }
    }

    public static int findMatchIn(PMTLine lineToSearchIn, PMTElement whatLookFor, int startFrom) {
        for (int i = startFrom; i <= lineToSearchIn.getMaxPos(); i++) {
            PMTElement element = lineToSearchIn.getElement(i);

            if (element == null) break;
            if (element.isSkipped()) continue;

            if (compareWords(whatLookFor.getWord(), lineToSearchIn.getElement(i).getWord())) {
                return i;
            }
        }

        return -1;
    }

    private static boolean compareWords(AbstractWord word1, AbstractWord word2) {
        //if ("_".equals(word1.getOriginal()) || "_".equals(word2.getOriginal())) return true;
        if (Idiom.PREPOSITIONS.contains(word1.getLower()) && Idiom.PREPOSITIONS.contains(word2.getLower())) return true;

        return word1.equals(word2);
    }
}
