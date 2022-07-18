package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.BattleManager2;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.entities.Talent;
import de.prog2.dungeontop.model.game.MoveDirection;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.CoordinateUtils;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.ArenaController;

import java.util.ArrayList;

public class EntityController
{
    /**
     * Initiates the movement towards the selected direction
     * @return returns if the move was successful
     */
    @Deprecated
    public static boolean tryMoveTowards(Arena arena, Entity entity, MoveDirection direction)
    {
        Coordinate newCoord = CoordinateUtils.getCoordinateFromMoveDirection(entity.getPosition(), direction);

        if(!isValidMove(arena, entity, direction))
        {
            GlobalLogger.log(LoggerStringValues.ENTITY_MOVE_FAILED);
            return false;
        }

        arena.removeEntity(entity.getPosition());
        arena.insertEntity(newCoord, entity);
        entity.setPosition(newCoord);
        entity.setMovement(entity.getMovement()-1);
        GlobalLogger.log(LoggerStringValues.ENTITY_MOVE_SUCCESSFUL);
        return true;
    }

    /**
     * @param arena current arena
     * @param entity moving entity
     * @param direction the desired direction
     * @return returns if nextPosition doesn't exist or is already occupied
     */
    @Deprecated
    public static boolean isValidMove(Arena arena, Entity entity, MoveDirection direction)
    {
        Coordinate newCoord = CoordinateUtils.getCoordinateFromMoveDirection(entity.getPosition(), direction);

        return arena.getEntity(newCoord) == null && canMove(entity);
    }

    @Deprecated
    public static boolean canMove(Entity entity)
    {
        return entity.getMovement() > 0;
    }

    @Deprecated
    public static MoveDirection[] getValidMoveDirections (Arena arena, Entity entity)
    {
        var results = new ArrayList<MoveDirection>();
        for (MoveDirection direction :
                MoveDirection.values())
        {
            if (isValidMove(arena, entity, direction))
            {
                    results.add(direction);
            }
        }
        MoveDirection[] ar = new MoveDirection[results.size()];
        return results.toArray(ar);
    }

    @Deprecated
    public static Arena attack (Entity attacker, Coordinate position, Arena arena)
    {
        System.out.println("this shall never be visible to you or you use the wrong Battlemanager");
        Entity attacked = arena.getEntity(position);
        attacked.setHp(attacked.getHp() - attacker.getAttackDamage());
        if (attacked.getHp() <= 0)
        {
            if (attacked instanceof Hero)
            {
                //BattleManager.getInstance().endBattle(attacker.getOwner(), attacker.getOwner().getHero().getHp());
                GlobalLogger.log(LoggerStringValues.HERO_DIED_GAME_OVER);
            }
            arena.removeEntity(position);
        }
        //make attacker unable to attack again
        return arena;
    }

    /**
     * @return returns, if entity is still alive
     */
    public static boolean applyDamage(Entity entity, int damage)
    {
        entity.setHp(entity.getHp() - (Math.max(damage - entity.getDefense(), 0)));
        if (entity.getHp() <= 0)
        {
            if (entity instanceof Hero)
            {
                BattleManager2.getInstance().endBattle(!GameManager.getInstance().isDM(),true);
                GlobalLogger.log(LoggerStringValues.HERO_DIED_GAME_OVER);
            }
            BattleManager2.getInstance().getArenaController().remove(BattleManager2.getInstance().getCoordinateFromEntity(entity));

        }
        return entity.getHp() > 0;
    }
    public static void applyHeal(Entity entity, int heal)
    {
        entity.setHp(entity.getHp() + heal);
    }

    public static void resetMovement(Entity entity)
    {
        entity.setMovement(entity.getMaxMovement());
    }

    public static void handleTalent(Entity entity, Talent talent)
    {
        switch (talent)
        {
            case SPEEDKNOT -> resetMovement(entity);
            // TODO: implement the other talents
        }
    }
}
