package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.control.controller.DeckController;
import de.prog2.dungeontop.model.data.SerializableSimpleIntegerProperty;
import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.world.rooms.Room;
import de.prog2.dungeontop.resources.GameConstants;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

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
    private Deck discardPile = new Deck();
    private final SerializableSimpleIntegerProperty currentEgoPoints = new SerializableSimpleIntegerProperty(0);
    private transient ObservableList<Card> handCards = FXCollections.observableArrayList();
    private int experiencePoints;
    private int level;
    private final int expCap = GameConstants.LEVEL_1_EXP_CAP * level;
    private int soulArtBonus = 0;
    private int startEgoPointsMax = GameConstants.START_EGOPOINTS;

    public Player (){
        this.soulsProperty = new SerializableSimpleIntegerProperty(0);
        this.hpProperty = new SerializableSimpleIntegerProperty(GameConstants.DEFAULT_PLAYER_MAX_HP);
        GlobalLogger.log(LoggerStringValues.PLAYER_CREATED);
        this.deck = DeckController.getRandomDeck(GameConstants.PLAYER_DECK_USE_ALL_CARDS);
    }
    public Player (int souls, int healthPoints){
        this.hpProperty = new SerializableSimpleIntegerProperty(healthPoints);
        this.soulsProperty = new SerializableSimpleIntegerProperty(souls);
        GlobalLogger.log(LoggerStringValues.PLAYER_CREATED);
    }

    public void levelUp()
    {
        setExperiencePoints(getExperiencePoints() - getExpCap());
        setLevel(getLevel() + 1);
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

    public Deck getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(Deck discardPile) {
        this.discardPile = discardPile;
    }

    public SerializableSimpleIntegerProperty currentEgoPointsProperty() {
        return currentEgoPoints;
    }

    public ObservableList<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(ObservableList<Card> handCards) {
        this.handCards = handCards;
    }

    public int getExperiencePoints()
    {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints)
    {
        this.experiencePoints = experiencePoints;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getExpCap()
    {
        return expCap;
    }

    public int getSoulArtBonus()
    {
        return soulArtBonus;
    }

    public void setSoulArtBonus(int soulArtBonus)
    {
        this.soulArtBonus = soulArtBonus;
    }

    public int getStartEgoPointsMax ()
    {
        return startEgoPointsMax;
    }

    public void setStartEgoPointsMax (int startEgoPointsMax)
    {
        this.startEgoPointsMax = startEgoPointsMax;
    }
}
