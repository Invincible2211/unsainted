package de.prog2.dungeontop.control.controller;

import com.rits.cloning.Cloner;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;

@Deprecated
public class EntityCardController
{
    /**
     *  Instantiate a new minion stored in the card
     * @param card the card that stores the minion
     * @param arena the arena where the minion is spawned
     * @param coordinate the position of the minion in the arena
     * @return true when the minion is spawned
     */
    public static boolean tryInstantiate(EntityCard card, Arena arena, Coordinate coordinate)
    {
        if(arena.getEntity(coordinate) != null) return false;

        Cloner cloner = new Cloner();
        Entity entity = cloner.deepClone(card.getEntity());

        arena.insertEntity(coordinate, entity);
        return true;
    }
}