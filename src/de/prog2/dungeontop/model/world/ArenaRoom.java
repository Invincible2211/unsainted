package de.prog2.dungeontop.model.world;

public class ArenaRoom extends Room
{
    public ArenaRoom()
    {
        super(new OpenArenaAction());
    }
}
