package de.prog2.dungeontop.model.game;

import java.util.Stack;

public class Deck
{
    private Stack<Card> cards;

    public Deck()
    {
        this.cards = new Stack<>();
    }
    public Deck(Stack<Card> deck)
    {
        this.cards = deck;
    }

    public void pushCard(Card card)
    {
        cards.push(card);
    }

    public void removeCard(Card card)
    {
        cards.remove(card);
    }
    public Card popCard()
    {
        return cards.pop();
    }

    public boolean containsCard(Card card)
    {
        return this.cards.contains(card);
    }

    //Set- and Getters
    public Stack<Card> getCards ()
    {
        return cards;
    }
}
