package de.prog2.dungeontop.model.world;

public class DefaultAction implements Action
{
    @Override
    public <T> boolean execute(T... args)
    {
        return true;
    }
}
