package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import de.prog2.dungeontop.view.cardViews.CardView;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandView extends HandViewAbstract
{
    private int currentHandCardsSize = 0;

    public PlayerHandView() {
        PlayerManager.getInstance().getPlayer().getHandCards().addListener(new ListChangeListener<Card>() {
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

    double handCardScale = ArenaViewConstants.HAND_PLAYER_Y / ArenaViewConstants.CARD_HEIGHT;

    public void removeAll ()
    {
        super.getChildren().clear();
    }

    private void addCard(Card card)
    {
        this.getChildren().add(CardViewController.getCardView(card, handCardScale));
    }

    @Deprecated
    public void addCards (List<Card> listOfCards)
    {
        for (Card card : listOfCards)
        {
            addCard(card);
        }
    }

    @Deprecated
    private void removeCard(Card card)
    {
        super.getChildren().get(super.getChildren().indexOf(card));
    }

    @Deprecated
    public int getCurrentHandCardsSize() {
        return super.getChildren().size();
    }

}
