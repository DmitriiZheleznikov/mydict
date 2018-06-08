package md.textanalysis.utils;

import java.util.Collection;
import java.util.TreeSet;

public class PhrasesTreeSet extends TreeSet<String> {
    public PhrasesTreeSet() {
        super(new PhrasesComparator());
    }

    public PhrasesTreeSet(Collection<String> c) {
        super(new PhrasesComparator());
        addAll(c);
    }
}
