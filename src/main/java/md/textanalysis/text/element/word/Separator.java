package md.textanalysis.text.element.word;

import md.textanalysis.text.element.word.enums.State;

import java.util.HashSet;
import java.util.Set;

public class Separator extends AbstractWord {
    private static final Set<String> BREAKABLE = new HashSet<>();
    private static final Set<String> REQUIRED_SPACE_BEFORE = new HashSet<>();
    private static final Set<String> NOT_REQUIRED_SPACE_AFTER = new HashSet<>();
    static {
        BREAKABLE.add(".");
        //BREAKABLE.add(",");
        BREAKABLE.add("!");
        BREAKABLE.add("?");
        BREAKABLE.add(":");
        BREAKABLE.add(";");
        REQUIRED_SPACE_BEFORE.add("&");
        REQUIRED_SPACE_BEFORE.add("(");
        REQUIRED_SPACE_BEFORE.add("‘");
        NOT_REQUIRED_SPACE_AFTER.add("(");
        NOT_REQUIRED_SPACE_AFTER.add(")");
        NOT_REQUIRED_SPACE_AFTER.add("‘");
    }

    public Separator(String original) {
        super(original);
        this.state = State.SKIPPED;
    }

//    public Separator(String original, Phrase phrase) {
//        super(original, phrase);
//        this.state = State.SKIPPED;
//    }

    @Override
    public String getSeparatorBefore() {
        if (REQUIRED_SPACE_BEFORE.contains(original)) return " ";
        return "";
    }

    @Override
    public boolean isSeparatorAfterRequired() {
        return !NOT_REQUIRED_SPACE_AFTER.contains(original);
    }

    @Override
    public boolean isPhraseBreak() {
        return BREAKABLE.contains(original);
    }

    protected AbstractWord cloneOnlyOriginal() {
        return new Separator(this.original);
    }
}
