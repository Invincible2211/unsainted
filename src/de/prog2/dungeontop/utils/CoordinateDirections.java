package de.prog2.dungeontop.utils;

import de.prog2.dungeontop.model.world.Coordinate;

public class CoordinateDirections
{
    public static Coordinate getLeftTop(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() - 1, coordinate.getY() + 1);
    }
    public static Coordinate getRightTop(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() + 1, coordinate.getY() + 1);
    }
    public static Coordinate getLeftBottom(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() - 1, coordinate.getY() - 1);
    }
    public static Coordinate getRightBottom(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() + 1, coordinate.getY() - 1);
    }
    public static Coordinate getTop(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX(), coordinate.getY() + 1);
    }
    public static Coordinate getBottom(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX(), coordinate.getY() - 1);
    }
    public static Coordinate getLeft(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() - 1, coordinate.getY());
    }
    public static Coordinate getRight(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() + 1, coordinate.getY());
    }
}
