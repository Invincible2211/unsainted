package de.prog2.dungeontop.model.items.Artifacts;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.items.BonusType;
import de.prog2.dungeontop.model.items.Item;

public class Artifact extends Item
{
    public Artifact(String name, String description, int value, int price, BonusType bonusType, int assetID) {
        super(name, description, value, price, bonusType, assetID);
    }

    @Override
    public void use()
    {
        if (PlayerManager.getInstance().getPlayer().getArtifactSlots().size() == 2)
        {
            Item item1 = PlayerManager.getInstance().getPlayer().getArtifactSlots().get(0);
            if (item1.getBonusType().equals(BonusType.SOULS))
            {
                PlayerManager.getInstance().revertArtSoulsBonus(item1);
            }
            else if (item1.getBonusType().equals(BonusType.DEFENSE))
            {
                PlayerManager.getInstance().revertArtDefenseBonus(item1);
            }
            else
            {
                PlayerManager.getInstance().revertArtAttackBonus(item1);
            }
            PlayerManager.addItem(PlayerManager.getInstance().getPlayer().getArtifactSlots().get(0));
            PlayerManager.getInstance().getPlayer().getArtifactSlots().remove(0);
        }
        PlayerManager.getInstance().getPlayer().getArtifactSlots().add(this);
        PlayerManager.removeItem(this);

        if (this.getBonusType().equals(BonusType.SOULS))
        {
            PlayerManager.getInstance().addArtSoulsBonus(this);
        }
        else if (this.getBonusType().equals(BonusType.DEFENSE))
        {
            PlayerManager.getInstance().addArtDefenseBonus(this);
        }
        else
        {
            PlayerManager.getInstance().addArtAttackBonus(this);
        }
    }

}
