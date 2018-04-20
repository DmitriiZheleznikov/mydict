package md.layout.background.effect.impl.dflt;

import heli.htweener.Ease;
import heli.htweener.HT;
import heli.htweener.fx.ext.HCircle;
import heli.htweener.fx.ext.effect.HDropShadow;
import heli.htweener.tween.ICallable;
import javafx.scene.paint.Color;
import md.layout.background.effect.IBackgroundEffect;
import md.layout.background.impl.DefaultBackground;

public class BgEffect_Point implements IBackgroundEffect<DefaultBackground> {
    private static final Color DOT_COLOR = Color.color(0.9804, 0.7647, 0.4824);

    @Override
    public void appear(DefaultBackground bg, ICallable onComplete) {
        //Pre-init
        HDropShadow dropShadow = new HDropShadow();
        dropShadow.setRadius(bg.getShadowRadius());
        dropShadow.setColor(Color.color(bg.getShadowColor().getRed(), bg.getShadowColor().getGreen(), bg.getShadowColor().getBlue(), 0));

        bg.getTopRect().setOpacity(0);
        bg.getCenterRect().setOpacity(0);
        bg.getBottomRect().setOpacity(0);
        bg.getTopRect().setEffect(dropShadow);
        bg.getCenterRect().setEffect(dropShadow);
        bg.getBottomRect().setEffect(dropShadow);
        bg.getCenterRect().setFill(DOT_COLOR);

        HCircle circ = new HCircle();
        circ.setOpacity(0);
        circ.setFill(DOT_COLOR);
        bg.getGroup().getChildren().add(circ);

        HT.to(300).onComplete(() -> {
            //init
            bg.locate();
            double cY = bg.getCenterRect().getY();
            double cH = bg.getCenterRect().getHeight();
            double bY = bg.getBottomRect().getY();

            bg.getTopRect().setY(-bg.getTopRect().getHeight()-bg.getShadowRadius()-1);
            bg.getBottomRect().setY(bg.getBottomRect().getY()+bg.getBottomRect().getHeight()+bg.getShadowRadius()+1);
            bg.getCenterRect().setX(bg.getScene().getWidth()/2);
            bg.getCenterRect().setY(bg.getScene().getHeight()/2-10);
            bg.getCenterRect().setHeight(20);
            bg.getCenterRect().setWidth(0);
            circ.setX(bg.getScene().getWidth()/2);
            circ.setY(bg.getScene().getHeight()/2);
            circ.setRadius(0);
            circ.setOpacity(1);
            //Tween
            HT.to(circ, 1500, Ease.bounceOut).w(10).onComplete(() -> {
                HT.to(750).onComplete(() -> {
                    bg.getCenterRect().setOpacity(1);
                    HT.to(circ, 1200, Ease.sineIn).color(Color.ANTIQUEWHITE);
                    HT.to(bg.getCenterRect(), 1200, Ease.sineIn).color(Color.ANTIQUEWHITE);
                    HT.to(bg.getCenterRect(), 1000, Ease.quintOut).x(0).w(bg.getScene().getWidth()).onComplete(() -> {
                        bg.getGroup().getChildren().remove(circ);
                        HT.to(bg.getCenterRect(), 750, Ease.quintOut).y(cY).h(cH).onComplete(() -> {
                            HT.to(bg.getTopRect(), 200, Ease.quintOut).o(1);
                            HT.to(bg.getBottomRect(), 200, Ease.quintOut).o(1);
                            HT.to(bg.getTopRect(), 500, Ease.quintOut).y(0);
                            HT.to(bg.getBottomRect(), 500, Ease.quintOut).y(bY).onComplete(() -> {
                                BgEffectHelper.appearWelcomeText(bg);
                                HT.to(dropShadow, 1000, Ease.sineIn).color(bg.getShadowColor()).onComplete(onComplete);
                            });
                        });
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
        double tH = bg.getTopRect().getHeight();
        double bH = bg.getBottomRect().getHeight();
        bg.getTopRect().setOpacity(1);
        bg.getTopRect().setEffect(dropShadow);
        bg.getCenterRect().setOpacity(1);
        bg.getCenterRect().setEffect(dropShadow);
        bg.getBottomRect().setOpacity(1);
        bg.getBottomRect().setEffect(dropShadow);
        bg.getCenterRect().setFill(Color.ANTIQUEWHITE);

        HCircle circ = new HCircle();
        circ.setOpacity(1);
        circ.setFill(Color.ANTIQUEWHITE);
        circ.setX(bg.getScene().getWidth()/2);
        circ.setY(bg.getScene().getHeight()/2);
        circ.setRadius(10);

        //tween
        HT.to(300).onComplete(() -> {
            HT.to(dropShadow, 500, Ease.sineIn).color(Color.color(bg.getShadowColor().getRed(), bg.getShadowColor().getGreen(), bg.getShadowColor().getBlue(), 0));
            HT.to(300).onComplete(() -> {
                HT.to(bg.getTopRect(), 200, Ease.quintOut).o(0);
                HT.to(bg.getBottomRect(), 200, Ease.quintOut).o(0);
            });
            HT.to(bg.getTopRect(), 500, Ease.quintOut).y(-tH-bg.getShadowRadius()-1);
            HT.to(bg.getBottomRect(), 500, Ease.quintOut).y(bg.getBottomRect().getY()+bH+bg.getShadowRadius()+1).onComplete(() -> {
                HT.to(250).onComplete(() -> {
                    bg.getGroup().getChildren().add(circ);
                    HT.to(circ, 1200, Ease.sineIn).color(DOT_COLOR);
                    HT.to(bg.getCenterRect(), 1200, Ease.sineIn).color(DOT_COLOR);
                });
                HT.to(bg.getCenterRect(), 750, Ease.quintOut).y(bg.getScene().getHeight()/2-10).h(20).onComplete(() -> {
                    HT.to(bg.getCenterRect(), 1000, Ease.quintOut).x(bg.getScene().getWidth()/2).w(0).onComplete(() -> {
                        bg.getCenterRect().setOpacity(0);
                        HT.to(circ, 750, Ease.backIn).w(0).onComplete(() -> {
                            bg.getTopRect().setOpacity(0);
                            bg.getCenterRect().setOpacity(0);
                            bg.getBottomRect().setOpacity(0);
                            if (onComplete != null) onComplete.call();
                        });
                    });
                });
            });
        });
    }
}
