package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public class Item
{
    private final String name;
    private final String description;
    private int value; // value for heals, etc
    private ItemType type;
    private int price;
    private final int assetID;

    public Item(String name, String description, int value, int price, int assetID) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.price = price;
        this.assetID = assetID;
        GlobalLogger.log(String.format(LoggerStringValues.ITEM_CREATED, this.name, this.description, this.value, this.price, this.assetID));
    }

    public Item(String name, String description, int assetID)
    {
        this.name = name;
        this.description = description;
        this.assetID = assetID;
        GlobalLogger.log(String.format(LoggerStringValues.ITEM_CREATED, this.name, this.description, this.price, this.assetID));
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

    public String getDescription()
    {
        return description;
    }

    public ItemType getType()
    {
        return type;
    }

    public int getValue()
    {
        return value;
    }

    public int getAssetID()
    {
        return assetID;
    }
}
