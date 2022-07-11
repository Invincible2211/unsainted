package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.world.Coordinate;

public class MoveEntityPackage extends Package {

    private Coordinate start;
    private Coordinate target;

    public MoveEntityPackage(Coordinate start, Coordinate target){
        this.start = start;
        this.target = target;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getTarget() {
        return target;
    }

}
