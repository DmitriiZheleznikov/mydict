package heli.component.shape.list.centerlist.view.effect.appear;

import heli.component.shape.list.centerlist.model.CListModelWindow;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.tween.ICallable;
import md.shape.mdcenterlist.view.MDListView;

public interface IListAppearEffect {
    void appear(CListView listView, CListModelWindow window, ICallable onComplete);
    void disappear(CListView listView, ICallable onComplete);
}
