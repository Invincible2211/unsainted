package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.actions.OpenArenaAction;

/**
 * The ArenaRoom is a room, where the player has to fight.
 */
public class ArenaRoom extends Room
{
    public ArenaRoom()
    {
        super(new OpenArenaAction());
    }
}
