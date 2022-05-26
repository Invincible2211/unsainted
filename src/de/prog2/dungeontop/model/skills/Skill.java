package de.prog2.dungeontop.model.skills;
//Will only be implemented when we implement furter battlelogik. Optional fuer die Abgabe
public abstract class Skill
{
    private final String name;
    private final String description;
    private int range;

    public Skill(String name, String description, int range)
    {
        this.name = name;
        this.description = description;
        this.range = range;
    }

    public abstract void doSomething();

    //Set- and Getters
    public String getName()
    {
        return name;
    }

    public int getRange()
    {
        return range;
    }

    public void setRange(int range)
    {
        this.range = range;
    }

    public String getDescription()
    {
        return description;
    }
}
