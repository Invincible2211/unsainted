package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.resources.RotationAngles;

public enum Rotation
{
    UP(RotationAngles.UP),
    LEFT(RotationAngles.LEFT),
    RIGHT(RotationAngles.RIGHT),
    DOWN(RotationAngles.DOWN);

    private int angle;
    private Rotation(int angle)
    {
        this.angle = angle;
    }

    public int getAngle()
    {
        return this.angle;
    }
}
