package de.prog2.dungeontop.model.spells;

public abstract class Spell
{
    private final String name;
    private final String description;
    private final int assetId;

    public Spell (String name, String description, int assetId)
    {
        this.name = name;
        this.description = description;
        this.assetId = assetId;
    }

    public String getName()
    {
        return name;
    }
    public int getAssetId()
    {
        return assetId;
    }

    public String getDescription()
    {
        return description;
    }
}
