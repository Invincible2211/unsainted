package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;

import java.util.LinkedList;
import java.util.Random;

public interface DeckConstants
{
    int DECK_MAX_NO_OF_CARDS = 35;
    int DECK_MIN_NO_OF_CARDS = 25;

    public static Deck GET_RANDOM_DECK ()
    {
        Deck randomDeck = new Deck();
        LinkedList<Card> allCards = new LinkedList<>(AvailableCards.AVAILABLE_CARDS);
        Random random = new Random();
        int numberOfCards = random.nextInt(DECK_MAX_NO_OF_CARDS-DECK_MIN_NO_OF_CARDS+1);

        for (int i = 0; i < (DECK_MIN_NO_OF_CARDS+numberOfCards); i++)
        {
            randomDeck.pushCard(allCards.get(random.nextInt(allCards.size())));
        }

        return randomDeck;
    }
}
