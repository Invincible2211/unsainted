package de.prog2.dungeontop.model.world.arena;

import de.prog2.dungeontop.model.entities.Entity;

public class ArenaComponent
{
    private Entity Occupant;
    public ArenaComponent(Entity Occupant)
    {
        this.Occupant = Occupant;
    }
    //Set- and Getters
    public boolean isOccupied() {
        return Occupant != null;
    }
    public void setOccupant(Entity occupant)
    {
        Entity Occupant = occupant;
    }
}
