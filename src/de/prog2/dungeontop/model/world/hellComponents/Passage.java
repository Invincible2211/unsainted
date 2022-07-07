package de.prog2.dungeontop.model.world.hellComponents;

import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellToStringValues;

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

    @Override
    public String toString()
    {
        return switch (this.getRotation())
        {
            case UP, DOWN -> HellToStringValues.ROOM_CENTER;
            case LEFT, RIGHT -> HellToStringValues.WHITESPACE;
        };
    }
}
