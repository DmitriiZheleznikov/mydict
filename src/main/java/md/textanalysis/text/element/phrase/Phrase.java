package md.textanalysis.text.element.phrase;

import md.textanalysis.callback.IProgressFunction;
import md.textanalysis.text.element.word.AbstractWord;

import java.util.ArrayList;
import java.util.List;

public class Phrase {
    public static final int NUM_LIMIT_DURING_CONTAINS = 30;

    protected List<AbstractWord> entities;
//    protected Phrase prev;
//    protected Phrase next;
    protected int num;

    public Phrase(int globalNum) {
        this.entities = new ArrayList<>();
        this.num = globalNum;
//        this.prev = null;
//        this.next = null;
    }

//    public Phrase(int num, Phrase prev) {
//        this.entities = new ArrayList<>();
//        this.num = num;
//        this.prev = prev;
//        this.next = null;
//        if (prev != null) prev.next = this;
//    }

    public void init() {
        init(IProgressFunction.NULL);
    }

    public void init(IProgressFunction progressFunction) {
        for (AbstractWord entity : entities) {
            entity.init();
        }
        progressFunction.step();
    }

    public int getNum() {
        return num;
    }

    public void addEntity(AbstractWord entity) {
        entities.add(entity);
    }

    public List<AbstractWord> getEntities() {
        return entities;
    }

//    public Phrase getPrev() {
//        return prev;
//    }
//
//    public Phrase getNext() {
//        return next;
//    }

    public boolean contains(Phrase phrase, int startPos) {
        return phrase.isContainedIn(this, startPos);
    }

    protected boolean isContainedIn(Phrase phrase, int startPos) {
        if (phrase.entities.size() < this.entities.size()) return false;
        int firstEntityNum = -1;
        for (int i = startPos; i < phrase.entities.size(); i++) {
            if (firstEntityNum < 0) {
                //Search for first words overlap
                if (this.entities.get(0).equals(phrase.entities.get(i))) {
                    firstEntityNum = i;
                }
            } else {
                //Check other words
                if (!this.entities.get(i-firstEntityNum).equals(phrase.entities.get(i))) {
                    return false;
                }
                if (this.entities.size() == (i-firstEntityNum+1)) return true;
            }
            if (i >= NUM_LIMIT_DURING_CONTAINS+startPos) return false;
        }

        return firstEntityNum >= 0;
    }

    public int getWordPosInText(int wordNum) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (int i = 0; i < wordNum; i++) {
            AbstractWord entity = entities.get(i);
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(entity.getSeparatorBefore());
            }
            sb.append(entity);
        }
        return sb.length() + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phrase)) return false;

        Phrase phrase = (Phrase) o;

        return entities != null ? entities.equals(phrase.entities) : phrase.entities == null;
    }

    @Override
    public int hashCode() {
        return entities != null ? entities.hashCode() : 0;
    }

    @Override
    public String toString() {
        return toString(0, false);
    }

    public String toString(int startPos, boolean lower) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        AbstractWord entityPrev = null;
        for (int i = startPos; i < entities.size(); i++) {
            AbstractWord entity = entities.get(i);
            if (isFirst) {
                isFirst = false;
            } else {
                if (entityPrev.isSeparatorAfterRequired()) sb.append(entity.getSeparatorBefore());
            }
            sb.append(lower ? entity.getLower() : entity.getOriginal());
            entityPrev = entity;
        }
        return sb.toString();
    }

    public String toLowerString(int startPos) {
        return toString(startPos, true);
    }
}
