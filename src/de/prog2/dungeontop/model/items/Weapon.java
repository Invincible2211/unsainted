package de.prog2.dungeontop.model.items;

public abstract class Weapon extends Item
{
    public Weapon(String name, int price, int bonus)
    {
        super(name, price, bonus);
    }

    public Weapon(String name, int price, int bonus, int bonus2)
    {
        super(name, price, bonus, bonus2);
    }

}
