package de.prog2.dungeontop.model.world;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Map
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private int levelCount;
    private List<Level> levels;
    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public Map(int levelCount)
    {
        this.levelCount = levelCount;
    }
    private void generateLevels(int levelCount)
    {
        List<LevelComponent> levelComponents = Arrays.asList(new Room(), new Path());

        for (int i = 0; i < levelCount; i++)
        {
            levels.add(new Level(200, 200, levelComponents));
        }
    }
}
