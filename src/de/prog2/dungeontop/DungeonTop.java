package de.prog2.dungeontop;

import de.prog2.dungeontop.control.controller.EntityViewController;
import de.prog2.dungeontop.control.controller.ShopViewController;
import de.prog2.dungeontop.control.manager.BattleManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.TestItem;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.view.*;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.HellGenerator;
import de.prog2.dungeontop.view.SettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

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
        //AudioManager.getInstance().playSound(99);
        SettingsController.initStage();
        RoomDialogueViewController.initStage();

        //testArenaView();
        //testSelectHero(primaryStage);
        testInventory(primaryStage);
        //testCardView(primaryStage);
        //testEntityView(primaryStage);
        //testHellView(scene);
    }
    public static void testEntityView(Stage primaryStage) throws Exception
    {
        Entity entity = new Minion("Harald", 6, 4, 3, 19);
        Scene scene = new Scene((Parent) Objects.requireNonNull(EntityViewController.getEntityView(entity)));
        scene.getStylesheets().add("view/shopView.css");
        primaryStage.setScene(scene);
    }
    public static void testCardView(Stage primaryStage) throws Exception
    {
        Entity entity = new Minion("Harald", 6, 4, 1, 19);
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 6; i++)
        {
            cards.add(new EntityCard(entity, 6, 100, 1 + i, 2));
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream("view/shopView.fxml"));
        ShopViewController.addCards(fxmlLoader.getController(), cards);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("view/shopView.css");
        primaryStage.setScene(scene);
    }

    public static void testHellView(Scene scene) throws Exception
    {
        Hell hell = new Hell(WorldConstants.HELL_SIZE, WorldConstants.HELL_SIZE);
        HellGenerator.initHell(hell);
        HellView view = new HellView();
        Scene hellView = view.initHellView(hell);
        //view.initPlayerCamera(scene);
        //view.initOverlay(hellView);

        stage.setScene(scene);
        stage.setScene(hellView);


//        System.out.println(hell);
    }

    public static void testArenaView() throws Exception
    {
        Entity harald = new Minion("Harald", 6, 4, 1, 19);
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        for (int i = 0; i < 10; i++)
        {
            deck1.pushCard(new EntityCard(harald, 5, 3, 1, 2 + i));
            deck2.pushCard(new EntityCard(harald, 5, 3, 1, 1 + i));
        }
        Player player1 = new Player(12, 10);
        player1.setDeck(deck1);
        Player player2 = new Player(12, 10);
        player2.setDeck(deck2);
        player1.setHandCardLimit(5);
        player2.setHandCardLimit(5);


        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.ARENABASE_VIEW));
        BattleManager.getInstance().startBattle(player1, player2, player1.getDeck(), player2.getDeck(),new Arena(5, 5),fxmlLoader.getController());
        Scene scene = new Scene(root);
        getStage().setScene(scene);

    }

    public static void testSelectHero(Stage stage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.SELECT_HERO_FXML));
        Scene scene = new Scene(root);
        getStage().setScene(scene);
    }

    public static void testInventory(Stage stage) throws Exception
    {
        TestItem item = new TestItem("Potion",66);
        Inventory inventory = new Inventory(new LinkedList<Item>());
        for (int i = 0; i < 8; i++)
        {
            inventory.addItem(item);
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.INVENTORY_FXML));
        InventoryController.addItems(fxmlLoader.getController(), inventory.getInventory());
        Scene scene = new Scene(root);
        getStage().setScene(scene);
    }
    public static Stage getStage()
    {
        return stage;
    }

}
