package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.control.controller.HellController;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.HashMap;

/**
 * Holds all the Hells
 */
public class World
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private final int hellCount;
    private int currentHell = 0;
    private HashMap<Integer, Hell> hells;
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
            HellController.initHell(hell);
            hells.put(i, hell);
            GlobalLogger.log(LoggerStringValues.ADDED_HELL_TO_WORLD + i);
        }
    }

    /**
     * @return returns the current Hell, the player should be playing
     */
    public Hell getCurrentHell()
    {
        return hells.get(this.currentHell);
    }

    /**
     * Changes the current hell to the next one.
     * @return returns the next hell.
     */
    public Hell getNextHell()
    {
        currentHell++;
        return getCurrentHell();
    }
}
