package de.prog2.dungeontop.model.world.actions;

public class DefaultAction implements Action
{
    @Override
    public <T> boolean execute(T... args)
    {
        return true;
    }
}
