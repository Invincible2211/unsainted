package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.control.controller.ItemController;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Hero;

public class Weapon extends Item implements Equippable
{
    private final int attackDamage;
    private final int attackRange;
    public Weapon(String name, String description, int price, int assetID, int attackDamage, int attackRange)
    {
        super(name, description, price, assetID);
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
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

    @Override
    public boolean isEquipped()
    {
        return PlayerManager.getInstance().getPlayerHero().getWeapon() == this;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public int getAttackRange()
    {
        return attackRange;
    }
}
