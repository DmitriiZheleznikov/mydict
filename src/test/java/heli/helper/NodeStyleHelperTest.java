package heli.helper;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeStyleHelperTest {

    @Test
    public void addProperty() {
        String expected = "-some-prop;-test-prop:#8fbc8f;";
        String actual = NodeStyleHelper.addProperty("-test-prop:#000000;-some-prop;", "-test-prop", Color.DARKSEAGREEN);
        assertEquals(expected, actual);
    }

    @Test
    public void removeProperty() {
        String actual;
        String expected;

        actual = NodeStyleHelper.removeProperty(null, "-test-prop");
        expected = null;
        assertEquals(expected, actual);

        actual = NodeStyleHelper.removeProperty("", "-test-prop");
        expected = "";
        assertEquals(expected, actual);

        actual = NodeStyleHelper.removeProperty(" ", "-test-prop");
        expected = " ";
        assertEquals(expected, actual);

        actual = NodeStyleHelper.removeProperty("-some-prop;", "-test-prop");
        expected = "-some-prop;";
        assertEquals(expected, actual);

        actual = NodeStyleHelper.removeProperty("-test-prop: wwew;-some-prop;", "-test-prop");
        expected = "-some-prop;";
        assertEquals(expected, actual);

        actual = NodeStyleHelper.removeProperty("-some-prop-beg;-test-prop: wwew;-some-prop;", "-test-prop");
        expected = "-some-prop-beg;-some-prop;";
        assertEquals(expected, actual);

        actual = NodeStyleHelper.removeProperty("-some-prop;-test-prop: wwew;", "-test-prop");
        expected = "-some-prop;";
        assertEquals(expected, actual);
    }

}