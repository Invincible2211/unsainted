package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.model.exceptions.customexceptions.CardAlreadyMaxRankException;
import de.prog2.dungeontop.model.exceptions.customexceptions.CardNotFoundException;
import de.prog2.dungeontop.model.exceptions.customexceptions.ItemNotFoundException;
import de.prog2.dungeontop.model.exceptions.customexceptions.NotEnoughSoulsException;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.Valuable;
import de.prog2.dungeontop.utils.GlobalLogger;

public class NpcController
{
    /**
     * Method to buy an upgrade for the rank of the specified card.
     *
     * @param card Card which shall be upgraded.
     * @param price Cost of the upgrade.
     * @throws CardAlreadyMaxRankException Thrown if the card already reached the maximum defined level.
     * @throws NotEnoughSoulsException Thrown if the player doesn't own enough souls for the upgrade.
     */
    public static void upgradeCard (Card card, int price) throws CardAlreadyMaxRankException, NotEnoughSoulsException
    {
        PlayerManager playerManager = PlayerManager.getInstance();
        Player player = playerManager.getPlayer();

        if(card.getMaxRank() > card.getRank()){
            if( player.getSouls() >= price){
                card.increaseRank();
                playerManager.removeSouls(price);

                GlobalLogger.log(LoggerStringValues.CARD_RANK_INCREASED);

                return;
            }
            throw new NotEnoughSoulsException();
        }
        throw new CardAlreadyMaxRankException();
    }

    /**
     * Removes a specified card from the player's card deck.
     *
     * @param card Card which shall be removed from the player's card deck
     * @param price Cost for removing the specified card from the player's deck.
     * @throws NotEnoughSoulsException Thrown if the player doesn't own enough souls to remove the card from his deck.
     */
    public static void removeCard (Card card, int price) throws NotEnoughSoulsException
    {
        PlayerManager playerManager = PlayerManager.getInstance();
        Player player = playerManager.getPlayer();

        if(player.getSouls() >= price){
            player.getDeck().removeCard(card);
            playerManager.removeSouls(price);

            GlobalLogger.log(LoggerStringValues.CARD_REMOVED_FROM_DECK);

            return;
        }
        throw new NotEnoughSoulsException();
    }

    // TODO: Remove this method. It is no longer needed.
    /**
     * Sells a valuable item from the player's possession.
     *
     * @param item Item or Card which shall be sold.
     * @throws CardNotFoundException Thrown if the specified item is a card which the player's deck doesn't contain.
     * @throws ItemNotFoundException Thrown if the specified item is an item which the player doesn't possess.
     */
    public static void sellItem (Valuable item) throws CardNotFoundException, ItemNotFoundException
    {
        PlayerManager playerManager = PlayerManager.getInstance();
        Player player = playerManager.getPlayer();

        if(item instanceof Card)
        {
            if(player.getDeck().containsCard((Card) item)){
                playerManager.addSouls(((Card) item).getPrice());
                player.getDeck().removeCard((Card) item);

                GlobalLogger.log(LoggerStringValues.CARD_FROM_DECK_SOLD);

                return;
            }
            throw new CardNotFoundException();
        } else if (item instanceof Item) {
            if(player.getInventory().containsItem((Item) item)){
                playerManager.addSouls(((Item) item).getPrice());
                player.getInventory().removeItem((Item) item);

                GlobalLogger.log(LoggerStringValues.ITEM_SOLD);

                return;
            }
            throw new ItemNotFoundException();
        }
    }
}
