package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.world.Coordinate;

public class PlaceEntityPackage extends Package {

    private final Entity entity;
    private final Coordinate coordinate;

    public PlaceEntityPackage(Entity entity, Coordinate coordinate){
        this.entity = entity;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Entity getEntity() {
        return entity;
    }

}
