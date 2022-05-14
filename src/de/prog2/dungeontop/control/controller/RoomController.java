package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.EmptyRoom;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.Room;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

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
        top.setBottomRoom(room);
        room.setTopRoom(top);
        HellController.addRoomToGrid(hell, top);
        GlobalLogger.log(LoggerStringValues.ADDED_TOP_ROOM);
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
        bot.setTopRoom(room);
        room.setBottomRoom(bot);
        HellController.addRoomToGrid(hell, bot);
        GlobalLogger.log(LoggerStringValues.ADDED_BOTTOM_ROOM);
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
        left.setRightRoom(room);
        room.setLeftRoom(left);
        HellController.addRoomToGrid(hell, left);
        GlobalLogger.log(LoggerStringValues.ADDED_LEFT_ROOM);
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
        right.setLeftRoom(room);
        room.setRightRoom(right);
        HellController.addRoomToGrid(hell, right);
        GlobalLogger.log(LoggerStringValues.ADDED_RIGHT_ROOM);
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
}
