package md.textanalysis.converter.state;

import md.textanalysis.converter.constant.Fb2ConverterConstants;
import md.textanalysis.text.element.ctrl.HTMLSymbol;
import md.textanalysis.text.element.ctrl.HTMLTag;

public class Fb2ConverterStateMachine {
    private enum State {NORMAL, REMOVE_TAG}

    private State state = State.NORMAL;

    private HTMLTag tag = new HTMLTag();
    private HTMLTag tagInner = new HTMLTag();
    private int tagDepthLevel = 0;

    private HTMLSymbol symbol = new HTMLSymbol();

    public HTMLSymbol getSymbol() {
        return symbol;
    }

    /**
     * @return true if word should be processed
     */
    public boolean step(String word) {
        if (state == State.NORMAL) return stepNormal(word);
        return stepRemoveTag(word);
    }

    private boolean stepNormal(String word) {
        processTag(tag, word);
        processSymbol(symbol, word);

        if (tag.isComplete()) {
            if (Fb2ConverterConstants.TAGS_TO_BE_ERASED_WITH_CONTENT.contains(tag.getNameLowerCase()) && !tag.isClose()) {
                state = State.REMOVE_TAG;
                tagDepthLevel = 1;
            } else {
                tag.clear();
                return false;
            }
        } else if (symbol.isValid()) {
            return false;
        }

        return state == State.NORMAL && tag.isEmpty() && symbol.isEmpty();
    }

    private boolean stepRemoveTag(String word) {
        processTag(tagInner, word);

        if (tagInner.isComplete()) {
            if (tagInner.getNameLowerCase().equals(tag.getNameLowerCase())) {
                if (tagInner.isOpen() && !tagInner.isClose()) tagDepthLevel++;
                else if (!tagInner.isOpen() && tagInner.isClose()) tagDepthLevel--;
            }

            if (tagDepthLevel <= 0) {
                state = State.NORMAL;
                tag.clear();
            }
            tagInner.clear();
        }

        return false;
    }

    private void processTag(HTMLTag tag, String word) {
        if (tag.isEmpty()) {
            if (isTagStart(word)) {
                tag.add(word);
            }
        } else {
            tag.add(word);
        }
    }

    private void processSymbol(HTMLSymbol symbol, String word) {
        if (symbol.isComplete() || !symbol.isValid()) {
            symbol.clear();
        }

        if (symbol.isEmpty()) {
            if (isSymbolStart(word)) {
                symbol.add(word);
            }
        } else {
            symbol.add(word);
        }
    }

    private boolean isTagStart(String word) {
        return "<".equals(word);
    }

    private boolean isSymbolStart(String word) {
        return "&".equals(word);
    }
}
