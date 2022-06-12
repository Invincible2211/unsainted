package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.BattleManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.ArenaBaseView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;

public abstract class ArenaBaseController
{

    //TODO changable bg
    public static void init(ArenaBaseView arenaBaseView)
    {
       initBattlefield(arenaBaseView, BattleManager.getInstance().getArena().getHeight(),
               BattleManager.getInstance().getArena().getWidth());
       initEgoPoints(arenaBaseView);
       setBackgroundImage(arenaBaseView, AssetIds.ARENA_BG_DEFAULT_ID);
       arenaBaseView.getBackGroundAnchorPane().setPrefSize(ViewStrings.RESOLUTION_X, ViewStrings.RESOLUTION_Y);
       arenaBaseView.getBackGroundAnchorPane().setMaxSize(ViewStrings.RESOLUTION_X, ViewStrings.RESOLUTION_Y);

       //Check if this helps scaling the hand down to the right size
        arenaBaseView.getBorderPaneID().getTop().prefHeight(ViewStrings.HAND_PLAYER_Y);
        arenaBaseView.getBorderPaneID().getBottom().prefHeight(ViewStrings.HAND_PLAYER_Y);
        arenaBaseView.getBorderPaneID().getTop().maxHeight(ViewStrings.HAND_PLAYER_Y);
        arenaBaseView.getBorderPaneID().getTop().maxWidth(ViewStrings.HAND_PLAYER_Y);
        arenaBaseView.getBorderPaneID().getBottom().maxHeight(ViewStrings.HAND_PLAYER_Y);

    }

    public static void setBackgroundImage(ArenaBaseView arenaBaseView, int imageID)
    {
        Image image = AssetsManager.getImageByAssetId(imageID);
        BackgroundImage myBI= new BackgroundImage(image, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.DEFAULT,
                new BackgroundSize(ViewStrings.RESOLUTION_X, ViewStrings.RESOLUTION_Y, false, false, false, false));
        arenaBaseView.getBackGroundAnchorPane().setBackground(new Background(myBI));
    }

    /**
     * die Aufteilung auf player one und player two soll soaeter dabei helfen DM und Spieler in der Controlle zu unterscheiden
     * @param arenaBaseView
     */
    private static void initEgoPoints (ArenaBaseView arenaBaseView)
    {
        ImageView egoPointsPlayerOneImageView = arenaBaseView.getEgopointsPlayerOneImageView();
        ImageView egoPointsPlayerTwoImageView = arenaBaseView.getEgopointsPlayerTwoImageView();
        egoPointsPlayerOneImageView.setImage(AssetsManager.getImageByAssetId(AssetIds.SUMMON_COST_ICON));
        egoPointsPlayerTwoImageView.setImage(AssetsManager.getImageByAssetId(AssetIds.SUMMON_COST_ICON));
        egoPointsPlayerOneImageView.setFitHeight(ViewStrings.EGOPOINTS_BACKROUND_HEIGHT);
        egoPointsPlayerOneImageView.setFitWidth(ViewStrings.EGOPOINTS_BACKROUND_WIDTH);
        egoPointsPlayerTwoImageView.setFitHeight(ViewStrings.EGOPOINTS_BACKROUND_HEIGHT);
        egoPointsPlayerTwoImageView.setFitWidth(ViewStrings.EGOPOINTS_BACKROUND_WIDTH);

    }

    //TODO BINDING THIS -> binddirectional maybe
    //TODO Nicht die skalierung aendern sondern die Ueberlappung bei groSen Haenden
    public static void updatePlayerHands (ArenaBaseView arenaBaseView, List<Card> handOne, List<Card> handTwo)
    {
        HBox handcontainer = arenaBaseView.getPlayerHandHBox();
        handcontainer.setPrefSize(ViewStrings.HAND_PLAYER_X, 40);
        handcontainer.setMaxSize(ViewStrings.HAND_PLAYER_X, 40);
        HBox secondHandContainer = arenaBaseView.getEnemyHandHBox();
        secondHandContainer.setPrefSize(ViewStrings.HAND_PLAYER_X, 40);
        secondHandContainer.setMaxSize(ViewStrings.HAND_PLAYER_X, 40);
        double scal = (ViewStrings.HAND_PLAYER_X / handOne.size())/ViewStrings.CARD_WIDTH;
        scal = scal * 0.8;
        for (Card card : handOne)
        {
            Node cardViewNode = CardViewController.getCardView(card);
            cardViewNode.setScaleX(scal);
            cardViewNode.setScaleY(scal);
            handcontainer.getChildren().add(cardViewNode);
        }
        for (Card card : handTwo)
        {
            Node cardViewNode = CardViewController.getCardView(card);
            cardViewNode.setScaleX(scal);
            cardViewNode.setScaleY(scal);
            secondHandContainer.getChildren().add(cardViewNode);
        }
    }

    /**
     *
     * @param arenaBaseView the view to update
     * @param egoPointsOne the amount of ego points of player one
     * @param egoPointsTwo the amount of ego points of player two
     */
    public static void updateEgoPoints (ArenaBaseView arenaBaseView, int egoPointsOne, int egoPointsTwo)
    {
        arenaBaseView.getEgopointsPlayerOne().setText(String.valueOf(egoPointsOne));
        arenaBaseView.getEgopointsPlayerTwo().setText(String.valueOf(egoPointsTwo));
    }

    private static void initBattlefield(ArenaBaseView arenaBaseView, int height, int width)
    {
        GridPane gridPane = arenaBaseView.getBattlefieldGridPane();
        gridPane.setHgap(ViewStrings.BATTLEFIELD_HGAP_DEFAULT);
        gridPane.setVgap(ViewStrings.BATTLEFIELD_VGAP_DEFAULT);
        double size = ViewStrings.BATTLEFIELDSIZE_Y / height;
        gridPane.setAlignment(Pos.CENTER);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                StackPane stackPane = new StackPane();
                Image background = AssetsManager.getImageByAssetId(AssetIds.BATTLEFIELDGRIDPANE_BACKGROUND_IMAGEID);
                ImageView imageView = new ImageView();
                imageView.setImage(background);
                imageView.setFitWidth(size);
                imageView.setFitHeight(size);
                stackPane.getChildren().add(imageView);
                gridPane.add(stackPane, x, y);
            }
        }
    }


    public static Node getBattleFieldPane(ArenaBaseView arenaBaseView, int x,int y)
    {
        for (Node node : arenaBaseView.getBattlefieldGridPane().getChildren()) {
            if (arenaBaseView.getBattlefieldGridPane().getColumnIndex(node) == y && arenaBaseView.getBattlefieldGridPane().getRowIndex(node) == x) {
                GlobalLogger.log(LoggerStringValues.RETURN_NODE_ON_BATTLEFIELD + node.toString());
                return node;
            }
        }
        GlobalLogger.warning(LoggerStringValues.COULD_NOT_FIND_NODE_ON_BATTLEFIELD + x + " " +y);
        return null;
    }




}
