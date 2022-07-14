package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.control.manager.PlayerManager;

public class Weapon extends Item
{
    int attackDamage = 0;
    public Weapon(String name, String description, int value, int price, BonusType bonusType, int assetID) {
        super(name, description, value, price, bonusType, assetID);
    }

    @Override
    public void use()
    {
        if(!PlayerManager.getInstance().getPlayer().getWeaponSlot().isEmpty())
        {
            PlayerManager.getInstance().revertEquipAttackBonus();

            PlayerManager.getInstance().getPlayerInventory().
                    addItem(PlayerManager.getInstance().getPlayer().getWeaponSlot().get(0));

            PlayerManager.getInstance().getPlayer().getWeaponSlot().clear();
        }
        PlayerManager.getInstance().getPlayer().getWeaponSlot().add(this);
        PlayerManager.removeItem(this);
        PlayerManager.getInstance().addEquipAttackBonus();
    }
    public int getAttackDamage()
    {
        return attackDamage;
    }
}
