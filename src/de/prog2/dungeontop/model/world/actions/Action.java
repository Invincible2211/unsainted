package de.prog2.dungeontop.model.world.actions;

import de.prog2.dungeontop.model.world.rooms.Room;

import java.io.Serializable;

/**
 * Action Interface. It exists for all the different Room-Actions.
 */
public interface Action extends Serializable
{
    boolean execute(Room room);
}
