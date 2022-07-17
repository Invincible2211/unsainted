package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.world.Coordinate;

public class ChangeEntityHpByCoordinatePackage extends Package
{
    private Coordinate coordinate;
    private int amount;

    public ChangeEntityHpByCoordinatePackage (Coordinate coordinate, int amount)
    {
        this.coordinate = coordinate;
        this.amount = amount;
    }

    public Coordinate getCoordinate ()
    {
        return coordinate;
    }

    public int getAmount ()
    {
        return amount;
    }

}
