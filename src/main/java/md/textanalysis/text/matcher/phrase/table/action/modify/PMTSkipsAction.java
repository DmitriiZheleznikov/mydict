package md.textanalysis.text.matcher.phrase.table.action.modify;

import md.textanalysis.text.element.word.Word;
import md.textanalysis.text.matcher.phrase.table.PMTElement;
import md.textanalysis.text.matcher.phrase.table.PMTLine;
import md.textanalysis.text.matcher.phrase.table.PhraseMatchingTable;

import java.util.Set;

public class PMTSkipsAction {
    public static void process(PhraseMatchingTable matchingTable, Set<String> toSkip) {
        processLine(matchingTable.getLine1(), toSkip);
        processLine(matchingTable.getLine2(), toSkip);
    }

    private static void processLine(PMTLine line, Set<String> toSkip) {
        for (int i = 0; i <= line.getMaxPos(); i++) {
            PMTElement element = line.getElement(i);

            if (!(element.getWord() instanceof Word)) {
                element.setSkip();
                continue;
            }

            if (toSkip.contains(element.getWord().getLower())) {
                element.setSkip();
                //continue;
            }
        }
    }
}
