package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.rooms.NPCRoom;
import de.prog2.dungeontop.resources.HellToStringValues;

import java.io.Serializable;

/**
 * The LavaPondRoom is a room, where the player can remove cards from his deck
 */
public class LavaPondRoom extends NPCRoom implements Serializable
{
    @Override
    public String toString()
    {
        return HellToStringValues.LAVAPOND_ROOM;
    }
}
