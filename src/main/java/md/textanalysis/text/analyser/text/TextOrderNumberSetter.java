package md.textanalysis.text.analyser.text;

import md.textanalysis.text.element.word.AbstractWord;

import java.util.List;

public class TextOrderNumberSetter {
    public void perform(List<AbstractWord> wordsList) {
        int orderNumber = 0;
        for (AbstractWord word : wordsList) {
            word.setOrderNumber(orderNumber++);
        }
    }
}
