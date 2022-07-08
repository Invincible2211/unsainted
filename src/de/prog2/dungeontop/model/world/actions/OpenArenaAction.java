package de.prog2.dungeontop.model.world.actions;

import de.prog2.dungeontop.model.world.rooms.ArenaRoom;
import de.prog2.dungeontop.model.world.rooms.Room;
import de.prog2.dungeontop.view.RoomDialogueViewController;

/**
 * This Action should open an arena dialog
 */
public class OpenArenaAction implements Action
{
    @Override
    public boolean execute(Room room)
    {
        if (!((ArenaRoom) room).isAlive()) return false;

        if(((ArenaRoom) room).isBoss())
        {
            RoomDialogueViewController.getInstance().showBossDialogue(room);
        }
        else
        {
            RoomDialogueViewController.getInstance().showArenaDialogue(room);
        }
        return true;
    }
}
