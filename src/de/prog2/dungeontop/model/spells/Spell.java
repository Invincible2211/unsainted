package de.prog2.dungeontop.model.spells;

public abstract class Spell
{
    private String name;
    private int mana_cost;
    private int num; //number for damage,heal,cards drawn, etc

    public Spell (String name, int mana_cost, int num)
    {
        this.name = name;
        this.mana_cost = mana_cost;
        this.num = num;
    }

    protected abstract void effekt();

    //Set- and Getters
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public int getMana_cost()
    {
        return mana_cost;
    }

    public void setMana_cost(int mana_cost)
    {
        this.mana_cost = mana_cost;
    }

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

}
