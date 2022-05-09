package de.prog2.dungeontop.model.world;

public interface Action
{
    <T>boolean execute(T... args);
}
