package de.prog2.dungeontop.model.items;

import java.util.LinkedList;
import java.util.List;

public class Inventory
{
    private final List<Item> inventory;

    public Inventory()
    {
        this.inventory = new LinkedList<>();
    }
    public Inventory(List<Item> inventory)
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
    public List<Item> getInventory()
    {
        return inventory;
    }

    public void setInventory(List<Item> inventory)
    {
        this.inventory = inventory;
    }
}
