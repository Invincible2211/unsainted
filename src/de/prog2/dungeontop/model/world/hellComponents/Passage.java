package de.prog2.dungeontop.model.world.hellComponents;

import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.WorldConstants;

import java.io.Serializable;

/**
 * Represents a way to the next room. It is a passage between two corners
 */
public class Passage extends HellComponent implements Serializable
{
    /**
     * Creates a Wall facing the right direction
     * @param rotation Use the constants from @see de.prog2.dungeontop.resources.WorldConstants.HellComponentRotations
     */
    public Passage(Rotation rotation)
    {
        super(AssetIds.PASSAGE, rotation);
    }
}
