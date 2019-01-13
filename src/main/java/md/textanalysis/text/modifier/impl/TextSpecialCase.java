package md.textanalysis.text.modifier.impl;

import md.textanalysis.text.element.word.AbstractWord;

import java.util.List;

public class TextSpecialCase {
    private List<AbstractWord> from;
    //private List<AbstractWord> to;

    public TextSpecialCase(List<AbstractWord> from/*, List<AbstractWord> to*/) {
        this.from = from;
        //this.to = to;
    }

    public List<AbstractWord> getFrom() {
        return from;
    }

//    public List<AbstractWord> getTo() {
//        return to;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (AbstractWord w : from) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(" ");
            }
            sb.append(w.getOriginal());
        }

        return sb.toString();
    }
}
