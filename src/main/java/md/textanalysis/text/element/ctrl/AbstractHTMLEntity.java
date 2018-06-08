package md.textanalysis.text.element.ctrl;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractHTMLEntity {
    protected List<String> elements = new ArrayList<>();

    public void add(String element) {
        elements.add(element);
    }

    public void clear() {
        elements.clear();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    abstract public boolean isComplete();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : elements) {
            sb.append(s).append(" ");
        }
        return sb.toString();
    }
}
