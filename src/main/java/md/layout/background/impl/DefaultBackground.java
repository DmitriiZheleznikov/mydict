package md.layout.background.impl;

import heli.htweener.tween.ICallable;
import javafx.scene.paint.Color;
import md.layout.background.effect.IBackgroundEffect;
import md.layout.background.effect.impl.dflt.*;
import md.skin.colorschema.i.IMDColorSchema;

import java.util.Random;

public class DefaultBackground extends MDBackground {
    private static final IBackgroundEffect[] LIST_OF_EFFECTS = new IBackgroundEffect[] {
            new BgEffect_ScaleIn(), new BgEffect_Point(),
            new BgEffect_SlideBothSides(), new BgEffect_Fade(),
            new BgEffect_SlideFromLeft(), new BgEffect_SlideFromRight()
    };
    private static final Random random = new Random();


    private Color shadowColor;
    private double shadowRadius = 15;

    public DefaultBackground(IMDColorSchema colorSchema) {
        super(colorSchema);
        topRect.setFill(colorSchema.top().bg().background());
        centerRect.setFill(colorSchema.center().bg().background());
        bottomRect.setFill(colorSchema.bottom().bg().background());
        shadowColor = colorSchema.top().bg().shadow();
    }

    @Override
    public void appear(ICallable onComplete) {
        getRandomBgEffect().appear(this, () -> {
            locate();
            if (onComplete != null) onComplete.call();
        });
    }

    @Override
    public void disappear(ICallable onComplete) {
        getRandomBgEffect().disappear(this, onComplete);
    }

    @Override
    public void lock() {

    }

    @Override
    public void unlock() {

    }

    @Override
    public void locate() {
        topRect.setX(0);
        centerRect.setX(0);
        bottomRect.setX(0);

        double y = 0;
        topRect.setY(y);
        y += 1.5*scene.getHeight()/9;
        centerRect.setY(y);
        y += 6*scene.getHeight()/9;
        bottomRect.setY(y);

        topRect.setWidth(scene.getWidth());
        centerRect.setWidth(scene.getWidth());
        bottomRect.setWidth(scene.getWidth());

        topRect.setHeight(1.5*scene.getHeight()/9);
        centerRect.setHeight(6*scene.getHeight()/9);
        bottomRect.setHeight(1.5*scene.getHeight()/9);
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public double getShadowRadius() {
        return shadowRadius;
    }

    private IBackgroundEffect<DefaultBackground> getRandomBgEffect() {
        return LIST_OF_EFFECTS[random.nextInt(LIST_OF_EFFECTS.length)];
    }
}
