package de.prog2.dungeontop.model.game;

import java.util.LinkedList;

public class Deck {
    LinkedList<Card> deck;

    public Deck()
    {
        this.deck = new LinkedList<>();
    }

    public void addCard(Card card)
    {
        deck.add(card);
    }

    public void removeCard(Card card)
    {
        deck.remove(card);
    }

    public boolean containsCard(Card card)
    {
        return this.deck.contains(card);
    }

    //Set- and Getters
    public LinkedList<Card> getDeck()
    {
        return deck;
    }
}
