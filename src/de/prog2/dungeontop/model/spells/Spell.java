package de.prog2.dungeontop.model.spells;

public abstract class Spell
{
    private String name;
    private int ego_points;

    public Spell (String name, int ego_points)
    {
        this.name = name;
        this.ego_points = ego_points;
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

    public int getEgo_points()
    {
        return ego_points;
    }

    public void setEgo_points(int ego_points)
    {
        this.ego_points = ego_points;
    }
}
