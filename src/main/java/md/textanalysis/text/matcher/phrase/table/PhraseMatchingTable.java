package md.textanalysis.text.matcher.phrase.table;

import md.textanalysis.text.element.phrase.Idiom;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.matcher.phrase.table.action.analyse.PMTGapsAnalyser;
import md.textanalysis.text.matcher.phrase.table.action.modify.PMTMatchLine1ToLine2Action;
import md.textanalysis.text.matcher.phrase.table.action.modify.PMTSkipsAction;

import java.util.Set;

/**
 * Thread unsafe
 */
public class PhraseMatchingTable {
    private PMTLine line1;
    private PMTLine line2;

    public PhraseMatchingTable() {
        this.line1 = new PMTLine();
        this.line2 = new PMTLine();
    }

    public void init(Phrase phrase1, Phrase phrase2, int startPos1, int startPos2) {
        clear();
        line1.init(phrase1, startPos1);
        line2.init(phrase2, startPos2);
    }

    public void clear() {
        line1.clear();
        line2.clear();
    }

    public PMTLine getLine1() {
        return line1;
    }

    public PMTLine getLine2() {
        return line2;
    }

    public void modifyMarkSkips(Set<String> toSkip) {
        PMTSkipsAction.process(this, Idiom.TO_SKIP);
    }

    public void modifyMatchLine1ToLine2() {//int startPos) {
        PMTMatchLine1ToLine2Action.process(this);//, startPos);
    }

    public boolean analyseAreAllWordsFoundInLine1() {
        return getLine1().analyseAreAllWordsFound();
    }

    public int analyseCountGapsInLine2ByLine1() {
        return PMTGapsAnalyser.countGapsInLine2ByLine1(this);
    }

    public int analyseCalcMaxGapSize(int allowedLimit) {
        return PMTGapsAnalyser.calcMaxGapSize(this, allowedLimit);
    }

    @Override
    public String toString() {
        return "PhraseMatchingTable{" +
                "line1=" + line1 +
                ", line2=" + line2 +
                '}';
    }
}
