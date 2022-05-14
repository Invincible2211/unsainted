package de.prog2.dungeontop.model.world.arena;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.world.Coordinate;

import java.util.HashMap;

public class Arena
{
    private int height;
    private int width;
    private HashMap<Coordinate, ArenaComponent> arenaHashmap;
    public Arena(int height, int width)
    {
        this.height = height;
        this.width = width;
    }

    public HashMap<Coordinate, ArenaComponent> getArenaHashmap()
    {
        return arenaHashmap;
    }

    public ArenaComponent getCoordinate (Coordinate coordinate)
    {
        return arenaHashmap.get(coordinate);
    }

    public void insertComponent (Coordinate coordinate, ArenaComponent arenaComponent)
    {
        this.arenaHashmap.put(coordinate, arenaComponent);
    }

    //Set- and Getters
    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public Entity[] getAllMinions ()
    {
        return null;
    }
}
