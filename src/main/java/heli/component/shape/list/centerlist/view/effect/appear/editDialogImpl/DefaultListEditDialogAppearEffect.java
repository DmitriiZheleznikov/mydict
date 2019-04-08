package heli.component.shape.list.centerlist.view.effect.appear.editDialogImpl;

import heli.component.shape.list.centerlist.ext.CButton;
import heli.component.shape.list.centerlist.ext.CTextField;
import heli.component.shape.list.centerlist.view.CListView;
import heli.htweener.Ease;
import heli.htweener.HT;
import heli.htweener.fx.ext.HRectangle;
import heli.htweener.tween.ICallable;

public class DefaultListEditDialogAppearEffect extends AbstractListEditDialogAppearEffect {
    public static final int APPEAR_SPEED = 250;


    @Override
    public void appear(CListView listView, ICallable onComplete) {
        HRectangle bg = listView.getEditDialog().getBg();
        CTextField tf = listView.getEditDialog().getTfNewValue();
        CButton bs = listView.getEditDialog().getbSave();
        CButton bc = listView.getEditDialog().getbCancel();

        listView.getEditDialog().show();
        listView.getEditDialog().locate();
        bg.setOpacity(0);
        tf.setOpacity(0);
        bs.setOpacity(0);
        bc.setOpacity(0);
        double tfY = tf.getY();
        tf.setY(tfY - tf.getHeight()*0.5);

        HT.kill(bg, NAME).to(bg, APPEAR_SPEED, Ease.sineInOut, NAME).o(1).onComplete(() -> {
            if (onComplete != null) onComplete.call();
        });
        HT.kill(tf, NAME).to(tf, APPEAR_SPEED, Ease.sineInOut, NAME).o(1);
        HT.to(tf, APPEAR_SPEED/2, Ease.sineInOut, NAME).y(tfY);
        HT.kill(bs, NAME).to(bs, APPEAR_SPEED, Ease.sineInOut, NAME).o(1);
        HT.kill(bc, NAME).to(bc, APPEAR_SPEED, Ease.sineInOut, NAME).o(1);
    }

    @Override
    public void disappear(CListView listView, ICallable onComplete) {
        HRectangle bg = listView.getEditDialog().getBg();
        CTextField tf = listView.getEditDialog().getTfNewValue();
        CButton bs = listView.getEditDialog().getbSave();
        CButton bc = listView.getEditDialog().getbCancel();

        HT.kill(bg, NAME).to(bg, APPEAR_SPEED/2, Ease.sineInOut, NAME).o(0).onComplete(() -> {
            if (onComplete != null) onComplete.call();
        });
        HT.kill(tf, NAME).to(tf, APPEAR_SPEED/2, Ease.sineInOut, NAME).o(0);
        //HT.to(tf, APPEAR_SPEED/4, Ease.linear, NAME).onComplete(() ->
        //        HT.to(tf, APPEAR_SPEED/4, Ease.sineInOut, NAME).y(tf.getY() - tf.getHeight()*0.5));
        HT.kill(bs, NAME).to(bs, APPEAR_SPEED/2, Ease.sineInOut, NAME).o(0);
        HT.kill(bc, NAME).to(bc, APPEAR_SPEED/2, Ease.sineInOut, NAME).o(0);
    }
}
