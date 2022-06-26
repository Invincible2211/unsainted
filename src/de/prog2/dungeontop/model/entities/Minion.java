package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public class Minion extends Entity
{

    private Minion(String name, int hp, int attackDamage, int movement, int assetId, Player owner)
    {
        super(name, hp, attackDamage, movement, assetId, owner);
        GlobalLogger.log(LoggerStringValues.MINION_CREATED);
    }
    public Minion(String name, int hp, int attackDamage, int movement, int assetId)
    {
        this(name, hp, attackDamage, movement, assetId, null);
    }
}
