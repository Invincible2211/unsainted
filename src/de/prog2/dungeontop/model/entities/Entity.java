package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;

import java.io.Serializable;

public abstract class Entity implements Serializable
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private Card card;
    private String name;
    private int hp = 0;
    private int attackDamage = 0;
    private int movement = 0;
    private int maxMovement = 0;
    private int assetId;
    private String talent;
    private Coordinate position;
    private Player owner = null;


    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public Entity(String name, int hp, int attackDamage, int movement, int assetId, Player owner)
    {
        this.name = name;
        this.hp = hp;
        this.attackDamage = attackDamage;
        this.movement = movement;
        this.assetId = assetId;
        this.owner = owner;
    }
    public Entity(String name, int hp, int attackDamage, int movement, int assetId, Coordinate position, boolean canMove, Player owner)
    {
        this(name, hp, attackDamage, movement, assetId, owner);
        this.position = position;
    }
    public Entity(String name, int hp, int attackDamage, int maxMovement, String talent)
    {
        this.name = name;
        this.hp = hp;
        this.attackDamage = attackDamage;
        this.maxMovement = maxMovement;
        this.talent = talent;
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage)
    {
        this.attackDamage = attackDamage;
    }

    public int getMovement()
    {
        return movement;
    }

    public void setMovement(int movement)
    {
        this.movement = movement;
    }

    public Coordinate getPosition()
    {
        return position;
    }

    public void setPosition(Coordinate position)
    {
        this.position = position;
    }

    public Player getOwner()
    {
        return owner;
    }

    public void setOwner(Player owner)
    {
        this.owner = owner;
    }

    public Card getCard()
    {
        return card;
    }

    public void setCard (Card card)
    {
        this.card = card;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAssetId()
    {
        return assetId;
    }

    public void setAssetId(int assetId)
    {
        this.assetId = assetId;
    }

    public String getTalent()
    {
        return talent;
    }

    public int getMaxMovement ()
    {
        return maxMovement;
    }

    public void setMaxMovement (int maxMovement)
    {
        this.maxMovement = maxMovement;
    }
}
