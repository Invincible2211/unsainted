package de.prog2.dungeontop.model.items.Consumables;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.items.BonusType;
import de.prog2.dungeontop.model.items.Item;

@Deprecated
public class Consumable extends Item
{
    public Consumable(String name, String description, int value, int price, BonusType bonusType, int assetID) {
        super(name, description, value, price, bonusType, assetID);
    }

    @Override
    public void use()
    {
        PlayerManager.getInstance().addHp(this.getValue());
        PlayerManager.getInstance().getPlayerInventory().removeItem(this);
    }
}
