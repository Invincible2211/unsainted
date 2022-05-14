package de.prog2.dungeontop.model.spells;

public abstract class Spell
{
    private String name;
    private int ego_points;
    private int num; //number for damage,heal,cards drawn, etc

    public Spell (String name, int ego_points, int num)
    {
        this.name = name;
        this.ego_points = ego_points;
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
        return ego_points;
    }

    public void setMana_cost(int mana_cost)
    {
        this.ego_points = mana_cost;
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
