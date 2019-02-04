package md.textanalysis.text.matcher.phrase.table.action.analyse;

import md.textanalysis.text.matcher.phrase.table.PMTLine;
import md.textanalysis.text.matcher.phrase.table.PhraseMatchingTable;

public class PMTGapsAnalyser {
    public static int countGapsInLine2ByLine1(PhraseMatchingTable table) {
        PMTLine line1 = table.getLine1();
        PMTLine line2 = table.getLine2();

        int gapsCnt = 0;
        int prevMatchingNum = 0;
        int currMatchingNum = 0;
        for (int i1 = 0; i1 <= line1.getMaxPos(); i1++) {
            if (line1.getElement(i1).isSkipped()) continue;

            currMatchingNum = line1.getElement(i1).getNumMatchingElement();
            if (isThereGap(line2, prevMatchingNum, currMatchingNum)) {
                gapsCnt++;
            }
            prevMatchingNum = currMatchingNum;
        }

        return gapsCnt;
    }

    public static int calcMaxGapSize(PhraseMatchingTable table, int allowedLimit) {
        PMTLine line1 = table.getLine1();
        PMTLine line2 = table.getLine2();

        int gapsMaxSize = 0;
        int prevMatchingNum = 0;
        int currMatchingNum = 0;
        for (int i1 = 0; i1 <= line1.getMaxPos(); i1++) {
            if (line1.getElement(i1).isSkipped()) continue;

            currMatchingNum = line1.getElement(i1).getNumMatchingElement();
            if (isThereGap(line2, prevMatchingNum, currMatchingNum)) {
                int gapSize = calcGapSize(line2, prevMatchingNum, currMatchingNum, allowedLimit);
                if (gapSize > gapsMaxSize) {
                    gapsMaxSize = gapSize;
                }
            }
            prevMatchingNum = currMatchingNum;
        }

        return gapsMaxSize;
    }

    public static boolean isThereGap(PMTLine line, int posFrom, int posTo) {
        if (posFrom == posTo || posFrom == (posTo+1) || posFrom > posTo) return false;

        for (int i = posFrom; i <= posTo; i++) {
            if (line.getElement(i).isNotMatched()) return true;
        }

        return false;
    }

    public static int calcGapSize(PMTLine line, int posFrom, int posTo, int allowedLimit) {
        if (posFrom == posTo) return 0;

        int gapSize = 0;
        for (int i = posFrom; i <= posTo; i++) {
            if (line.getElement(i).isNotMatched()) gapSize++;
            if (gapSize > allowedLimit) return gapSize;
        }

        return gapSize;
    }
}
