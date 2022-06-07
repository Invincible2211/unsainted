package de.prog2.dungeontop.utils;

public abstract class MyMath
{
    public static float clamp(float value, float min, float max)
    {
        return Math.max(min, Math.min(max, value));
    }
}
