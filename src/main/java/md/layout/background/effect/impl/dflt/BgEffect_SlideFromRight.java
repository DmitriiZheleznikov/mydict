package md.layout.background.effect.impl.dflt;

import heli.htweener.Ease;
import heli.htweener.HTweener;
import heli.htweener.fx.ext.effect.HDropShadow;
import heli.htweener.tween.ICallable;
import javafx.scene.paint.Color;
import md.layout.background.effect.IBackgroundEffect;
import md.layout.background.impl.DefaultBackground;

public class BgEffect_SlideFromRight implements IBackgroundEffect<DefaultBackground> {
    @Override
    public void appear(DefaultBackground bg, ICallable onComplete) {
        HDropShadow dropShadow = new HDropShadow();
        dropShadow.setRadius(bg.getShadowRadius());
        dropShadow.setColor(Color.color(bg.getShadowColor().getRed(), bg.getShadowColor().getGreen(), bg.getShadowColor().getBlue(), 0));

        bg.getTopRect().setOpacity(0);
        bg.getCenterRect().setOpacity(0);
        bg.getBottomRect().setOpacity(0);

        HTweener.I.to(300).onComplete(() -> {
            //init
            double xx = bg.getTopRect().getWidth()+bg.getShadowRadius()+1;
            bg.locate();
            bg.getTopRect().setX(xx);
            bg.getTopRect().setEffect(dropShadow);

            bg.getCenterRect().setX(xx);
            bg.getCenterRect().setEffect(dropShadow);

            bg.getBottomRect().setX(xx);
            bg.getBottomRect().setEffect(dropShadow);

            HTweener.I.to(bg.getTopRect(), 400, Ease.quintIn).x(0).o(1).onComplete(() -> {
                HTweener.I.to(bg.getCenterRect(), 400, Ease.quintIn).x(0).o(1).onComplete(() -> {
                    HTweener.I.to(bg.getBottomRect(), 400, Ease.quintIn).x(0).o(1).onComplete(() -> {
                        BgEffectHelper.appearWelcomeText(bg);
                        HTweener.I.to(dropShadow, 1000, Ease.sineIn)
                                .color(bg.getShadowColor()).onComplete(onComplete);
                    });
                });
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

        double xx = bg.getTopRect().getWidth()+bg.getShadowRadius()+1;

        //tween
        HTweener.I.to(300).onComplete(() -> {
            HTweener.I.to(dropShadow, 500, Ease.sineIn).color(Color.color(bg.getShadowColor().getRed(), bg.getShadowColor().getGreen(), bg.getShadowColor().getBlue(), 0));
            HTweener.I.to(bg.getBottomRect(), 400, Ease.quintOut).o(0.4).x(xx).onComplete(() ->
                    HTweener.I.to(bg.getCenterRect(), 400, Ease.quintOut).o(0.4).x(xx).onComplete(() ->
                            HTweener.I.to(bg.getTopRect(), 400, Ease.quintOut).o(0.4).x(xx).onComplete(onComplete)
                    ));
        });
    }
}
