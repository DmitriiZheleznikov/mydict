package md.textanalysis;

import javafx.concurrent.Task;
import md.shape.mdcenterlist.model.MDListLineModel;
import md.textanalysis.helper.ExamplesHelper;
import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.helper.root.IrregularVerbHelper;
import md.textanalysis.helper.root.RootFinderHelper;
import md.textanalysis.helper.root.SpecialCasesHelper;

import java.io.File;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
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
    private File fileToAnalyse;
    private MyDict myDict;
    private String rawTextToAnalyse;
    private String lowerTextToAnalyse;
    private MDListLineModel firstModelLine;
    private Exception exception;

    public TextAnalyser(File fileToAnalyse, MyDict myDict) {
        this.fileToAnalyse = fileToAnalyse;
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

    @Override
    protected Void call() throws Exception {
        updateProgress(0, MAX_WORK);
        try {
            if (!fileToAnalyse.exists()) {
                throw new IllegalArgumentException("File " + fileToAnalyse.getName() + " doesn't exist");
            }

            myDict.init();
            updateProgress(1, MAX_WORK);

            List<String> rawLinesToAnalyse = Files.readAllLines(Paths.get(fileToAnalyse.getAbsolutePath()), StandardCharsets.UTF_8);
            updateProgress(2, MAX_WORK);

            rawTextToAnalyse = TextAnalyserHelper.convertToString(getFileExt(fileToAnalyse), rawLinesToAnalyse);
            updateProgress(3, MAX_WORK);

            lowerTextToAnalyse = TextAnalyserHelper.convertToLowerCase(rawTextToAnalyse);
            updateProgress(4, MAX_WORK);

            String textToAnalyse = TextAnalyserHelper.convertToLettersOnly(lowerTextToAnalyse);
            updateProgress(5, MAX_WORK);

//            textToAnalyse = TextAnalyserHelper.convertToSingleSpaces(textToAnalyse);
//            updateProgress(6, MAX_WORK);

            firstModelLine = convertToModel(textToAnalyse);
            myDict.applyFilterTo(firstModelLine);

            updateProgress(MAX_WORK, MAX_WORK);
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

    /*
       Have:
       1. lower text without delimiters (except spaces) = textToAnalyse
       2. lower original text = lowerTextToAnalyse
       3. original text = rawTextToAnalyse
       Reason:
       1. is used for tokenizer and search words
       2. is used for searching word position in the original text
       3. is used for taking example
     */
    private MDListLineModel convertToModel(String textToAnalyse) {
        StringTokenizer st = new StringTokenizer(textToAnalyse);
        double wordsCount = st.countTokens();
        double i = 0;
        Map<String, MDListLineModel> map = new HashMap<>((int)wordsCount);
        MDListLineModel lastLine = null;
        int curPosInOriginalText = 0;

        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            String key = null;
            String wordToUse = word;
            curPosInOriginalText = lowerTextToAnalyse.indexOf(word, curPosInOriginalText+1);

            if (word.length() < MIN_WORD_LENGTH) continue;

            String specialCase = SpecialCasesHelper.get(word);
            if (specialCase != null) {
                key = specialCase;
                wordToUse = specialCase;
            }

            if (key == null) {
                String irrVerbForm1 = IrregularVerbHelper.get(word);
                if (irrVerbForm1 != null) {
                    key = irrVerbForm1;
                    wordToUse = irrVerbForm1;
                }
            }

            if (key == null) {
                key = RootFinderHelper.get(word);
                wordToUse = word;
            }

            MDListLineModel line = map.get(key);
            if (line == null) {
                String example = ExamplesHelper.getExample(curPosInOriginalText, rawTextToAnalyse);
                line = new MDListLineModel(wordToUse, example, lastLine);
                lastLine = line;
                if (this.firstModelLine == null) this.firstModelLine = line;
            } else {
                if (line.getCount() < 2) {
                    String example = ExamplesHelper.getExample(curPosInOriginalText, rawTextToAnalyse);
                    line.addExample(example);
                    if (line.getWord().length() < wordToUse.length()) {
                        line.setWord(wordToUse);
                    }
                }
                line.increaseCount();
            }
            map.put(key, line);
            updateProgress((++i/wordsCount)*99 + 5, MAX_WORK);
        }

        return firstModelLine;
    }

    private String getFileExt(File file) {
        String fileName = file.getName();
        int iDot = fileName.lastIndexOf(".");
        if (iDot == -1 || iDot == fileName.length()) {
            return "txt";
        }
        return fileName.substring(iDot+1);
    }
}
