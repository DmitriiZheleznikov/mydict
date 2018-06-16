package heli.component.shape.text.htext;

public class HString {
    public enum FontWeight {THIN, REGULAR, BOLD}

    protected String string;
    protected FontWeight fontWeight;

    public HString(String string, FontWeight fontWeight) {
        this.string = string;
        this.fontWeight = fontWeight;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public FontWeight getFontWeight() {
        return fontWeight;
    }

    @Override
    public String toString() {
        return getString();
    }
}
