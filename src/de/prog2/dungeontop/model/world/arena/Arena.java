package de.prog2.dungeontop.model.world.arena;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.spells.Spell;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.ArrayList;
import java.util.HashMap;

public class Arena
{
    private final int height;
    private final int width;
    private final HashMap<Coordinate, ArenaComponent> arenaHashmap = new HashMap<>();
    public Arena(int height, int width)
    {
        this.height = height;
        this.width = width;
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

    @Deprecated
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

    public boolean hasSelectedUnit()
    {
        if (arenaHashmap.isEmpty())
        {
            return false;
        }
        for (ArenaComponent arenaComponent : arenaHashmap.values()) {
            if (arenaComponent.getOccupant().isSelected()) {
                return true;
            }
        }
        return false;
    }

    public Entity getSelectedUnit()
    {
        for (ArenaComponent arenaComponent : arenaHashmap.values()) {
            if (arenaComponent.getOccupant().isSelected()) {
                return arenaComponent.getOccupant();
            }
        }
        GlobalLogger.warning(LoggerStringValues.NO_UNIT_SELECTED);
        return null;
    }

    public ArrayList<Minion> getAllMinions ()
    {
        ArrayList<Minion> allMinions = new ArrayList<>();
        return  null;
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

    public void selectUnit (Coordinate coordinate)
    {
        if (this.hasSelectedUnit() == false){
        getArenaComponent(coordinate).getOccupant().setSelected(true);
    } else  {
            GlobalLogger.warning(LoggerStringValues.UNIT_ALREADY_SELECTED);
        }
    }
}
