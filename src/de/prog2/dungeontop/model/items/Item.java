package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public abstract class Item
{
    private final String name;
    private int price;

    public Item(String name, int price)
    {
        this.name = name;
        this.price = price;
        GlobalLogger.log(String.format(LoggerStringValues.ITEM_CREATED, this.name, this.price));
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

    public void setPrice(int price)
    {
        this.price = price;
    }
}
