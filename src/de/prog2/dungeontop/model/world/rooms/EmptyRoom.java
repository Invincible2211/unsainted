package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.Coordinate;

import java.io.Serializable;

/**
 * The EmptyRoom is a room without any actions.
 */
public class EmptyRoom extends Room implements Serializable
{
    public EmptyRoom (Coordinate coordinate)
    {
        super(coordinate);
    }
}
