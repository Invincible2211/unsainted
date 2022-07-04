package de.prog2.dungeontop.model.world.hellComponents;

import de.prog2.dungeontop.resources.RotationAngles;

import java.io.Serializable;

/**
 * Represents the rotation of a HellComponent
 * Also holds the rotation-angle
 */
public enum Rotation implements Serializable
{
    UP(RotationAngles.UP),
    LEFT(RotationAngles.LEFT),
    RIGHT(RotationAngles.RIGHT),
    DOWN(RotationAngles.DOWN);

    private final double angle;
    Rotation(double angle)
    {
        this.angle = angle;
    }

    public double getAngle()
    {
        return this.angle;
    }
}
