package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.actions.RandomEventAction;
import de.prog2.dungeontop.resources.HellToStringValues;

import java.io.Serializable;

/**
 * The RandomEventRoom is a room where random events, such as traps, occur.
 */
public class RandomEventRoom extends Room
{
    // RandomEventAction not implemented yet
    public RandomEventRoom () { super(new RandomEventAction());}

    @Override
    public String toString()
    {
        return HellToStringValues.RANDOM_EVENT_ROOM;
    }
}

