package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.control.controller.ItemController;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Hero;

public class Weapon extends Item implements Equipable
{
    int attackDamage = 0;
    public Weapon(String name, String description, int price, int assetID, int attackDamage) {
        super(name, description, price, assetID);
        this.attackDamage = attackDamage;
    }

    @Override
    public boolean equip()
    {
        Hero hero = PlayerManager.getInstance().getPlayerHero();
        return ItemController.equipWeapon(hero, this);
    }

    @Override
    public boolean unequip()
    {
        Hero hero = PlayerManager.getInstance().getPlayerHero();
        return ItemController.unequipWeapon(hero);
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }
}
