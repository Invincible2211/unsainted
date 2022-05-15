package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;
import java.util.Collections;

public class DeckController
{
    /**
     * Shuffles the List of cards in the deck
     */
    public static void shuffleDeck(Deck deck)
    {
        Collections.shuffle(deck.getDeck());
    }

    /**
     * Draw the first card on the deck
     */
    public static Card drawCard(Deck deck)
    {
        return deck.popCard();
    }

    /**
     * Add a card to the deck
     */
    public static void pushCard(Deck deck, Card card)
    {
        deck.pushCard(card);
    }
}
