package de.prog2.dungeontop.model.world;

import java.util.HashMap;
import java.util.List;

public class Level
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private final int width, height;
    private HashMap<Coordinate, LevelComponent> levelComponentHashMap;
    private Field[][] fields;
    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public Level (int width, int height, List<LevelComponent> levelComponents)
    {
        this.width = width;
        this.height = height;
        generateFields(width, height);
    }
    private void generateFields (int width, int height)
    {
        this.fields = new Field[width][height];
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                this.fields[x][y] = new Field(new Coordinate(x, y));
            }
        }
    }
    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public LevelComponent getLevelComponent(Coordinate coordinate)
    {
        return levelComponentHashMap.get(coordinate);
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
}
