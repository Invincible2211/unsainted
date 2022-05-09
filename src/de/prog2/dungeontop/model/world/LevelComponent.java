package de.prog2.dungeontop.model.world;

public abstract class LevelComponent
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private final int width, height;
    private Field[][] occupatedFields;
    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public LevelComponent (int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
}
