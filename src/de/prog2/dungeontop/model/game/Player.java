package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.world.rooms.Room;
import de.prog2.dungeontop.resources.GameConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

import de.prog2.dungeontop.resources.LoggerStringValues;
import javafx.beans.property.SimpleIntegerProperty;

public class Player
{
    private final SimpleIntegerProperty soulsProperty;
    private final SimpleIntegerProperty hpProperty;
    private int ego_points;
    private Deck deck;
    private final Inventory inventory = new Inventory();
    private Room currentRoom;
    private int handCardLimit;

    public Player (){
        this.soulsProperty = new SimpleIntegerProperty(0);
        this.hpProperty = new SimpleIntegerProperty(GameConstants.DEFAULT_PLAYER_MAX_HP);
        GlobalLogger.log(LoggerStringValues.PLAYER_CREATED);
    }
    public Player (int souls, int healthPoints){
        this.hpProperty = new SimpleIntegerProperty(healthPoints);
        this.soulsProperty = new SimpleIntegerProperty(souls);
        GlobalLogger.log(LoggerStringValues.PLAYER_CREATED);
    }




    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public int getSouls(){
        GlobalLogger.log(LoggerStringValues.PLAYERSOULS_GET);
        return this.soulsProperty.get();
    }
    public void setSouls(int souls){
        GlobalLogger.log(LoggerStringValues.PLAYERSOULS_SET + souls + LoggerStringValues.SET);
        this.soulsProperty.set(souls);
    }

    public SimpleIntegerProperty getSoulsProperty() {
        GlobalLogger.log(LoggerStringValues.PLAYERSOULS_PROPERTY_GET);
        return this.soulsProperty;
    }

    public int getHp ()
    {
        GlobalLogger.log(LoggerStringValues.PLAYERHP_GET);
        return this.hpProperty.get();
    }

    public void setHp(int healthPoints)
    {
        GlobalLogger.log(LoggerStringValues.PLAYERHP_SET + healthPoints + LoggerStringValues.SET);
        this.hpProperty.set(healthPoints);
    }

    public SimpleIntegerProperty getHpProperty ()
    {
        GlobalLogger.log(LoggerStringValues.PLAYERHP_PROPERTY_GET);
        return this.hpProperty;
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

    /*
    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }
     */

    public int getHandCardLimit ()
    {
        return handCardLimit;
    }

    public void setHandCardLimit (int handCardLimit)
    {
        this.handCardLimit = handCardLimit;
    }
}
