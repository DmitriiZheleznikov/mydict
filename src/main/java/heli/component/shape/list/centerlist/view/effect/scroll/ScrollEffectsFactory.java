package heli.component.shape.list.centerlist.view.effect.scroll;

import heli.component.shape.list.centerlist.view.effect.scroll.impl.*;
import heli.component.shape.list.centerlist.view.effect.scroll.CListViewAnimation.Animation;

import java.util.HashMap;
import java.util.Map;

public class ScrollEffectsFactory {
    private static final IScrollEffect END = new EndScrollEffect();
    private static final IScrollEffect COMMON = new CommonScrollEffect();
    private static final IScrollEffect SCROLL = new ScrollScrollEffect();
    private static final IScrollEffect MOVE = new MoveScrollEffect();
    private static final IScrollEffect REMOVE = new RemoveScrollEffect();
    private static final IScrollEffect RESTORE = new RestoreScrollEffect();

    private static final Map<Animation, IScrollEffect> map = new HashMap<>(6);
    static {
        map.put(Animation.END, END);
        map.put(Animation.COMMON, COMMON);
        map.put(Animation.SCROLL, SCROLL);
        map.put(Animation.MOVE, MOVE);
        map.put(Animation.REMOVE, REMOVE);
        map.put(Animation.RESTORE, RESTORE);
    }

    public static IScrollEffect get(Animation animation) {
        IScrollEffect effect = map.get(animation);
        return effect == null ? COMMON : effect;
    }


}
