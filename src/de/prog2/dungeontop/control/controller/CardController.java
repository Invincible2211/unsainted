package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.CardManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;

public abstract class CardController
{
    public static void increaseRank(Card card)
    {
        if (card.getRank() >= card.getMaxRank())
            return;
        Deck playerDeck = PlayerManager.getInstance().getPlayer().getDeck();
        playerDeck.removeCard(card);
        for (Card unlocked : CardManager.getInstance().getUnlockedCards())
        {
            if (card.getID() == (unlocked.getID() - 1))
            {
                playerDeck.pushCard(unlocked);
            }
        }
    }
}
