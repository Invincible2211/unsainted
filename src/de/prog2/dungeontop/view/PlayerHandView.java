package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import de.prog2.dungeontop.view.cardViews.CardView;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.List;

public class PlayerHandView extends HandViewAbstract
{
    private int currentHandCardsSize = 0;

    double handCardScale = ArenaViewConstants.HAND_PLAYER_Y / ArenaViewConstants.CARD_HEIGHT;

    public void removeAll ()
    {
        super.getChildren().clear();
    }

    public void addCard(Card card)
    {
        this.getChildren().add(CardViewController.getCardView(card, handCardScale));
    }

    public void addCards (List<Card> listOfCards)
    {
        for (Card card : listOfCards)
        {
            addCard(card);
        }
    }

    public void removeCard(Card card)
    {
        super.getChildren().get(super.getChildren().indexOf(card));
    }

    public int getCurrentHandCardsSize() {
        return super.getChildren().size();
    }

}
