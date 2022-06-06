package de.prog2.dungeontop;

import de.prog2.dungeontop.control.controller.ShopViewController;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.view.SettingsController;
import de.prog2.dungeontop.utils.HellGenerator;
import de.prog2.dungeontop.view.HellView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        //AudioManager.getInstance().playSound(99);
        SettingsController.initStage();

//        testCardView(primaryStage);
    }
    public static void testCardView(Stage primaryStage) throws Exception
    {
        var card = new EntityCard(3, 100, 1, 2);
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

    public static void testArena() throws  Exception
    {
        public static void testArenaView() throws Exception
        {
            Entity harald = new Minion(6, 4, 1, "Harald");
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

            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream("view/arenaView.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("view/arenaView.css");
            getStage().setScene(scene);
        }
    }

    public static Stage getStage()
    {
        return stage;
    }

}
