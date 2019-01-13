package md.textanalysis.text.modifier;

import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.AbstractAnalyser;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.modifier.impl.TextSpecialCase;
import md.textanalysis.text.modifier.impl.TextSpecialCasesList;

import java.util.*;

public class TextSpecialCasesModifier extends AbstractAnalyser {
    private final Map<AbstractWord, TextSpecialCasesList> MAP = new HashMap<>();

    @Override
    protected String iniFileName() {
        return "/resources/data/word/SpecialCases.list";
    }

    @Override
    protected void initLine(String line) {
        if (!line.contains("=")) return;

        //1. Divide line into 2 parts by "=" symbol
        String lineFrom = line.substring(0, line.indexOf("=")).trim().toLowerCase();
        //String lineTo = line.substring(line.indexOf("=") + 1).trim().toLowerCase();

        if (lineFrom == null || lineFrom.length() == 0) return;

        //2. For each part call TXT converter to get correct words list
        List<AbstractWord> from = TextAnalyserHelper.convertTxtLineToWordsArray(lineFrom);
        if (from.isEmpty() || from.size() == 1) return;
        //List<AbstractWord> to = TextAnalyserHelper.convertTxtLineToWordsArray(lineTo);

        //3. Init words
        TextAnalyserHelper.initWords(from);
        //TextAnalyserHelper.initWords(to);

        //4. Collect
        TextSpecialCasesList list = MAP.computeIfAbsent(from.get(0), k -> new TextSpecialCasesList());
        list.add(from/*, to*/);
    }

    public void perform(List<AbstractWord> wordsList) {
        ListIterator<AbstractWord> it = wordsList.listIterator();
        while (it.hasNext()) {
            AbstractWord word = it.next();
            TextSpecialCasesList list = MAP.get(word);
            if (list != null) {
                process(word, it, list);
            }
        }
    }

    protected void process(AbstractWord word, ListIterator<AbstractWord> it, TextSpecialCasesList list) {
        //1. get list from original text with list.getMaxNumWords() words
        List<AbstractWord> peaceOfText = getPeaceOfOriginalText(word, it, list.getMaxNumWords());

        //2. go through all cases and find longest equal
        TextSpecialCase foundCase = null;
        for (TextSpecialCase sCase : list.getList()) {
            if (isCasePresented(peaceOfText, sCase)) {
                if (foundCase == null || foundCase.getFrom().size() < sCase.getFrom().size()) {
                    foundCase = sCase;
                }
            }
        }

        //3. if case found need to perform union
        if (foundCase != null) {
            //3.1. replace current word
            //3.2. remove other words from text
            for (int i = 1; i < foundCase.getFrom().size(); i++) {
                if (it.hasNext()) {
                    AbstractWord nxtWord = it.next();
                    word.append(nxtWord);
                } else {
                    break;
                }
                it.remove();
            }
        }
    }

    protected boolean isCasePresented(List<AbstractWord> text, TextSpecialCase sCase) {
        int i = -1;
        for (AbstractWord caseWord : sCase.getFrom()) {
            i++;
            if (text.size() < i) return false;
            if (!text.get(i).equals(caseWord)) return false;
        }

        return true;
    }

    protected List<AbstractWord> getPeaceOfOriginalText(AbstractWord word, ListIterator<AbstractWord> it, int length) {
        List<AbstractWord> originalText = new ArrayList<>(length);

        originalText.add(word);
        int i;
        for (i = 1; i < length; i++) {
            if (!it.hasNext()) break;
            AbstractWord w = it.next();
            originalText.add(w);
        }

        //Scroll 'it' back
        for (; i > 1; i--) {
            it.previous();
        }

        return originalText;
    }

    @Override
    public String toString() {
        return MAP.toString();
    }
}
