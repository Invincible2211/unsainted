package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.actions.ForgeAction;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellToStringValues;

/**
 * The ForgeRoom is a room, where the player can upgrade cards from his deck
 */
public class ForgeRoom extends NPCRoom
{
    public ForgeRoom(Room room)
    {
        super(room, new ForgeAction(), AssetIds.FORGE_ROOM);
    }

    @Override
    public String toString()
    {
        return HellToStringValues.FORGE_ROOM;
    }
}
