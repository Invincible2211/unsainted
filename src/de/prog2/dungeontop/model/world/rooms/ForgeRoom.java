package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.actions.Action;
import de.prog2.dungeontop.model.world.actions.DefaultAction;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellToStringValues;

import java.io.Serializable;

/**
 * The ForgeRoom is a room, where the player can upgrade cards from his deck
 */
public class ForgeRoom extends NPCRoom
{
    public ForgeRoom(Room room)
    {
        // TODO Change Action to ForgeAction
        super(room, new DefaultAction(), AssetIds.FORGE_ROOM);
    }

    @Override
    public String toString()
    {
        return HellToStringValues.FORGE_ROOM;
    }
}
