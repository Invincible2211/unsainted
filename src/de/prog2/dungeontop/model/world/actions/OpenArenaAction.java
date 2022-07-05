package de.prog2.dungeontop.model.world.actions;

import java.io.Serializable;

/**
 * This Action should open an arena dialog
 */
public class OpenArenaAction implements Action, Serializable
{
    @Override
    public <T> boolean execute(T... args)
    {
        // TODO : open arena
        return true;
    }
}
