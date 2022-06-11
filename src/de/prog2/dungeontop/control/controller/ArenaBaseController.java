package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.BattleManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.ArenaBaseView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.List;

public abstract class ArenaBaseController
{

    public static void init(ArenaBaseView arenaBaseView)
    {
       initBattlefield(arenaBaseView, BattleManager.getInstance().getArena().getHeight(), BattleManager.getInstance().getArena().getWidth());
    }

    //TODO BINDING THIS -> binddirectional maybe
    public static void updatePlayerHands (ArenaBaseView arenaBaseView, List<Card> handOne, List<Card> handTwo)
    {
        HBox handcontainer = arenaBaseView.getPlayerHandHBox();
        HBox secondHandContainer = arenaBaseView.getEnemyHandHBox();
        for (Card card : handOne)
        {
            handcontainer.getChildren().add(CardViewController.getCardView(card));
        }
        for (Card card : handTwo)
        {
            secondHandContainer.getChildren().add(CardViewController.getCardView(card));
        }
    }


    private static void initBattlefield(ArenaBaseView arenaBaseView, int height, int width)
    {
        GridPane gridPane = arenaBaseView.getBattlefieldGridPane();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pane pane = new Pane();
                Image image = AssetsManager.getImageByAssetId(22);
                ImageView iv = new ImageView();
                iv.setImage(image);
                pane.getChildren().add(iv);
                gridPane.add(pane, x, y);
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
