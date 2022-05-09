package de.prog2.dungeontop.model.world;

public class DefaultFieldAction implements FieldAction
{
    @Override
    public <T> boolean execute(T... args)
    {
        return true;
    }
}
