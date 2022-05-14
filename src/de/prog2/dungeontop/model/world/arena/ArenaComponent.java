package de.prog2.dungeontop.model.world.arena;

import de.prog2.dungeontop.model.entities.Entity;

public class ArenaComponent
{
    private Entity occupant;
    public ArenaComponent(Entity occupant)
    {
        this.occupant = occupant;
    }

    //Set- and Getters
    public boolean isOccupied()
    {
        return occupant != null;
    }
    public Entity getOccupant()
    {
        return occupant;
    }
    public void setOccupant(Entity occupant)
    {
         this.occupant = occupant;
    }
}
