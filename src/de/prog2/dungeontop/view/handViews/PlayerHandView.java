package de.prog2.dungeontop.view.handViews;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.manager.BattleManager;
import de.prog2.dungeontop.control.manager.BattleManager2;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.view.ArenaController;

public class PlayerHandView extends HandViewAbstract
{
    @Override
    protected void addCard(Card card)
    {
        getChildren().add(CardViewController.getCardView(card, handCardScale, BattleManager2.getInstance().getCardDetailViewContainer()));
    }
}
