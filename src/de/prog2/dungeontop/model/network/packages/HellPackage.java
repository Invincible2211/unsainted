package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;

public class HellPackage extends Package
{
    private final Hell hell;
    private final Coordinate playerCoordinate;

    public HellPackage(Hell hell, Coordinate playerCoordinate) {
        this.playerCoordinate = playerCoordinate;
        this.hell = hell;
    }

    public Hell getHell ()
    {
        return this.hell;
    }

    public Coordinate getPlayerCoordinate ()
    {
        return this.playerCoordinate;
    }

}
