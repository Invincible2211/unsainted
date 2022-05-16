package de.prog2.dungeontop.model.world.arena;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.spells.Spell;
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

    public void castSpell(Spell spell, Coordinate coordinate)
    {

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
        this.arenaHashmap.remove(coordinate);
    }

    //Set- and Getters
    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    // TODO: FynnK
    //Get all minions of player, muss in ArenaController, hier nicht !!!!!
    public Entity[] getAllMinions ()
    {
        return null;
    }
}
