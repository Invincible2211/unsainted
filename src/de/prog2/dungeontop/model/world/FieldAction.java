package de.prog2.dungeontop.model.world;

public interface FieldAction
{
    <T>boolean execute(T... args);
}
