package de.prog2.dungeontop.model.game;

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deck;

    public Deck(ArrayList<Card> deck)
    {
        this.deck = deck;
    }

    public ArrayList<Card> getDeck()
    {
        return deck;
    }
    public void setDeck(ArrayList<Card> deck)
    {
        this.deck = deck;
    }
    public void addCard(Card card)
    {
        deck.add(card);
    }

    public void removeCard(Card card)
    {
        deck.remove(card);
    }
}
