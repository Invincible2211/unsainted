package de.prog2.dungeontop;

import de.prog2.dungeontop.control.controller.HellController;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.resources.WorldConstants;

public class Unsainted
{
    public static void main(String[] args)
    {
        Hell hell = new Hell(WorldConstants.HELL_SIZE, WorldConstants.HELL_SIZE);
        HellController.initHell(hell);
        System.out.println(hell);
    }
    // TODO Jesse
    public static void testEntities()
    {

    }
    // TODO Fynn#1
    public static void testGame()
    {

    }
    // TODO Thomas
    public static void testItems()
    {

    }
    // TODO Thomas
    public static void testPerks()
    {

    }
    // TODO Fynn#2
    public static void testSaveGame()
    {

    }
    // TODO Jason
    public static void testSkills()
    {

    }
    // TODO Jason
    public static void testSpells()
    {

    }
    // TODO Jason
    public static void testTalents()
    {

    }
    // TODO Thomas
    public static void testWorld()
    {

    }
}
