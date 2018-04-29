package md.textanalysis.wordanalyse;

import md.textanalysis.AContext;
import md.textanalysis.helper.root.SpecialCasesHelper;

public class SpecialCasesWordAnalyser implements IWordAnalyser {
    @Override
    public void process(AContext context) {
        String specialCase = SpecialCasesHelper.get(context.getWordOriginal());
        if (specialCase != null) {
            context.setWordRoot(specialCase);
            context.setWordToUse(specialCase);
        }
    }
}
