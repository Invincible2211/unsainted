package de.prog2.dungeontop;

import de.prog2.dungeontop.control.controller.ArenaBaseController;
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
import de.prog2.dungeontop.model.items.TestItem;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.model.world.arena.ArenaComponent;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.TestConstants;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.HellGenerator;
import de.prog2.dungeontop.view.HellView;
import de.prog2.dungeontop.control.controller.InventoryController;
import de.prog2.dungeontop.view.RoomDialogueViewController;
import de.prog2.dungeontop.view.SettingsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

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

        fxmlLoader = new FXMLLoader();
        Parent parent = fxmlLoader.load((DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.LOBBY_FXML)));
        Scene scene1 = new Scene(parent);
        //stage.setScene(scene1);
        testArenaView();
        //testSelectHero(primaryStage);
        //testInventory(primaryStage);
        //testCardView(primaryStage);
        //testEntityView(primaryStage);
    }
    public static void testEntityView(Stage primaryStage) throws Exception
    {
        List<Entity> entities = TestConstants.getTestEntities();
        entities.addAll(TestConstants.getTestEntities());
        ArrayList<Node> entityViews = new ArrayList<>();
        int i = 0;
        for (Entity entity : entities)
        {
            i++;
            entityViews.add(EntityViewController.getEntityView(entity, 1));//0.1 + 0.05 * (i)));
        }
        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: #000000;");
        for (Node entityView : entityViews)
        {
            entityView.setScaleX(0.5);
            entityView.setScaleY(0.5);
            hBox.getChildren().add(entityView);
        }
        Scene scene = new Scene(hBox);
        scene.getStylesheets().add(ViewStrings.SHOP_VIEW_CSS);
        primaryStage.setScene(scene);
    }
    public static void testCardView(Stage primaryStage) throws Exception
    {
        List<Card> cards = TestConstants.getTestCards();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.SHOP_VIEW_FXML));
        ShopViewController.addCards(fxmlLoader.getController(), cards);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(ViewStrings.SHOP_VIEW_CSS);
        primaryStage.setScene(scene);
    }

    public static void testHellView(Scene scene)
    {
        Hell hell = new Hell(WorldConstants.HELL_SIZE, WorldConstants.HELL_SIZE);
        HellGenerator.initHell(hell);
        HellView view = new HellView();
        Scene hellView = view.initHellView(hell);

        Hell hell2 = new Hell(WorldConstants.HELL_SIZE, WorldConstants.HELL_SIZE);
        HellGenerator.initHell(hell2);
        HellGenerator.initHell(hell2);
        Scene hellView2 = view.initHellView(hell2);

        System.out.println(hell2);

        stage.setScene(scene);

        stage.setScene(hellView);
        stage.setScene(hellView2);
    }

    public static void testWorld ()
    {
        for (int i = 1; i<= 100000; i++)
        {
            Hell hell = new Hell(WorldConstants.HELL_SIZE, WorldConstants.HELL_SIZE);
            HellGenerator.initHell(hell);
            System.out.println("Hell No." + i +" von 100:");
            System.out.println(hell);
        }
    }

    /**
     * As we do not know that Unit tests exist and time is short this is where i put magic numbers for testing
     * @throws Exception
     */
    public static void testArenaView() throws Exception
    {
        Entity harald = new Minion("Harald", 6, 4, 1, 45);
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
        player1.setHandCardLimit(6);
        player2.setHandCardLimit(2);

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.ARENABASE_VIEW));
        BattleManager.getInstance().startBattle(player1, player2, player1.getDeck(), player2.getDeck(),new Arena(5, 5),fxmlLoader.getController());
        Scene scene = new Scene(root);
        scene.getStylesheets().add(ViewStrings.SHOP_VIEW_CSS);
        getStage().setScene(scene);
        harald.setPosition(new Coordinate(1, 1));
        BattleManager.getInstance().getArena().getArenaHashmap().put(new Coordinate(1, 1),new ArenaComponent(harald));
        ArenaBaseController.updateBattlefield(fxmlLoader.getController(), BattleManager.getInstance().getArena());
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
        TestItem item = new TestItem("Potion", AssetIds.HEALTH_POTION);
        Inventory inventory = new Inventory();
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
