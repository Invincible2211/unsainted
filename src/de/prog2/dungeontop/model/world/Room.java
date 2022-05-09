package de.prog2.dungeontop.model.world;

public abstract class Room extends HellComponent
{
    private Action action = new DefaultAction();

    public Room(){}
    public Room(Action action)
    {
        this.action = action;
    }

    public <T> boolean executeAction(T... args)
    {
        return this.action.execute(args);
    }
}
