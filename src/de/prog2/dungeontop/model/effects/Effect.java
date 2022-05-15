package de.prog2.dungeontop.model.effects;

public abstract class Effect
{
    private int num;
    private int num1;
    private int num2;

    public Effect(int num, int num1, int num2)
    {
        this.num = num;
        this.num1 = num1;
        this.num2 = num2;
        effect();
    }

    public abstract void effect();

    //Set- and Getters
    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public int getNum1()
    {
        return num1;
    }

    public void setNum1(int num1)
    {
        this.num1 = num1;
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
