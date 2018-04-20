package md.layout.content.view.helper;

import heli.htweener.tweenable.ITweenableShape;
import md.layout.content.view.AbstractMDContentBase;
import md.util.IPosFunction;

public class ShapeHelper {
    private AbstractMDContentBase content;

    private double initSceneHeight;
    private double initSceneWidth;

    private double internalSaveScaleX;
    private double internalSaveScaleY;

    public ShapeHelper(AbstractMDContentBase content) {
        this.content = content;
        initSceneInitialSize();
    }

    public void initSceneInitialSize() {
        if (content.getScene() != null) {
            initSceneHeight = content.getScene().getHeight();
            initSceneWidth = content.getScene().getWidth();
        }
    }

    public void locateObject(ITweenableShape object, ITweenableShape holder, IPosFunction fX, IPosFunction fY) {
        if (object == null || object.equals(holder)) {
            saveState(holder);
            holder.setX(fX.calculate());
            holder.setY(fY.calculate());
            restoreSate(holder);
        }
    }

    public double getScaleFactor() {
        if (content.getScene() == null) return 1;
        double sX = content.getScene().getWidth() / initSceneWidth;
        double sY = content.getScene().getHeight() / initSceneHeight;
        return Math.round(10f*(sX < sY ? sX : sY)-0.49f)/10f;
    }

    public void saveState(ITweenableShape object) {
        internalSaveScaleX = object.getScaleX();
        internalSaveScaleY = object.getScaleY();
        object.setScaleX(1);
        object.setScaleY(1);
    }

    public void restoreSate(ITweenableShape object) {
        object.setScaleX(internalSaveScaleX);
        object.setScaleY(internalSaveScaleY);
    }
}
