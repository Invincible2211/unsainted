package de.prog2.dungeontop.model.world;

public abstract class Room
{
    private Room topRoom, bottomRoom, leftRoom, rightRoom;
    
    private Action action = new DefaultAction();

    public Room(){}
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

    public void setTopRoom(Room topRoom)
    {
        this.topRoom = topRoom;
    }

    public void setBottomRoom(Room bottomRoom)
    {
        this.bottomRoom = bottomRoom;
    }

    public void setLeftRoom(Room leftRoom)
    {
        this.leftRoom = leftRoom;
    }

    public void setRightRoom(Room rightRoom)
    {
        this.rightRoom = rightRoom;
    }
}
