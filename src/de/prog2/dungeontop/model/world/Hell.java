package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.control.controller.RoomController;
import de.prog2.dungeontop.model.world.rooms.ArenaRoom;
import de.prog2.dungeontop.model.world.rooms.Room;
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
                                res.append("┌");
                                res.append(RoomController.hasTopRoom(
                                        this.getRoomByCoordinate(currCoordinate)) ? "   " : "───");
                                res.append("\u2510");
                                break;
                            }
                            res.append("     ");
                            break;
                        case 1:

                            if (this.getRoomHashMap().containsKey(currCoordinate))
                            {
                                res.append(RoomController.hasLeftRoom(
                                        this.getRoomByCoordinate(currCoordinate)) ? " " : "│");
                                res.append("   ");
                                res.append(RoomController.hasRightRoom(
                                        this.getRoomByCoordinate(currCoordinate)) ? " " : "│");
                                break;
                            }
                            res.append("     ");
                            break;
                        case 2:
                            if (this.getRoomHashMap().containsKey(currCoordinate))
                            {
                                res.append("└");
                                res.append(RoomController.hasBottomRoom(
                                        this.getRoomByCoordinate(currCoordinate)) ? "   " : "───");
                                res.append("┘");
                                break;
                            }
                            res.append("     ");
                            break;
                        default:
                            GlobalLogger.warning(LoggerStringValues.INDEX_OUT_OF_BOUND);
                            break;
                    }
                }
                res.append("\n");
            }
        }
        return res.toString();
    }
}
