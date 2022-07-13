package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.data.SerializableSimpleIntegerProperty;
import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.world.rooms.Room;
import de.prog2.dungeontop.resources.DeckConstants;
import de.prog2.dungeontop.resources.GameConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

import de.prog2.dungeontop.resources.LoggerStringValues;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable
{
    private final SerializableSimpleIntegerProperty soulsProperty;
    private final SerializableSimpleIntegerProperty hpProperty;
    private int max_ego_points = GameConstants.MAX_EGO_POINTS;
    private Deck deck;
    private final Inventory inventory = new Inventory();
    private Room currentRoom;
    private int handCardLimit;
    private Hero hero;
    private List<Item> weaponSlot = new ArrayList<>();
    private List<Item> artifactSlot1 = new ArrayList<>();
    private List<Item> artifactSlot2 = new ArrayList<>();
    private int soulArtBonus = 0;
    private int defArtBonus = 0;

    public Player (){
        this.soulsProperty = new SerializableSimpleIntegerProperty(0);
        this.hpProperty = new SerializableSimpleIntegerProperty(GameConstants.DEFAULT_PLAYER_MAX_HP);
        GlobalLogger.log(LoggerStringValues.PLAYER_CREATED);
        this.deck = DeckConstants.GET_RANDOM_DECK();
    }
    public Player (int souls, int healthPoints){
        this.hpProperty = new SerializableSimpleIntegerProperty(healthPoints);
        this.soulsProperty = new SerializableSimpleIntegerProperty(souls);
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

    public int getMax_ego_points ()
    {
        return max_ego_points;
    }
    public void setMax_ego_points (int max_ego_points)
    {
        this.max_ego_points = max_ego_points;
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

    public int getHandCardLimit ()
    {
        return handCardLimit;
    }

    public void setHandCardLimit (int handCardLimit)
    {
        this.handCardLimit = handCardLimit;
    }

    public Hero getHero()
    {
        return hero;
    }

    public void setHero(Hero hero)
    {
        this.hero = hero;
    }

    public List<Item> getWeaponSlot()
    {
        return weaponSlot;
    }

    public List<Item> getArtifactSlot1()
    {
        return artifactSlot1;
    }

    public List<Item> getArtifactSlot2()
    {
        return artifactSlot2;
    }

    public int getSoulArtBonus()
    {
        return soulArtBonus;
    }

    public void setSoulArtBonus(int soulArtBonus)
    {
        this.soulArtBonus = soulArtBonus;
    }

    public int getDefArtBonus()
    {
        return defArtBonus;
    }

    public void setDefArtBonus(int defArtBonus)
    {
        this.defArtBonus = defArtBonus;
    }
}
