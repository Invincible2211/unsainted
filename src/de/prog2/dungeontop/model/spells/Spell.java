package de.prog2.dungeontop.model.spells;

public abstract class Spell
{
    protected String name;

    protected int mana_cost;

    // definitely incomplete, maybe damage and heal = changes in health instead?
    protected int damage;
    protected int heal;
    protected int draw_card;


    protected abstract void effekt();

    public Spell (String name, int mana_cost)
    {
        this.name = name;
        this.mana_cost = mana_cost;
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

    public int getMC()
    {
        return mana_cost;
    }

    public void setMC(int mana_cost)
    {
        this.mana_cost = mana_cost;
    }

}
