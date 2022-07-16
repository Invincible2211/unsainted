package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.control.manager.PlayerManager;

public abstract class Consumable extends Item
{
    public Consumable(String name, String description, int price, int assetID)
    {
        super(name, description, price, assetID);
    }

    @Override
    public boolean equip()
    {
        PlayerManager.getInstance().getPlayerInventory().removeItem(this);
        return true;
    }
}
