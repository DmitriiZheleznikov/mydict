package heli.htweener.tween;

import heli.htweener.constant.Status;
import heli.htweener.property.list.TweenPropComList;
import heli.htweener.property.list.TweenPropList;
import heli.htweener.property.function.IPropComFunction;
import heli.htweener.property.function.IPropFunction;
import heli.htweener.tweenable.ITweenableColor;
import heli.htweener.tweenable.ITweenableShape;
import heli.htweener.Ease;
import heli.htweener.ease.IEaseFunction;
import heli.htweener.property.function.PropFunctions;
import javafx.scene.paint.Color;

import java.util.Map;

import static heli.htweener.constant.Status.ACTIVE;
import static heli.htweener.constant.Status.INACTIVE;
import static heli.htweener.constant.Status.MARK_INTERRUPT;

/**
 * Set of steps in animation of 1 object. Consists from reference ot object, properties and functions that should be applied to object in time
 */
public class HTween {
    protected String name = null;
    /** Current position [0, duration] in ms */
    protected double pos;
    /** duration in ms, (input duration in seconds divided to 1000) */
    protected double duration;
    /*Ease function*/
    public IEaseFunction ease;
    /** List of properties */
    protected TweenPropList properties;
    protected TweenPropComList propComplList;
    /** Target object */
    protected Object targetObject;

    protected ICallable onComplete = null;
    protected ICallable onInterrupt = null;

    protected Status status = INACTIVE;

    public HTween(int maxNumberOfProps) {
        properties = new TweenPropList(maxNumberOfProps);
        propComplList = new TweenPropComList(maxNumberOfProps);
    }

    public boolean isActive() {
        return status == ACTIVE;
    }

    public boolean isInactive() {
        return status == INACTIVE;
    }

    public HTween init(Object targetObject, int duration, IEaseFunction ease, String name) {
        this.targetObject = targetObject;
        this.duration = duration;
        this.pos = 0;
        this.ease = ease == null ? Ease.linear : ease;
        this.status = ACTIVE;
        this.name = name;

        return this;
    }

    public boolean step(double dt) {
        if (status == MARK_INTERRUPT) return true;

        pos += dt;
        double ratio = pos / duration;
        ratio = ratio > 1 ? 1 : ratio;
        double ratioEased = ease.transform(ratio);

        properties.updateValue(targetObject, ratioEased);
        propComplList.updateValue(targetObject, ratioEased);

        return ratio == 1;
    }

    public boolean isMarkedInterrupt() {
        return status == MARK_INTERRUPT;
    }

    public void markInterrupt() {
        status = MARK_INTERRUPT;
    }

    public ICallable completeTween() {
        ICallable onCompleteSave = onComplete;
        dispose();
        return onCompleteSave;
    }

    public ICallable interruptTween() {
        ICallable onInterruptSave = onInterrupt;
        dispose();
        return onInterruptSave;
    }

