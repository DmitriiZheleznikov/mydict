package md.textanalysis.wordanalyse;

import md.textanalysis.AContext;
import md.textanalysis.helper.root.PhrasalVerbHelper;

public class PhrasalVerbsWordAnalyser implements IWordAnalyser {
    @Override
    public void process(AContext context) {
        String key = PhrasalVerbHelper.get(
                context.getCurPosInOriginalText(),
                context.getWordRoot(),
                context.getWordOriginal(),
                context.getTextToAnalyse().getTextRaw());
        if (key != null) {
            context.setWordRoot(key);
            context.setWordToUse(key);
        }
    }
}
