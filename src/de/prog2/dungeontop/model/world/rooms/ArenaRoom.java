package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.actions.OpenArenaAction;
import de.prog2.dungeontop.model.world.arena.Arena;

/**
 * The ArenaRoom is a room, where the player has to fight.
 */
public class ArenaRoom extends Room
{
    private final Arena arena;
    private boolean isAlive = true;
    public ArenaRoom(Arena arena)
    {
        super(new OpenArenaAction());
        this.arena = arena;
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
}
