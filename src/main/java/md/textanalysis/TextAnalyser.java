package md.textanalysis;

import javafx.concurrent.Task;
import md.shape.mdcenterlist.model.MDListLineModel;
import md.textanalysis.helper.root.PhrasalVerbHelper;
import md.textanalysis.wordanalyse.WordAnalyserFacade;

import java.io.File;
import java.nio.charset.MalformedInputException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Provides 'task' for parallel text analysis with progress update, forming list and its filtration<ul>
 *     <li>read file to RAM</li>
 *     <li>convert SRT/FB2 format to TXT</li>
 *     <li>union text into 1 big string (because words can be divided into two lines)</li>
 *     <li>lower text</li>
 *     <li>remove everything except words divided by spaces</li>
 *     <li>get words (no filtration)</li>
 *     <li>union irregular verbs</li>
 *     <li>union words by root</li>
 *     <li>find examples</li>
 *     <li>filter by MyDict</li>
 * </ul>
 */
abstract public class TextAnalyser extends Task<Void> {
    private static final int MAX_WORK = 100;
    private static final int MIN_WORD_LENGTH = 3;
    private MyDict myDict;
    private TextToAnalyse textToAnalyse;
    private MDListLineModel firstModelLine;
    private Exception exception;
    private WordAnalyserFacade wordAnalyserFacade;

    private double workDone;

    public static void init() throws Exception {
        PhrasalVerbHelper.init();
    }

    public TextAnalyser(File fileToAnalyse, MyDict myDict) {
        this.textToAnalyse = new TextToAnalyse(fileToAnalyse);
        this.firstModelLine = null;
        this.myDict = myDict;
        this.wordAnalyserFacade = new WordAnalyserFacade();
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

            myDict.init(i -> increaseProgress());
            textToAnalyse.init(i -> increaseProgress());

            firstModelLine = convertToModel(textToAnalyse);
            myDict.applyFilterTo(firstModelLine);

            finishProgress();
        } catch (MalformedInputException e) {
            this.exception = new IllegalArgumentException("Encoding of file is not UTF-8", e);
            throw exception;
        } catch (Exception e) {
            //e.printStackTrace();
            this.exception = e;
            throw e;
        }
        return null;
    }

    private MDListLineModel convertToModel(TextToAnalyse textToAnalyse) {
        StringTokenizer st = new StringTokenizer(textToAnalyse.getTextLowerNoDelimiters());
        double wordsCount = st.countTokens();
        Map<String, MDListLineModel> map = new HashMap<>((int)wordsCount);
        MDListLineModel lastLine = null;
        double workDoneOnStart = workDone;
        AContext context = new AContext(textToAnalyse);

        while (st.hasMoreTokens()) {
            context.newWord(st.nextToken());

            if (context.getWordOriginal().length() < MIN_WORD_LENGTH) {
                addProgress(wordsCount, workDoneOnStart);
                continue;
            }

            wordAnalyserFacade.processSpecialCases(context);
            wordAnalyserFacade.processIrregularVerbs(context);
            wordAnalyserFacade.processPhrasalVerbs(context);
            wordAnalyserFacade.processFinderRoots(context);
            wordAnalyserFacade.processExamples(context);

            MDListLineModel line = map.get(context.getWordRoot());
            if (line == null) {
                line = new MDListLineModel(context.getWordToUse(), context.getExample(), lastLine);
                lastLine = line;
                if (this.firstModelLine == null) this.firstModelLine = line;
                map.put(context.getWordRoot(), line);
            } else {
                if (line.getCount() < 2) {
                    line.addExample(context.getExample());
                }
                if (line.getWord().length() < context.getWordToUse().length()) {
                    line.setWord(context.getWordToUse());
                }
                line.increaseCount();
            }

            addProgress(wordsCount, workDoneOnStart);
        }

        return firstModelLine;
    }
}
