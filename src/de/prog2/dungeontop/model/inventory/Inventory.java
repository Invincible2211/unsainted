package de.prog2.dungeontop.model.inventory;

import de.prog2.dungeontop.model.items.Item;

import java.util.LinkedList;

public class Inventory {
    LinkedList<Item> inventory;

    public Inventory(LinkedList<Item> inventory)
    {
        this.inventory = inventory;
    }

    public void addItem(Item item)
    {
        inventory.add(item);
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
    public LinkedList<Item> getInventory()
    {
        return inventory;
    }

    public void setInventory(LinkedList<Item> inventory)
    {
        this.inventory = inventory;
    }
}
