package md.shape.mdcenterlist.model;

import heli.component.shape.list.centerlist.model.CListLineModel;
import heli.component.shape.text.htext.HStringFlow;

import java.util.ArrayList;

public class MDListLineModel extends CListLineModel {
    private int count;
    private ArrayList<HStringFlow> examples;

    public MDListLineModel(String word, HStringFlow example, MDListLineModel prev) {
        super(word, prev);
        this.count = 1;
        this.examples = new ArrayList<>();
        addExample(example);
    }

    public String getWord() {
        return text;
    }

    public void setWord(String value) {
        this.text = value;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<HStringFlow> getExamples() {
        return examples;
    }

    public HStringFlow getExampleSafe(int n) {
        return getExamples().size() > n ? getExamples().get(n) : null;
    }

    public void increaseCount() {
        count++;
    }

    public void addExample(HStringFlow example) {
        if (example != null) examples.add(example);
    }

    public void addExample(HStringFlow example, int count) {
        if (examples.size() < count) {
            addExample(example);
        }
    }
}
