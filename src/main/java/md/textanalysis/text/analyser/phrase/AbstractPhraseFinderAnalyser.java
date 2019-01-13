package md.textanalysis.text.analyser.phrase;

import md.textanalysis.ctrl.AContext;
import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.AbstractAnalyser;
import md.textanalysis.text.analyser.AnalyserFacade;
import md.textanalysis.text.element.phrase.Idiom;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.enums.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract public class AbstractPhraseFinderAnalyser<T extends Phrase> extends AbstractAnalyser {
    public final HashMap<String, List<T>> PHRASE_MAP = new HashMap<>(1000);

    abstract protected T newPhraseInstance();

    @Override
    protected void initLine(String line) {
        T phrase = createAndInitPhrase(line);
        initLine(phrase);
    }

    protected void initLine(T phrase) {
        String root = AnalyserFacade.getWordRoot(phrase.getEntities().get(0).getLower());
        if (Idiom.TO_SKIP.contains(root)) {
            if (phrase.getEntities().size() <= 1) return;
            root = AnalyserFacade.getWordRoot(phrase.getEntities().get(1).getLower());
        }
        List<T> list = PHRASE_MAP.computeIfAbsent(root, k -> new ArrayList<>());
        list.add(phrase);
    }

    protected T createAndInitPhrase(String str) {
        T phrase = newPhraseInstance();
        phrase.addAllEntities(TextAnalyserHelper.convertTxtLineToWordsArray(str));
        phrase.init();

        return phrase;
    }

    public void process(AContext context) {
        int wordPosGlobal = context.getCurWordNumber();
        String root = AnalyserFacade.getWordRoot(context.getLower());
        Phrase phraseToSearchIn = context.getCurrentPhrase();

        List<T> phrases = PHRASE_MAP.get(root);
        if (phrases == null) return;

        for (T phrase : phrases) {
            List<Integer> numbers = phraseToSearchIn.contains(phrase, wordPosGlobal - phraseToSearchIn.getOrderNumberGlobal());
            if (!numbers.isEmpty()) {
                processFoundPhrase(context, phrase, numbers);
                return;
            }
        }
    }

    protected void processFoundPhrase(AContext context, T phrase, List<Integer> numbers) {
        Phrase phraseToSearchIn = context.getCurrentPhrase();
        String found = phrase.toLowerString(0);
        context.setLower(found);
        context.setRoot(AnalyserFacade.getPhraseRoot(found));
        for (Integer i : numbers) {
            context.boldWordByGlobalNum(i + phraseToSearchIn.getOrderNumberGlobal());
            phraseToSearchIn.getEntities().get(i).setState(State.SKIPPED);
        }
    }
}
