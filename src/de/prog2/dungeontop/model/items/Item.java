package de.prog2.dungeontop.model.items;

public abstract class Item
{
    private String name;
    private int price;
    private int bonus; // int for bonuses that weapon or artifacts provide
    private int bonus2; // e.g bonus health and damage, etc.

    public Item(String name, int price)
    {
        this.name = name;
        this.price = price;
    }

    public Item(String name, int price, int bonus)
    {
        this.name = name;
        this. price = price;
        this.bonus = bonus;
    }

    public Item(String name, int price, int bonus, int bonus2)
    {
        this.name = name;
        this. price = price;
        this.bonus = bonus;
        this.bonus2 = bonus2;
    }

    //Set- and Getters
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getBonus()
    {
        return bonus;
    }

    public void setBonus(int bonus)
    {
        this.bonus = bonus;
    }

    public int getBonus2()
    {
        return bonus2;
    }

    public void setBonus2(int bonus2)
    {
        this.bonus2 = bonus2;
    }
}
