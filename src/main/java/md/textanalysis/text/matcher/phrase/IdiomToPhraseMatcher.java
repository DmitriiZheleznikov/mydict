package md.textanalysis.text.matcher.phrase;

import md.textanalysis.callback.IProgressFunction;
import md.textanalysis.text.element.phrase.Idiom;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.matcher.phrase.table.PhraseMatchingTable;
import md.textanalysis.text.matcher.phrase.table.action.analyse.PMTMatchNumbersDetectAnalyser;

import java.util.List;


/**
 * Thread unsafe
 */
public class IdiomToPhraseMatcher {
    public static final int ALLOWED_GAP_SIZE = 3;

    PhraseMatchingTable matchingTable;

    public IdiomToPhraseMatcher() {
    }

    public void init(IProgressFunction progressFunction) {
        this.matchingTable = new PhraseMatchingTable();
        progressFunction.step();
    }

    public List<Integer> findMatchingNumbersInPhrase(Idiom idiom, Phrase phrase, int startPhrasePos) {
        initTable(idiom, phrase, startPhrasePos);

        //Analyse table results
        boolean areAllWordsFound = matchingTable.analyseAreAllWordsFoundInLine1();
        if (!areAllWordsFound) return Phrase.EMPTY_LIST_INT;

        int cntGaps = matchingTable.analyseCountGapsInLine2ByLine1();
        if (cntGaps <= 0) return PMTMatchNumbersDetectAnalyser.findNumbersInLine2(matchingTable, startPhrasePos);
        if (cntGaps > idiom.getMaxCntOfGaps()) return Phrase.EMPTY_LIST_INT;

        int maxGapSize = matchingTable.analyseCalcMaxGapSize(ALLOWED_GAP_SIZE);
        if (maxGapSize > ALLOWED_GAP_SIZE) return Phrase.EMPTY_LIST_INT;

        return PMTMatchNumbersDetectAnalyser.findNumbersInLine2(matchingTable, startPhrasePos);
    }

    private void initTable(Idiom idiom, Phrase phrase, int startPhrasePos) {
        matchingTable.init(idiom, phrase, 0, startPhrasePos);
        matchingTable.modifyMarkSkips(Idiom.TO_SKIP);
        matchingTable.modifyMatchLine1ToLine2();
    }
}
