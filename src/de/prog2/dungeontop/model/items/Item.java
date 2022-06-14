package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public abstract class Item
{
    private final String name;
    private String description;
    private int price;
    private int assetID;

    public Item(String name, String description, int price, int assetID)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.assetID = assetID;
        GlobalLogger.log(String.format(LoggerStringValues.ITEM_CREATED, this.name, this.description, this.price, this.assetID));
    }

    public Item(String name, int assetID)
    {
        this.name = name;
        this.assetID = assetID;
        GlobalLogger.log(String.format(LoggerStringValues.ITEM_CREATED, this.name,this.assetID));
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
