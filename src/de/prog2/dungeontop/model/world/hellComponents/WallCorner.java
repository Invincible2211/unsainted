package de.prog2.dungeontop.model.world.hellComponents;

import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellToStringValues;

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
        super(AssetIds.WALL_CORNER, rotation);
    }

    @Override
    public String toString()
    {
        return switch (this.getRotation())
        {
            case UP -> HellToStringValues.TOP_LEFT_CORNER;
            case RIGHT -> HellToStringValues.TOP_RIGHT_CORNER;
            case LEFT -> HellToStringValues.BOTTOM_LEFT_CORNER;
            case DOWN -> HellToStringValues.BOTTOM_RIGHT_CORNER;
        };
    }
}
