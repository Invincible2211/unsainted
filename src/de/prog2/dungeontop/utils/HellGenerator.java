package de.prog2.dungeontop.utils;

import de.prog2.dungeontop.control.controller.HellController;
import de.prog2.dungeontop.control.controller.RoomController;
import de.prog2.dungeontop.model.exceptions.customexceptions.NotAValidRoomtypeException;
import de.prog2.dungeontop.model.exceptions.customexceptions.NotValidRngRollException;
import de.prog2.dungeontop.model.exceptions.customexceptions.SpotAlreadyOccupiedException;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.hellComponents.*;
import de.prog2.dungeontop.model.world.rooms.*;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.WorldConstants;

import java.util.List;
import java.util.Random;

public class HellGenerator
{
    /**
     * Method to initialize a hell.
     *
     * @param hell hell which has to be initialized
     */
    public static void initHell(Hell hell)
    {
        // generate new maps until one is created which has a suitable room count
        while (true)
        {
            GlobalLogger.log(LoggerStringValues.HELL_GENERATION_START);

            Room startingRoom = new EmptyRoom(new Coordinate(hell.getWidth()/2, hell.getHeight()/2));
            try
            {
                HellController.addRoomToGrid(hell, startingRoom);
            }
            catch (SpotAlreadyOccupiedException ex)
            {
                GlobalLogger.warning(ex.getMessage());
            }
            hell.setStartingRoom(startingRoom);

            GlobalLogger.log(LoggerStringValues.START_ROOM_CREATED);

            // The start room will have four passages
            // Generating the neighbors of the starting room:
            RoomController.addTopRoom(hell, startingRoom);
            RoomController.addBottomRoom(hell, startingRoom);
            RoomController.addRightRoom(hell, startingRoom);
            RoomController.addLeftRoom(hell, startingRoom);
            GlobalLogger.log(LoggerStringValues.START_ROOM_NEIGHBORS);


            try
            {
                initNeighbors(hell, startingRoom.getCoordinate());
            }
            catch (NotValidRngRollException ex)
            {
                GlobalLogger.warning(ex.getMessage());
            }
            // regenerate if the hell doesn't have the defined room count
            if (HellController.getRoomList(hell).size() < WorldConstants.MINIMUM_ROOM_COUNT ||
                    HellController.getRoomList(hell).size() > WorldConstants.MAXIMUM_ROOM_COUNT)
            {
                hell.getRoomHashMap().clear();
                GlobalLogger.log(LoggerStringValues.ROOM_COUNT_OUT_OF_RANGE);
            }
            else
            {
                GlobalLogger.log(LoggerStringValues.SUITABLE_HELL);
                break;
            }
        }
        randomizeRoomTypes(hell);
        initHellComponentHashMap(hell);
    }

