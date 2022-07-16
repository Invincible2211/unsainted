package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.game.Talent;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public class Minion extends Entity
{
    private Minion(String name, int hp, int attackDamage, int defense, int attackRange, int movement, Talent talent, int assetId, Player owner)
    {
        super(name, hp, attackDamage, defense, attackRange, movement, talent, assetId, owner);
        GlobalLogger.log(LoggerStringValues.MINION_CREATED);
    }
    public Minion(String name, int hp, int attackDamage, int defense, int attackRange, int movement, int assetId)
    {
        this(name, hp, attackDamage, defense, attackRange, movement, null, assetId, null);
    }
}
