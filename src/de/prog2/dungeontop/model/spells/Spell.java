package de.prog2.dungeontop.model.spells;

import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;

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

    /**
     * applies the spell effecto to the coordinate on the arena
     */
    public abstract void cast(Arena arena, Coordinate coordinate);

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
