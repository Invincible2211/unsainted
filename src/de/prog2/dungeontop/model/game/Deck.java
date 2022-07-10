package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.Serializable;
import java.util.Stack;

public class Deck implements Serializable
{
    private final Stack<Card> cards;

    public Deck()
    {
        this.cards = new Stack<>();
        GlobalLogger.log(LoggerStringValues.DECK_CREATED);
    }
    public Deck(Stack<Card> deck)
    {
        this.cards = deck;
        GlobalLogger.log(LoggerStringValues.DECK_CREATED);
    }

    public void pushCard(Card card)
    {
        cards.push(card);
        GlobalLogger.log(LoggerStringValues.CARD_PUSHED_TO_DECK);
    }

    public void removeCard(Card card)
    {
        cards.remove(card);
        GlobalLogger.log(LoggerStringValues.CARD_REMOVED_FROM_DECK);
    }
    public Card popCard()
    {
        GlobalLogger.log(LoggerStringValues.CARD_POPPED);
        return cards.pop();
    }

    public boolean containsCard(Card card)
    {
        GlobalLogger.log(LoggerStringValues.CARD_CONTAIN_IN_DECK + card.getClass().getName());
        return this.cards.contains(card);
    }

    //Set- and Getters
    public Stack<Card> getCards ()
    {
        return cards;
    }
}
