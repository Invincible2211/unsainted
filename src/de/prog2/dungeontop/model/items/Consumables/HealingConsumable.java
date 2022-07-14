package de.prog2.dungeontop.model.items.Consumables;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.items.BonusType;

public class HealingConsumable extends Consumable{
    private int heals;

    public HealingConsumable(String name, String description, int heals, int price, BonusType bonusType, int assetID) {
        super(name, description, heals, price, bonusType, assetID);
    }

    @Override
    public void use()
    {
        PlayerManager.getInstance().addHp(this.getHeals());
        PlayerManager.getInstance().getPlayerInventory().removeItem(this);
    }

    public int getHeals()
    {
        return heals;
    }
}
