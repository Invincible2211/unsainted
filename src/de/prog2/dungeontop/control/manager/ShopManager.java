package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.model.exceptions.customexceptions.CardAlreadyUnlockedException;
import de.prog2.dungeontop.model.exceptions.customexceptions.NotEnoughGoldException;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.Set;

public class ShopManager {
    // instance of the singleton
    private final static ShopManager shop = new ShopManager();

    // class attributes
    private Set lockedCards;

    private ShopManager (){
        this.lockedCards = GameManager.getLockedCards();
        GlobalLogger.log(LoggerStringValues.SHOPMANAGER_CREATED);
    }

    /**
     * Getter to get the instance of the shop used to unlock new cards for the drop pool.
     *
     * @return Instance of the shop
     */
    public ShopManager getShop (){
        GlobalLogger.log(LoggerStringValues.SHOPMANAGER_GET);
        return shop;
    }

    /**
     *
     * @param toUnlock Card which shall be unlocked.
     * @param price Price of the Card which shall be unlocked.
     * @throws CardAlreadyUnlockedException Thrown if the card has already been unlocked or doesn't exist.
     * @throws NotEnoughGoldException Thrown if the Player doesn't own enough gold to unlock the card.
     */
    public void unlockCard (Card toUnlock, int price) throws CardAlreadyUnlockedException, NotEnoughGoldException {
        PlayerManager playerManager = PlayerManager.getInstance();
        Player player = playerManager.getPlayer();

        if(this.lockedCards.contains(toUnlock)){
            if(player.getGoldValue() >= price){
                PlayerManager.addUnlockedCard(toUnlock);
                playerManager.removeGold(price);
                this.lockedCards.remove(toUnlock);

                GlobalLogger.log(LoggerStringValues.CARD_UNLOCKED);

                return;
            }
            throw new NotEnoughGoldException();
        }
        throw new CardAlreadyUnlockedException();
    }
}
