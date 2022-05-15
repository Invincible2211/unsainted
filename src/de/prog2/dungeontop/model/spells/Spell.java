package de.prog2.dungeontop.model.spells;

public abstract class Spell
{
    private String name;
    private int ego_points;
    private int num; //number for damage,heal,cards drawn, etc
    private int num2;

    public Spell (String name, int ego_points, int num)
    {
        this.name = name;
        this.ego_points = ego_points;
        this.num = num;
    }

    public Spell (String name, int ego_points, int num, int num2)
    {
        this.name = name;
        this.ego_points = ego_points;
        this.num = num;
        this.num2 = num2;
    }

    protected abstract void effect();

    //Set- and Getters
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public int getEgo_points()
    {
        return ego_points;
    }

    public void setEgo_points(int mana_cost)
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

    public int getNum2()
    {
        return num2;
    }

    public void setNum2(int num2)
    {
        this.num2 = num2;
    }
}
