package de.prog2.dungeontop.model.world.actions;

import de.prog2.dungeontop.model.world.rooms.RandomEventRoom;
import de.prog2.dungeontop.model.world.rooms.Room;
import de.prog2.dungeontop.view.RoomDialogueViewController;

public class RandomEventAction implements Action
{
    @Override
    public boolean execute(Room room)
    {
        if (!((RandomEventRoom) room).isEventFinished())
            RoomDialogueViewController.getInstance().showRandomEventDialogue(room);
        return false;
    }
}