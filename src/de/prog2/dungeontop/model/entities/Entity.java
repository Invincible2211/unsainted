package de.prog2.dungeontop.model.entities;

public abstract class Entity
{
    /*---------------------------------------------ATTRIBUTES---------------------------------------------------------*/
    private int hp = 0;
    private int attackDamage = 0;
    private int movement = 0;

    /*--------------------------------------------CONSTRUCTORS--------------------------------------------------------*/
    public Entity(int hp, int attackDamage, int movement)
    {
        this.hp = hp;
        this.attackDamage = attackDamage;
        this.movement = movement;
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
}
