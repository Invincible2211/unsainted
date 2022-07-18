package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.StringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.Serializable;

public abstract class Card implements Serializable
{
    private int rank = 1;
    private int price;
    private final int maxRank;
    private final int ID;
    private int summonCost;
    private boolean selected = false;

    public Card(int maxRank, int price, int rank, int summonCost, int ID)
    {
        GlobalLogger.log(LoggerStringValues.CARD_CREATED);
        this.maxRank = maxRank;
        this.price = price;
        this.rank = rank;
        this.summonCost = summonCost;
        this.ID = ID;
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public int getMaxRank()
    {
        return maxRank;
    }

    public int getPrice()
    {
        return price;
    }
    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getSummonCost()
    {
        return summonCost;
    }

    public void setSummonCost(int summonCost)
    {
        this.summonCost = summonCost;
    }

    public boolean isSelected ()
    {
        return selected;
    }

    public int getID ()
    {
        return this.ID;
    }

    public void setSelected (boolean selected)
    {
        this.selected = selected;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(StringValues.RANK);
        builder.append(this.getRank());
        builder.append(StringValues.OF);
        builder.append(this.getMaxRank());
        return builder.toString();
    }
}
