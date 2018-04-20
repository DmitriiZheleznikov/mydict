package heli.htweener.util;

import javafx.scene.canvas.GraphicsContext;

public class GCSaver {
    private static final GCSaver I = new GCSaver();

    private double mxx;
    private double mxy;
    private double mxt;
    private double myx;
    private double myy;
    private double myt;
    private double alpha;

    public static void save(GraphicsContext gc) {
        I.saveGC(gc);
    }

    public static void restore(GraphicsContext gc) {
        I.restoreGC(gc);
    }

    public void saveGC(GraphicsContext gc) {
        mxx = gc.getTransform().getMxx();
        mxy = gc.getTransform().getMxy();
        mxt = gc.getTransform().getTx();

        myx = gc.getTransform().getMyx();
        myy = gc.getTransform().getMyy();
        myt = gc.getTransform().getTy();

        alpha = gc.getGlobalAlpha();
    }

    public void restoreGC(GraphicsContext gc) {
        gc.setTransform(mxx, myx,
                        mxy, myy,
                        mxt, myt);
        gc.setGlobalAlpha(alpha);
    }
}
