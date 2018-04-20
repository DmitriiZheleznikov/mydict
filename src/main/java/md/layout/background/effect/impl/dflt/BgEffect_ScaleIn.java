package md.layout.background.effect.impl.dflt;

import heli.htweener.Ease;
import heli.htweener.HTweener;
import heli.htweener.fx.ext.effect.HDropShadow;
import heli.htweener.tween.ICallable;
import javafx.scene.paint.Color;
import md.layout.background.effect.IBackgroundEffect;
import md.layout.background.impl.DefaultBackground;

public class BgEffect_ScaleIn implements IBackgroundEffect<DefaultBackground> {
    @Override
    public void appear(DefaultBackground bg, ICallable onComplete) {
        HDropShadow dropShadow = new HDropShadow();
        dropShadow.setRadius(bg.getShadowRadius());
        dropShadow.setColor(Color.color(bg.getShadowColor().getRed(), bg.getShadowColor().getGreen(), bg.getShadowColor().getBlue(), 0));

        //init
        bg.getTopRect().setOpacity(0);
        bg.getTopRect().setEffect(dropShadow);

        bg.getCenterRect().setOpacity(0);
        bg.getCenterRect().setEffect(dropShadow);

        bg.getBottomRect().setOpacity(0);
        bg.getBottomRect().setEffect(dropShadow);

        //tween
        HTweener.I.to(300).onComplete(() -> {
            bg.locate();
            bg.getTopRect().setScaleX(5);
            bg.getTopRect().setScaleY(5);
            bg.getCenterRect().setScaleX(5);
            bg.getCenterRect().setScaleY(5);
            bg.getBottomRect().setScaleX(5);
            bg.getBottomRect().setScaleY(5);
            HTweener.I.to(bg.getTopRect(), 3000, Ease.quintInOut).o(1).s(1);
            HTweener.I.to(bg.getCenterRect(), 3000, Ease.quintInOut).o(1).s(1);
            HTweener.I.to(bg.getBottomRect(), 3000, Ease.quintInOut).o(1).s(1);
            HTweener.I.to(dropShadow, 750, Ease.linear).onComplete(() -> {
                BgEffectHelper.appearWelcomeText(bg);
                HTweener.I.to(dropShadow, 1000, Ease.sineIn)
                        .color(bg.getShadowColor()).onComplete(onComplete);
            });
        });
    }

    @Override
    public void disappear(DefaultBackground bg, ICallable onComplete) {
        HDropShadow dropShadow = new HDropShadow();
        dropShadow.setRadius(bg.getShadowRadius());
        dropShadow.setColor(bg.getShadowColor());

        //init
        bg.locate();
        bg.getTopRect().setOpacity(1);
        bg.getTopRect().setEffect(dropShadow);

        bg.getCenterRect().setOpacity(1);
        bg.getCenterRect().setEffect(dropShadow);

        bg.getBottomRect().setOpacity(1);
        bg.getBottomRect().setEffect(dropShadow);

        //tween
        HTweener.I.to(300).onComplete(() -> {
            HTweener.I.to(dropShadow, 1250, Ease.sineIn).color(Color.color(bg.getShadowColor().getRed(), bg.getShadowColor().getGreen(), bg.getShadowColor().getBlue(), 0));
            HTweener.I.to(bg.getTopRect(), 2000, Ease.quintOut).o(0).s(5);
            HTweener.I.to(bg.getCenterRect(), 2000, Ease.quintOut).o(0).s(5);
            HTweener.I.to(bg.getBottomRect(), 2000, Ease.quintOut).o(0).s(5).onComplete(onComplete);
        });
    }
}

