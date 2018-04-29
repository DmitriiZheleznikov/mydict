package md.textanalysis.wordanalyse;

import md.textanalysis.AContext;
import md.textanalysis.helper.root.IrregularVerbHelper;

public class IrregularVerbWordAnalyser implements IWordAnalyser {
    @Override
    public void process(AContext context) {
        String irrVerbForm1 = IrregularVerbHelper.get(context.getWordOriginal());
        if (irrVerbForm1 != null) {
            context.setWordRoot(irrVerbForm1);
            context.setWordToUse(irrVerbForm1);
        }
    }
}
