package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.actions.RandomEventAction;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellToStringValues;

/**
 * The RandomEventRoom is a room where random events, such as traps, occur.
 */
public class RandomEventRoom extends Room
{
    // TODO RandomEventAction not implemented yet
    public RandomEventRoom(Room room)
    {
        super(room, new RandomEventAction(), AssetIds.RANDOM_EVENT_ROOM);
    }

    @Override
    public String toString()
    {
        return HellToStringValues.RANDOM_EVENT_ROOM;
    }
}

