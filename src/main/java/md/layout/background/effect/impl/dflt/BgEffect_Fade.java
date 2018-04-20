package md.layout.background.effect.impl.dflt;

import heli.htweener.Ease;
import heli.htweener.HT;
import heli.htweener.fx.ext.effect.HDropShadow;
import heli.htweener.tween.ICallable;
import javafx.scene.paint.Color;
import md.layout.background.effect.IBackgroundEffect;
import md.layout.background.impl.DefaultBackground;

public class BgEffect_Fade implements IBackgroundEffect<DefaultBackground> {
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
        HT.to(300).onComplete(() -> {
            bg.locate();
            HT.to(bg.getTopRect(), 1250, Ease.sineOut).o(1);
            HT.to(bg.getCenterRect(), 1250, Ease.sineOut).o(1);
            HT.to(bg.getBottomRect(), 1250, Ease.sineOut).o(1);
            HT.to(dropShadow, 750, Ease.linear).onComplete(() -> {
                BgEffectHelper.appearWelcomeText(bg);
                HT.to(dropShadow, 1000, Ease.sineIn)
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
        HT.to(300).onComplete(() -> {
            HT.to(dropShadow, 1250, Ease.sineIn).color(Color.color(bg.getShadowColor().getRed(), bg.getShadowColor().getGreen(), bg.getShadowColor().getBlue(), 0));
            HT.to(bg.getTopRect(), 1250, Ease.sineIn).o(0);
            HT.to(bg.getCenterRect(), 1250, Ease.sineIn).o(0);
            HT.to(bg.getBottomRect(), 1250, Ease.sineIn).o(0).onComplete(onComplete);
        });
    }
}
