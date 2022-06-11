package de.prog2.dungeontop.utils;

public abstract class MyMath
{
    public static float clamp(float value, float min, float max)
    {
        return Math.max(min, Math.min(max, value));
    }
    public static <T extends Comparable<T>> T clamp(T val, T min, T max) {
        if (val.compareTo(min) < 0) return min;
        else if (val.compareTo(max) > 0) return max;
        else return val;
    }
}
