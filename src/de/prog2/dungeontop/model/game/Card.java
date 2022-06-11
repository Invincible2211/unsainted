package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.HashMap;
import java.util.List;

public abstract class Card
{
    private int rank = 1;
    private int price;
    private final int maxRank;
    private int summonCost;
    private final HashMap<AssetType, Integer> assetTypeHashMap = new HashMap<>();

    public enum AssetType
    {
        A,B,C
    }

    public Card(int maxRank, int price, int rank, int summonCost, List... assets)
    {
        GlobalLogger.log(LoggerStringValues.CARD_CREATED);
        this.maxRank = maxRank;
        this.price = price;
        this.rank = rank;
        this.summonCost = summonCost;

        for (var asset : assets)
        {
            assetTypeHashMap.put((AssetType) asset.get(0), (Integer) asset.get(1));
        }
    }

    public int getAssetId (AssetType type)
    {
        return assetTypeHashMap.get(type);
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }
    public void increaseRank()
    {
        rank = rank < maxRank ? rank + 1 : maxRank;
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
}
