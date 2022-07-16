package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.control.network.NetManager;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.utils.HellGenerator;
import de.prog2.dungeontop.view.HellView;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Holds all the Hells
 */
public class World implements Serializable
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private final int hellCount;
    private int currentHell = 0;
    private final HashMap<Integer, Hell> hells = new HashMap<>();
    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public World(int hellCount)
    {
        this.hellCount = hellCount;
    }

    /**
     * Generates all the Hells
     */
    public void generateLevels()
    {
        for (int i = 0; i < this.hellCount; i++)
        {
            var hell = new Hell(WorldConstants.HELL_SIZE,WorldConstants.HELL_SIZE);
            HellGenerator.initHell(hell);
            hells.put(i, hell);
            GlobalLogger.log(LoggerStringValues.ADDED_HELL_TO_WORLD + i);
        }
    }

    public void initWorld ()
    {
        this.generateLevels();
        PlayerManager.getInstance().getPlayer().setCurrentRoom(getCurrentHell().getStartingRoom());
        HellView view = new HellView();
        HellView.setCurrHellView(view.initHellView(getCurrentHell()));
    }

    /**
     * @return returns the current Hell, the player should be playing
     */
    public Hell getCurrentHell()
    {
        return hells.get(this.currentHell);
    }

    /**
     * @return current stage the player is in
     */
    public int getCurrentDepth ()
    {
        return this.currentHell + 1;
    }

    /**
     * Changes the current hell to the next one.
     * @return returns the next hell.
     */
    public Hell getNextHell()
    {
        currentHell++;
        PlayerManager.getInstance().getPlayer().setCurrentRoom(getCurrentHell().getStartingRoom());
        HellView view = new HellView();
        HellView.setCurrHellView(view.initHellView(getCurrentHell()));
        NetManager.getInstance().getNetworkAPI().sendHellData(getCurrentHell(), getCurrentHell().getStartingRoom().getCoordinate());
        return getCurrentHell();
    }
}
