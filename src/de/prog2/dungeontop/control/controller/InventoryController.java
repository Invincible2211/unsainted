package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.items.Item;

public class InventoryController
{
    /**
     * Adds an item to the player's inventory.
     * @return true if the item was added, false if not.
     */
    public static boolean addItem(Item item)
    {
        Inventory inventory = PlayerManager.getInstance().getPlayerInventory();
        if(inventory.getItems().size() >= 28)
        {
            return false;
        }
        inventory.addItem(item);
        return true;
    }

    /**
     * Removes the specified item from the player's inventory.
     */
    public static void removeItem(Item item)
    {
        PlayerManager.getInstance().getPlayerInventory().removeItem(item);
    }
}
