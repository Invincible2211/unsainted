package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.control.controller.HellController;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

/**
 * Represents all the different Rooms.
 */
public abstract class Room
{
    private Room topRoom, bottomRoom, leftRoom, rightRoom;
    private Coordinate coordinate;
    private boolean processed = false;
    private int distanceFromStart = 0;

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

    public Room getLeftRoom()
    {
        return leftRoom;
    }

    public Room getRightRoom()
    {
        return rightRoom;
    }

    public Coordinate getCoordinate () { return this.coordinate; }

    public boolean getProcessed () {return this.processed; }

    public int getDistanceFromStart () { return this.distanceFromStart; }

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

    public void setDistanceFromStart (int distance)
    {
        this.distanceFromStart = distance;
    }
}
