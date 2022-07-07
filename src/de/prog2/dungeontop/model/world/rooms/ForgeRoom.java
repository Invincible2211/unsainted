package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.resources.HellToStringValues;

import java.io.Serializable;

/**
 * The ForgeRoom is a room, where the player can upgrade cards from his deck
 */
public class ForgeRoom extends NPCRoom
{
    @Override
    public String toString()
    {
        return HellToStringValues.FORGE_ROOM;
    }
}
