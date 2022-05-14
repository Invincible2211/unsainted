package de.prog2.dungeontop.model.game;

public abstract class Card
{
    private int rank = 1;
    private int price;
    private final int maxRank;

    public Card(int maxRank, int price)
    {
        this.maxRank = maxRank;
        this.price = price;
    }
    public Card(int maxRank, int price, int rank)
    {
        this.maxRank = maxRank;
        this.price = price;
        this.rank = rank;
    }

    public Card(int maxRank, int price, int rank, int ego_points)
    {
        this.maxRank = maxRank;
        this.price = price;
        this.rank = rank;
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
}
