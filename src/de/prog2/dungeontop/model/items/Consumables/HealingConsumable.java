package de.prog2.dungeontop.model.items.Consumables;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.items.Consumable;

public class HealingConsumable extends Consumable
{
    private final int healingAmount;

    public HealingConsumable(String name, String description, int healingAmount, int assetID)
    {
        super(name, String.format(description, healingAmount), assetID);
        this.healingAmount = healingAmount;
    }

    @Override
    public boolean equip()
    {
        PlayerManager.getInstance().addHp(this.getHealingAmount());
        return super.equip();
    }

    public int getHealingAmount()
    {
        return healingAmount;
    }
}
