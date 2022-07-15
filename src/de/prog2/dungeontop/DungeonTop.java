package de.prog2.dungeontop;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.controller.DeckController;
import de.prog2.dungeontop.control.controller.EntityViewController;
import de.prog2.dungeontop.control.controller.InventoryViewController;
import de.prog2.dungeontop.control.manager.BattleManager;
import de.prog2.dungeontop.control.manager.BattleManager2;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.Weapon;
import de.prog2.dungeontop.model.items.artifacts.ExtraSoulsArtifact;
import de.prog2.dungeontop.model.spells.Spell;
import de.prog2.dungeontop.model.spells.TestSpell;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.model.world.rooms.EmptyRoom;
import de.prog2.dungeontop.model.world.rooms.LavaPondRoom;
import de.prog2.dungeontop.resources.*;
import de.prog2.dungeontop.resources.items.ItemConstants;
import de.prog2.dungeontop.utils.HellGenerator;
import de.prog2.dungeontop.view.*;
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
        initialize();

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

        //MainMenueController.addMenuebar();
        //AudioManager.getInstance().playSound(990);

        //testBattle();

        //testSelectHero(primaryStage);
        //testInventory(primaryStage);
        //testEntityView();
        //testCardView();
        //testCardDetailView();
        //testHellView(scene);
        //testLavaPondView(primaryStage);
        //testHell();
    }

    private void testBattle()
    {
        BattleManager2.getInstance().startBattle(PlayerManager.getInstance().getPlayer(), new Player());
    }

    public void testHell()
    {
        Hell hell = new Hell(WorldConstants.HELL_SIZE, WorldConstants.HELL_SIZE);
        HellGenerator.initHell(hell);
        System.out.println(hell);
    }

    //<editor-fold defaultstate="collapsed" desc="testModularViews">
    public static void testEntityView()
    {
        List<Entity> entities = TestConstants.getTestEntities();
        entities.addAll(TestConstants.getTestEntities());
        ArrayList<Node> entityViews = new ArrayList<>();
        int i = 0;
        for (Entity entity : entities)
        {
            i++;
            entityViews.add(EntityViewController.getEntityView(entity, 0.2 + 0.1 * (i)));
        }
        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: #000000;");
        for (Node entityView : entityViews)
        {
            hBox.getChildren().add(entityView);
        }
        Scene scene = new Scene(hBox);
        getStage().setScene(scene);
    }
    public static void testCardView()
    {
        List<Card> cards = TestConstants.getTestCards();
        HBox box = new HBox();
        for (Card card : cards)
        {
            Node cardView = CardViewController.getCardView(card, 1);
            box.getChildren().add(cardView);
        }
        Scene scene = new Scene(box);
        getStage().setScene(scene);
    }
    public static void testCardDetailView()
    {
        List<Card> cards = TestConstants.getTestCards();
        HBox box = new HBox();
        for (Card card : cards)
        {
            Node cardView = CardViewController.getCardDetailView(card, 1);
            box.getChildren().add(cardView);
        }
        Scene scene = new Scene(box);
        getStage().setScene(scene);
    }
    //</editor-fold>

    public static void testHellView(Scene scene)
    {
        Hell hell = new Hell(WorldConstants.HELL_SIZE, WorldConstants.HELL_SIZE);
        HellGenerator.initHell(hell);
        HellView view = new HellView();
        Scene hellView = view.initHellView(hell);
        stage.setScene(hellView);

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
     */
    public static void testArenaView() throws Exception
    {
        Entity harald = new Minion("Harald", 6, 4, 1, 450);
        harald.setMaxMovement(2);
        Spell testSpell = new TestSpell();
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        for (int i = 0; i < 15; i++)
        {
//            deck1.pushCard(new SpellCard(testSpell, 5, 3, 1, 2 + i));
            deck1.pushCard(new EntityCard(harald, 5, 3, 1, 2, 0));
            deck2.pushCard(new EntityCard(harald, 5, 3, 1, 2, 0));
        }
        Player player1 = new Player(12, 10);
        player1.setDeck(deck1);
        Player player2 = new Player(12, 10);
        player2.setDeck(deck2);
        player1.setHandCardLimit(2);
        player2.setHandCardLimit(4);
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
        Item item = ItemConstants.minorPotion;
        Item item1 = ItemConstants.bread;
        Item weapon = ItemConstants.sword;
        Item weapon1 = new Weapon("Test", "1", 1, 662, 1);
        Item art1 = ItemConstants.necklace;
        Item art2 = ItemConstants.bracelet;
        Item art3 = new ExtraSoulsArtifact("Test", "1", 1, 662, 662);
        Item art4 = new ExtraSoulsArtifact("Test", "1", 1, 662, 662);
        PlayerManager.getInstance().getPlayerInventory().addItem(weapon);
        PlayerManager.getInstance().getPlayerInventory().addItem(item);
        PlayerManager.getInstance().getPlayerInventory().addItem(item1);
        PlayerManager.getInstance().getPlayerInventory().addItem(art1);
        PlayerManager.getInstance().getPlayerInventory().addItem(art2);
        PlayerManager.getInstance().getPlayerInventory().addItem(art3);
        PlayerManager.getInstance().getPlayerInventory().addItem(art4);
        PlayerManager.getInstance().getPlayerInventory().addItem(weapon1);
        Player player = PlayerManager.getInstance().getPlayer();
        player.setHero(SelectHeroConstants.MAGE);
        PlayerManager.getInstance().setPlayer(player);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.INVENTORY_FXML));
        InventoryViewController.initInventory(fxmlLoader.getController());
        Scene scene = new Scene(root);
        getStage().setScene(scene);
    }

    public static void testLavaPondView (Stage stage)
    {
        Entity harald = new Minion("Harald", 6, 4, 1, 45);
        Deck deck = new Deck();
        for (int i = 0; i < 30; i++)
        {
            deck.pushCard(new EntityCard(harald, 5, 3, 1, 2 + i, 0));
        }

        LavaPondRoom room = new LavaPondRoom(new EmptyRoom(new Coordinate(0,0), 1));
        PlayerManager.getInstance().addSouls(100);
        PlayerManager.getInstance().getPlayer().setDeck(deck);
        NpcRoomView view = new NpcRoomView(room);
        Scene scene = view.getNpcRoomView();
        stage.setScene(scene);
    }

    public static void testDeckCreation ()
    {
        System.out.println(DeckController.getRandomDeck(false));
    }

    public static Stage getStage()
    {
        return stage;
    }

    public static void initialize ()
    {
        SettingsController.initStage();
        RoomDialogueViewController.initStage();
        NetworkController.initStage();
        AvailableCards.INIT_CARD_DATA();
    }
}
