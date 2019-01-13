package md.textanalysis.text.analyser.phrase;

import heli.component.shape.text.htext.HString;
import heli.component.shape.text.htext.HStringFlow;
import md.textanalysis.ctrl.AContext;
import md.textanalysis.text.AbstractAnalyser;
import md.textanalysis.text.element.phrase.Phrase;

import java.util.Collections;
import java.util.Set;

public class ExampleFinderAnalyser extends AbstractAnalyser {
    private static final int COUNT_SYMBOLS_SIDE = 125;
    private static final int MAX_SYMBOLS = COUNT_SYMBOLS_SIDE*2;

    @Override
    protected String iniFileName() {
        return null;
    }

    @Override
    protected void initLine(String line) {

    }

    public void process(AContext context) {
        Phrase phrase = context.getCurrentPhrase();
        HStringFlow flow = phrase.toStringFlow(context.getWNumBold());
        if (!checkLength(flow)) {
            cutLeft(flow, phrase, context.getWNumBold());
            cutRight(flow, phrase, context.getWNumBold());
        }
        context.setExample(flow);
    }

    private boolean checkLength(HStringFlow flow) {
        return flow == null || flow.getStringLength() <= MAX_SYMBOLS;
    }

    private void cutLeft(HStringFlow flow, Phrase phrase, Set<Integer> wNums) {
        int l = phrase.countLenLeftOf(Collections.min(wNums));
        if (l <= COUNT_SYMBOLS_SIDE) return;

        flow.cutLeft(l-COUNT_SYMBOLS_SIDE);
        HString str = flow.getData().get(0);
        str.setString("..." + str.getString());
    }

    private void cutRight(HStringFlow flow, Phrase phrase, Set<Integer> wNums) {
        int l = phrase.countLenRightOf(Collections.max(wNums));
        if (l <= COUNT_SYMBOLS_SIDE) return;

        flow.cutRight(l-COUNT_SYMBOLS_SIDE);
        HString str = flow.getData().get(flow.getData().size()-1);
        str.setString(str.getString() + "...");
    }

}
