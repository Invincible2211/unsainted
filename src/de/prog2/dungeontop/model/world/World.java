package de.prog2.dungeontop.model.world;

import java.util.Arrays;
import java.util.List;

public class World
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private int hellCount;
    private List<Hell> hells;
    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public World(int hellCount)
    {
        this.hellCount = hellCount;
    }
    private void generateLevels(int levelCount)
    {
        List<HellComponent> hellComponents = Arrays.asList(new Wall(), new Wall(),
                new Wall(),new Wall(),new Wall(),new Wall());

        for (int i = 0; i < levelCount; i++)
        {
            hells.add(new Hell(200, 200, hellComponents));
        }
    }
}
