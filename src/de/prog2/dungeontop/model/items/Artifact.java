package de.prog2.dungeontop.model.items;

import de.prog2.dungeontop.control.controller.ItemController;
import de.prog2.dungeontop.control.manager.PlayerManager;

public abstract class Artifact extends Item implements Equippable
{
    public Artifact(String name, String description, int assetID)
    {
        super(name, description, assetID);
    }

    @Override
    public boolean equip()
    {
        return ItemController.equipArtifact(PlayerManager.getInstance().getPlayerHero(), this);
    }

    @Override
    public boolean unequip()
    {
        return ItemController.unequipArtifact(PlayerManager.getInstance().getPlayerHero(), this);
    }

    @Override
    public boolean isEquipped()
    {
        return PlayerManager.getInstance().getPlayerHero().hasArtifact(this);
    }
}
