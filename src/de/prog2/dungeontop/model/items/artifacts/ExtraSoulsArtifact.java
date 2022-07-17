package de.prog2.dungeontop.model.items.artifacts;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.items.Artifact;

public class ExtraSoulsArtifact extends Artifact
{
    private final int extraSouls;

    public ExtraSoulsArtifact(String name, String description, int extraSouls, int assetID)
    {
        super(name, description, assetID);
        this.extraSouls = extraSouls;
    }

    @Override
    public boolean equip() {
        PlayerManager.getInstance().getPlayer().setSoulArtBonus(PlayerManager.getInstance().getPlayer().getSoulArtBonus() + extraSouls);
        return super.equip();
    }

    @Override
    public boolean unequip() {
        PlayerManager.getInstance().getPlayer().setSoulArtBonus(PlayerManager.getInstance().getPlayer().getSoulArtBonus() - extraSouls);
        return super.unequip();
    }

    public int getExtraSouls()
    {
        return extraSouls;
    }
}
