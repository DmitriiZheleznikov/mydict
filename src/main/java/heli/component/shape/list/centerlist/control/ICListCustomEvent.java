package heli.component.shape.list.centerlist.control;

import heli.component.shape.list.centerlist.model.CListLineModel;
import heli.component.shape.list.centerlist.model.CListModel;
import heli.component.shape.list.centerlist.model.CListModelOperation;
import heli.component.shape.list.centerlist.model.CListModelWindow;

public interface ICListCustomEvent {
    void call(CListLineModel prevLineModel, CListLineModel currLineModel, CListModelOperation op, CListModelWindow currentWindow);
}
