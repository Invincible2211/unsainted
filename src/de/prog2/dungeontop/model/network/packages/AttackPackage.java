package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.world.Coordinate;

public class AttackPackage extends Package {

    private final Entity attack;
    private Entity defender;
    private final Coordinate target;

    public AttackPackage(Entity attack, Coordinate target){
        this.attack = attack;
        this.target = target;
    }

    public Coordinate getTarget() {
        return target;
    }

    public Entity getAttack() {
        return attack;
    }

}
