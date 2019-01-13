package md.textanalysis.text.converter;

import java.util.List;

public interface ITextConverter<T> {
    void perform();
    void perform(List<T> initialResultList);
    List<T> getResult();
}
