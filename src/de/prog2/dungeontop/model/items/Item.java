package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.Serializable;

public abstract class Item implements Serializable
{
    private final String name;
    private final String description;
    private final int assetID;

    public Item(String name, String description, int assetID)
    {
        this.name = name;
        this.description = description;
        this.assetID = assetID;
        GlobalLogger.log(String.format(LoggerStringValues.ITEM_CREATED, this.name, this.description, this.assetID));
    }

    public abstract boolean equip();

    //Set- and Getters
    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getAssetID()
    {
        return assetID;
    }
}
