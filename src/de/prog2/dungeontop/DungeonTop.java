package de.prog2.dungeontop;

import de.prog2.dungeontop.control.controller.HellController;
import de.prog2.dungeontop.control.controller.ShopViewController;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.TestItem;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.World;
import de.prog2.dungeontop.resources.StringValues;
import de.prog2.dungeontop.resources.WorldConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

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

        testCardView(primaryStage);
    }
    public static void testCardView(Stage primaryStage) throws Exception
    {
        var card = new EntityCard(3, 100, 1);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream("view/shopView.fxml"));
        ShopViewController.addCards(fxmlLoader.getController(), cards);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("view/shopView.css");
        primaryStage.setScene(scene);
    }

    public static Stage getStage()
    {
        return stage;
    }

}
