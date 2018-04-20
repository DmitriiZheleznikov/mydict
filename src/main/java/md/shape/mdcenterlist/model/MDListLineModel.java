package md.shape.mdcenterlist.model;

import heli.component.shape.list.centerlist.model.CListLineModel;

import java.util.ArrayList;

public class MDListLineModel extends CListLineModel {
    private int count;
    private ArrayList<String> examples;

    public MDListLineModel(String word, String example, MDListLineModel prev) {
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

    public ArrayList<String> getExamples() {
        return examples;
    }

    public String getExampleSafe(int n) {
        return getExamples().size() > n ? getExamples().get(n) : null;
    }

    public void increaseCount() {
        count++;
    }

    public void addExample(String example) {
        if (example != null) examples.add(example);
    }

    public void addExample(String example, int count) {
        if (examples.size() < count) {
            addExample(example);
        }
    }
}
