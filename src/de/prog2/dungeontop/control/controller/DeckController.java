package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.CardManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;
import de.prog2.dungeontop.resources.AvailableCards;
import de.prog2.dungeontop.resources.DeckConstants;
import de.prog2.dungeontop.resources.views.CardConstants;

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

    /**
     * Create a random deck.
     *
     * @param useAllCards true if the deck shall be created from all available cards,
     *                    false if it shall be created from the unlocked cards only
     * @return
     */
    public static Deck getRandomDeck (boolean useAllCards)
    {
        Deck randomDeck = new Deck();
        Random random = new Random();
        LinkedList<Card> cardsToUse = null;
        int numberOfCards = random.nextInt(DeckConstants.DECK_MAX_NO_OF_CARDS - DeckConstants.DECK_MIN_NO_OF_CARDS+1);

        // set which cards to use in the deck creation
        if (useAllCards)
            cardsToUse = new LinkedList<>(AvailableCards.AVAILABLE_CARDS);
        else
            cardsToUse = new LinkedList<>(CardManager.getInstance().getUnlockedCards());

        // until the desired number of cards is in the deck, put a pseudo-random one into the deck
        for (int i = 0; i < (DeckConstants.DECK_MIN_NO_OF_CARDS+numberOfCards); i++)
        {
            randomDeck.pushCard(cardsToUse.get(random.nextInt(cardsToUse.size())));
        }
        return randomDeck;
    }
}
