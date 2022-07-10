package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.world.World;

import java.io.Serializable;

public class SaveGame implements Serializable {

    int i = 3;
    World gameWorld;

    public int getI() {
        return i;
    }

    public World getGameWorld ()
    {
        return this.gameWorld;
    }

    public void setGameWorld (World gameWorld)
    {
        this.gameWorld = gameWorld;
    }
}
