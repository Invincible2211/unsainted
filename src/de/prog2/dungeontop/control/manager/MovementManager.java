package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.control.controller.RoomController;
import de.prog2.dungeontop.model.game.MoveDirection;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

/**
 * This is a Singleton that handles the Player-Movement.
 */
public class MovementManager
{
    private static final MovementManager instance = new MovementManager();

    /**
     * Initiates the movement towards the selected direction
     * @return returns if the move was successful
     */
    public boolean moveTowards(MoveDirection direction)
    {
        return switch (direction)
        {
            case UP -> moveUp();
            case DOWN -> moveDown();
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
        };
    }

    /**
     * @return returns if the move was successful
     */
    private boolean moveUp()
    {
        Player player = PlayerManager.getInstance().getPlayer();
        if(RoomController.hasTopRoom(player.getCurrentRoom()))
        {
            player.setCurrentRoom(player.getCurrentRoom().getTopRoom());
            GlobalLogger.log(LoggerStringValues.MOVE_UP_SUCCESS);
            return true;
        }
        GlobalLogger.log(LoggerStringValues.MOVE_UP_FAIL);
        return false;
    }
    /**
     * @return returns if the move was successful
     */
    private boolean moveDown()
    {
        Player player = PlayerManager.getInstance().getPlayer();
        if(RoomController.hasBottomRoom(player.getCurrentRoom()))
        {
            player.setCurrentRoom(player.getCurrentRoom().getBottomRoom());
            GlobalLogger.log(LoggerStringValues.MOVE_DOWN_SUCCESS);
            return true;
        }
        GlobalLogger.log(LoggerStringValues.MOVE_DOWN_FAIL);
        return false;
    }
    /**
     * @return returns if the move was successful
     */
    private boolean moveLeft()
    {
        Player player = PlayerManager.getInstance().getPlayer();
        if(RoomController.hasLeftRoom(player.getCurrentRoom()))
        {
            player.setCurrentRoom(player.getCurrentRoom().getLeftRoom());
            GlobalLogger.log(LoggerStringValues.MOVE_LEFT_SUCCESS);
            return true;
        }
        GlobalLogger.log(LoggerStringValues.MOVE_LEFT_FAIL);
        return false;
    }
    /**
     * @return returns if the move was successful
     */
    private boolean moveRight()
    {
        Player player = PlayerManager.getInstance().getPlayer();
        if(RoomController.hasRightRoom(player.getCurrentRoom()))
        {
            player.setCurrentRoom(player.getCurrentRoom().getRightRoom());
            GlobalLogger.log(LoggerStringValues.MOVE_RIGHT_SUCCESS);
            return true;
        }
        GlobalLogger.log(LoggerStringValues.MOVE_RIGHT_FAIL);
        return false;
    }

    public static MovementManager getInstance()
    {
        return instance;
    }
}
