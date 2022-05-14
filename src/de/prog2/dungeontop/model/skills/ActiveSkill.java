package de.prog2.dungeontop.model.skills;

public class ActiveSkill
{
    private String name;
    private int attacks_cost;
    private int num;
    private int cast_range;

    public ActiveSkill (String name, int attacks_cost, int num, int cast_range)
    {
        this.name = name;
        this.attacks_cost = attacks_cost;
        this.num = num;
        this.cast_range = cast_range;
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

    public int getAttacks_cost()
    {
        return attacks_cost;
    }

    public void setAttacks_cost(int attacks_cost)
    {
        this.attacks_cost = attacks_cost;
    }

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public int getCast_range()
    {
        return cast_range;
    }

    public void setCast_range(int cast_range)
    {
        this.cast_range = cast_range;
    }
}
