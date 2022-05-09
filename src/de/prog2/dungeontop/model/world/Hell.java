package de.prog2.dungeontop.model.world;

import java.util.HashMap;
import java.util.List;

public class Hell
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private final int width, height;
    private HashMap<Coordinate, HellComponent> levelComponentHashMap;
    private Cell[][] cells;
    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public Hell(int width, int height, List<HellComponent> hellComponents)
    {
        this.width = width;
        this.height = height;
        generateFields(width, height);
    }
    private void generateFields (int width, int height)
    {
        this.cells = new Cell[width][height];
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                this.cells[x][y] = new Cell(new Coordinate(x, y));
            }
        }
    }
    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public HellComponent getLevelComponent(Coordinate coordinate)
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
