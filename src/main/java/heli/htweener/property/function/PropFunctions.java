package heli.htweener.property.function;

import heli.htweener.tweenable.ITweenableColor;
import heli.htweener.tweenable.ITweenableShape;
import javafx.scene.paint.Color;

public class PropFunctions {
    public static final IPropFunction updateX = new IPropFunction() {
        public void updateValue(double value) {}
        public void updateValue(Object target, double value) {
            ((ITweenableShape)target).setX(value);
        }
    };

    public static final IPropFunction updateY = new IPropFunction() {
        public void updateValue(double value) {}
        public void updateValue(Object target, double value) {
            ((ITweenableShape)target).setY(value);
        }
    };

    public static final IPropFunction updateOpacity = new IPropFunction() {
        public void updateValue(double value) {}
        public void updateValue(Object target, double value) {
            ((ITweenableShape)target).setOpacity(value);
            ((ITweenableShape)target).setVisible(value != 0);
        }
    };

    public static final IPropFunction updateRotate = new IPropFunction() {
        public void updateValue(double value) {}
        public void updateValue(Object target, double value) {
            ((ITweenableShape)target).setRotate(value);
        }
    };

    public static final IPropFunction updateScaleX = new IPropFunction() {
        public void updateValue(double value) {}
        public void updateValue(Object target, double value) {
            ((ITweenableShape)target).setScaleX(value);
        }
    };

    public static final IPropFunction updateScaleY = new IPropFunction() {
        public void updateValue(double value) {}
        public void updateValue(Object target, double value) {
            ((ITweenableShape)target).setScaleY(value);
        }
    };

    public static final IPropFunction updateHeight = new IPropFunction() {
        public void updateValue(double value) {}
        public void updateValue(Object target, double value) {
            ((ITweenableShape)target).setHeight(value);
        }
    };

    public static final IPropFunction updateWidth = new IPropFunction() {
        public void updateValue(double value) {}
        public void updateValue(Object target, double value) {
            ((ITweenableShape)target).setWidth(value);
        }
    };

// -------- Common --------

    public static final IPropComFunction updateColor = new IPropComFunction() {
        public void updateValue(Object initialValue, Object targetValue, double ratio) {}
        public void updateValue(Object target, Object initialValue, Object targetValue, double ratio) {
            ((ITweenableColor)target).setColor(((Color)initialValue).interpolate((Color)targetValue, ratio));
        }
    };
}
