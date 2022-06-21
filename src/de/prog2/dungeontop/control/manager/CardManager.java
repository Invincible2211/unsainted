package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.control.file.GameSaveFileReader;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.HashSet;
import java.util.Set;

public class CardManager
{
    private final static CardManager instance = new CardManager();

    private Set<Card> unlockedCards = null;
    private Set<Card> lockedCards = null;

    private CardManager ()
    {
        initCardData();
        GlobalLogger.log(LoggerStringValues.CARD_MANAGER_CREATED);
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    public static Set<Card> getLockedCards() { return instance.lockedCards; }
    public  static Set<Card> getUnlockedCards () { return instance.unlockedCards; }
    public static void addUnlockedCard (Card card) { instance.unlockedCards.add(card); }
    public static CardManager getInstance() { return CardManager.instance; }
    private void initCardData ()
    {
        // TODO: Add reader for locked and unlocked cards
        // this.unlockedCards = GameSaveFileReader.getInstance().getPlayerSaveData().unlockedCards();
    }
}
