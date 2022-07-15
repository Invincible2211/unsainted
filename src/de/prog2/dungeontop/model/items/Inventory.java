package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Inventory implements Serializable
{
    private final List<Item> items;

    public Inventory()
    {
        GlobalLogger.log(LoggerStringValues.INVENTORY_CREATED);
        this.items = new LinkedList<>();
    }
    public Inventory(List<Item> items)
    {
        GlobalLogger.log(LoggerStringValues.INVENTORY_CREATED);
        this.items = items;
    }

    public void addItem(Item item)
    {
        items.add(item);
        GlobalLogger.log(String.format(LoggerStringValues.ITEM_ADDED, item.getName()));
    }

    public void removeItem(Item item)
    {
        items.remove(item);
    }
    public boolean containsItem(Item item)
    {
        return this.items.contains(item);
    }

    //Set- and Getters
    public List<Item> getItems()
    {
        return items;
    }
}
