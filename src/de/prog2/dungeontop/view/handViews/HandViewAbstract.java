package de.prog2.dungeontop.view.handViews;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.HBox;

import java.util.List;

public abstract class HandViewAbstract extends HBox
{
    public HandViewAbstract() {
        PlayerManager.getInstance().getPlayer().getHandCards().addListener(new ListChangeListener<Card>()
        {
            @Override
            public void onChanged(Change<? extends Card> c) {
                updateHand();
            }
        });
    }

    private void updateHand()
    {
        removeAll();
        for (Card card : PlayerManager.getInstance().getPlayer().getHandCards())
        {
            addCard(card);
        }
    }

    // TODO remove this magic number
    double handCardScale = ArenaViewConstants.HAND_PLAYER_Y / ArenaViewConstants.CARD_HEIGHT / 1.2;

    public void removeAll ()
    {
        getChildren().clear();
    }

    protected abstract void addCard(Card card);

    @Deprecated
    public void addCards (List<Card> listOfCards)
    {
        for (Card card : listOfCards)
        {
            addCard(card);
        }
    }
}
