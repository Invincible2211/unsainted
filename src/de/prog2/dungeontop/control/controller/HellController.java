package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.world.*;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.CoordinateDirections;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.Random;

public class HellController
{
    /**
     * Method to initialize a hell.
     *
     * @param hell hell which has to be initialized
     */
    public static void initHell(Hell hell)
    {
        Room startingRoom = new EmptyRoom (new Coordinate(hell.getWidth()/2, hell.getHeight()/2));
        addRoomToGrid(hell, startingRoom);
        GlobalLogger.log(LoggerStringValues.START_ROOM_CREATED);

        // The start room will have four passages
        // Generating the neighbors of the starting room:
        RoomController.addTopRoom(hell, startingRoom);
        RoomController.addBottomRoom(hell, startingRoom);
        RoomController.addRightRoom(hell, startingRoom);
        RoomController.addLeftRoom(hell, startingRoom);
        GlobalLogger.log(LoggerStringValues.START_ROOM_NEIGHBORS);

        initNeighbors(hell, startingRoom.getCoordinate());

        initHellComponentHashMap(hell);
    }

    /**
     * Recursive Method which generates the Hell by generating the neighbors for each individual room.
     *
     * @param coordinate Coordinate of the room for which the neighbor rooms shall be created
     */
    private static void initNeighbors (Hell hell, Coordinate coordinate)
    {
        // flag the current room as processed, to prevent endless recursion
        Room currentRoom = hell.getRoomByCoordinate(coordinate);
        currentRoom.setProcessed();

        // based on the pseudo-randomly generated number and the relative position of the predecessor room
        // we will decide the number and position of passages of the current room
        Random randomizer = new Random();
        int randomRoomNumber = randomizer.nextInt(WorldConstants.RANDOMIZER_LIMIT);
        GlobalLogger.log(LoggerStringValues.ROOM_RNG_ROLL + randomRoomNumber);


        // Legend for the following abbreviations:
        // T - Top, B - Bottom, R - Right, L - Left
        // e.g.: TRL is a room that has passages on the top side, right side and left side
        switch (randomRoomNumber)
        {
            // DEAD END == only one passage
            case 0:
                break;

            // One potential new neighbor room
            /*
                bottom room = hasTopRoom    = TR -> add right room
                top room    = hasBottomRoom = RB -> add right room
                left room   = hasRightRoom  = TR -> add top room
                right room  = hasLeftRoom   = TL -> add left room
            */
            case 1:
                if ((RoomController.hasBottomRoom(currentRoom) || RoomController.hasTopRoom(currentRoom)) &&
                        rightSideIsAvailable(hell, coordinate))
                {
                    RoomController.addRightRoom(hell, currentRoom);
                }
                else if (RoomController.hasRightRoom(currentRoom) && topSideIsAvailable(hell, coordinate))
                {
                    RoomController.addTopRoom(hell, currentRoom);
                }
                else if (RoomController.hasLeftRoom(currentRoom) && leftSideIsAvailable(hell, coordinate))
                {
                    RoomController.addLeftRoom(hell, currentRoom);
                }
                break;

            /*
                bottom room = hasTopRoom    = TL -> add left room
                top room    = hasBottomRoom = LB -> add left room
                left room   = hasRightRoom  = RL -> add left room
                right room  = hasLeftRoom   = RL -> add right room
            */
            case 2:
                if ((RoomController.hasBottomRoom(currentRoom) || RoomController.hasTopRoom(currentRoom) ||
                        RoomController.hasRightRoom(currentRoom)) && leftSideIsAvailable(hell, coordinate))
                {
                    RoomController.addLeftRoom(hell, currentRoom);
                }
                else if (RoomController.hasLeftRoom(currentRoom) && rightSideIsAvailable(hell, coordinate))
                {
                    RoomController.addRightRoom(hell, currentRoom);
                }
                break;

            /*
                bottom room = hasTopRoom    = TB -> add bottom room
                top room    = hasBottomRoom = TB -> add top room
                left room   = hasRightRoom  = RB -> add bottom room
                right room  = hasLeftRoom   = LB -> add bottom room
            */
            case 3:
                if ((RoomController.hasTopRoom(currentRoom) || RoomController.hasRightRoom(currentRoom) ||
                        RoomController.hasLeftRoom(currentRoom)) && bottomSideIsAvailable(hell, coordinate))
                {
                    RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasBottomRoom(currentRoom) && topSideIsAvailable(hell, coordinate))
                {
                    RoomController.addTopRoom(hell, currentRoom);
                }
                break;


            // Two potential new neighbors
            /*
                bottom room = hasTopRoom    = TRB -> add right and bottom room
                top room    = hasBottomRoom = TRB -> add right and top room
                left room   = hasRightRoom  = TRB -> add top and bottom room
                right room  = hasLeftRoom   = TLB -> add top and bottom room
            */
            case 4:
                if (RoomController.hasTopRoom(currentRoom))
                {
                    if (rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasBottomRoom(currentRoom))
                {
                    if (rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                }
                else if (RoomController.hasRightRoom(currentRoom) || RoomController.hasLeftRoom(currentRoom))
                {
                    if (topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                break;

            /*
                bottom room = hasTopRoom    = TRL -> add right and left room
                top room    = hasBottomRoom = RLB -> add right and left room
                left room   = hasRightRoom  = RLB -> add left and bottom room
                right room  = hasLeftRoom   = RLB -> add right and bottom room
            */
            case 5:
                if (RoomController.hasTopRoom(currentRoom) || RoomController.hasBottomRoom(currentRoom))
                {
                    if (rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                }
                else if (RoomController.hasRightRoom(currentRoom))
                {
                    if (leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                    if (bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasLeftRoom(currentRoom))
                {
                    if (rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                break;

            /*
                bottom room = hasTopRoom    = TLB -> add left and bottom room
                top room    = hasBottomRoom = TLB -> add top and left room
                left room   = hasRightRoom  = TRL -> add top and left room
                right room  = hasLeftRoom   = TRL -> add top and right room
             */
            case 6:
                if (RoomController.hasTopRoom(currentRoom))
                {
                    if (leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                    if (bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasBottomRoom(currentRoom) || RoomController.hasRightRoom(currentRoom))
                {
                    if (topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                }
                else if (RoomController.hasLeftRoom(currentRoom))
                {
                    if (topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                }
                break;

            // three potential new neighbors (== room that has passage on each side), currently disabled
            /*
                bottom room = hasTopRoom    = TRLB -> add right, left and bottom room
                top room    = hasBottomRoom = TRLB -> add top, right and left room
                left room   = hasRightRoom  = TRLB -> add top, left, bottom room
                right room  = hasLeftRoom   = TRLB -> add top, right and bottom room
             */
            case 7:
                if (RoomController.hasTopRoom(currentRoom))
                {
                    if (rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                    if (bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasBottomRoom(currentRoom))
                {
                    if (topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                }
                else if (RoomController.hasRightRoom(currentRoom))
                {
                    if (topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                    if (bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasLeftRoom(currentRoom))
                {
                    if (topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                break;

            default:
                // TODO: throw an exception
                GlobalLogger.warning(LoggerStringValues.ROOM_RNG_FAILURE);
                break;
        }

        // Recursion: for all neighbor rooms which haven't been processed yet run this method
        if (RoomController.hasTopRoom(currentRoom) && !hell.getRoomByCoordinate(new Coordinate(coordinate.getX(), coordinate.getY() + 1)).getProcessed())
        {
            GlobalLogger.log(LoggerStringValues.TOP_ROOM_RECURSION_BEGUN);
            initNeighbors(hell, new Coordinate(coordinate.getX(), coordinate.getY() + 1));
            GlobalLogger.log(LoggerStringValues.TOP_ROOM_RECURSION_ENDED);
        }

        if (RoomController.hasBottomRoom(currentRoom) && !hell.getRoomByCoordinate(new Coordinate(coordinate.getX(), coordinate.getY() - 1)).getProcessed())
        {
            GlobalLogger.log(LoggerStringValues.BOTTOM_ROOM_RECURSION_BEGUN);
            initNeighbors(hell, new Coordinate(coordinate.getX(), coordinate.getY() - 1));
            GlobalLogger.log(LoggerStringValues.BOTTOM_ROOM_RECURSION_ENDED);
        }

        if (RoomController.hasRightRoom(currentRoom) && !hell.getRoomByCoordinate(new Coordinate(coordinate.getX() + 1, coordinate.getY())).getProcessed())
        {
            GlobalLogger.log(LoggerStringValues.RIGHT_ROOM_RECURSION_BEGUN);
            initNeighbors(hell, new Coordinate(coordinate.getX() + 1, coordinate.getY()));
            GlobalLogger.log(LoggerStringValues.RIGHT_ROOM_RECURSION_ENDED);
        }

        if (RoomController.hasLeftRoom(currentRoom) && !hell.getRoomByCoordinate(new Coordinate(coordinate.getX() - 1, coordinate.getY())).getProcessed())
        {
            GlobalLogger.log(LoggerStringValues.LEFT_ROOM_RECURSION_BEGUN);
            initNeighbors(hell, new Coordinate(coordinate.getX() - 1, coordinate.getY()));
            GlobalLogger.log(LoggerStringValues.LEFT_ROOM_RECURSION_ENDED);
        }
    }

    /**
     * Adds a room to the room hashmap of the hell.
     *
     * @param hell hell for which the room shall be added
     * @param room room which shall be added
     */
    public static void addRoomToGrid (Hell hell, Room room)
    {
        // room has to be within the bounds of the hell and cannot overlap an already existing room
        if (!roomIsInsideHellBounds(hell, room) || hell.getRoomHashMap().containsKey(room.getCoordinate()))
        {
            // TODO: Add an exception
            GlobalLogger.warning(LoggerStringValues.ADD_ROOM_ERROR);
            return;
        }
        hell.insertRoom(room.getCoordinate(), room);
        GlobalLogger.log(LoggerStringValues.ADDED_ROOM_TO_GRID);
    }

    public static boolean roomIsInsideHellBounds(Hell hell, Room room)
    {
        return room.getCoordinate().getX() > WorldConstants.LOWEST_COORDINATE &&
                room.getCoordinate().getX() <= hell.getWidth() &&
                room.getCoordinate().getY() > WorldConstants.LOWEST_COORDINATE &&
                room.getCoordinate().getY() <= hell.getHeight();
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
        if (coordinate.getY() + 1 > hell.getHeight())
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
        if (coordinate.getX() + 1 > hell.getWidth())
            return false;
        return !hell.getRoomHashMap().containsKey(new Coordinate(coordinate.getX() + 1, coordinate.getY()));
    }

    /**
     * Pseudo-randomizes the different types of rooms in this hell
     *
     * @param hell hell which contains the rooms that have to be randomized
     */
    public static void randomizeRoomTypes (Hell hell)
    {

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
    }

    /**
     * Inserts the correct HellComponents given the roomHashMap into hell
     * @param hell hell that contains the rooms and gets the hellComponents
     */
    public static void initHellComponentHashMap(Hell hell)
    {
        GlobalLogger.log(LoggerStringValues.INIT_HELL_COMPONENT_HASH_MAP_START);
        for (var entry : hell.getRoomHashMap().entrySet())
        {
            // get coordinates
            Coordinate coordCenter = new Coordinate(entry.getKey().getX() * WorldConstants.ROOM_SIZE + 1,
                    entry.getKey().getY() * WorldConstants.ROOM_SIZE + 1);

            Coordinate coordTopLeft = CoordinateDirections.getLeftTop(coordCenter);
            Coordinate coordTopRight = CoordinateDirections.getRightTop(coordCenter);
            Coordinate coordBottomLeft = CoordinateDirections.getLeftBottom(coordCenter);
            Coordinate coordBottomRight = CoordinateDirections.getRightBottom(coordCenter);

            Coordinate coordTop = CoordinateDirections.getTop(coordCenter);
            Coordinate coordBottom = CoordinateDirections.getBottom(coordCenter);
            Coordinate coordLeft = CoordinateDirections.getLeft(coordCenter);
            Coordinate coordRight = CoordinateDirections.getRight(coordCenter);

            // add roomCenter
            hell.insertHellComponent(entry.getKey(), new RoomCenter(entry.getValue()));

            // add the 4 corners
            hell.insertHellComponent(coordTopLeft, new WallCorner(WorldConstants.HellComponentRotations.TOP_LEFT));
            hell.insertHellComponent(coordTopRight, new WallCorner(WorldConstants.HellComponentRotations.TOP_RIGHT));
            hell.insertHellComponent(coordBottomLeft, new WallCorner(WorldConstants.HellComponentRotations.BOTTOM_LEFT));
            hell.insertHellComponent(coordBottomRight, new WallCorner(WorldConstants.HellComponentRotations.BOTTOM_RIGHT));

            // add passages and walls
            // top
            if(RoomController.hasTopRoom(entry.getValue()))
                hell.insertHellComponent(coordTop, new Passage(WorldConstants.HellComponentRotations.HORIZONTAL));
            else
                hell.insertHellComponent(coordTop, new Wall(WorldConstants.HellComponentRotations.HORIZONTAL));
            // bottom
            if(RoomController.hasBottomRoom(entry.getValue()))
                hell.insertHellComponent(coordBottom, new Passage(WorldConstants.HellComponentRotations.HORIZONTAL));
            else
                hell.insertHellComponent(coordBottom, new Wall(WorldConstants.HellComponentRotations.HORIZONTAL));
            // left
            if(RoomController.hasLeftRoom(entry.getValue()))
                hell.insertHellComponent(coordLeft, new Passage(WorldConstants.HellComponentRotations.VERTICAL));
            else
                hell.insertHellComponent(coordLeft, new Wall(WorldConstants.HellComponentRotations.VERTICAL));
            // right
            if(RoomController.hasRightRoom(entry.getValue()))
                hell.insertHellComponent(coordRight, new Passage(WorldConstants.HellComponentRotations.VERTICAL));
            else
                hell.insertHellComponent(coordRight, new Wall(WorldConstants.HellComponentRotations.VERTICAL));
        }
        GlobalLogger.log(LoggerStringValues.INIT_HELL_COMPONENT_HASH_MAP_END);
    }
}
