package de.prog2.dungeontop;

import de.prog2.dungeontop.control.controller.HellController;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.logging.Logger;

public class DungeonTop {
    public static void main(String[] args)
    {
        testWorld();
    }

    /**
     * Method which can be called to test the different models and methods depending the game world
     */
    public static void testWorld ()
    {
        Hell hell = new Hell(WorldConstants.HELL_SIZE, WorldConstants.HELL_SIZE);
        HellController.initHell(hell);
        System.out.println(hell);
    }
}
