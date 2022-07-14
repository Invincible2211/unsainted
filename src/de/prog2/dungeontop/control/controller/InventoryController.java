package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.items.Item;

public class InventoryController
{
    public static boolean addItem(Item item)
    {
        Inventory inventory = PlayerManager.getInstance().getPlayerInventory();
        if(inventory.getInventory().size() >= 28)
        {
            return false;
        }
        inventory.addItem(item);
        return true;
    }
    public static boolean removeItem(Item item)
    {
        PlayerManager.getInstance().getPlayerInventory().removeItem(item);
        return true;
    }
}
