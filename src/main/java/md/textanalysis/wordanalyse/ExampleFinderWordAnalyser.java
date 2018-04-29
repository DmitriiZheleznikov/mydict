package md.textanalysis.wordanalyse;

import md.textanalysis.AContext;
import md.textanalysis.helper.ExamplesHelper;

public class ExampleFinderWordAnalyser implements IWordAnalyser {
    @Override
    public void process(AContext context) {
        context.setExample(ExamplesHelper.getExample(context.getCurPosInOriginalText(), context.getTextToAnalyse().getTextRaw()));
    }
}
