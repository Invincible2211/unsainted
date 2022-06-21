package de.prog2.dungeontop.model.items;

public abstract class Artifact extends Item
{
    public Artifact(String name, int price)
    {
        super(name, price);
    }

    public Artifact(String name, int price, int bonus)
    {
        super(name, price, bonus);
    }
}
