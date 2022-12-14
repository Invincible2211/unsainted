package de.prog2.dungeontop.model.world.arena;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.Serializable;
import java.util.HashMap;

public class Arena implements Serializable
{
    private final int height;
    private final int width;
    private Entity selectedEntity;
    @Deprecated
    private final HashMap<Coordinate, Entity> arenaHashmap = new HashMap<>();

    private final HashMap<Coordinate, Entity> friendly = new HashMap<>();
    private final HashMap<Coordinate, Entity> opponent = new HashMap<>();
    public Arena(int height, int width)
    {
        this.height = height;
        this.width = width;
    }

    public Arena (int size)
    {
        this.height = size;
        this.width = size;
    }

    public HashMap<Coordinate, Entity> getArenaHashmap()
    {
        return arenaHashmap;
    }

    public Entity getEntity (Coordinate coordinate)
    {
        return arenaHashmap.get(coordinate);
    }

    public Entity getEntity (int x, int y)
    {
        return arenaHashmap.get(new Coordinate(x, y));
    }

    @Deprecated
    public void insertEntity (Coordinate coordinate, Entity entity)
    {
        this.arenaHashmap.put(coordinate, entity);
        entity.setPosition(coordinate);
        GlobalLogger.log(LoggerStringValues.ARENA_ENTITY_INSERTED_MESSAGE + coordinate.toString());
    }

    @Deprecated
    public void removeEntity (Coordinate coordinate)
    {
        this.arenaHashmap.remove(coordinate);
    }

    @Deprecated
    public boolean hasSelectedUnit()
    {
        return this.selectedEntity != null;
    }

    @Deprecated
    public Entity[] getAllMinions ()
    {
        return this.arenaHashmap.values().stream()
                .filter(entity -> entity instanceof Minion)
                .toArray(Entity[]::new);
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

    public void selectUnit (Entity entity)
    {
        this.selectedEntity = entity;
    }

    public Entity getSelectedEntity ()
    {
        return selectedEntity;
    }

    public void setSelectedEntity (Entity selectedEntity)
    {
        this.selectedEntity = selectedEntity;
    }

    public HashMap<Coordinate, Entity> getFriendly() {
        return friendly;
    }

    public HashMap<Coordinate, Entity> getOpponent() {
        return opponent;
    }
}