    public HTween onComplete(ICallable onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public HTween onInterrupt(ICallable onInterrupt) {
        this.onInterrupt = onInterrupt;
        return this;
    }

    // ------------------

    public HTween prop(double init, double target, IPropFunction updateValueFunction) {
        properties.add(init, target, updateValueFunction);
        return this;
    }

    public HTween prop(Object init, Object target, IPropComFunction updateValueFunction) {
        propComplList.add(init, target, updateValueFunction);
        return this;
    }

    public HTween onUpdate(IPropFunction updateValueFunction) {
        properties.add(0, 1, updateValueFunction);
        return this;
    }

    public HTween x(double x) {
        properties.add(((ITweenableShape)targetObject).getX(), x, PropFunctions.updateX);
        return this;
    }

    public HTween y(double y) {
        properties.add(((ITweenableShape)targetObject).getY(), y, PropFunctions.updateY);
        return this;
    }

    public HTween xy(double x, double y) {
        properties.add(((ITweenableShape)targetObject).getX(), x, PropFunctions.updateX);
        properties.add(((ITweenableShape)targetObject).getY(), y, PropFunctions.updateY);
        return this;
    }

    public HTween h(double h) {
        properties.add(((ITweenableShape)targetObject).getHeight(), h, PropFunctions.updateHeight);
        return this;
    }

    public HTween w(double w) {
        properties.add(((ITweenableShape)targetObject).getWidth(), w, PropFunctions.updateWidth);
        return this;
    }

    public HTween hw(double h, double w) {
        properties.add(((ITweenableShape)targetObject).getHeight(), h, PropFunctions.updateHeight);
        properties.add(((ITweenableShape)targetObject).getWidth(), w, PropFunctions.updateWidth);
        return this;
    }

    public HTween o(double alpha) {
        properties.add(((ITweenableShape)targetObject).getOpacity(), alpha, PropFunctions.updateOpacity);
        return this;
    }

    public HTween r(double rotateAngel) {
        properties.add(((ITweenableShape)targetObject).getRotate(), rotateAngel, PropFunctions.updateRotate);
        return this;
    }

    public HTween sX(double sx) {
        properties.add(((ITweenableShape)targetObject).getScaleX(), sx, PropFunctions.updateScaleX);
        return this;
    }

    public HTween sY(double sy) {
        properties.add(((ITweenableShape)targetObject).getScaleY(), sy, PropFunctions.updateScaleY);
        return this;
    }

    public HTween s(double s) {
        properties.add(((ITweenableShape)targetObject).getScaleX(), s, PropFunctions.updateScaleX);
        properties.add(((ITweenableShape)targetObject).getScaleY(), s, PropFunctions.updateScaleY);
        return this;
    }

    public HTween color(Color color) {
        propComplList.add(((ITweenableColor)targetObject).getColor(), color, PropFunctions.updateColor);
        return this;
    }

    public HTween addX(double x) {
        properties.add(((ITweenableShape)targetObject).getX(), ((ITweenableShape)targetObject).getX() + x, PropFunctions.updateX);
        return this;
    }

    public HTween addY(double y) {
        properties.add(((ITweenableShape)targetObject).getY(), ((ITweenableShape)targetObject).getY() + y, PropFunctions.updateY);
        return this;
    }

    public HTween addXY(double x, double y) {
        properties.add(((ITweenableShape)targetObject).getX(), ((ITweenableShape)targetObject).getX() + x, PropFunctions.updateX);
        properties.add(((ITweenableShape)targetObject).getY(), ((ITweenableShape)targetObject).getY() + y, PropFunctions.updateY);
        return this;
    }

    public HTween addO(double alpha) {
        properties.add(((ITweenableShape)targetObject).getOpacity(), ((ITweenableShape)targetObject).getOpacity() + alpha, PropFunctions.updateOpacity);
        return this;
    }

    public HTween addR(double rotateAngel) {
        properties.add(((ITweenableShape)targetObject).getRotate(), ((ITweenableShape)targetObject).getRotate() + rotateAngel, PropFunctions.updateRotate);
        return this;
    }

    public HTween addSX(double sx) {
        properties.add(((ITweenableShape)targetObject).getScaleX(), ((ITweenableShape)targetObject).getScaleX() + sx, PropFunctions.updateScaleX);
        return this;
    }

    public HTween addSY(double sy) {
        properties.add(((ITweenableShape)targetObject).getScaleY(), ((ITweenableShape)targetObject).getScaleY() + sy, PropFunctions.updateScaleY);
        return this;
    }

    public HTween addS(double sx, double sy) {
        properties.add(((ITweenableShape)targetObject).getScaleX(), ((ITweenableShape)targetObject).getScaleX() + sx, PropFunctions.updateScaleX);
        properties.add(((ITweenableShape)targetObject).getScaleY(), ((ITweenableShape)targetObject).getScaleY() + sy, PropFunctions.updateScaleY);
        return this;
    }

    public HTween addH(double h) {
        properties.add(((ITweenableShape)targetObject).getHeight(), ((ITweenableShape)targetObject).getHeight() + h, PropFunctions.updateHeight);
        return this;
    }

    public HTween addW(double w) {
        properties.add(((ITweenableShape)targetObject).getWidth(), ((ITweenableShape)targetObject).getWidth() + w, PropFunctions.updateWidth);
        return this;
    }

    public HTween addHW(double h, double w) {
        properties.add(((ITweenableShape)targetObject).getHeight(), ((ITweenableShape)targetObject).getHeight() + h, PropFunctions.updateHeight);
        properties.add(((ITweenableShape)targetObject).getWidth(), ((ITweenableShape)targetObject).getWidth() + w, PropFunctions.updateWidth);
        return this;
    }

    public boolean isFor(Object targetObject, String name) {
        return (targetObject == null || targetObject.equals(this.targetObject))
                && (name == null || name.equals(this.name));
    }

    public void dispose() {
        name = null;
        status = INACTIVE;
        onComplete = null;
        onInterrupt = null;
        properties.dispose();
        propComplList.dispose();
    }
}
