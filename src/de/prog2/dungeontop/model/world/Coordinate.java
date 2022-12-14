package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.resources.StringValues;

import java.io.Serializable;
import java.util.Objects;

/**
 * Dient als Container für den X- und Y-Wert eines Fields
 */
public class Coordinate implements Serializable
{
    private final int x;
    private final int y;
    private final int hashCode;


    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.hashCode = Objects.hash(x, y);
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }
    @Override
    public int hashCode()
    {
        return this.hashCode;
    }

    @Override
    public String toString()
    {
        return String.format(StringValues.COORDINATE, this.getX(), this.getY());
    }
}
