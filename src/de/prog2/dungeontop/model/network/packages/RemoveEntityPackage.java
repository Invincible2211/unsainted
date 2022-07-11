package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.world.Coordinate;

public class RemoveEntityPackage extends Package {

    private Coordinate coordinate;

    public RemoveEntityPackage(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

}
