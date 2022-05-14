package de.prog2.dungeontop.model.skills;

public abstract class Skill {
    private String name;
    private int attacks_cost;
    private int num;
    private int num2;
    private int cast_range;

    public Skill(String name, int num)
    {
        this.name = name;
        this.num = num;
    }

    public Skill(String name, int num, int num2)
    {
        this.name = name;
        this.num = num;
        this.num2 = num2;
    }

    public Skill (String name, int attacks_cost, int num, int cast_range)
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

    public int getNum2()
    {
        return num2;
    }

    public void setNum2(int num2)
    {
        this.num2 = num2;
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
