package md.textanalysis.text.modifier.impl;

import md.textanalysis.text.element.word.AbstractWord;

import java.util.ArrayList;
import java.util.List;

public class TextSpecialCasesList {
    private List<TextSpecialCase> list = new ArrayList<>();
    private int maxNumWords = 0;

    public void add(List<AbstractWord> from/*, List<AbstractWord> to*/) {
        list.add(new TextSpecialCase(from/*, to*/));
        if (maxNumWords < from.size()) maxNumWords = from.size();
    }

    public List<TextSpecialCase> getList() {
        return list;
    }

    public int getMaxNumWords() {
        return maxNumWords;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
