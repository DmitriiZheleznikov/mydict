package md.textanalysis;

import javafx.concurrent.Task;
import md.shape.mdcenterlist.model.MDListLineModel;
import md.textanalysis.ctrl.AContext;
import md.textanalysis.ctrl.MyDict;
import md.textanalysis.ctrl.TextToAnalyse;
import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.analyse.AnalyserFacade;
import md.textanalysis.text.element.word.AbstractWord;

import java.io.File;
import java.nio.charset.MalformedInputException;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides 'task' for parallel text analysis with progress update, forming list and its filtration<ul>
 *     <li>read file to RAM</li>
 *     <li>convert SRT/FB2 format to TXT</li>
 *     <li>union text into 1 big string (because words can be divided into two lines)</li>
 *     <li>lower text</li>
 *     <li>remove everything except words divided by spaces</li>
 *     <li>get words (no filtration)</li>
 *     <li>union irregular verbs</li>
 *     <li>union words by impl</li>
 *     <li>find examples</li>
 *     <li>filter by MyDict</li>
 * </ul>
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

    private void addProgress(double add) {
        workDone += add;
        updateProgress(workDone, MAX_WORK);
    }

    private void addProgress(double wordsCount, double workDoneOnStart) {
        addProgress((1/wordsCount)*(MAX_WORK-workDoneOnStart));
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
            //System.out.println("zeroProgress end");

            TextAnalyserHelper.init(this::increaseProgress);
            //System.out.println("TextAnalyserHelper.init end");

            myDict.init(this::increaseProgress);
            //System.out.println("myDict.init end");

            textToAnalyse.init(this::increaseProgress);
            //System.out.println("textToAnalyse.init end");

            initAllWords();
            //System.out.println("initAllWords end");

            firstModelLine = convertToModel(textToAnalyse);
            //System.out.println("convertToModel end");

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

    private void initAllWords() {
        double workDoneOnStart = workDone;
        double wordsCount = textToAnalyse.getEntities().size();
        AbstractWord entity;
        for (int i = 0; i < textToAnalyse.getEntities().size(); i++) {
            entity = textToAnalyse.getEntities().get(i);
            entity.init();
            addProgress(wordsCount*2, workDoneOnStart);
        }
    }

    private MDListLineModel convertToModel(TextToAnalyse textToAnalyse) {
        double wordsCount = textToAnalyse.calcCountValidEntities();
        Map<String, MDListLineModel> map = new HashMap<>((int)wordsCount);
        MDListLineModel lastLine = null;
        double workDoneOnStart = workDone;
        AContext context = new AContext(textToAnalyse);

        AbstractWord entity;
        for (int i = 0; i < textToAnalyse.getEntities().size(); i++) {
            entity = textToAnalyse.getEntities().get(i);
            if (!entity.isValid()) continue;

            context.nextWord(i);
            AnalyserFacade.findAndSetSpecialCases(context);
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

            addProgress(wordsCount*2, workDoneOnStart);
        }

        return firstModelLine;
    }
}
