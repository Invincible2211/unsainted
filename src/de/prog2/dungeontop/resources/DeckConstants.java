package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;

import java.util.LinkedList;
import java.util.Random;

public interface DeckConstants
{
    int DECK_MAX_NO_OF_CARDS = 35;
    int DECK_MIN_NO_OF_CARDS = 25;
    int DEFAULT_UNLOCKED_CARDS = 9;
    int CARD_MAX_RANK = 3;

    int PLAYERCARD_PRICE = 0;
    int PLAYERCARD_MAX_RANK = 0;
    int PLAYERCARD_RANK = PLAYERCARD_MAX_RANK;
    int PLAYERCARD_SUMMON_COST = 0;
    int PLAYERCARD_ID = -1;
}
