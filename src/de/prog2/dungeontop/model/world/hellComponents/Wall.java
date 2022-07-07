package de.prog2.dungeontop.model.world.hellComponents;

import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellToStringValues;

import java.io.Serializable;

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
        super(AssetIds.WALL, rotation);
    }

    @Override
    public String toString()
    {
        return switch (this.getRotation())
        {
            case UP, DOWN -> HellToStringValues.HORIZONTAL_WALL;
            case LEFT, RIGHT -> HellToStringValues.VERTICAL_WALL;
        };
    }
}
