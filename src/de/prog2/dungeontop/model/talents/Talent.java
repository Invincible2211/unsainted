package de.prog2.dungeontop.model.talents;

public abstract class Talent
{
    private final String name;
    private final String description;
    public Talent(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    public abstract void effect();

    //Set- and Getters
    public String getName()
    {
        return this.name;
    }

    public String getDescription() {
        return description;
    }
}
