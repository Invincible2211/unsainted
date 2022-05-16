package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.HellComponent;
import de.prog2.dungeontop.model.world.Rotation;
import de.prog2.dungeontop.resources.WorldConstants;

/**
 * Represents a wall of a room between two corners
 */
public class Wall extends HellComponent
{
    /**
     * Creates a Wall facing the right direction
     * @param rotation Use the constants from @see de.prog2.dungeontop.resources.WorldConstants.HellComponentRotations
     */
    public Wall(Rotation rotation)
    {
        super(WorldConstants.HellComponentAssetIds.WALL_ASSET_ID, rotation);
    }
}
