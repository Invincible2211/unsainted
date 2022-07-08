package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.actions.LavaPondAction;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellToStringValues;

/**
 * The LavaPondRoom is a room, where the player can remove cards from his deck
 */
public class LavaPondRoom extends NPCRoom
{
    public LavaPondRoom(Room room)
    {
        super(room, new LavaPondAction(), AssetIds.LAVA_POND);
    }

    @Override
    public String toString()
    {
        return HellToStringValues.LAVAPOND_ROOM;
    }
}
