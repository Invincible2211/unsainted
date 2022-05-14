package de.prog2.dungeontop.model.inventory;

import de.prog2.dungeontop.model.items.Item;

import java.util.LinkedList;

public class Inventory {
    LinkedList<Item> inventory;

    public Inventory(LinkedList<Item> inventory)
    {
        this.inventory = inventory;
    }

    public void addItems(Item neu)
    {
        inventory.add(neu);
    }

    public void removeItems(Item delete)
    {
        inventory.remove(delete);
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
