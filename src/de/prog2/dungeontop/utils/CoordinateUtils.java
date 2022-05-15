package de.prog2.dungeontop.utils;

import de.prog2.dungeontop.model.game.MoveDirection;
import de.prog2.dungeontop.model.world.Coordinate;

public abstract class CoordinateUtils
{
    public static Coordinate getCoordinateFromMoveDirection(Coordinate origin, MoveDirection direction)
    {
        return switch (direction)
        {
            case UP -> new Coordinate(origin.getX(),origin.getY() + 1);
            case DOWN -> new Coordinate(origin.getX(),origin.getY() - 1);
            case LEFT -> new Coordinate(origin.getX() - 1,origin.getY());
            case RIGHT -> new Coordinate(origin.getX() + 1,origin.getY());
        };
    }
}
