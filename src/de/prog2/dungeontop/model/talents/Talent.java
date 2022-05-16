package de.prog2.dungeontop.model.talents;

public abstract class Talent
{
    private String name;
    private int num; //number for damage,heal,cards drawn, etc
    private int num2;

    //Set- and Getters
    public Talent(String name, int num)
    {
        this.name = name;
        this.num = num;
    }

    public Talent(String name, int num, int num2)
    {
        this.name = name;
        this.num = num;
        this.num2 = num2;
    }

    protected abstract void effect();

    //Set- and Getters
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

    public int getNum2()
    {
        return num2;
    }

    public void setNum2(int num2)
    {
        this.num2 = num2;
    }
}
