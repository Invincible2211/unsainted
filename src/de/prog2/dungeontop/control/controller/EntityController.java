package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.MoveDirection;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.model.world.arena.ArenaComponent;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public class EntityController
{
    /**
     * Initiates the movement towards the selected direction
     * @return returns if the move was successful
     */
    public static boolean moveTowards(Arena arena, Entity entity, MoveDirection direction)
    {
        ArenaComponent currentAC = arena.getArenaComponent(entity.getPosition());

        Coordinate newCoord = switch (direction)
        {
            case UP -> new Coordinate(entity.getPosition().getX(),entity.getPosition().getY() + 1);
            case DOWN -> new Coordinate(entity.getPosition().getX(),entity.getPosition().getY() - 1);
            case LEFT -> new Coordinate(entity.getPosition().getX() - 1,entity.getPosition().getY());
            case RIGHT -> new Coordinate(entity.getPosition().getX() + 1,entity.getPosition().getY());
        };
        ArenaComponent nextAC = arena.getArenaComponent(newCoord);

        if(nextAC == null || nextAC.isOccupied()) // if nextPosition doesn't exist or is already occupied
        {
            GlobalLogger.log(LoggerStringValues.MOVE_UP_FAIL);
            return false;
        }

        currentAC.deleteOccupant();
        nextAC.setOccupant(entity);
        entity.setPosition(newCoord);
        GlobalLogger.log(LoggerStringValues.MOVE_UP_SUCCESS);
        return true;
    }
}
