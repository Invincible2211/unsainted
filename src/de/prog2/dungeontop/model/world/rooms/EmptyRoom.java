package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.actions.DefaultAction;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellToStringValues;

/**
 * The EmptyRoom is a room without any actions.
 */
public class EmptyRoom extends Room
{
    public EmptyRoom (Coordinate coordinate, int distanceFromStart)
    {
        super(coordinate, distanceFromStart, new DefaultAction(), AssetIds.EMPTY_ROOM);
    }

    @Override
    public String toString()
    {
        return HellToStringValues.ROOM_CENTER;
    }
}
