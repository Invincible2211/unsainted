package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.resources.RandomEventConstants;

import java.util.Random;

public class RandomEventRoomController
{

    public static void changeHpEvent ()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(RandomEventConstants.RNG_MAX);

        if (randomNumber < RandomEventConstants.RNG_CHANGE_HP_POS)
        {
            // positive change
            PlayerManager.getInstance().addHp(RandomEventConstants.ADDED_HP_AMOUNT);
        }
        else
        {
            // negative change
            PlayerManager.getInstance().removeHp(RandomEventConstants.REMOVED_HP_AMOUNT);
        }
    }

    public static void changeSoulsEvent ()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(RandomEventConstants.RNG_MAX);

        if (randomNumber < RandomEventConstants.RNG_CHANGE_HP_POS)
        {
            // positive change
            PlayerManager.getInstance().addSouls(RandomEventConstants.ADED_SOULS_AMOUNT);
        }
        else
        {
            // negative change
            PlayerManager.getInstance().removeSouls(RandomEventConstants.REMOVED_SOULS_AMOUNT);
        }
    }
}
