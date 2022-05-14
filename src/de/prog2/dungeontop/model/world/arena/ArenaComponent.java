package de.prog2.dungeontop.model.world.arena;

public class ArenaComponent
{
    private boolean isOccupied;
    public ArenaComponent(boolean isOccupied)
    {
        this.isOccupied = isOccupied;
    }
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
