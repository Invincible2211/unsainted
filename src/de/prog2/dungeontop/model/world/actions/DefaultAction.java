package de.prog2.dungeontop.model.world.actions;

import de.prog2.dungeontop.model.world.rooms.Room;

public class DefaultAction implements Action
{
    @Override
    public boolean execute(Room room)
    {
        return true;
    }
}
