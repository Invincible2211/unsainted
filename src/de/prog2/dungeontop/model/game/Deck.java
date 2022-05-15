package de.prog2.dungeontop.model.game;

import java.util.Stack;

public class Deck
{
    Stack<Card> deck;

    public Deck()
    {
        this.deck = new Stack<>();
    }
    public Deck(Stack<Card> deck)
    {
        this.deck = deck;
    }

    public void pushCard(Card card)
    {
        deck.push(card);
    }

    public void removeCard(Card card)
    {
        deck.remove(card);
    }
    public Card popCard()
    {
        return deck.pop();
    }

    public boolean containsCard(Card card)
    {
        return this.deck.contains(card);
    }

    //Set- and Getters
    public Stack<Card> getDeck()
    {
        return deck;
    }
}
