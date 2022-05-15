package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.control.controller.HellController;
import de.prog2.dungeontop.control.controller.RoomController;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.HashMap;

/**
 * The Hell represents a level with rooms.
 */
public class Hell
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private final int width, height;
    private HashMap<Coordinate, Room> roomHashMap;
    private HashMap<Coordinate, HellComponent> hellComponentHashMap;
    private Room startingRoom, bossRoom;
    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public Hell(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.roomHashMap = new HashMap<>();
    }
    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public Room getRoomByCoordinate(Coordinate coordinate)
    {
        return roomHashMap.get(coordinate);
    }
    public void insertRoom(Coordinate coordinate, Room room)
    {
        roomHashMap.put(coordinate, room);
    }
    public HashMap<Coordinate, Room> getRoomHashMap()
    {
        return this.roomHashMap;
    }

    public HellComponent getHellComponentByCoordinate(Coordinate coordinate)
    {
        return hellComponentHashMap.get(coordinate);
    }
    public void insertHellComponent(Coordinate coordinate, HellComponent hellComponent)
    {
        this.hellComponentHashMap.put(coordinate, hellComponent);
    }

    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public void setStartingRoom (Room room) { this.startingRoom = room; }
    public void setBossRoom (ArenaRoom room) { this.bossRoom = room; }
    public Room getStartingRoom () { return this.startingRoom; }
    public Room getBossRoom () { return this.bossRoom; }
    @Override
    public String toString ()
    {
        String res = "";

        for (int i = 0; WorldConstants.HELL_SIZE < i; i++)
        {
            for (int k = 0; k<3; k++)
            {
                for (int j = 0; WorldConstants.HELL_SIZE < j; j++)
                {
                    switch(k)
                    {
                        case 0:
                            //res += "┌" + (RoomController.hasTopRoom(this.getRoomByCoordinate(new Coordinate(i, j))) ? "   " : "───") + "┐";
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        default:
                            GlobalLogger.warning(LoggerStringValues.INDEX_OUT_OF_BOUND);
                            break;
                    }
                }
            }
        }

        return res;
    }
}
