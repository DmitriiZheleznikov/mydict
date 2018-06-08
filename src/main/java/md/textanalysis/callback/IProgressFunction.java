package md.textanalysis.callback;

public interface IProgressFunction {
    IProgressFunction NULL = () -> {};

    void step();
}
