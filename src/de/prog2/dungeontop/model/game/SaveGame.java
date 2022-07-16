package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.control.manager.CardManager;
import de.prog2.dungeontop.model.world.World;

import java.io.Serializable;

public class SaveGame implements Serializable
{
    World gameWorld;
    Player player;
    CardManager cardManager;

    public World getGameWorld ()
    {
        return this.gameWorld;
    }

    public void setGameWorld (World gameWorld)
    {
        this.gameWorld = gameWorld;
    }

    public Player getPlayer ()
    {
        return this.player;
    }
    public void setPlayer (Player player)
    {
        this.player = player;
    }
    public CardManager getCardManager ()
    {
        return this.cardManager;
    }
    public void setCardManager (CardManager cardManager)
    {
        this.cardManager = cardManager;
    }
}
