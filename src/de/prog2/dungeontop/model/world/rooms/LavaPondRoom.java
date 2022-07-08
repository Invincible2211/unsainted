package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.actions.Action;
import de.prog2.dungeontop.model.world.actions.DefaultAction;
import de.prog2.dungeontop.model.world.rooms.NPCRoom;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellToStringValues;

import java.io.Serializable;

/**
 * The LavaPondRoom is a room, where the player can remove cards from his deck
 */
public class LavaPondRoom extends NPCRoom
{
    public LavaPondRoom(Room room)
    {
        // TODO Change Action to LavaPondAction
        super(room, new DefaultAction(), AssetIds.LAVA_POND);
    }

    @Override
    public String toString()
    {
        return HellToStringValues.LAVAPOND_ROOM;
    }
}
