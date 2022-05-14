package de.prog2.dungeontop.model.world;

/**
 * Action Interface. It exists for all the different Room-Actions.
 */
public interface Action
{
    <T>boolean execute(T... args);
}
