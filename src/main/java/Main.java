import heli.helper.ResourceHelper;
import heli.htweener.Ease;
import heli.htweener.HT;
import heli.htweener.util.HTLoop;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import md.layout.background.impl.DefaultBackground;
import md.layout.background.impl.MDBackground;
import md.layout.content.control.MDContent;
import md.settings.Settings;
import md.skin.colorschema.ColorSchemaFactory;
import md.skin.colorschema.ColorSchemas;
import md.skin.colorschema.i.IMDColorSchema;

public class Main extends Application {
    private static final double APP_WIDTH = 16*70;
    private static final double APP_HEIGHT = 9*70;

    private HTLoop mainLoop;

    @Override
    public void start(Stage stage) throws Exception {
        wa();
        Scene scene = setupScene(stage);

        IMDColorSchema colorSchema = ColorSchemaFactory.get(ColorSchemas.DEFAULT);
        MDBackground bg = new DefaultBackground(colorSchema);
        MDContent content = new MDContent(bg, colorSchema);
        bg.addToScene(scene);

        setupResizeListeners(stage, scene, bg, content);

        stage.show();
        wa();

        mainLoop = new HTLoop();
        mainLoop.start();
        wa();
    }

    private Scene setupScene(Stage stage) {
        Group group = new Group();

        Scene scene = new Scene(group);
        stage.setTitle("My Dict");
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setResizable(false);
        wa();
        stage.setMinHeight(APP_HEIGHT);
        stage.setMinWidth(APP_WIDTH);
        stage.getIcons().add(ResourceHelper.image(Main.class, "/resources/img/ico.png"));

        return scene;
    }

    private void setupResizeListeners(Stage stage, Scene scene, MDBackground bg, MDContent content) {
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            bg.resize(-1, newValue.doubleValue());
            content.resize(-1, newValue.doubleValue());
            if (stage.isResizable()) Settings.saveWindowSize(stage.getHeight(), stage.getWidth(), stage.isMaximized());
        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            bg.resize(newValue.doubleValue(), -1);
            content.resize(newValue.doubleValue(), -1);
            if (stage.isResizable()) Settings.saveWindowSize(stage.getHeight(), stage.getWidth(), stage.isMaximized());
        });

        bg.appear(() -> {
            boolean isAnimated = false;
            if (Settings.getHeight() > APP_HEIGHT) {
                HT.to(stage, 700, Ease.sineInOut).prop(stage.getHeight(), Settings.getHeight(), stage::setHeight);
                isAnimated = true;
            }
            if (Settings.getWidth() > APP_WIDTH) {
                HT.to(stage, 700, Ease.sineInOut).prop(stage.getWidth(), Settings.getWidth(), stage::setWidth);
                isAnimated = true;
            }
            HT.to(isAnimated ? 730 : 30).onComplete(() -> {
//            if (Settings.isMaximized()) {
//                stage.setMaximized(true);
//            }
                content.addToScene(scene);
                content.initState();
                stage.setResizable(true);
            });
        });
    }

    private void wa() {
        //This is strange WA for strange bug, without this code window sometimes appears twice bigger on java9 (some old revisions)
        Screen.getPrimary().toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
