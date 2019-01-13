package md.textanalysis.text.analyser.phrase;

import md.textanalysis.ctrl.AContext;
import md.textanalysis.text.element.phrase.Idiom;

import java.util.HashMap;
import java.util.List;

public class IdiomFinderAnalyser extends AbstractPhraseFinderAnalyser<Idiom> {
    public final HashMap<Idiom, Idiom> SPECIAL_CASES_MAP = new HashMap<>(100);

    @Override
    protected String iniFileName() {
        return "/resources/data/phrase/Idioms.list";
    }

    @Override
    protected Idiom newPhraseInstance() {
        return new Idiom();
    }

    @Override
    protected void initLine(String line) {
        boolean isSpecialCase = false;
        String idiomLine = line;

        if (line.contains("==")) {
            isSpecialCase = true;
            idiomLine = line.substring(0, line.indexOf("=="));
        }

        Idiom idiom = createAndInitPhrase(idiomLine);
        if (isSpecialCase) {
            Idiom idiomEq = createAndInitPhrase(line.substring(line.indexOf("==") + 2).trim());
            SPECIAL_CASES_MAP.put(idiom, idiomEq);
        }

        initLine(idiom);
    }

    @Override
    protected void processFoundPhrase(AContext context, Idiom phrase, List<Integer> numbers) {
        Idiom idiomEq = SPECIAL_CASES_MAP.get(phrase);
        super.processFoundPhrase(context, (idiomEq == null ? phrase : idiomEq), numbers);
    }
}
