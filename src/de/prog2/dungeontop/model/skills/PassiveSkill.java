package de.prog2.dungeontop.model.skills;

public class PassiveSkill
{
    private String name;
    private int num;

    public PassiveSkill(String name, int num)
    {
        this.name = name;
        this.num = num;
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

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }
}