    /**
     * Recursive Method which generates the Hell by generating the neighbors for each individual room.
     *
     * @param coordinate Coordinate of the room for which the neighbor rooms shall be created
     * @throws NotValidRngRollException thrown if the pseudo-randomly generated number lies outside the defined value range
     */
    private static void initNeighbors (Hell hell, Coordinate coordinate) throws NotValidRngRollException
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
                        HellController.rightSideIsAvailable(hell, coordinate))
                {
                    RoomController.addRightRoom(hell, currentRoom);
                }
                else if (RoomController.hasRightRoom(currentRoom) && HellController.topSideIsAvailable(hell, coordinate))
                {
                    RoomController.addTopRoom(hell, currentRoom);
                }
                else if (RoomController.hasLeftRoom(currentRoom) && HellController.leftSideIsAvailable(hell, coordinate))
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
                        RoomController.hasRightRoom(currentRoom)) && HellController.leftSideIsAvailable(hell, coordinate))
                {
                    RoomController.addLeftRoom(hell, currentRoom);
                }
                else if (RoomController.hasLeftRoom(currentRoom) && HellController.rightSideIsAvailable(hell, coordinate))
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
                        RoomController.hasLeftRoom(currentRoom)) && HellController.bottomSideIsAvailable(hell, coordinate))
                {
                    RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasBottomRoom(currentRoom) && HellController.topSideIsAvailable(hell, coordinate))
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
                    if (HellController.rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (HellController.bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasBottomRoom(currentRoom))
                {
                    if (HellController.rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (HellController.topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                }
                else if (RoomController.hasRightRoom(currentRoom) || RoomController.hasLeftRoom(currentRoom))
                {
                    if (HellController.topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (HellController.bottomSideIsAvailable(hell, coordinate))
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
                    if (HellController.rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (HellController.leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                }
                else if (RoomController.hasRightRoom(currentRoom))
                {
                    if (HellController.leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                    if (HellController.bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasLeftRoom(currentRoom))
                {
                    if (HellController.rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (HellController.bottomSideIsAvailable(hell, coordinate))
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
                    if (HellController.leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                    if (HellController.bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasBottomRoom(currentRoom) || RoomController.hasRightRoom(currentRoom))
                {
                    if (HellController.topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (HellController.leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                }
                else if (RoomController.hasLeftRoom(currentRoom))
                {
                    if (HellController.topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (HellController.rightSideIsAvailable(hell, coordinate))
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
                    if (HellController.rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (HellController.leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                    if (HellController.bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasBottomRoom(currentRoom))
                {
                    if (HellController.topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (HellController.rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (HellController.leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                }
                else if (RoomController.hasRightRoom(currentRoom))
                {
                    if (HellController.topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (HellController.leftSideIsAvailable(hell, coordinate))
                        RoomController.addLeftRoom(hell, currentRoom);
                    if (HellController.bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                else if (RoomController.hasLeftRoom(currentRoom))
                {
                    if (HellController.topSideIsAvailable(hell, coordinate))
                        RoomController.addTopRoom(hell, currentRoom);
                    if (HellController.rightSideIsAvailable(hell, coordinate))
                        RoomController.addRightRoom(hell, currentRoom);
                    if (HellController.bottomSideIsAvailable(hell, coordinate))
                        RoomController.addBottomRoom(hell, currentRoom);
                }
                break;

            default:
                GlobalLogger.warning(LoggerStringValues.ROOM_RNG_FAILURE);
                throw new NotValidRngRollException();
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

    private enum RoomType { FORGE_ROOM, LAVA_POND_ROOM, RANDOM_EVENT_ROOM, ARENA_ROOM }
    /**
     * Pseudo-randomizes the different types of rooms in this hell
     *
     * @param hell hell which contains the rooms that have to be randomized
     */
    private static void randomizeRoomTypes (Hell hell)
    {
        // add boss room
        addBossRoom(hell);

        try
        {
            // add NPC rooms
            // Forge
            addNpcRoom(hell, RoomType.FORGE_ROOM);
            // Lava Pond
            addNpcRoom(hell, RoomType.LAVA_POND_ROOM);

            // add monster and random event rooms
            int numberOfRndMobRooms = HellController.getRoomList(hell).size()/2;
            int mobRooms = numberOfRndMobRooms * WorldConstants.MONSTER_ROOM_RATIO / WorldConstants.ONE_HUNDRED;
            int randomEventRooms = numberOfRndMobRooms - mobRooms;
            addRndOrMobRoom(hell, mobRooms, RoomType.ARENA_ROOM);
            addRndOrMobRoom(hell, randomEventRooms, RoomType.RANDOM_EVENT_ROOM);
        }
        catch (NotAValidRoomtypeException ex)
        {
            GlobalLogger.warning(ex.getMessage());
        }
    }
    /**
     * Adds a Boss to the room that has the longest distance from the starting room.
     *
     * @param hell hell that should get the new boss room
     */
    private static void addBossRoom (Hell hell)
    {
        List<Room> roomList = HellController.getRoomList(hell);
        Room currRoom = null;

        for (Room room : roomList)
        {
            if (currRoom==null || room.getDistanceFromStart() > currRoom.getDistanceFromStart())
            {
                currRoom = room;
            }
        }
        ArenaRoom bossRoom = new ArenaRoom(null);
        bossRoom.setCoordinate(currRoom.getCoordinate());
        HellController.replaceRoom(hell, currRoom, bossRoom);
        roomList.remove(currRoom);
        roomList.add(bossRoom);
        hell.setBossRoom(bossRoom);

        GlobalLogger.log(LoggerStringValues.BOSS_ROOM_ADDED);
    }
    /**
     * Replaces a pseudo-random room by a new npc room of the given type.
     *
     * @param hell hell that should contain the new room
     * @param roomType type of the room that shall be added
     * @throws NotAValidRoomtypeException thrown if the given RoomType is not a valid NPC room type
     */
    private static void addNpcRoom (Hell hell, RoomType roomType) throws NotAValidRoomtypeException
    {
        List<Room> roomList = HellController.getRoomList(hell);
        int currIndex = -1;
        Random randomizer = new Random();
        Room currRoom = null;
        Room newRoom = null;

        while (true)
        {
            currIndex = randomizer.nextInt(roomList.size() - 1);
            currRoom = roomList.get(currIndex);
            if (currRoom.getDistanceFromStart() > 1 && currRoom instanceof EmptyRoom &&
                    !RoomController.neighborsContainNPC(currRoom))
            {
                switch(roomType)
                {
                    case FORGE_ROOM:
                        newRoom = new ForgeRoom();
                        newRoom.setCoordinate(currRoom.getCoordinate());
                        GlobalLogger.log(LoggerStringValues.FORGE_ROOM_ADDED);
                        break;

                    case LAVA_POND_ROOM:
                        newRoom = new LavaPondRoom();
                        newRoom.setCoordinate(currRoom.getCoordinate());
                        GlobalLogger.log(LoggerStringValues.LAVA_POND_ROOM_ADDED);
                        break;

                    default:
                        GlobalLogger.warning(LoggerStringValues.NO_VALID_ROOMTYPE);
                        throw new NotAValidRoomtypeException();
                }
                roomList.remove(currRoom);
                roomList.add(newRoom);

                break;
            }
        }
    }
    /**
     * Replaces a pseudo-random room by a new monster or random event room
     *
     * @param hell hell that should contain the new room
     * @param count how many of the room should be added
     * @param type type of the new room that should be added
     * @throws NotAValidRoomtypeException thrown if the given RoomType is not a RandomEventRoom or a ArenaRoom
     */
    private static void addRndOrMobRoom (Hell hell, int count, RoomType type) throws NotAValidRoomtypeException
    {
        int currIndex = -1;
        Random randomizer = new Random();
        Room currRoom = null;
        List<Room> roomList = HellController.getRoomList(hell);

        while (count > 0)
        {
            currIndex = randomizer.nextInt(roomList.size() - 1);
            currRoom = roomList.get(currIndex);

            if (currRoom.getDistanceFromStart() > 1 && currRoom instanceof EmptyRoom)
            {
                switch(type)
                {
                    case ARENA_ROOM:
                        ArenaRoom arenaRoom = new ArenaRoom(null);
                        arenaRoom.setCoordinate(currRoom.getCoordinate());
                        HellController.replaceRoom(hell, currRoom, arenaRoom);
                        roomList.remove(currRoom);
                        roomList.add(arenaRoom);
                        GlobalLogger.log(LoggerStringValues.MONSTER_ROOM_ADDED);
                        break;

                    case RANDOM_EVENT_ROOM:
                        RandomEventRoom eventRoom = new RandomEventRoom();
                        eventRoom.setCoordinate(currRoom.getCoordinate());
                        HellController.replaceRoom(hell, currRoom, eventRoom);
                        roomList.remove(currRoom);
                        roomList.add(eventRoom);
                        GlobalLogger.log((LoggerStringValues.RANDOM_EVENT_ROOM_ADDED));
                        break;

                    default:
                        GlobalLogger.warning(LoggerStringValues.NO_VALID_ROOMTYPE);
                        throw new NotAValidRoomtypeException();
                }
                count--;
            }
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
            hell.insertHellComponent(coordCenter, new RoomCenter(entry.getValue()));

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
