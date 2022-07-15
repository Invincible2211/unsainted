package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.items.Artifact;
import de.prog2.dungeontop.model.items.Weapon;

public class ItemController
{
    /**
     * Tries to equip the weapon to the hero.
     */
    public static boolean equipWeapon(Hero hero, Weapon weapon)
    {
        if (hero.getWeapon() != null)
        {
            if(!unequipWeapon(hero))
            {
                return false;
            }
        }
        InventoryController.removeItem(weapon);
        hero.setWeapon(weapon);
        return true;
    }
    /**
     * Tries to unequip the weapon from the hero.
     */
    public static boolean unequipWeapon(Hero hero)
    {
        if (InventoryController.addItem(hero.getWeapon()))
        {
            hero.setWeapon(null);
            return true;
        }
        return false;
    }

    /**
     * Tries to equip the artifact to the hero.
     */
    public static boolean equipArtifact(Hero hero, Artifact artifact)
    {
        if(hero.getArtifactSize() >= hero.getArtifactSlots())
        {
            return false;
        }
        InventoryController.removeItem(artifact);
        hero.addArtifact(artifact);
        return true;
    }
    /**
     * Tries to unequip the artifact from the hero.
     */
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
