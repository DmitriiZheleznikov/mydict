package md.textanalysis.wordanalyse;

import md.textanalysis.AContext;

public class WordAnalyserFacade {
    private SpecialCasesWordAnalyser specialCasesWordAnalyser;
    private IrregularVerbWordAnalyser irregularVerbWordAnalyser;
    private CommonRootFinderWordAnalyser commonRootFinderWordAnalyser;
    private ExampleFinderWordAnalyser exampleFinderWordAnalyser;
    private PhrasalVerbsWordAnalyser phrasalVerbsWordAnalyser;

    public WordAnalyserFacade() {
        specialCasesWordAnalyser = new SpecialCasesWordAnalyser();
        irregularVerbWordAnalyser = new IrregularVerbWordAnalyser();
        commonRootFinderWordAnalyser = new CommonRootFinderWordAnalyser();
        exampleFinderWordAnalyser = new ExampleFinderWordAnalyser();
        phrasalVerbsWordAnalyser = new PhrasalVerbsWordAnalyser();
    }

    public void processSpecialCases(AContext context) {
        process(context, specialCasesWordAnalyser);
    }

    public void processIrregularVerbs(AContext context) {
        process(context, irregularVerbWordAnalyser);
    }

    public void processFinderRoots(AContext context) {
        process(context, commonRootFinderWordAnalyser);
    }

    public void processExamples(AContext context) {
        exampleFinderWordAnalyser.process(context);
    }

    public void processPhrasalVerbs(AContext context) {
        phrasalVerbsWordAnalyser.process(context);
    }

    public void process(AContext context, IWordAnalyser analyser) {
        if (context.getWordRoot() == null) {
            analyser.process(context);
        }
    }
}
