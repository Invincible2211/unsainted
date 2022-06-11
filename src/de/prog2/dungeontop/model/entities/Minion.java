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

    //Will only be implemented when we implement furter battlelogik. Optional fuer die Abgabe
    @Override
    public Arena attackAction (Coordinate position, Arena arena)
    {

        // check active perks,
        // check if mana is full or not -> if so instead of attack it will do spell
        // call takeDamage on each reciever and set arenda to version with damge taken on reciever
        //fill up mana with fixed value
        //
        return null;
    }

    //Will only be implemented when we implement furter battlelogik. Optional fuer die Abgabe
    @Override
    public Arena takeDamage (Coordinate position, Arena arena)
    {
        //check if something happens when it takes damage
        //This can not trigger attacks as to avoid long loops

        //give mana half as much as an attack would give
        //reduce hitpoints
        //return Arena with lost hitpoints and or other effect have taken place
        return null;
    }


}
