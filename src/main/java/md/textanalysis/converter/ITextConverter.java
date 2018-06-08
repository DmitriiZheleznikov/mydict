package md.textanalysis.converter;

import java.util.List;

public interface ITextConverter<T> {
    void perform();
    List<T> getResult();
}
