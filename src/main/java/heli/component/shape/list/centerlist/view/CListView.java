package heli.component.shape.list.centerlist.view;

import heli.component.shape.list.centerlist.adj.ICListColorSchema;
import heli.component.shape.list.centerlist.model.CListLineModel;
import heli.component.shape.list.centerlist.model.CListModel;
import heli.component.shape.list.centerlist.model.CListModelOperation;
import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.effect.appear.IListAppearEffect;
import heli.component.shape.list.centerlist.view.effect.appear.impl.*;
import heli.component.shape.list.centerlist.view.effect.scroll.CListViewAnimation;
import heli.component.shape.list.centerlist.view.effect.scroll.ScrollEffectsFactory;
import heli.htweener.fx.ext.HRectangle;
import heli.htweener.tween.ICallable;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.Random;

/**
 * Base of "view" part of CList. It consists from "view" lines and provides some basic features:<ul>
 *     <li>animated appearance/disappearance</li>
 *     <li>resize</li>
 *     <li>location</li>
 * </ul>
 */
public class CListView {
    public static final double SF_ACTIVE_BLOCK_HIGH = 2.5;
    public static final int SIZE = CListModel.SIZE;
    protected static final long SIZE_PX_BETWEEN_LINES = 10;
    protected static final Random random = new Random();
    protected static final IListAppearEffect[] LIST_OF_EFFECTS = new IListAppearEffect[] {
            new FadeListAppearEffect(),
            new SlideUpAppearEffect(),
            new SlideLeftAppearEffect()
    };

    protected Group group;
    protected HRectangle bgRect;
    protected CListLineView[] lines;
    protected CListLineView lineFakeOnFront;
    protected long sizeBetween;
    protected double blockHight;
    protected double blockWidth;
    protected ICListColorSchema colorSchema;

    protected boolean isLocked = true;
    protected boolean isVisible = false;

    public CListView(Group group, HRectangle rect, ICListColorSchema colorSchema) {
        this.colorSchema= colorSchema;
        this.group = group;
        this.bgRect = rect;
        this.sizeBetween = SIZE_PX_BETWEEN_LINES;
        this.lines = createLines();

        this.lineFakeOnFront = createNewLineView();
    }

    protected CListLineView[] createLines() {
        lines = new CListLineView[SIZE];
        for (int i = 0; i < SIZE; i++) {
            lines[i] = createNewLineView();
        }
        return lines;
    }

    protected CListLineView createNewLineView() {
        return new CListLineView(this);
    }

    public void appear(CListModelWindow window) {
        appear(window, null);
    }
    public void appear(CListModelWindow window, ICallable onComplete) {
        isLocked = true;
        getRandomAppearEffect().appear(this, window, () -> {
            if (onComplete != null) onComplete.call();
            locate(window);
            setVisible(true);
            isLocked = false;
            isVisible = true;
        });
    }

    public void disappear() {
        disappear(null);
    }
    public void disappear(ICallable onComplete) {
        if (!isVisible) {
            if (onComplete != null) onComplete.call();
            return;
        }
        if (isLocked) {
            if (onComplete != null) onComplete.call();
            return;
        }
        isLocked = true;
        getRandomAppearEffect().disappear(this, () -> {
            if (onComplete != null) onComplete.call();
            setVisible(false);
        });
    }

    public void clip() {
        for (CListLineView line : lines()) {
            line.clip();
        }
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public HRectangle getBgRect() {
        return bgRect;
    }

    public CListLineView[] lines() {
        return lines;
    }

    public int getCurrentLineNumber() {
        for (int i = 0; i < lines.length; i++) {
            CListLineView line = lines[i];
            if (line.isActive()) return i;
        }
        return 0;
    }

    public CListLineView getCurrentLine() {
        return this.lines()[this.getCurrentLineNumber()];
    }

    public void calcWindow(CListModelWindow modelWindow) {
        if (modelWindow.getLine(1) == null) {
            return;
        }

        int wCur = modelWindow.getCurrent();
        double h = 0;

        lines[0].calcFuturePosition(modelWindow.getLine(0)==null?-1:modelWindow.getLine(0).getNum(), sizeBetween, bgRect.getY() - blockHight, 1);

        for (int i = 1; i < SIZE; i++) {
            h += lines[i].calcFuturePosition(modelWindow.getLine(i)==null?-1:modelWindow.getLine(i).getNum(),
                    sizeBetween,
                    bgRect.getY() + i*sizeBetween + h,
                    calcDistance(wCur, i));
        }
    }

    public void renewContent(CListModelWindow modelWindow) {
        lines[0].renewContent(modelWindow.getLine(0));
        for (int i = 1; i < SIZE; i++) {
            lines[i].renewContent(modelWindow.getLine(i));
        }
    }

    public void locate() {
        for (int i = 0; i < SIZE; i++) {
            lines[i].locate();
        }
    }

    public void locate(CListModelWindow modelWindow) {
        renewContent(modelWindow);
        calcWindow(modelWindow);
        locate();
    }

    public CListLineView showFakeLine(CListLineView positionFrom, CListLineView contentFrom) {
        lineFakeOnFront.renewContentBy(contentFrom);
        lineFakeOnFront.locateBy(positionFrom);
        lineFakeOnFront.unsetClip();

        return lineFakeOnFront;
    }

    public CListLineView showFakeLine(CListLineViewFuturePosition windowLine, CListLineModel contentFrom) {
        lineFakeOnFront.renewContent(contentFrom);
        lineFakeOnFront.locateBy(windowLine);
        lineFakeOnFront.unsetClip();

        return lineFakeOnFront;
    }

    public void hideFakeLine() {
        lineFakeOnFront.hide();
    }

    public double calcDistance(double wCur, double curLine) {
        double res = (wCur-curLine)/wCur;
        if (res < 0f) {
            res = (curLine-wCur)/(SIZE-1-wCur);
        }
        return res;
    }

    public void resize(double sf) {
        sizeBetween = Math.round(SIZE_PX_BETWEEN_LINES * sf);
        blockHight = (bgRect.getHeight() - (SIZE - 1) * (sizeBetween)) / (SIZE - 3 + SF_ACTIVE_BLOCK_HIGH);
        blockWidth = bgRect.getWidth() - 2 * sizeBetween;
        for (CListLineView line : lines) {
            line.resize(sf);
        }
        lineFakeOnFront.resize(sf);
    }

    public long getSizeBetween() {
        return sizeBetween;
    }

    public double getBlockHight() {
        return blockHight;
    }

    public double getBlockWidth() {
        return blockWidth;
    }

    public void addToScene(Scene scene) {
        for (CListLineView line : lines) {
            line.addToScene(scene);
        }
        lineFakeOnFront.addToScene(scene);
    }

    public void setVisible(boolean isVisible) {
        for (CListLineView line : lines) {
            if (isVisible) {
                line.show();
            } else {
                line.hide();
            }
        }
    }

    public Group getGroup() {
        return group;
    }

    public ICListColorSchema getColorSchema() {
        return colorSchema;
    }

    protected IListAppearEffect getRandomAppearEffect() {
        return LIST_OF_EFFECTS[random.nextInt(LIST_OF_EFFECTS.length)];
    }

    public void applyAnimation(CListModelOperation op, CListViewAnimation.Animation animation, CListModelWindow window) {
        ScrollEffectsFactory.get(animation).perform(this, window, op, null);
    }
}