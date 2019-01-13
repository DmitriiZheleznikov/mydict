package md.textanalysis.text.element.phrase;

import heli.component.shape.text.htext.HString;
import heli.component.shape.text.htext.HStringFlow;
import md.textanalysis.callback.IProgressFunction;
import md.textanalysis.text.element.word.AbstractWord;

import java.util.*;

public class Phrase {
    public static final int NUM_LIMIT_DURING_CONTAINS = 30;
    public static final List<Integer> EMPTY_LIST_INT = Collections.emptyList();

    protected List<AbstractWord> entities;
    //protected int orderNumberGlobal;

    public Phrase() {//int orderNumberGlobal) {
        this.entities = new ArrayList<>();
        //this.orderNumberGlobal = orderNumberGlobal;
    }

    public void init() {
        init(IProgressFunction.NULL);
    }

    public void init(IProgressFunction progressFunction) {
        for (AbstractWord entity : entities) {
            entity.init();
        }
        progressFunction.step();
    }

    public int getOrderNumberGlobal() {
        return entities.get(0).getOrderNumber();
    }

    public void addEntity(AbstractWord entity) {
        entities.add(entity);
    }

    public void addAllEntities(Collection<AbstractWord> entities) {
        if (entities == null) return;

        for (AbstractWord entity : entities) {
            addEntity(entity);
        }
    }

    public List<AbstractWord> getEntities() {
        return entities;
    }

    public List<Integer> contains(Phrase phrase, int startPos) {
        return phrase.isContainedIn(this, startPos);
    }

    protected List<Integer> isContainedIn(Phrase phrase, int startPos) {
        if (phrase.entities.size() < this.entities.size()) return EMPTY_LIST_INT;

        int firstEntityNum = -1;
        List<Integer> numbers = new ArrayList<>();
        for (int i = startPos; i < phrase.entities.size(); i++) {
            if (firstEntityNum < 0) {
                //Search for first words overlap
                if (this.entities.get(0).equals(phrase.entities.get(i))) {
                    numbers.add(i);
                    firstEntityNum = i;
                }
            } else {
                //Check other words
                if (!this.entities.get(i-firstEntityNum).equals(phrase.entities.get(i))) {
                    return EMPTY_LIST_INT;
                }
                numbers.add(i);
                if (this.entities.size() == (i-firstEntityNum+1)) return numbers;
            }
            if (i >= NUM_LIMIT_DURING_CONTAINS+startPos) return EMPTY_LIST_INT;
        }

        return firstEntityNum >= 0 ? numbers : EMPTY_LIST_INT;
    }

    public int countLenLeftOf(int wordOrderNumberGlobal) {
        int localNum = wordOrderNumberGlobal - getOrderNumberGlobal();
        if (localNum == 0) return 0;

        int l = 0;
        boolean isFirst = true;
        AbstractWord entityPrev = null;
        for (int i = 0; i < entities.size(); i++) {
            AbstractWord entity = entities.get(i);

            if (isFirst) {
                isFirst = false;
            } else {
                if (entityPrev.isSeparatorAfterRequired()) l += entity.getSeparatorBefore().length();
            }

            if (i == localNum) break;

            l += entity.getOriginal().length();
            entityPrev = entity;
        }
        return l;
    }

    public int countLenRightOf(int wordOrderNumberGlobal) {
        int localNum = wordOrderNumberGlobal - getOrderNumberGlobal();
        if (localNum == (entities.size()-1)) return 0;

        int l = 0;
        AbstractWord entityPrev = entities.get(localNum);
        for (int i = localNum+1; i < entities.size(); i++) {
            AbstractWord entity = entities.get(i);

            if (entityPrev.isSeparatorAfterRequired()) l += entity.getSeparatorBefore().length();

            l += entity.getOriginal().length();
            entityPrev = entity;
        }
        return l;
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

    public HStringFlow toStringFlow(Set<Integer> globalNumbersBold) {
        HStringFlow flow = new HStringFlow();
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        boolean isBold = false;
        boolean flush = false;
        AbstractWord entityPrev = null;

        for (int i = 0; i < entities.size(); i++) {
            flush = false;
            AbstractWord entity = entities.get(i);
            if (isFirst) {
                isFirst = false;
            } else {
                if (entityPrev.isSeparatorAfterRequired()) sb.append(entity.getSeparatorBefore());
            }

            boolean isBoldNew = globalNumbersBold.contains(i + getOrderNumberGlobal());
            if (isBold != isBoldNew) flush = true;

            if (flush && sb.length() > 0) {
                flow.add(new HString(sb.toString(), isBold ? HString.FontWeight.BOLD : HString.FontWeight.REGULAR));
                sb = new StringBuilder();
            }
            isBold = isBoldNew;

            sb.append(entity.getOriginal());
            entityPrev = entity;
        }

        flow.add(new HString(sb.toString(), isBold ? HString.FontWeight.BOLD : HString.FontWeight.REGULAR));

        return flow;
    }
}
