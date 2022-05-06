package de.prog2.dungeontop.model.talents;

public abstract class Talent
{
    private String name;
    private int num; //number for damage,heal,cards drawn, etc

    //Set- and Getters
    public Talent(String name, int num)
    {
        this.name = name;
        this.num = num;
    }

    protected abstract void effekt();

    public String getName()
    {
        return this.name;
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
