package de.prog2.dungeontop.model.spells;

public abstract class Spell
{
    private final String name;
    private final int assetId;

    public Spell (String name, int assetId)
    {
        this.name = name;
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
}
