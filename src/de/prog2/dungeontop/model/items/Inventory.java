package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.LinkedList;
import java.util.List;

public class Inventory
{
    private final List<Item> inventory;

    public Inventory()
    {
        GlobalLogger.log(LoggerStringValues.INVENTORY_CREATED);
        this.inventory = new LinkedList<>();
    }
    public Inventory(List<Item> inventory)
    {
        GlobalLogger.log(LoggerStringValues.INVENTORY_CREATED);
        this.inventory = inventory;
    }

    public void addItem(Item item)
    {
        inventory.add(item);
        GlobalLogger.log(String.format(LoggerStringValues.ITEM_ADDED, item.getName()));
    }

    public void removeItem(Item item)
    {
        inventory.remove(item);
    }
    public boolean containsItem(Item item)
    {
        return this.inventory.contains(item);
    }

    //Set- and Getters
    public List<Item> getInventory()
    {
        return inventory;
    }
}
