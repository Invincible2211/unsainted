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

        for (int hoch = 0; hoch < height; hoch++) {
            for (int weit = 0; weit < width; weit++) {
                insertComponent(new Coordinate(hoch, weit), new ArenaComponent(null));
            }
        }
    }

    public HashMap<Coordinate, ArenaComponent> getArenaHashmap()
    {
        return arenaHashmap;
    }

    public ArenaComponent getArenaComponent(Coordinate coordinate)
    {
        return arenaHashmap.get(coordinate);
    }

    public boolean isOccupied (Coordinate coordinate)
    {
        return getArenaComponent(coordinate).isOccupied();
    }

    public void insertComponent (Coordinate coordinate, ArenaComponent arenaComponent)
    {
        this.arenaHashmap.put(coordinate, arenaComponent);
    }

    public void removeComponent (Coordinate coordinate)
    {
        this.arenaHashmap.remove(getArenaComponent(coordinate));
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
