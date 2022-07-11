package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.CardManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;
import de.prog2.dungeontop.resources.AvailableCards;
import de.prog2.dungeontop.resources.DeckConstants;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class DeckController
{
    /**
     * Shuffles the List of cards in the deck
     */
    public static void shuffleDeck(Deck deck)
    {
        Collections.shuffle(deck.getCards());
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

    public static Deck getRandomDeck ()
    {
        Deck randomDeck = new Deck();
        LinkedList<Card> allCards = new LinkedList<>(CardManager.getInstance().getUnlockedCards());
        Random random = new Random();
        int numberOfCards = random.nextInt(DeckConstants.DECK_MAX_NO_OF_CARDS - DeckConstants.DECK_MIN_NO_OF_CARDS+1);

        for (int i = 0; i < (DeckConstants.DECK_MIN_NO_OF_CARDS+numberOfCards); i++)
        {
            randomDeck.pushCard(allCards.get(random.nextInt(allCards.size())));
        }

        return randomDeck;
    }
}
