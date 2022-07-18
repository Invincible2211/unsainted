package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.data.SerializableSimpleIntegerProperty;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.world.Coordinate;

import java.io.Serializable;

public abstract class Entity implements Serializable
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private EntityCard card;
    private String name;
    private SerializableSimpleIntegerProperty hp;
    private int attackDamage = 0;
    private final SerializableSimpleIntegerProperty movement;
    private final int maxMovement;
    private int assetId;
    private final Talent talent;
    private Coordinate position;
    private int defense = 0;
    private int attackRange = 0;


    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public Entity(String name, int hp, int attackDamage, int defense, int attackRange, int movement, Talent talent, int assetId)
    {
        this.name = name;
        this.hp = new SerializableSimpleIntegerProperty(hp);
        this.movement = new SerializableSimpleIntegerProperty(0);
        this.attackDamage = attackDamage;
        this.defense = defense;
        this.attackRange = attackRange;
        this.maxMovement = movement;
        this.talent = talent;
        this.assetId = assetId;

        //EntityController.handleTalent(this, talent);
    }
    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public int getHp()
    {
        return hp.getValue();
    }

    public void setHp(int hp)
    {
        this.hp.setValue(hp);
    }

    public SerializableSimpleIntegerProperty getHpProperty ()
    {
        return hp;
    }

    public void setHpProperty (SerializableSimpleIntegerProperty hpProperty)
    {
        this.hp = hpProperty;
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
        return movement.getValue();
    }

    public void setMovement(int movement)
    {
        this.movement.setValue(movement);
    }
    public SerializableSimpleIntegerProperty getMovementProperty ()
    {
        return this.movement;
    }

    public Coordinate getPosition()
    {
        return position;
    }

    public void setPosition(Coordinate position)
    {
        this.position = position;
    }

    public EntityCard getCard()
    {
        return card;
    }

    public void setCard (EntityCard card)
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

    public Talent getTalent()
    {
        return talent;
    }

    public int getMaxMovement ()
    {
        return maxMovement;
    }

    public int getDefense()
    {
        return defense;
    }

    public void setDefense(int defense)
    {
        this.defense = defense;
    }

    public int getAttackRange()
    {
        return attackRange;
    }

    public void setAttackRange(int attackRange)
    {
        this.attackRange = attackRange;
    }

    public void resetMovement() {
        movement.setValue(maxMovement);
    }
}
