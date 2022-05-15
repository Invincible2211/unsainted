package de.prog2.dungeontop.control.controller;

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
}
