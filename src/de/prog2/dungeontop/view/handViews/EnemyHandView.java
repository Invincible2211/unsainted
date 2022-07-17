package de.prog2.dungeontop.view.handViews;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import javafx.scene.layout.AnchorPane;

public class EnemyHandView extends PlayerHandView
{
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
        getChildren().add(new AnchorPane());
    }
}
