package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.items.Artifact;
import de.prog2.dungeontop.model.items.Weapon;

public class ItemController
{
    public static boolean equipWeapon(Hero hero, Weapon weapon)
    {
        if (!InventoryController.addItem(hero.getWeapon()))
        {
            return false;
        }
        hero.setWeapon(weapon);
        return true;
    }
    public static boolean unequipWeapon(Hero hero)
    {
        if (!InventoryController.addItem(hero.getWeapon()))
        {
            return false;
        }
        hero.setWeapon(null);
        return true;
    }

    public static boolean equipArtifact(Hero hero, Artifact artifact)
    {
        if(hero.getArtifactSize() >= hero.getArtifactSlots())
        {
            return false;
        }
        hero.addArtifact(artifact);
        return true;
    }
    public static boolean unequipArtifact(Hero hero, Artifact artifact)
    {
        if (hero.hasArtifact(artifact) && !InventoryController.addItem(artifact))
        {
            return false;
        }
        hero.removeArtifact(artifact);
        return true;
    }
}
