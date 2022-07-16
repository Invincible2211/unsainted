package de.prog2.dungeontop.model.items.artifacts;

import de.prog2.dungeontop.model.items.Artifact;

public class DefenseArtifact extends Artifact
{
    private final int defense;

    public DefenseArtifact(String name, String description, int defense, int assetID)
    {
        super(name, description, assetID);
        this.defense = defense;

    }

    public int getDefense()
    {
        return defense;
    }
}
