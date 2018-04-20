package md.layout.content.view.helper;

import heli.htweener.HT;
import heli.htweener.tween.ICallable;
import javafx.stage.FileChooser;
import md.layout.content.control.ISaveFileEvent;
import md.layout.content.view.AbstractMDContentBase;
import md.layout.content.control.ILoadFileEvent;
import md.layout.content.view.helper.visibility.VisibilityHelper;
import md.shape.MDButton;

import java.io.File;
import java.io.IOException;

public class FileHelper {
    private AbstractMDContentBase content;

    public FileHelper(AbstractMDContentBase content) {
        this.content = content;
    }

    public void setOnLoadFileEvent(VisibilityHelper.Type type, MDButton button, ILoadFileEvent onLoadFileEvent) {
        button.setOnMouseClicked(event -> {
            if (!button.isLocked()) {
                FileChooser fc = new FileChooser();
                File file = fc.showOpenDialog(content.getScene().getWindow());
                if (file != null) {
                    loadFileButtonAnimation(type, button, file, () -> {
                        if (onLoadFileEvent != null) onLoadFileEvent.call(file);
                    });
                }
            }
        });
    }

    private void loadFileButtonAnimation(VisibilityHelper.Type type, MDButton button, File f, ICallable onComplete) {
        content.visibilityHelper(type).hide(button, () -> {
            button.setText(f.getName());
            content.visibilityHelper(type).show(button, () -> {
                if (onComplete != null) onComplete.call();
            });
        });
    }

    public void setOnSaveFileEvent(VisibilityHelper.Type type, MDButton button, ISaveFileEvent action) {
        if (button.isLocked()) return;

        content.visibilityHelper(type).hide(button, () -> {
            //TODO think about separate thread
            String message = "DONE";
            int delay = 500;
            if (action != null) try {
                action.call();
            } catch (IOException e) {
                message = "ERROR: " + e.getMessage();
                delay = 1500;
            }
            final int delayMessage = delay;
            String btnText = button.getText();
            button.setText(message);
            content.visibilityHelper(type).show(button, () -> {
                button.setLocked(true);
                HT.to(delayMessage).onComplete(() -> {
                    content.visibilityHelper(type).hide(button, () -> {
                        button.setText(btnText);
                        content.visibilityHelper(type).show(button);
                    });
                });
            });
        });
    }
}
