package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.control.manager.PlayerManager;

public class Weapon extends Item
{
    int attackDamage = 0;
    public Weapon(String name, String description, int price, int assetID, int attackDamage) {
        super(name, description, price, assetID);
        this.attackDamage = attackDamage;
    }

    @Override
    public void use()
    {
        if(!PlayerManager.getInstance().getPlayer().getWeaponSlot().isEmpty())
        {
            PlayerManager.getInstance().getPlayerHero().unequipWeapon();

            PlayerManager.getInstance().getPlayerInventory().
                    addItem(PlayerManager.getInstance().getPlayer().getWeaponSlot().get(0));

            PlayerManager.getInstance().getPlayer().getWeaponSlot().clear();
        }
        PlayerManager.getInstance().getPlayer().getWeaponSlot().add(this);
        PlayerManager.removeItem(this);
        PlayerManager.getInstance().getPlayerHero().equipWeapon(this);
    }

    @Override
    public void unequip()
    {
        PlayerManager.getInstance().getPlayerHero().unequipWeapon();
        PlayerManager.getInstance().getPlayerInventory().addItem(this);
        PlayerManager.getInstance().getPlayer().getWeaponSlot().clear();
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }
}
