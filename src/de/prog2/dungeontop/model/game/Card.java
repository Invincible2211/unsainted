package de.prog2.dungeontop.model.game;

import java.util.HashMap;
import java.util.List;

public abstract class Card
{
    private int rank = 1;
    private int price;
    private final int maxRank;
    private final HashMap<AssetType, Integer> assetTypeHashMap = new HashMap<>();

    public enum AssetType
    {
        A,B,C
    }

    public Card(int maxRank, int price, int rank, List... assets)
    {
        this.maxRank = maxRank;
        this.price = price;
        this.rank = rank;

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
}
