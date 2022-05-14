package de.prog2.dungeontop.model.world;

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
}
