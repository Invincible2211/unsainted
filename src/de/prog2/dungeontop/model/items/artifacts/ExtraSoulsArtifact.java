package de.prog2.dungeontop.model.items.artifacts;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.items.Artifact;

public class ExtraSoulsArtifact extends Artifact
{
    private final int extraSouls;

    public ExtraSoulsArtifact(String name, String description, int extraSouls, int price, int assetID)
    {
        super(name, description, price, assetID);
        this.extraSouls = extraSouls;
    }

    @Override
    public void use()
    {
        PlayerManager.getInstance().getPlayer().setSoulArtBonus(PlayerManager.getInstance().getPlayer().getSoulArtBonus() + extraSouls);
    }

    public int getExtraSouls()
    {
        return extraSouls;
    }
}
