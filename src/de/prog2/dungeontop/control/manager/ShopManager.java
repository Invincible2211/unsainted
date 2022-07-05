package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.model.exceptions.customexceptions.CardAlreadyUnlockedException;
import de.prog2.dungeontop.model.exceptions.customexceptions.NotEnoughSoulsException;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.Set;

public class ShopManager
{
    // instance of the singleton
    private final static ShopManager instance = new ShopManager();

    private ShopManager (){
        GlobalLogger.log(LoggerStringValues.SHOPMANAGER_CREATED);
    }

    /**
     * Getter to get the instance of the shop used to unlock new cards for the drop pool.
     *
     * @return Instance of the shop
     */
    public static ShopManager getInstance (){
        GlobalLogger.log(LoggerStringValues.SHOPMANAGER_GET);
        return instance;
    }

    /**
     *
     * @param toUnlock Card which shall be unlocked.
     * @param price Price of the Card which shall be unlocked.
     * @throws CardAlreadyUnlockedException Thrown if the card has already been unlocked or doesn't exist.
     * @throws NotEnoughSoulsException Thrown if the Player doesn't own enough souls to unlock the card.
     */
    public void unlockCard (Card toUnlock, int price) throws CardAlreadyUnlockedException, NotEnoughSoulsException {
        PlayerManager playerManager = PlayerManager.getInstance();
        Player player = playerManager.getPlayer();

        if(CardManager.getLockedCards().contains(toUnlock)){
            if(player.getSouls() >= price){
                CardManager.addUnlockedCard(toUnlock);
                playerManager.removeSouls(price);
                CardManager.getLockedCards().remove(toUnlock);

                GlobalLogger.log(LoggerStringValues.CARD_UNLOCKED);

                return;
            }
            throw new NotEnoughSoulsException();
        }
        throw new CardAlreadyUnlockedException();
    }
}
