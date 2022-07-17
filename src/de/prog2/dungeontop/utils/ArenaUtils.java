package de.prog2.dungeontop.utils;

import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;

public class ArenaUtils
{
    public static Coordinate invertCoordinate(Arena arena, Coordinate coordinate){
        return new Coordinate(arena.getWidth() -1 - coordinate.getX(), arena.getHeight() -1 - coordinate.getY());
    }
}
