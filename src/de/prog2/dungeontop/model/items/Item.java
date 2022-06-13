package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public abstract class Item
{
    private final String name;
    private String description;
    private int price;

    public Item(String name, String description, int price)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        GlobalLogger.log(String.format(LoggerStringValues.ITEM_CREATED, this.name, this.description, this.price));
    }

    //Set- and Getters
    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
