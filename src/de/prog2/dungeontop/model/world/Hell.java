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

        for (int i = WorldConstants.HELL_SIZE - 1; i >= WorldConstants.LOWEST_COORDINATE; i--)
        {
            for (int k = 0; k<3; k++)
            {
                for (int j = 0; WorldConstants.HELL_SIZE > j; j++)
                {
                    Coordinate currCoordinate = new Coordinate(j, i);
                    switch(k)
                    {
                        case 0:
                            if (this.getRoomHashMap().containsKey(currCoordinate))
                            {
                                res.append(HellToStringValues.TOP_LEFT_CORNER);
                                res.append(RoomController.hasTopRoom(
                                        this.getRoomByCoordinate(currCoordinate)) ?
                                        HellToStringValues.ROOM_CENTER : HellToStringValues.HORIZONTAL_WALL);
                                res.append(HellToStringValues.TOP_RIGHT_CORNER);
                                break;
                            }
                            res.append(HellToStringValues.NO_ROOM);
                            break;
                        case 1:

                            if (this.getRoomHashMap().containsKey(currCoordinate))
                            {
                                res.append(RoomController.hasLeftRoom(
                                        this.getRoomByCoordinate(currCoordinate)) ?
                                        HellToStringValues.WHITESPACE : HellToStringValues.VERTICAL_WALL);

                                //res.append(HellToStringValues.ROOM_CENTER);
                                Room currRoom = getRoomByCoordinate(currCoordinate);
                                if (currRoom instanceof ArenaRoom)
                                {
                                    if (currRoom == this.getBossRoom())
                                        res.append(HellToStringValues.BOSS_ROOM);
                                    else
                                        res.append(HellToStringValues.ARENA_ROOM);
                                }
                                if (currRoom instanceof EmptyRoom)
                                    res.append(HellToStringValues.ROOM_CENTER);
                                if (currRoom instanceof NPCRoom)
                                {
                                    if (currRoom instanceof ForgeRoom)
                                        res.append(HellToStringValues.FORGE_ROOM);
                                    else if (currRoom instanceof LavaPondRoom)
                                        res.append(HellToStringValues.LAVAPOND_ROOM);
                                    else
                                        res.append(HellToStringValues.NPC_ROOM);
                                }
                                if (currRoom instanceof RandomEventRoom)
                                    res.append(HellToStringValues.RANDOM_EVENT_ROOM);

                                res.append(RoomController.hasRightRoom(
                                        this.getRoomByCoordinate(currCoordinate)) ?
                                        HellToStringValues.WHITESPACE : HellToStringValues.VERTICAL_WALL);
                                break;
                            }
                            res.append(HellToStringValues.NO_ROOM);
                            break;
                        case 2:
                            if (this.getRoomHashMap().containsKey(currCoordinate))
                            {
                                res.append(HellToStringValues.BOTTOM_LEFT_CORNER);
                                res.append(RoomController.hasBottomRoom(
                                        this.getRoomByCoordinate(currCoordinate)) ?
                                        HellToStringValues.ROOM_CENTER : HellToStringValues.HORIZONTAL_WALL);
                                res.append(HellToStringValues.BOTTOM_RIGHT_CORNER);
                                break;
                            }
                            res.append(HellToStringValues.NO_ROOM);
                            break;
                        default:
                            GlobalLogger.warning(LoggerStringValues.INDEX_OUT_OF_BOUND);
                            break;
                    }
                }
                res.append(HellToStringValues.LINE_BREAK);
            }
        }
        return res.toString();
    }
}
