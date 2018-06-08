package md.textanalysis.text.element;

import md.textanalysis.helper.TextAnalyserHelper;
import md.textanalysis.text.element.phrase.Phrase;
import md.textanalysis.text.element.word.AbstractWord;
import md.textanalysis.text.element.word.Number;
import md.textanalysis.text.element.word.Separator;
import md.textanalysis.text.element.word.Word;

public class TextElementFactory {
    public static AbstractWord create(String entity) {
        if (entity == null || entity.length() == 0) return null;

        if (TextAnalyserHelper.isWord(entity)) return new Word(entity);
        if (TextAnalyserHelper.isNumber(entity)) return new Number(entity);
        return new Separator(entity);
    }

    public static AbstractWord create(String entity, Phrase phrase) {
        if (entity == null || entity.length() == 0) return null;

        if (TextAnalyserHelper.isWord(entity)) return new Word(entity, phrase);
        if (TextAnalyserHelper.isNumber(entity)) return new Number(entity, phrase);
        return new Separator(entity, phrase);
    }
}
