package de.prog2.dungeontop.view.handViews;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.model.game.Card;

public class EnemyHandView extends PlayerHandView
{
    @Override
    protected void addCard(Card card)
    {
        getChildren().add(CardViewController.getEnemyCardView(handCardScale));
    }
}
