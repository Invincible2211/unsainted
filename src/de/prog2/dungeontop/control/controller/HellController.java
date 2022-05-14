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
        Room startingRoom = new Room (new Coordinate(hell.getWidth()/2, hell.getHeight()/2));
        addRoomToGrid(hell, startingRoom);
        GlobalLogger.log(LoggerStringValues.START_ROOM_CREATED);

        // The start room will have four passages
        // Generating the neighbors of the starting room:
        startingRoom.addTopRoom(hell);
        startingRoom.addBottomRoom(hell);
        startingRoom.addRightRoom(hell);
        startingRoom.addLeftRoom(hell);
        GlobalLogger.log(LoggerStringValues.START_ROOM_NEIGHBORS);

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
        int roomRNG = randomizer.nextInt(WorldConstants.RANDOMIZER_LIMIT);
        GlobalLogger.log(LoggerStringValues.ROOM_RNG_ROLL + roomRNG);


        // Legend for the following abbreviations:
        // T - Top, B - Bottom, R - Right, L - Left
        // e.g.: TRL is a room that has passages on the top side, right side and left side
        switch (roomRNG)
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
                if ((currentRoom.hasBottomRoom() || currentRoom.hasTopRoom()) && rightSideIsAvailable(hell, coordinate))
                {
                    currentRoom.addRightRoom(hell);
                }
                else if (currentRoom.hasRightRoom() && topSideIsAvailable(hell, coordinate))
                {
                    currentRoom.addTopRoom(hell);
                }
                else if (currentRoom.hasLeftRoom() && leftSideIsAvailable(hell, coordinate))
                {
                    currentRoom.addLeftRoom(hell);
                }
                break;

            /*
                bottom room = hasTopRoom    = TL -> add left room
                top room    = hasBottomRoom = LB -> add left room
                left room   = hasRightRoom  = RL -> add left room
                right room  = hasLeftRoom   = RL -> add right room
            */
            case 2:
                if ((currentRoom.hasBottomRoom() || currentRoom.hasTopRoom() || currentRoom.hasRightRoom()) &&
                        leftSideIsAvailable(hell, coordinate))
                {
                    currentRoom.addLeftRoom(hell);
                }
                else if (currentRoom.hasLeftRoom() && rightSideIsAvailable(hell, coordinate))
                {
                    currentRoom.addRightRoom(hell);
                }
                break;

            /*
                bottom room = hasTopRoom    = TB -> add bottom room
                top room    = hasBottomRoom = TB -> add top room
                left room   = hasRightRoom  = RB -> add bottom room
                right room  = hasLeftRoom   = LB -> add bottom room
            */
            case 3:
                if ((currentRoom.hasTopRoom() || currentRoom.hasRightRoom() || currentRoom.hasLeftRoom()) &&
                bottomSideIsAvailable(hell, coordinate))
                {
                    currentRoom.addBottomRoom(hell);
                }
                else if (currentRoom.hasBottomRoom() && topSideIsAvailable(hell, coordinate))
                {
                    currentRoom.addTopRoom(hell);
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
                if (currentRoom.hasTopRoom())
                {
                    if (rightSideIsAvailable(hell, coordinate))
                        currentRoom.addRightRoom(hell);
                    if (bottomSideIsAvailable(hell, coordinate))
                        currentRoom.addBottomRoom(hell);
                }
                else if (currentRoom.hasBottomRoom())
                {
                    if (rightSideIsAvailable(hell, coordinate))
                        currentRoom.addRightRoom(hell);
                    if (topSideIsAvailable(hell, coordinate))
                        currentRoom.addTopRoom(hell);
                }
                else if (currentRoom.hasRightRoom() || currentRoom.hasLeftRoom())
                {
                    if (topSideIsAvailable(hell, coordinate))
                        currentRoom.addTopRoom(hell);
                    if (bottomSideIsAvailable(hell, coordinate))
                        currentRoom.addBottomRoom(hell);
                }
                break;

            /*
                bottom room = hasTopRoom    = TRL -> add right and left room
                top room    = hasBottomRoom = RLB -> add right and left room
                left room   = hasRightRoom  = RLB -> add left and bottom room
                right room  = hasLeftRoom   = RLB -> add right and bottom room
            */
            case 5:
                if (currentRoom.hasTopRoom() || currentRoom.hasBottomRoom())
                {
                    if (rightSideIsAvailable(hell, coordinate))
                        currentRoom.addRightRoom(hell);
                    if (leftSideIsAvailable(hell, coordinate))
                        currentRoom.addLeftRoom(hell);
                }
                else if (currentRoom.hasRightRoom())
                {
                    if (leftSideIsAvailable(hell, coordinate))
                        currentRoom.addLeftRoom(hell);
                    if (bottomSideIsAvailable(hell, coordinate))
                        currentRoom.addBottomRoom(hell);
                }
                else if (currentRoom.hasLeftRoom())
                {
                    if (rightSideIsAvailable(hell, coordinate))
                        currentRoom.addRightRoom(hell);
                    if (bottomSideIsAvailable(hell, coordinate))
                        currentRoom.addBottomRoom(hell);
                }
                break;

            /*
                bottom room = hasTopRoom    = TLB -> add left and bottom room
                top room    = hasBottomRoom = TLB -> add top and left room
                left room   = hasRightRoom  = TRL -> add top and left room
                right room  = hasLeftRoom   = TRL -> add top and right room
             */
            case 6:
                if (currentRoom.hasTopRoom())
                {
                    if (leftSideIsAvailable(hell, coordinate))
                        currentRoom.addLeftRoom(hell);
                    if (bottomSideIsAvailable(hell, coordinate))
                        currentRoom.addBottomRoom(hell);
                }
                else if (currentRoom.hasBottomRoom() || currentRoom.hasRightRoom())
                {
                    if (topSideIsAvailable(hell, coordinate))
                        currentRoom.addTopRoom(hell);
                    if (leftSideIsAvailable(hell, coordinate))
                        currentRoom.addLeftRoom(hell);
                }
                else if (currentRoom.hasLeftRoom())
                {
                    if (topSideIsAvailable(hell, coordinate))
                        currentRoom.addTopRoom(hell);
                    if (rightSideIsAvailable(hell, coordinate))
                        currentRoom.addRightRoom(hell);
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
                if (currentRoom.hasTopRoom())
                {
                    if (rightSideIsAvailable(hell, coordinate))
                        currentRoom.addRightRoom(hell);
                    if (leftSideIsAvailable(hell, coordinate))
                        currentRoom.addLeftRoom(hell);
                    if (bottomSideIsAvailable(hell, coordinate))
                        currentRoom.addBottomRoom(hell);
                }
                else if (currentRoom.hasBottomRoom())
                {
                    if (topSideIsAvailable(hell, coordinate))
                        currentRoom.addTopRoom(hell);
                    if (rightSideIsAvailable(hell, coordinate))
                        currentRoom.addRightRoom(hell);
                    if (leftSideIsAvailable(hell, coordinate))
                        currentRoom.addLeftRoom(hell);
                }
                else if (currentRoom.hasRightRoom())
                {
                    if (topSideIsAvailable(hell, coordinate))
                        currentRoom.addTopRoom(hell);
                    if (leftSideIsAvailable(hell, coordinate))
                        currentRoom.addLeftRoom(hell);
                    if (bottomSideIsAvailable(hell, coordinate))
                        currentRoom.addBottomRoom(hell);
                }
                else if (currentRoom.hasLeftRoom())
                {
                    if (topSideIsAvailable(hell, coordinate))
                        currentRoom.addTopRoom(hell);
                    if (rightSideIsAvailable(hell, coordinate))
                        currentRoom.addRightRoom(hell);
                    if (bottomSideIsAvailable(hell, coordinate))
                        currentRoom.addBottomRoom(hell);
                }
                break;

            default:
                // TODO: throw an exception
                GlobalLogger.warning(LoggerStringValues.ROOM_RNG_FAILURE);
                break;
        }

        // Recursion: for all neighbor rooms which haven't been processed yet run this method
        if (currentRoom.hasTopRoom() && !hell.getRoomByCoordinate(new Coordinate(coordinate.getX(), coordinate.getY() + 1)).getProcessed())
        {
            GlobalLogger.log(LoggerStringValues.TOP_ROOM_RECURSION_BEGUN);
            initNeighbors(hell, new Coordinate(coordinate.getX(), coordinate.getY() + 1));
            GlobalLogger.log(LoggerStringValues.TOP_ROOM_RECURSION_ENDED);
        }

        if (currentRoom.hasBottomRoom() && !hell.getRoomByCoordinate(new Coordinate(coordinate.getX(), coordinate.getY() - 1)).getProcessed())
        {
            GlobalLogger.log(LoggerStringValues.BOTTOM_ROOM_RECURSION_BEGUN);
            initNeighbors(hell, new Coordinate(coordinate.getX(), coordinate.getY() - 1));
            GlobalLogger.log(LoggerStringValues.BOTTOM_ROOM_RECURSION_ENDED);
        }

        if (currentRoom.hasRightRoom() && !hell.getRoomByCoordinate(new Coordinate(coordinate.getX() + 1, coordinate.getY())).getProcessed());
        {
            GlobalLogger.log(LoggerStringValues.RIGHT_ROOM_RECURSION_BEGUN);
            initNeighbors(hell, new Coordinate(coordinate.getX() + 1, coordinate.getY()));
            GlobalLogger.log(LoggerStringValues.RIGHT_ROOM_RECURSION_ENDED);
        }

        if (currentRoom.hasLeftRoom() && !hell.getRoomByCoordinate(new Coordinate(coordinate.getX() - 1, coordinate.getY())).getProcessed())
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
        if (room.getCoordinate().getX() > WorldConstants.LOWEST_COORDINATE &&
            room.getCoordinate().getX() <= hell.getWidth() &&
            room.getCoordinate().getY() > WorldConstants.LOWEST_COORDINATE &&
            room.getCoordinate().getY() <= hell.getHeight() &&
            !hell.getRoomHashMap().containsKey(room.getCoordinate()))
        {
            hell.insertRoom(room.getCoordinate(), room);
            GlobalLogger.log(LoggerStringValues.ADDED_ROOM_TO_GRID);
        }
        else
        {
            // TODO: Add an exception
            GlobalLogger.warning(LoggerStringValues.ADD_ROOM_ERROR);
        }
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
        if (hell.getRoomHashMap().containsKey(new Coordinate(coordinate.getX(), coordinate.getY() + 1)))
            return false;
        return true;
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
        if (hell.getRoomHashMap().containsKey(new Coordinate(coordinate.getX(), coordinate.getY() - 1)))
            return false;
        return true;
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
        if (hell.getRoomHashMap().containsKey(new Coordinate(coordinate.getX() - 1, coordinate.getY())))
            return false;
        return true;
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
        if (hell.getRoomHashMap().containsKey(new Coordinate(coordinate.getX() + 1, coordinate.getY())))
            return false;
        return true;
    }

    public static void initHellComponentHashMap(Hell hell)
    {
        for (var entry : hell.getRoomHashMap().entrySet())
        {
            // get coordinates
            Coordinate coordCenter = new Coordinate(entry.getKey().getX() * 3 + 1, entry.getKey().getY() * 3 + 1);

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
            if(entry.getValue().getTopRoom() != null)
                hell.insertHellComponent(coordTop, new Passage(WorldConstants.HellComponentRotations.HORIZONTAL));
            else
                hell.insertHellComponent(coordTop, new Wall(WorldConstants.HellComponentRotations.HORIZONTAL));
            // bottom
            if(entry.getValue().getBottomRoom() != null)
                hell.insertHellComponent(coordBottom, new Passage(WorldConstants.HellComponentRotations.HORIZONTAL));
            else
                hell.insertHellComponent(coordBottom, new Wall(WorldConstants.HellComponentRotations.HORIZONTAL));
            // left
            if(entry.getValue().getLeft() != null)
                hell.insertHellComponent(coordLeft, new Passage(WorldConstants.HellComponentRotations.VERTICAL));
            else
                hell.insertHellComponent(coordLeft, new Wall(WorldConstants.HellComponentRotations.VERTICAL));
            // right
            if(entry.getValue().getRightRoom() != null)
                hell.insertHellComponent(coordRight, new Passage(WorldConstants.HellComponentRotations.VERTICAL));
            else
                hell.insertHellComponent(coordRight, new Wall(WorldConstants.HellComponentRotations.VERTICAL));
        }
    }
}
