package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.world.actions.Action;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.actions.DefaultAction;

import java.io.Serializable;

/**
 * Represents all the different Rooms.
 */
public abstract class Room implements Serializable
{
    private Room topRoom, bottomRoom, leftRoom, rightRoom;
    private Coordinate coordinate;
    private boolean processed = false;
    private int distanceFromStart = 0;
    private final int assetId;

    private Action action = new DefaultAction();

    public Room (Coordinate coordinate, int distanceFromStart, Action action, int assetId)
    {
        this.coordinate = coordinate;
        this.distanceFromStart = distanceFromStart;
        this.action = action;
        this.assetId = assetId;
    }
    public Room (Room room, Action action, int assetId)
    {
        this(room.getCoordinate(), room.getDistanceFromStart(), action, assetId);
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

    public int getAssetId()
    {
        return assetId;
    }
}
