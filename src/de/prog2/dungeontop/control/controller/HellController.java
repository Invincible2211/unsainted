package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.exceptions.customexceptions.SpotAlreadyOccupiedException;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.hellComponents.HellComponent;
import de.prog2.dungeontop.model.world.hellComponents.RoomCenter;
import de.prog2.dungeontop.model.world.hellComponents.Wall;
import de.prog2.dungeontop.model.world.hellComponents.WallCorner;
import de.prog2.dungeontop.model.world.rooms.Room;
import de.prog2.dungeontop.resources.HellToStringValues;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.CoordinateDirections;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class HellController
{

    /**
     * Adds a room to the room hashmap of the hell.
     *
     * @param hell hell for which the room shall be added
     * @param room room which shall be added
     * @throws SpotAlreadyOccupiedException thrown if the method tries to place a room on a spot
     * that is already occupied by another room
     */
    public static void addRoomToGrid (Hell hell, Room room) throws SpotAlreadyOccupiedException
    {
        // room has to be within the bounds of the hell and cannot overlap an already existing room
        if (!roomIsInsideHellBounds(hell, room) || hell.getRoomHashMap().containsKey(room.getCoordinate()))
        {
            GlobalLogger.warning(String.format(LoggerStringValues.HELL_CONTROLLER_WARNING_ROOM_INSIDE_BOUNDS,
                    roomIsInsideHellBounds(hell,room)));
            GlobalLogger.warning(String.format(LoggerStringValues.HELL_CONTROLLER_WARNING_ROOM_COORDINATES,
                    room.getCoordinate().getX(), room.getCoordinate().getY()));
            throw new SpotAlreadyOccupiedException();
        }
        hell.insertRoom(room.getCoordinate(), room);
        GlobalLogger.log(LoggerStringValues.ADDED_ROOM_TO_GRID);
    }

    /**
     * Removes the given room from the given hell.
     *
     * @param hell hell that contains the to be removed room
     * @param room room that shall be removed
     */
    public static void removeRoomFromGrid (Hell hell, Room room)
    {
        hell.getRoomHashMap().remove(room.getCoordinate());
    }

    public static boolean roomIsInsideHellBounds(Hell hell, Room room)
    {
        return room.getCoordinate().getX() >= WorldConstants.LOWEST_COORDINATE &&
                room.getCoordinate().getX() < hell.getWidth() &&
                room.getCoordinate().getY() >= WorldConstants.LOWEST_COORDINATE &&
                room.getCoordinate().getY() < hell.getHeight();
    }

    /**
     * Method that checks if the top side is empty and within bounds for a given room.
     *
     * @param coordinate coordinate of the room for which the top side has to be checked
     * @param hell hell that contains the room
     * @return if the top side spot is still free and within bounds
     */
    public static boolean topSideIsAvailable (Hell hell, Coordinate coordinate)
    {
        GlobalLogger.log(LoggerStringValues.TOP_AVAILABLE);
        if (coordinate.getY() + 1 >= hell.getHeight())
            return false;
        return !hell.getRoomHashMap().containsKey(new Coordinate(coordinate.getX(), coordinate.getY() + 1));
    }

    /**
     * Method that checks if the bottom side is empty and within bounds for a given room.
     *
     * @param coordinate coordinate of the room for which the bottom side has to be checked
     * @param hell hell that contains the room
     * @return if the bottom side spot is still free and within bounds
     */
    public static boolean bottomSideIsAvailable (Hell hell, Coordinate coordinate)
    {
        GlobalLogger.log(LoggerStringValues.BOTTOM_AVAILABLE);
        if (coordinate.getY() - 1 < WorldConstants.LOWEST_COORDINATE)
            return false;
        return !hell.getRoomHashMap().containsKey(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
    }

    /**
     * Method that checks if the left side is empty and within bounds for a given room.
     *
     * @param coordinate coordinate of the room for which the left side has to be checked
     * @param hell hell that contains the room
     * @return if the left side spot is still free and within bounds
     */
    public static boolean leftSideIsAvailable (Hell hell, Coordinate coordinate)
    {
        GlobalLogger.log((LoggerStringValues.LEFT_AVAILABLE));
        if (coordinate.getX() - 1 < WorldConstants.LOWEST_COORDINATE)
            return false;
        return !hell.getRoomHashMap().containsKey(new Coordinate(coordinate.getX() - 1, coordinate.getY()));
    }

    /**
     * Method that checks if the right side is empty and within bounds for a given room.
     *
     * @param coordinate coordinate of the room for which the right side has to be checked
     * @param hell hell that contains the room
     * @return if the right side spot is still free and within bounds
     */
    public static boolean rightSideIsAvailable (Hell hell, Coordinate coordinate)
    {
        GlobalLogger.log(LoggerStringValues.RIGHT_AVAILABLE);
        if (coordinate.getX() + 1 >= hell.getWidth())
            return false;
        return !hell.getRoomHashMap().containsKey(new Coordinate(coordinate.getX() + 1, coordinate.getY()));
    }

    /**
     * Replaces a room in a hell by another room.
     *
     * @param hell hell which contains the old room which shall be replaced by the new room
     * @param oldRoom room that has to be replaced
     * @param newRoom room that replaces the old room
     */
    public static void replaceRoom (Hell hell, Room oldRoom, Room newRoom)
    {
        newRoom.setCoordinate(oldRoom.getCoordinate());

        // check for top side room
        if (RoomController.hasTopRoom(oldRoom))
        {
            oldRoom.getTopRoom().setBottomRoom(newRoom);
            newRoom.setTopRoom(oldRoom.getTopRoom());
            GlobalLogger.log(LoggerStringValues.TOP_ROOM_REPLACED);
        }

        // check for botttom side room
        if (RoomController.hasBottomRoom(oldRoom))
        {
            oldRoom.getBottomRoom().setTopRoom(newRoom);
            newRoom.setBottomRoom(oldRoom.getBottomRoom());
            GlobalLogger.log(LoggerStringValues.BOTTOM_ROOM_REPLACED);
        }

        // check right side room
        if (RoomController.hasRightRoom(oldRoom))
        {
            oldRoom.getRightRoom().setLeftRoom(newRoom);
            newRoom.setRightRoom(oldRoom.getRightRoom());
            GlobalLogger.log(LoggerStringValues.RIGHT_ROOM_REPLACED);
        }

        // check for left room
        if (RoomController.hasLeftRoom(oldRoom))
        {
            oldRoom.getLeftRoom().setRightRoom(newRoom);
            newRoom.setLeftRoom(oldRoom.getLeftRoom());
            GlobalLogger.log(LoggerStringValues.LEFT_ROOM_REPLACED);
        }
        try
        {
            removeRoomFromGrid(hell, oldRoom);
            addRoomToGrid(hell, newRoom);
        }
        catch (SpotAlreadyOccupiedException ex)
        {
            GlobalLogger.warning(ex.getMessage());
        }
    }

    /**
     * Method to get a list representation of all the rooms in the given hell.
     *
     * @param hell hell for which the room list has to be created
     * @return list representation of all the rooms in the given hell
     */
    public static List<Room> getRoomList (Hell hell)
    {
        LinkedList<Room> roomList = new LinkedList<>();
        for (var room : hell.getRoomHashMap().entrySet())
        {
            roomList.add(room.getValue());
        }
        return roomList;
    }

    /**
     * Generates the string representation of a hellcomponentHashMap.
     *
     * @return string representation of a hellcomponentHashMap
     */
    @Deprecated
    public static String hellcomponentToString (Hell hell)
    {
        StringBuilder res = new StringBuilder();
        HashMap<Coordinate, HellComponent> hellComponents = hell.getHellComponentHashMap();
        int height = hell.getHeight();
        int width = hell.getWidth();

        for (int y=(height*WorldConstants.ROOM_SIZE)-1; y >= 0; y--)
        {
            for (int x=0; x<width*WorldConstants.ROOM_SIZE; x++)
            {
                Coordinate coord = new Coordinate(x,y);
                if(!hellComponents.containsKey(coord))
                {
                    res.append(x%WorldConstants.ROOM_SIZE == 1 ?
                            HellToStringValues.ROOM_CENTER: HellToStringValues.WHITESPACE);
                    continue;
                }
                HellComponent curr = hell.getHellComponentByCoordinate(coord);
                if ( curr instanceof WallCorner)
                {
                    switch (curr.getRotation()) {
                        case UP -> res.append(HellToStringValues.TOP_LEFT_CORNER);
                        case RIGHT -> res.append(HellToStringValues.TOP_RIGHT_CORNER);
                        case LEFT -> res.append(HellToStringValues.BOTTOM_LEFT_CORNER);
                        case DOWN -> res.append(HellToStringValues.BOTTOM_RIGHT_CORNER);
                    }
                }
                else if (curr instanceof Wall)
                {
                    switch (curr.getRotation())
                    {
                        case UP -> res.append(HellToStringValues.HORIZONTAL_WALL);
                        case LEFT -> res.append(HellToStringValues.VERTICAL_WALL);
                    }
                }
                else if (curr instanceof RoomCenter)
                {
                    res.append(HellToStringValues.ROOM_CENTER);
                } else {
                    res.append(x%WorldConstants.ROOM_SIZE == 1 ?
                            HellToStringValues.ROOM_CENTER: HellToStringValues.WHITESPACE);
                }
            }
            res.append(HellToStringValues.LINE_BREAK);
        }
        return res.toString();
    }
}
