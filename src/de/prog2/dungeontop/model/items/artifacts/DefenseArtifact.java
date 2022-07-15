package de.prog2.dungeontop.model.items.artifacts;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.items.Artifact;

public class DefenseArtifact extends Artifact {

    private int defBonus;

    public DefenseArtifact(String name, String description, int defBonus, int price, int assetID)
    {
        super(name, description, price, assetID);
        this.defBonus = defBonus;

    }

    public int getDefBonus()
    {
        return defBonus;
    }
}
