package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;

public abstract class Entity
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private Card card;
    private int hp = 0;
    private int attackDamage = 0;
    private int movement = 0;
    private Coordinate position;
    @Deprecated
    private int movesLeftOver = 0, maxMoves = 0;
    @Deprecated
    private int attackRange = 0;
    private Player owner = null;

    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public Entity(Card card, int hp, int attackDamage, int movement, int maxMoves, int attackRange, Player owner)
    {
        this.card = card;
        this.hp = hp;
        this.attackDamage = attackDamage;
        this.movement = movement;
        this.maxMoves = maxMoves;
        this.movesLeftOver = maxMoves;
        this.attackRange = attackRange;
        this.owner = owner;
    }
    //TEST
    public Entity(Card card, Player owner, int rank, Coordinate coordinate)
    {

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

    public int getMaxMoves()
    {
        return maxMoves;
    }

    public void setMaxMoves(int maxMoves)
    {
        this.maxMoves = maxMoves;
    }

    public int getMovesLeftOver()
    {
        return movesLeftOver;
    }
    public void setMovesLeftOver(int movesLeftOver)
    {
        this.movesLeftOver = movesLeftOver;
    }

    public int getAttackRange()
    {
        return attackRange;
    }

    public void setAttackRange(int attackRange)
    {
        this.attackRange = attackRange;
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

    public abstract Arena attackAction (Coordinate position, Arena arena);

    public abstract Arena takeDamage (Coordinate position, Arena arena);

    public void setCard (Card card)
    {
        this.card = card;
    }
}
