package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public abstract class Item
{
    private final String name;
    private final String description;
    private int value; // value for heals, etc
    private BonusType bonusType;
    private int price;
    private final int assetID;

    public Item(String name, String description, int value, int price, BonusType bonusType, int assetID) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.price = price;
        this.bonusType = bonusType;
        this.assetID = assetID;
        GlobalLogger.log(String.format(LoggerStringValues.ITEM_CREATED, this.name, this.description, this.value, this.price, this.bonusType, this.assetID));
    }

    public abstract void use();

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

    public BonusType getBonusType()
    {
        return bonusType;
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
