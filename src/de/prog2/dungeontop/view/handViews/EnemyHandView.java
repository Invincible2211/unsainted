package de.prog2.dungeontop.view.handViews;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import de.prog2.dungeontop.resources.views.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.cardViews.EnemyCardView;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EnemyHandView extends HandViewAbstract
{
    public EnemyHandView ()
    {
        for (int i = 0; i < GameManager.getInstance().getOpponentPlayer().getHandCardLimit(); i++)
        {
            addOne();
        }
    }

    @Override
    protected void addCard(Card card)
    {
        getChildren().add(CardViewController.getEnemyCardView(ArenaViewConstants.HAND_CARD_SCALE));
    }

    public void removeOne()
    {
        if (!getChildren().isEmpty()){
            getChildren().remove(0);
        }
    }

    public void addOne() {
        getChildren().add(CardViewController.getEnemyCardView(ArenaViewConstants.HAND_CARD_SCALE));
    }
}
