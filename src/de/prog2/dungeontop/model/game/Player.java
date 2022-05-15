package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.inventory.Inventory;
import de.prog2.dungeontop.model.world.Room;
import de.prog2.dungeontop.utils.GlobalLogger;

import de.prog2.dungeontop.resources.LoggerStringValues;

public class Player
{
    private int souls;
    private int ego_points;
    private Deck deck;
    private Inventory inventory;
    private Room currentRoom;

    public Player (){
        this.souls = 0;
    }
    public Player (int souls){
        this.souls = souls;
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public int getSouls(){
        GlobalLogger.log(LoggerStringValues.PLAYERSOULS_GET);
        return this.souls;
    }
    public void setSouls(int souls){
        GlobalLogger.log(LoggerStringValues.PLAYERSOULS_SET + souls + LoggerStringValues.SET);
        this.souls = souls;
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    public void setCurrentRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
    }

    public int getEgo_points()
    {
        return ego_points;
    }
    public void setEgo_points(int ego_points)
    {
        this.ego_points = ego_points;
    }

    public Deck getDeck()
    {
        return deck;
    }

    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }
}
