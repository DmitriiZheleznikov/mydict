package md.textanalysis.text.analyser;

import md.textanalysis.callback.IProgressFunction;
import md.textanalysis.ctrl.AContext;
import md.textanalysis.text.analyser.phrase.ExampleFinderAnalyser;
import md.textanalysis.text.analyser.phrase.IdiomFinderAnalyser;
import md.textanalysis.text.analyser.phrase.PhrasalVerbFinderAnalyser;
import md.textanalysis.text.analyser.phrase.PhraseRootFinderAnalyser;
import md.textanalysis.text.analyser.word.WordAnalyser;
import md.textanalysis.text.analyser.word.impl.BeautifyAnalyser;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.matcher.phrase.IdiomToPhraseMatcher;
import md.textanalysis.text.modifier.TextSpecialCasesModifier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class AnalyserFacade {
    public static final ThreadLocal<WordAnalyser> WORD_ANALYSER = ThreadLocal.withInitial(WordAnalyser::new);
    public static final ThreadLocal<ExampleFinderAnalyser> EXAMPLE_FINDER = ThreadLocal.withInitial(ExampleFinderAnalyser::new);
    public static final ThreadLocal<PhrasalVerbFinderAnalyser> PH_VERB_FINDER = ThreadLocal.withInitial(PhrasalVerbFinderAnalyser::new);
    public static final ThreadLocal<IdiomFinderAnalyser> IDIOM_FINDER = ThreadLocal.withInitial(IdiomFinderAnalyser::new);
    public static final ThreadLocal<PhraseRootFinderAnalyser> PH_ROOT_FINDER = ThreadLocal.withInitial(PhraseRootFinderAnalyser::new);
    public static final ThreadLocal<BeautifyAnalyser> BEAUTIFY_ANALYSER = ThreadLocal.withInitial(BeautifyAnalyser::new);
    public static final ThreadLocal<TextSpecialCasesModifier> TEXT_SPECIAL_CASES_MODIFIER = ThreadLocal.withInitial(TextSpecialCasesModifier::new);
    public static final ThreadLocal<IdiomToPhraseMatcher> IDIOM_TO_PHRASE_MATCHER = ThreadLocal.withInitial(IdiomToPhraseMatcher::new);

    public static void init() throws IOException, URISyntaxException {
        init(IProgressFunction.NULL);
    }

    public static void init(IProgressFunction progressFunction) throws IOException, URISyntaxException {
        getWordAnalyser().init(progressFunction);
        getExampleFinder().init(progressFunction);
        getPhrasalVerbFinder().init(progressFunction);
        getIdiomFinder().init(progressFunction);
        getBeautifyAnalyser().init(progressFunction);
        getTextSpecialCasesModifier().init(progressFunction);
        getIdiomToPhraseMatcher().init(progressFunction);
    }

    public static WordAnalyser getWordAnalyser() {
        return WORD_ANALYSER.get();
    }

    public static ExampleFinderAnalyser getExampleFinder() {
        return EXAMPLE_FINDER.get();
    }

    public static PhrasalVerbFinderAnalyser getPhrasalVerbFinder() {
        return PH_VERB_FINDER.get();
    }

    public static IdiomFinderAnalyser getIdiomFinder() {
        return IDIOM_FINDER.get();
    }

    public static PhraseRootFinderAnalyser getPhraseRootFinder() {
        return PH_ROOT_FINDER.get();
    }

    public static BeautifyAnalyser getBeautifyAnalyser() {
        return BEAUTIFY_ANALYSER.get();
    }

    public static TextSpecialCasesModifier getTextSpecialCasesModifier() {
        return TEXT_SPECIAL_CASES_MODIFIER.get();
    }

    public static IdiomToPhraseMatcher getIdiomToPhraseMatcher() {
        return IDIOM_TO_PHRASE_MATCHER.get();
    }

    public static String getWordRoot(String word) {
        return getWordAnalyser().getRoot(word);
    }

    public static String getPhraseRoot(String word) {
        return getPhraseRootFinder().getRoot(word);
    }

    public static void findAndSetExample(AContext context) {
        getExampleFinder().process(context);
    }

    public static void findAndSetPhrasalVerb(AContext context) {
        getPhrasalVerbFinder().process(context);
    }

    public static void findAndSetIdiom(AContext context) {
        getIdiomFinder().process(context);
    }

    public static void findAndSetSpecialCases(AContext context) {
        getWordAnalyser().processSpecialCases(context);
    }

    public static void beautifyWord(AContext context) {
        getBeautifyAnalyser().process(context);
    }

    public static void modifyTextSpecialCases(List<AbstractWord> wordsList) {
        getTextSpecialCasesModifier().perform(wordsList);
    }
}
