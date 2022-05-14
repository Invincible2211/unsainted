package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.control.controller.HellController;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

/**
 * Represents all the different Rooms.
 */
public class Room
{
    private Room topRoom, bottomRoom, leftRoom, rightRoom;
    private Coordinate coordinate;
    private boolean processed = false;

    private Action action = new DefaultAction();

    public Room(){}
    public Room (Coordinate coordinate) { this.coordinate = coordinate; }
    public Room(Action action)
    {
        this.action = action;
    }

    public <T> boolean executeAction(T... args)
    {
        return this.action.execute(args);
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public Room getTopRoom()
    {
        return topRoom;
    }

    public Room getBottomRoom()
    {
        return bottomRoom;
    }

    public Room getLeft()
    {
        return leftRoom;
    }

    public Room getRightRoom()
    {
        return rightRoom;
    }

    public Coordinate getCoordinate () { return this.coordinate; }

    public boolean getProcessed () {return this.processed; }

    public void setTopRoom(Room topRoom)
    {
        this.topRoom = topRoom;
    }

    public void setBottomRoom(Room bottomRoom)
    {
        this.bottomRoom = bottomRoom;
    }

    public void setLeftRoom(Room left)
    {
        this.leftRoom = left;
    }

    public void setRightRoom(Room rightRoom)
    {
        this.rightRoom = rightRoom;
    }

    public void setCoordinate (Coordinate coordinate) { this.coordinate = coordinate; }

    public void setProcessed () { this.processed = true; }

    /*-------------------------------ADDING TOP, BOTTOM, LEFT, RIGHT ROOMS--------------------------------------------*/

    /**
     * Method to add a new room on the top side.
     *
     * @param hell hell in which the current room is contained
     */
    public void addTopRoom (Hell hell)
    {
        Room top = new Room (new Coordinate( this.getCoordinate().getX(), this.getCoordinate().getY() + 1 ));
        top.setBottomRoom(this);
        this.setTopRoom(top);
        HellController.addRoomToGrid(hell, top);
        GlobalLogger.log(LoggerStringValues.ADDED_TOP_ROOM);
    }

    /**
     * Method to add a new room on the bottom side.
     *
     * @param hell hell in which the current room is contained
     */
    public void addBottomRoom(Hell hell)
    {
        Room bot = new Room (new Coordinate( this.getCoordinate().getX(), this.getCoordinate().getY() - 1 ));
        bot.setTopRoom(this);
        this.setBottomRoom(bot);
        HellController.addRoomToGrid(hell, bot);
        GlobalLogger.log(LoggerStringValues.ADDED_BOTTOM_ROOM);
    }

    /**
     * Method to add a new room on the left side.
     *
     * @param hell hell in which the current room is contained
     */
    public void addLeftRoom (Hell hell)
    {
        Room left = new Room (new Coordinate( this.getCoordinate().getX() - 1, this.getCoordinate().getY() ));
        left.setRightRoom(this);
        this.setLeftRoom(left);
        HellController.addRoomToGrid(hell, left);
        GlobalLogger.log(LoggerStringValues.ADDED_LEFT_ROOM);
    }

    /**
     * Method to add a new room on the right side.
     *
     * @param hell hell in which the current room is contained
     */
    public void addRightRoom (Hell hell)
    {
        Room right = new Room (new Coordinate( this.getCoordinate().getX() + 1, this.getCoordinate().getY() ));
        right.setLeftRoom(this);
        this.setRightRoom(right);
        HellController.addRoomToGrid(hell, right);
        GlobalLogger.log(LoggerStringValues.ADDED_RIGHT_ROOM);
    }

    /*----------------------QUERYING IF THE ROOM HAS A TOP, BOTTOM, LEFT, RIGHT ROOM----------------------------------*/

    /**
     * Method to query if a room has a top side neighbor room.
     *
     * @return true if there is a top side neighbor, false otherwise
     */
    public boolean hasTopRoom ()
    {
        GlobalLogger.log(LoggerStringValues.HAS_TOP_ROOM);
        return this.topRoom != null;
    }

    /**
     * Method to query if a room has a bottom side neighbor room.
     *
     * @return true if there is a bottom side neighbor, false otherwise
     */
    public boolean hasBottomRoom ()
    {
        GlobalLogger.log(LoggerStringValues.HAS_BOTTOM_ROOM);
        return this.bottomRoom != null;
    }

    /**
     * Method to query if a room has a left side neighbor room.
     *
     * @return true if there is a left side neighbor, false otherwise
     */
    public boolean hasLeftRoom ()
    {
        GlobalLogger.log(LoggerStringValues.HAS_LEFT_ROOM);
        return this.leftRoom != null;
    }

    /**
     * Method to query if a room has a right side neighbor room.
     *
     * @return true if there is a right side neighbor, false otherwise
     */
    public boolean hasRightRoom ()
    {
        GlobalLogger.log(LoggerStringValues.HAS_RIGHT_ROOM);
        return this.rightRoom != null;
    }

}
