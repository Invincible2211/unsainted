package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public class Minion extends Entity
{
    public Minion(String name, int hp, int attackDamage, int defense, int attackRange, int movement, int assetId)
    {
        this(name, hp, attackDamage, defense, attackRange, movement, Talent.NONE, assetId);
    }
    public Minion(String name, int hp, int attackDamage, int defense, int attackRange, int movement, Talent talent, int assetId)
    {
        super(name, hp, attackDamage, defense, attackRange, movement, talent, assetId);
        GlobalLogger.log(LoggerStringValues.MINION_CREATED);
    }
}
