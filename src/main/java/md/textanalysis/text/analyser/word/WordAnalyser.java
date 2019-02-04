package md.textanalysis.text.analyser.word;

import md.textanalysis.callback.IProgressFunction;
import md.textanalysis.ctrl.AContext;
import md.textanalysis.text.analyser.word.impl.IrregularVerbsAnalyser;
import md.textanalysis.text.analyser.word.impl.RootFinderAnalyser;
import md.textanalysis.text.analyser.word.impl.SpecialCasesAnalyser;

import java.io.IOException;
import java.net.URISyntaxException;

public class WordAnalyser {
    private SpecialCasesAnalyser specialCasesWordAnalyser = new SpecialCasesAnalyser();
    private IrregularVerbsAnalyser irregularVerbsWordAnalyser = new IrregularVerbsAnalyser();
    private RootFinderAnalyser rootFinderWordAnalyser = new RootFinderAnalyser();

    public void init() throws IOException, URISyntaxException {
        init(IProgressFunction.NULL);
    }

    public void init(IProgressFunction progressFunction) throws IOException, URISyntaxException {
        specialCasesWordAnalyser.init(progressFunction);
        irregularVerbsWordAnalyser.init(progressFunction);
        rootFinderWordAnalyser.init(progressFunction);
    }

    public String getRoot(String word) {
        String key = null;

        String wordPrepared = word.replaceAll("\"", "");
        String specialCase = specialCasesWordAnalyser.get(wordPrepared);
        if (specialCase != null) {
            key = specialCase;
        }

        if (key == null) {
            String irrVerbForm1 = irregularVerbsWordAnalyser.get(wordPrepared);
            if (irrVerbForm1 != null) {
                key = irrVerbForm1;
            }
        }

        if (key == null) {
            key = rootFinderWordAnalyser.get(wordPrepared);
        }

        return key;
    }

    public void processSpecialCases(AContext context) {
        specialCasesWordAnalyser.process(context);
    }
}
