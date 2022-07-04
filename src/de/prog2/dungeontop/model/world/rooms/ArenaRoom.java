package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.actions.OpenArenaAction;
import de.prog2.dungeontop.model.world.arena.Arena;

import java.io.Serializable;

/**
 * The ArenaRoom is a room, where the player has to fight.
 */
public class ArenaRoom extends Room implements Serializable
{
    private final Arena arena;
    private boolean isAlive = true;
    private boolean isBoss = false;
    public ArenaRoom(Arena arena)
    {
        super(new OpenArenaAction());
        this.arena = arena;
    }

    public ArenaRoom (Arena arena, boolean isBoss)
    {
        this(arena);
        this.isBoss = isBoss;
    }

    public Arena getArena()
    {
        return arena;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setAlive(boolean alive)
    {
        isAlive = alive;
    }

    public void setIsBoss (boolean isBoss)
    {
        this.isBoss = isBoss;
    }

    public boolean isBoss ()
    {
        return this.isBoss;
    }
}
