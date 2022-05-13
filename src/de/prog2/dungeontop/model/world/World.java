package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.control.controller.HellController;
import de.prog2.dungeontop.resources.WorldConstants;

import java.util.List;

public class World
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private final int hellCount;
    private List<Hell> hells;
    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public World(int hellCount)
    {
        this.hellCount = hellCount;
        generateLevels(hellCount);
    }
    private void generateLevels(int hellCount)
    {
        for (int i = 0; i < hellCount; i++)
        {
            var hell = new Hell(WorldConstants.HELL_SIZE,WorldConstants.HELL_SIZE);
            HellController.initHell(hell);
            hells.add(hell);
        }
    }
}
