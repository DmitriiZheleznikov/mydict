package md.shape;

import heli.htweener.Ease;
import heli.htweener.HT;
import heli.htweener.tween.ICallable;
import javafx.scene.paint.Color;

public class MDProgressText extends MDButton {
    private boolean isRun = false;
    private ICallable onRunStop;

    public MDProgressText(String text, Color minColor, Color maxColor, Color finalColor, double size) {
        super(text, maxColor, minColor, finalColor, size);
        setInactive();
        setLocked(true);
    }

    @Override
    protected void registerMouseEvents() {

    }

    public void run(ICallable onUpdate) {
        isRun = true;
        nextRun(onUpdate);
    }

    private void nextRun(ICallable onUpdate) {
        if (isRun) {
            if (getScaleX() == 1f) {
                HT.to(this, 600, Ease.sineInOut).s(2).color(colorRegular).onUpdate(ratio ->  {
                    if (onUpdate != null) onUpdate.call();
                }).onComplete(() -> nextRun(onUpdate));
            } else {
                HT.to(this, 600, Ease.sineInOut).s(1).color(colorInactive).onUpdate(ratio ->  {
                    if (onUpdate != null) onUpdate.call();
                }).onComplete(() -> nextRun(onUpdate));
            }
        } else {
            //if (getScaleX() == 1f) {
                HT.to(this, 1000, Ease.sineInOut).color(colorHovered);
                HT.to(this, 600, Ease.sineInOut).s(1.5).onComplete(() -> {
                    HT.to(400).onComplete(() -> {
                        if (onRunStop != null) onRunStop.call();
                    });
                });
            //} else {
            //    HT.to(this, 1000, Ease.sineInOut).color(colorHovered).onComplete(() -> {
            //        if (onRunStop != null) onRunStop.call();
            //    });
            //}
        }
    }

    public void stop(ICallable onComplete) {
        isRun = false;
        onRunStop = onComplete;
    }

}
