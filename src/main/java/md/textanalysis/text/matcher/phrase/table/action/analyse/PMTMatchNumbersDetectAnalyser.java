package md.textanalysis.text.matcher.phrase.table.action.analyse;

import md.textanalysis.text.matcher.phrase.table.PMTElement;
import md.textanalysis.text.matcher.phrase.table.PMTLine;
import md.textanalysis.text.matcher.phrase.table.PhraseMatchingTable;

import java.util.ArrayList;
import java.util.List;

public class PMTMatchNumbersDetectAnalyser {
    public static List<Integer> findNumbersInLine2(PhraseMatchingTable matchingTable, int shift) {
        List<Integer> result = new ArrayList<>();

        PMTLine line1 = matchingTable.getLine1();
        PMTLine line2 = matchingTable.getLine2();

        int minPos = 9999999;
        int maxPos = -1;
        for (int i = 0; i <= line1.getMaxPos(); i++) {
            PMTElement element = line1.getElement(i);
            if (element.isMatched()) {
                int pos = element.getNumMatchingElement();
                if (pos > maxPos) maxPos = pos;
                if (pos < minPos) minPos = pos;
            }
        }

        for (int i = minPos; i <= maxPos; i++) {
            result.add(i + shift);
        }

        result = findSkipsBefore(result, line2, minPos, shift);
        result = findSkipsAfter(result, line2, maxPos, shift);

        return result;
    }

    private static List<Integer> findSkipsBefore(List<Integer> result, PMTLine line2, int minPos, int shift) {
        if (minPos <= 0) return result;

        for (int i = minPos-1; i >= 0; i--) {
            PMTElement element = line2.getElement(i);
            if (!element.isSkipped()) break;
            result.add(i + shift);
        }

        return result;
    }

    private static List<Integer> findSkipsAfter(List<Integer> result, PMTLine line2, int maxPos, int shift) {
        if (maxPos >= line2.getMaxPos()) return result;

        for (int i = maxPos+1; i <= line2.getMaxPos(); i++) {
            PMTElement element = line2.getElement(i);
            if (!element.isSkipped()) break;
            result.add(i + shift);
        }

        return result;
    }
}
