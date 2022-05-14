package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.model.game.MoveDirection;
import de.prog2.dungeontop.model.game.Player;

public class MovementManager
{
    //prüfen laufen, movent smooth
    public static final MovementManager instance = new MovementManager();

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

    private boolean moveUp()
    {
        Player player = PlayerManager.getInstance().getPlayer();
        if(player.getCurrentRoom().hasTopRoom())
        {
            player.setCurrentRoom(player.getCurrentRoom().getTopRoom());
            return true;
        }
        return false;
    }
    private boolean moveDown()
    {
        Player player = PlayerManager.getInstance().getPlayer();
        if(player.getCurrentRoom().hasBottomRoom())
        {
            player.setCurrentRoom(player.getCurrentRoom().getBottomRoom());
            return true;
        }
        return false;
    }
    private boolean moveLeft()
    {
        Player player = PlayerManager.getInstance().getPlayer();
        if(player.getCurrentRoom().hasLeftRoom())
        {
            player.setCurrentRoom(player.getCurrentRoom().getLeftRoom());
            return true;
        }
        return false;
    }
    private boolean moveRight()
    {
        Player player = PlayerManager.getInstance().getPlayer();
        if(player.getCurrentRoom().hasRightRoom())
        {
            player.setCurrentRoom(player.getCurrentRoom().getRightRoom());
            return true;
        }
        return false;
    }

    public static MovementManager getInstance()
    {
        return instance;
    }
}
