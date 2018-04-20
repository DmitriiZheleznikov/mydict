package heli.htweener.tween;

import heli.htweener.ease.IEaseFunction;
import heli.htweener.exception.NotEnoughSpaceException;

import java.util.ArrayDeque;

public class TweenList {
    private int maxNumberOfProps;

    private HTween[] list;
    private int size = 0;
    private ArrayDeque<ICallable> onCompleteList;

    public TweenList(int maxTweens, int maxNumberOfProps) {
        this.list = new HTween[maxTweens];
        this.maxNumberOfProps = maxNumberOfProps;
        onCompleteList = new ArrayDeque<>(maxTweens);
    }

    public HTween add(Object target, int duration, IEaseFunction ease, String name) {
        if (size >= list.length) throw new NotEnoughSpaceException(size);
        HTween tween = list[size];
        if (tween == null) {
            tween = new HTween(maxNumberOfProps);
            list[size] = tween;
        }
        else if (tween.isActive()) {
            for (int i = 0; i < size; i++) {
                tween = list[i];
                if (tween.isInactive()) {
                    break;
                }
            }
        }

        tween.init(target, duration, ease, name);
        size++;

        return tween;
    }

    public void interrupt(Object target, String name) {
        for (HTween tween : list)
        {
            if (tween == null) break;
            if (tween.isInactive()) continue;

            if (tween.isFor(target, name)) {
                tween.markInterrupt();
            }
        }
    }

    public boolean step(double dt) {
        boolean isActive = false;

        for (HTween tween : list)
        {
            if (tween == null) break;
            if (tween.isInactive()) continue;

            isActive = true;
            if (tween.step(dt)) {
                ICallable onCompleteFunc;
                if (tween.isMarkedInterrupt()) {
                    onCompleteFunc= tween.interruptTween();
                } else {
                    onCompleteFunc = tween.completeTween();
                }

                if (onCompleteFunc != null) onCompleteList.addLast(onCompleteFunc);

                size--;
            }
        }

        if (!onCompleteList.isEmpty()) {
            ICallable onComplete;
            while ((onComplete = onCompleteList.pollLast()) != null) {
                onComplete.call();
            }
        }

        return isActive;
    }

    public String getTextRepresentationOfActualState() {
        StringBuilder sb = new StringBuilder();
        for (HTween aList : list)
            if (aList == null) {
                sb.append("N");
            } else if (aList.isActive()) {
                sb.append("A");
            } else {
                sb.append("I");
            }
        return sb.toString();
    }

    public String toString() {
        return getTextRepresentationOfActualState();
    }
}
