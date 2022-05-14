package de.prog2.dungeontop.model.game;


import java.util.LinkedList;

public class Deck {
    LinkedList<Card> deck;

    public Deck(LinkedList<Card> deck)
    {
        this.deck = deck;
    }

    public LinkedList<Card> getDeck()
    {
        return deck;
    }
    public void setDeck(LinkedList<Card> deck)
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
    public boolean containsCard(Card card)
    {
        return this.deck.contains(card);
    }
}
