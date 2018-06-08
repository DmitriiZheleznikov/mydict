package md.textanalysis.text.analyse.phrase;

import md.textanalysis.ctrl.AContext;
import md.textanalysis.text.analyse.AbstractAnalyser;
import md.textanalysis.text.analyse.AnalyserFacade;
import md.textanalysis.text.element.TextElementFactory;
import md.textanalysis.text.element.phrase.PhrasalVerb;
import md.textanalysis.text.element.phrase.Phrase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhrasalVerbFinderAnalyser extends AbstractAnalyser {
    public static final HashMap<String, List<PhrasalVerb>> PH_VERB_MAP = new HashMap<>(1000);

    @Override
    protected String iniFileName() {
        return "/resources/data/PhrasalVerbs.list";
    }

    @Override
    protected void initLine(String line) {
        String[] words = line.split(" +");

        PhrasalVerb phVerb = new PhrasalVerb(0);
        for (String word : words) {
            phVerb.addEntity(TextElementFactory.create(word));
        }
        phVerb.init();

        String root = AnalyserFacade.getWordRoot(phVerb.getEntities().get(0).getLower());
        List<PhrasalVerb> list = PH_VERB_MAP.computeIfAbsent(root, k -> new ArrayList<>());
        list.add(phVerb);
    }

    public void process(AContext context) {
        int wordPosGlobal = context.getCurWordNumber();
        String root = AnalyserFacade.getWordRoot(context.getLower());
        Phrase phraseToSearchIn = context.getCurrentPhrase();

        List<PhrasalVerb> phVerbs = PH_VERB_MAP.get(root);
        if (phVerbs == null) return;

        for (PhrasalVerb phVerb : phVerbs) {
            if (phraseToSearchIn.contains(phVerb, wordPosGlobal - phraseToSearchIn.getNum())) {
                String found = phVerb.toLowerString(0);
                context.setOriginal(found);
                context.setLower(found);
                context.setRoot(AnalyserFacade.getPhraseRoot(found));
                return;
            }
        }
    }
}
