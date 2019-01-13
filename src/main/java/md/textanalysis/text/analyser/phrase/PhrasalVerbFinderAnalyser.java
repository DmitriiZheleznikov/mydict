package md.textanalysis.text.analyser.phrase;

import md.textanalysis.text.element.phrase.PhrasalVerb;

public class PhrasalVerbFinderAnalyser extends AbstractPhraseFinderAnalyser<PhrasalVerb> {
    @Override
    protected String iniFileName() {
        return "/resources/data/phrase/PhrasalVerbs.list";
    }

    @Override
    protected PhrasalVerb newPhraseInstance() {
        return new PhrasalVerb();
    }
}
