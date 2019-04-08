package heli.component.shape.list.centerlist.view.effect.appear;

import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.tween.ICallable;

public interface IListEditDialogAppearEffect {
    void appear(CListView listView, ICallable onComplete);
    void disappear(CListView listView, ICallable onComplete);
}
