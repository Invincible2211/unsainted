package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.HellComponent;
import de.prog2.dungeontop.model.world.Rotation;
import de.prog2.dungeontop.resources.WorldConstants;

/**
 * Represents a corner of a room
 */
public class WallCorner extends HellComponent
{
    /**
     * Creates a WallCorner facing the right direction
     * @param rotation Use the constants from @see de.prog2.dungeontop.resources.WorldConstants.HellComponentRotations
     */
    public WallCorner(Rotation rotation)
    {
        super(WorldConstants.HellComponentAssetIds.WALL_CORNER_ASSET_ID, rotation);
    }
}
