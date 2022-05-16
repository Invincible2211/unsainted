package de.prog2.dungeontop.model.world.hellComponents;

import de.prog2.dungeontop.resources.RotationAngles;

/**
 * Represents the rotation of a HellComponent
 * Also holds the rotation-angle
 */
public enum Rotation
{
    UP(RotationAngles.UP),
    LEFT(RotationAngles.LEFT),
    RIGHT(RotationAngles.RIGHT),
    DOWN(RotationAngles.DOWN);

    private final int angle;
    Rotation(int angle)
    {
        this.angle = angle;
    }

    public int getAngle()
    {
        return this.angle;
    }
}
