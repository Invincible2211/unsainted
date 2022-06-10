package de.prog2.dungeontop;

import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.view.SettingsController;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.HellGenerator;
import de.prog2.dungeontop.view.HellView;
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


        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(ViewStrings.MAIN_MENUE_ICO));
        stage.sizeToScene();
        stage.show();
        //AudioManager.getInstance().playSound(99);

        SettingsController.initStage();
        testHellView(scene);
    }

    public static void testHellView(Scene scene) throws Exception
    {

        System.out.println(stage.getHeight());

        Hell hell = new Hell(WorldConstants.HELL_SIZE, WorldConstants.HELL_SIZE);
        HellGenerator.initHell(hell);
        HellView view = new HellView();
        Scene hellView = view.initHellView(hell);
        //view.initPlayerCamera(scene);
        //view.initOverlay(hellView);

        stage.setScene(scene);
        stage.setScene(hellView);


        System.out.println(scene.getHeight());

        System.out.println(hell);
    }

    public static Stage getStage()
    {
        return stage;
    }

}
