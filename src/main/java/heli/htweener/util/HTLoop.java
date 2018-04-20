package heli.htweener.util;

import heli.htweener.HT;
import javafx.animation.AnimationTimer;

/**
 * Main loop, without it HT will not work
 */
public class HTLoop extends AnimationTimer {
    public HTLoop() {
        super();
        HT.initTime();
    }
    @Override
    public void handle(long now) {
        HT.step(now);
    }
}