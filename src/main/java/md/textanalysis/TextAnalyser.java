package md.textanalysis;

import javafx.concurrent.Task;
import md.shape.mdcenterlist.model.MDListLineModel;
import md.textanalysis.ctrl.AContext;
import md.textanalysis.ctrl.MyDict;
import md.textanalysis.ctrl.TextToAnalyse;
import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.analyser.AnalyserFacade;
import md.textanalysis.text.element.word.AbstractWord;

import java.io.File;
import java.nio.charset.MalformedInputException;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides 'task' for text analysis with progress update, forming list and its filtration
 */
abstract public class TextAnalyser extends Task<Void> {
    private static final int MAX_WORK = 100;
    private MyDict myDict;
    private TextToAnalyse textToAnalyse;
    private MDListLineModel firstModelLine;
    private Exception exception;

    private double workDone;

    public TextAnalyser(File fileToAnalyse, MyDict myDict) {
        this.textToAnalyse = new TextToAnalyse(fileToAnalyse);
        this.firstModelLine = null;
        this.myDict = myDict;
    }

    abstract protected void successAction(MDListLineModel mdListLineModel);
    abstract protected void failAction(Exception exception);

    @Override
    protected void succeeded() {
        super.succeeded();
        successAction(firstModelLine);
    }

    @Override
    protected void failed() {
        super.failed();
        failAction(exception);
    }

    private void increaseProgress() {
        updateProgress(++workDone, MAX_WORK);
    }

    private void zeroProgress() {
        this.workDone = -1;
        increaseProgress();
    }

    private void finishProgress() {
        this.workDone = MAX_WORK;
        updateProgress(MAX_WORK, MAX_WORK);
    }

    @Override
    protected Void call() throws Exception {
        try {
            zeroProgress();
            init();
            prepare();

            firstModelLine = analyse();

            finishProgress();
        } catch (MalformedInputException e) {
            this.exception = new IllegalArgumentException("Encoding of file is not UTF-8", e);
            throw exception;
        } catch (Exception e) {
            e.printStackTrace();
            this.exception = e;
            throw e;
        }
        return null;
    }

    private void init() throws Exception {
        TextAnalyserHelper.init(this::increaseProgress);
        myDict.init(this::increaseProgress);
        textToAnalyse.init(this::increaseProgress);
    }

    private void prepare() throws Exception {
        textToAnalyse.prepare(this::increaseProgress);
    }

    private int calcWorkLeftInPercent() {
        int workDoneInPercent = (int)(100 * (workDone / MAX_WORK));
        int workLeftInPercent = 100 - workDoneInPercent;

        return workLeftInPercent < 0 ? 0 : workLeftInPercent;
    }

    private MDListLineModel analyse() throws Exception {
        int wordsCount = textToAnalyse.getEntities().size();
        Map<String, MDListLineModel> map = new HashMap<>(wordsCount);
        MDListLineModel lastLine = null;
        AContext context = new AContext();

        int workLeftInPercent = calcWorkLeftInPercent();
        int progressStepEach = TextAnalyserHelper.calcProgressStep(wordsCount, workLeftInPercent);

        int i = 0;
        for (AbstractWord entity : textToAnalyse.getEntities()) {
            if (!entity.isValid()) {
                TextAnalyserHelper.increaseProgress(i++, progressStepEach, this::increaseProgress);
                continue;
            }

            context.nextWord(entity);
            AnalyserFacade.findAndSetSpecialCases(context);
            AnalyserFacade.findAndSetIdiom(context);
            AnalyserFacade.findAndSetPhrasalVerb(context);

            MDListLineModel line = map.get(context.getRoot());
            if (line == null || line.getCount() < 2) {
                AnalyserFacade.findAndSetExample(context);
            }

            AnalyserFacade.beautifyWord(context);

            if (line == null) {
                line = new MDListLineModel(context.getLower(), context.getExample(), lastLine);
                lastLine = line;
                if (this.firstModelLine == null) this.firstModelLine = line;
                map.put(context.getRoot(), line);
            } else {
                if (line.getCount() < 2) {
                    line.addExample(context.getExample());
                }
                if (line.getWord().length() > context.getLower().length()) {
                    line.setWord(context.getLower());
                }
                line.increaseCount();
            }

            if (myDict.containsRoot(context.getRoot())) line.disable();

            TextAnalyserHelper.increaseProgress(i++, progressStepEach, this::increaseProgress);
        }

        return firstModelLine;
    }
}
