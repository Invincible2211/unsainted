package de.prog2.dungeontop.model.world.hellComponents;

import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.WorldConstants;

import java.io.Serializable;

/**
 * Represents a corner of a room
 */
public class WallCorner extends HellComponent implements Serializable
{
    /**
     * Creates a WallCorner facing the right direction
     * @param rotation Use the constants from @see de.prog2.dungeontop.resources.WorldConstants.HellComponentRotations
     */
    public WallCorner(Rotation rotation)
    {
        super(AssetIds.WALL_CORNER, rotation);
    }
}
