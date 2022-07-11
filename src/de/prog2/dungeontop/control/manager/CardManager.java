package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.control.file.GameSaveFileReader;
import de.prog2.dungeontop.control.file.GameSaveFileWriter;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.resources.AvailableCards;
import de.prog2.dungeontop.resources.DeckConstants;
import de.prog2.dungeontop.resources.EntityCardEnum;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CardManager implements Serializable
{
    private static CardManager instance = GameSaveFileReader.getInstance().getSaveGame() == null ?
            new CardManager() : GameManager.getInstance().getSaveGame().getCardManager();

    private final HashSet<Card> unlockedCards = new HashSet<>();
    private final HashSet<Card> lockedCards = new HashSet<>();

    private CardManager ()
    {
        GameManager.getInstance().getSaveGame().setCardManager(this);
        GlobalLogger.log(LoggerStringValues.CARD_MANAGER_CREATED);
    }

    public EntityCard getPlayerCard (Player player)
    {
        EntityCard playerCard = new EntityCard(player.getHero(), DeckConstants.PLAYERCARD_MAX_RANK,
                DeckConstants.PLAYERCARD_PRICE,DeckConstants.PLAYERCARD_RANK, DeckConstants.PLAYERCARD_SUMMON_COST,
                DeckConstants.PLAYERCARD_ID);

        return playerCard;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    public HashSet<Card> getLockedCards() { return instance.lockedCards; }
    public HashSet<Card> getUnlockedCards () { return instance.unlockedCards; }
    public void addUnlockedCard (Card card) { instance.unlockedCards.add(card); }
    public static CardManager getInstance() { return CardManager.instance; }
    public static void setCardManager (CardManager instance)
    {
        CardManager.instance = instance;
    }
}
