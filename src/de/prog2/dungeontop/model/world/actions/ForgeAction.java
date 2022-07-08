package de.prog2.dungeontop.model.world.actions;

import de.prog2.dungeontop.model.world.rooms.Room;
import de.prog2.dungeontop.view.RoomDialogueViewController;

public class ForgeAction implements Action
{
    @Override
    public boolean execute(Room room)
    {
        RoomDialogueViewController.getInstance().showForgeDialogue();
        return false;
    }
}
