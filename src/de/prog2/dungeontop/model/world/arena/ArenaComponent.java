package de.prog2.dungeontop.model.world.arena;

import de.prog2.dungeontop.model.entities.Entity;

import java.io.Serializable;

public class ArenaComponent implements Serializable
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
    public void deleteOccupant()
    {
        this.occupant = null;
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
