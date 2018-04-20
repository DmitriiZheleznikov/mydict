package heli.htweener.exception;

public class NotEnoughSpaceException extends ArrayIndexOutOfBoundsException {
    public NotEnoughSpaceException(int index) {
        super(index);
    }
}
