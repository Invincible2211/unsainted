package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.actions.RandomEventAction;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellToStringValues;
import de.prog2.dungeontop.resources.RandomEventConstants;

/**
 * The RandomEventRoom is a room where random events, such as traps, occur.
 */
public class RandomEventRoom extends Room
{
    boolean eventFinished = false;
    public RandomEventRoom(Room room)
    {
        super(room, new RandomEventAction(), AssetIds.RANDOM_EVENT_ROOM);
    }

    @Override
    public String toString()
    {
        return HellToStringValues.RANDOM_EVENT_ROOM;
    }

    public boolean isEventFinished ()
    {
        return this.eventFinished;
    }

    public void setEventFinished ()
    {
        this.eventFinished = RandomEventConstants.FINISHED;
    }
}

