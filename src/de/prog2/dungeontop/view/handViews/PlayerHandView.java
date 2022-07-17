package de.prog2.dungeontop.view.handViews;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.manager.BattleManager2;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import javafx.collections.ListChangeListener;

public class PlayerHandView extends HandViewAbstract
{
    public PlayerHandView()
    {
        PlayerManager.getInstance().getPlayer().getHandCards().addListener((ListChangeListener<Card>) c -> updateHand());
    }

    private void updateHand()
    {
        removeAll();
        for (Card card : PlayerManager.getInstance().getPlayer().getHandCards())
        {
            addCard(card);
        }
    }
    @Override
    protected void addCard(Card card)
    {
        getChildren().add(CardViewController.getCardView(card, ArenaViewConstants.HAND_CARD_SCALE,
                BattleManager2.getInstance().getCardDetailViewContainer()));
    }
}
