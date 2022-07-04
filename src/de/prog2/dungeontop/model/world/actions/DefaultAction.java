package de.prog2.dungeontop.model.world.actions;

import java.io.Serializable;

public class DefaultAction implements Action, Serializable
{
    @Override
    public <T> boolean execute(T... args)
    {
        return true;
    }
}
