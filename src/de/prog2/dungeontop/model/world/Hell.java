package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.control.controller.RoomController;
import de.prog2.dungeontop.model.world.hellComponents.HellComponent;
import de.prog2.dungeontop.model.world.rooms.*;
import de.prog2.dungeontop.resources.HellToStringValues;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The Hell represents a level with rooms.
 */
public class Hell implements Serializable
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private final int width, height;
    private final HashMap<Coordinate, Room> roomHashMap = new HashMap<>();
    private final HashMap<Coordinate, HellComponent> hellComponentHashMap = new HashMap<>();
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
    public HashMap<Coordinate, HellComponent> getHellComponentHashMap () { return this.hellComponentHashMap; }
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

    /**
     * Custom toString-Method that builds a String that represents the stage layout of the hell
     *
     * @return String representation of the hell
     */
    @Override
    public String toString ()
    {
        StringBuilder res = new StringBuilder();
        for (int y = WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE - 1; y >= WorldConstants.LOWEST_COORDINATE; y--)
        {
            for (int x = 0; x < width * WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE; x++)
            {
                Coordinate c = new Coordinate(x,y);
                HellComponent component = getHellComponentByCoordinate(c);
                if (component == null)
                {
                    res.append(x % WorldConstants.ROOM_SIZE == 1 ?
                            HellToStringValues.ROOM_CENTER : HellToStringValues.WHITESPACE);
                } else
                {
                    res.append(component);
                }
            }
            res.append(HellToStringValues.LINE_BREAK);
        }
        return res.toString();
    }
}
