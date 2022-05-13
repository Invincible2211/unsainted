package de.prog2.dungeontop.model.world.arena;

public abstract class ArenaComponent
{
    private boolean isOccupied = false;
    //Set- and Getters
    public boolean isOccupied()
    {
        return isOccupied;
    }
    public void setOccupied(boolean occupied)
    {
        isOccupied = occupied;
    }
}
