package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public class Minion extends Entity
{
    private final String name;

    private Minion(int hp, int attackDamage, int movement, Player owner, String name)
    {
        super(hp, attackDamage, movement, owner);
        this.name = name;
        GlobalLogger.log(LoggerStringValues.MINION_CREATED);
    }
    public Minion(int hp, int attackDamage, int movement, String name)
    {
        this(hp, attackDamage, movement, null, name);
    }

    @Deprecated
    //Will only be implemented when we implement furter battlelogik. Optional fuer die Abgabe
    public Minion (Card card, Player owner, int rank, Coordinate coordinate, String name)
    {
        super(card, owner, rank, coordinate);
        this.name = name;
    }




    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public String getName ()
    {
        return name;
    }
}
