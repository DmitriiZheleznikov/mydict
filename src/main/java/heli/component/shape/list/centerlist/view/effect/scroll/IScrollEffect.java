package heli.component.shape.list.centerlist.view.effect.scroll;

import heli.component.shape.list.centerlist.model.CListModelOperation;
import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.tween.ICallable;

public interface IScrollEffect {
    void perform(CListView listView, CListModelWindow modelWindow, CListModelOperation op, ICallable onComplete);
}
