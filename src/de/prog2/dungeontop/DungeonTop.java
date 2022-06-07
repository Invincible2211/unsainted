package de.prog2.dungeontop;

import de.prog2.dungeontop.control.manager.AudioManager;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.view.SettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DungeonTop extends Application
{

    private static Stage stage;

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.MAIN_MENUE_FXML));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(ViewStrings.MAIN_MENUE_ICO));
        stage.sizeToScene();
        stage.show();
        AudioManager.getInstance().playSound(99);
        SettingsController.initStage();
    }

    public static Stage getStage()
    {
        return stage;
    }

}
