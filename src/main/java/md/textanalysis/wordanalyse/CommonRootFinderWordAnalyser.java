package md.textanalysis.wordanalyse;

import md.textanalysis.AContext;
import md.textanalysis.helper.root.RootFinderHelper;

public class CommonRootFinderWordAnalyser implements IWordAnalyser {
    @Override
    public void process(AContext context) {
        String root = RootFinderHelper.get(context.getWordOriginal());
        context.setWordRoot(root);
        context.setWordToUse(context.getWordOriginal());
    }
}
