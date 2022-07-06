package de.prog2.dungeontop.model.world.actions;

import java.io.Serializable;

/**
 * Action Interface. It exists for all the different Room-Actions.
 */
public interface Action extends Serializable
{
    <T>boolean execute(T... args);
}
