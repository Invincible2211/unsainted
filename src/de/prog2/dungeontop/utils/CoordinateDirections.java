package de.prog2.dungeontop.utils;

import de.prog2.dungeontop.model.world.Coordinate;

import java.util.ArrayList;

public class CoordinateDirections
{
    public static Coordinate getLeftTop(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() - 1, coordinate.getY() - 1);
    }
    public static Coordinate getRightTop(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() + 1, coordinate.getY() - 1);
    }
    public static Coordinate getLeftBottom(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() - 1, coordinate.getY() + 1);
    }
    public static Coordinate getRightBottom(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() + 1, coordinate.getY() + 1);
    }
    public static Coordinate getTop(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX(), coordinate.getY() - 1);
    }
    public static Coordinate getBottom(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX(), coordinate.getY() + 1);
    }
    public static Coordinate getLeft(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() - 1, coordinate.getY());
    }
    public static Coordinate getRight(Coordinate coordinate)
    {
        return new Coordinate(coordinate.getX() + 1, coordinate.getY());
    }
    public static Coordinate[] getiAlleightNeighbours (Coordinate coordinate){
        ArrayList<Coordinate> neighbours = new ArrayList<>();
        neighbours.add(getLeftTop(coordinate));
        neighbours.add(getTop(coordinate));
        neighbours.add(getRightTop(coordinate));
        neighbours.add(getLeft(coordinate));
        neighbours.add(getRight(coordinate));
        neighbours.add(getLeftBottom(coordinate));
        neighbours.add(getBottom(coordinate));
        neighbours.add(getRightBottom(coordinate));

        Coordinate[] arr = new Coordinate[neighbours.size()];
        arr = neighbours.toArray(arr);
        return arr;
    }

    public static Coordinate[] getStraightNeighbours (Coordinate coordinate){
        ArrayList<Coordinate> neighbours = new ArrayList<>();

        neighbours.add(getTop(coordinate));
        neighbours.add(getLeft(coordinate));
        neighbours.add(getRight(coordinate));
        neighbours.add(getBottom(coordinate));

        Coordinate[] arr = new Coordinate[neighbours.size()];
        arr = neighbours.toArray(arr);
        return arr;
    }

}
