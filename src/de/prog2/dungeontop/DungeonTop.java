package de.prog2.dungeontop;

import de.prog2.dungeontop.control.controller.HellController;
import de.prog2.dungeontop.control.manager.AudioManager;
import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.TestItem;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.World;
import de.prog2.dungeontop.resources.StringValues;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.resources.WorldConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class DungeonTop extends Application
{

    private static Stage stage;

    public static void main(String[] args)
    {
        launch();
    }

    // TODO Fynn#2
    public static void testEntities()
    {

    }
    // TODO Fynn#1
    public static void testGame()
    {

    }
    // TODO Jesse
    public static void testItems()
    {
        Item testItem = new TestItem();
        Item testItem2 = new TestItem();
        Inventory inventory = new Inventory();
        inventory.addItem(testItem);
        inventory.addItem(testItem2);

    }
    // TODO Jesse
    public static void testPerks()
    {

    }
    // TODO Fynn#2
    public static void testSaveGame()
    {

    }
    // TODO Jason
    public static void testSkills()
    {

    }
    // TODO Jason
    public static void testSpells()
    {

    }
    // TODO Jason
    public static void testTalents()
    {

    }
    // TODO Thomas
    public static void testWorld()
    {
        World world = new World(WorldConstants.HELL_SIZE);
        world.generateLevels();

        for (int i = 0; i < WorldConstants.HELL_SIZE; i++)
        {
            Hell hell = world.getCurrentHell();
            System.out.println(StringValues.HELL);
            System.out.println(hell);
            System.out.println(StringValues.HELL_COMPONENT_MAP);
            System.out.println(HellController.hellcomponentToString(hell));

            if (i < WorldConstants.HELL_SIZE - 1)
                world.getNextHell();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.MAIN_MENUE_FXML));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.getIcons().add(new Image(ViewStrings.MAIN_MENUE_ICO));
        stage.show();
        AudioManager.getInstance().playSound(99);
    }

    public static Stage getStage() {
        return stage;
    }

}
