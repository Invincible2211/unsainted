package de.prog2.dungeontop.model.world;

/**
 * This Action should open an arena dialog
 */
public class OpenArenaAction implements Action
{
    @Override
    public <T> boolean execute(T... args)
    {
        //open arena
        return true;
    }
}
