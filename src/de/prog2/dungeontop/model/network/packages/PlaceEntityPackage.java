package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.world.Coordinate;

public class PlaceEntityPackage extends Package {

    private EntityCard entityCard;
    private Coordinate coordinate;

    public PlaceEntityPackage(EntityCard entityCard, Coordinate coordinate){
        this.entityCard = entityCard;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public EntityCard getEntityCard() {
        return entityCard;
    }

}
