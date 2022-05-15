package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.exceptions.customexceptions.SpotAlreadyOccupiedException;
import de.prog2.dungeontop.model.world.*;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.LinkedList;
import java.util.List;

public class RoomController
{
    /*-------------------------------ADDING TOP, BOTTOM, LEFT, RIGHT ROOMS--------------------------------------------*/

    /**
     * Method to add a new room on the top side.
     *
     * @param room room that shall get a topside neighbor
     * @param hell hell in which the current room is contained
     */
    public static void addTopRoom (Hell hell, Room room)
    {
        Room top = new EmptyRoom(new Coordinate( room.getCoordinate().getX(), room.getCoordinate().getY() + 1 ));
        top.setDistanceFromStart(room.getDistanceFromStart() + 1);
        top.setBottomRoom(room);
        room.setTopRoom(top);
        try
        {
            HellController.addRoomToGrid(hell, top);
            GlobalLogger.log(LoggerStringValues.ADDED_TOP_ROOM);
        }
        catch (SpotAlreadyOccupiedException ex)
        {
            GlobalLogger.warning(ex.getMessage());
        }
    }

    /**
     * Method to add a new room on the bottom side.
     *
     * @param room room that shall get a bottom side neighbor
     * @param hell hell in which the current room is contained
     */
    public static void addBottomRoom(Hell hell, Room room)
    {
        Room bot = new EmptyRoom (new Coordinate( room.getCoordinate().getX(), room.getCoordinate().getY() - 1 ));
        bot.setDistanceFromStart(room.getDistanceFromStart() + 1);
        bot.setTopRoom(room);
        room.setBottomRoom(bot);
        try
        {
            HellController.addRoomToGrid(hell, bot);
            GlobalLogger.log(LoggerStringValues.ADDED_BOTTOM_ROOM);
        }
        catch (SpotAlreadyOccupiedException ex)
        {
            GlobalLogger.warning(ex.getMessage());
        }

    }

    /**
     * Method to add a new room on the left side.
     *
     * @param room room that shall get a left side neighbor
     * @param hell hell in which the current room is contained
     */
    public static void addLeftRoom (Hell hell, Room room)
    {
        Room left = new EmptyRoom (new Coordinate( room.getCoordinate().getX() - 1, room.getCoordinate().getY() ));
        left.setDistanceFromStart(room.getDistanceFromStart() + 1);
        left.setRightRoom(room);
        room.setLeftRoom(left);
        try
        {
            HellController.addRoomToGrid(hell, left);
            GlobalLogger.log(LoggerStringValues.ADDED_LEFT_ROOM);
        }
        catch (SpotAlreadyOccupiedException ex)
        {
            GlobalLogger.warning(ex.getMessage());
        }

    }

    /**
     * Method to add a new room on the right side.
     *
     * @param room room that shall get a right side neighbor
     * @param hell hell in which the current room is contained
     */
    public static void addRightRoom (Hell hell, Room room)
    {
        Room right = new EmptyRoom (new Coordinate( room.getCoordinate().getX() + 1, room.getCoordinate().getY() ));
        right.setDistanceFromStart(room.getDistanceFromStart() + 1);
        right.setLeftRoom(room);
        room.setRightRoom(right);
        try
        {
            HellController.addRoomToGrid(hell, right);
            GlobalLogger.log(LoggerStringValues.ADDED_RIGHT_ROOM);
        }
        catch (SpotAlreadyOccupiedException ex)
        {
            GlobalLogger.warning(ex.getMessage());
        }
    }

    /*----------------------QUERYING IF THE ROOM HAS A TOP, BOTTOM, LEFT, RIGHT ROOM----------------------------------*/

    /**
     * Method to query if a room has a top side neighbor room.
     *
     * @param room room that has to be checked for a top side neighbor
     * @return true if there is a top side neighbor, false otherwise
     */
    public static boolean hasTopRoom (Room room)
    {
        GlobalLogger.log(LoggerStringValues.HAS_TOP_ROOM);
        return room.getTopRoom() != null;
    }

    /**
     * Method to query if a room has a bottom side neighbor room.
     *
     * @param room room that has to be checked for a bottom side neighbor
     * @return true if there is a bottom side neighbor, false otherwise
     */
    public static boolean hasBottomRoom (Room room)
    {
        GlobalLogger.log(LoggerStringValues.HAS_BOTTOM_ROOM);
        return room.getBottomRoom() != null;
    }

    /**
     * Method to query if a room has a left side neighbor room.
     *
     * @param room room that has to be checked for a left side room
     * @return true if there is a left side neighbor, false otherwise
     */
    public static boolean hasLeftRoom (Room room)
    {
        GlobalLogger.log(LoggerStringValues.HAS_LEFT_ROOM);
        return room.getLeftRoom() != null;
    }

    /**
     * Method to query if a room has a right side neighbor room.
     *
     * @param room room that has to be checked for a right side room
     * @return true if there is a right side neighbor, false otherwise
     */
    public static boolean hasRightRoom (Room room)
    {
        GlobalLogger.log(LoggerStringValues.HAS_RIGHT_ROOM);
        return room.getRightRoom() != null;
    }

    /*------------------------------GETTING INFORMATION ABOUT NEIGHBOR ROOMS------------------------------------------*/

    /**
     *  Method to get a list of neighbors for a given room.
     *
     * @param room room for which the neighbors shall be gotten
     * @return list representation of the adjacent rooms
     */
    public static List<Room> getNeighbors (Room room)
    {
        GlobalLogger.log(LoggerStringValues.NEIGHBORS_QUERIED);
        List<Room> neighbors = new LinkedList<>();

        // adding top side neighbor
        if (hasTopRoom(room))
        {
            neighbors.add(room.getTopRoom());
            GlobalLogger.log(LoggerStringValues.TOP_NEIGHBOR_ADDED);
        }
        // adding bottom side neighbor
        if (hasBottomRoom(room))
        {
            neighbors.add(room.getBottomRoom());
            GlobalLogger.log(LoggerStringValues.BOTTOM_NEIGHBOR_ADDED);
        }
        // adding right side neighbor
        if (hasRightRoom(room))
        {
            neighbors.add(room.getRightRoom());
            GlobalLogger.log(LoggerStringValues.RIGHT_NEIGHBOR_ADDED);
        }
        // adding left side neighbor
        if (hasLeftRoom(room))
        {
            neighbors.add(room.getLeftRoom());
            GlobalLogger.log(LoggerStringValues.LEFT_NEIGHBOR_ADDED);
        }

        return neighbors;
    }

    /**
     * Tells you for a given room if there is a neighbor in an adjacent room.
     *
     * @param room room that has to be checked for NPC neighbors
     * @return true if there is a NPC in an adjacent room
     */
    public static boolean neighborsContainNPC (Room room)
    {
        GlobalLogger.log(LoggerStringValues.NEIGHBOR_CONTAINS_NPC_START);
        List<Room> neighbors = getNeighbors(room);

        for (Room neighbor : neighbors)
        {
            if (room instanceof NPCRoom)
            {
                GlobalLogger.log(LoggerStringValues.NEIGHBOR_CONTAINS_NPC);
                return true;
            }
        }
        GlobalLogger.log(LoggerStringValues.NEIGHBORS_NO_NPC);
        return false;
    }
}
