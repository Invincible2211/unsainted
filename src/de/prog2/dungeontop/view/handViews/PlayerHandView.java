package de.prog2.dungeontop.view.handViews;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.model.game.Card;

public class PlayerHandView extends HandViewAbstract
{
    @Override
    protected void addCard(Card card)
    {
        getChildren().add(CardViewController.getCardView(card, handCardScale));
    }
}
