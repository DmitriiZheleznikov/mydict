package md.textanalysis.callback;

public interface IProgressFunction {
    IProgressFunction NULL = i -> {};

    void step(int i);
}
