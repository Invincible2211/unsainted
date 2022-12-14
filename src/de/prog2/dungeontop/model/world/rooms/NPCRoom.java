package de.prog2.dungeontop.model.world.rooms;

import de.prog2.dungeontop.model.data.SerializableSimpleIntegerProperty;
import de.prog2.dungeontop.model.world.actions.Action;
import de.prog2.dungeontop.resources.WorldConstants;

/**
 * Represents the different peaceful Action-Rooms
 */
public abstract class NPCRoom extends Room
{
    private final SerializableSimpleIntegerProperty freeActions = new SerializableSimpleIntegerProperty(WorldConstants.NPC_ROOM_FREE_ACTIONS);
    public NPCRoom (Room room, Action action, int assetId)
    {
        super(room, action, assetId);
    }

    public int getFreeActions () { return this.freeActions.getValue(); }
    public void setFreeActions (int freeActions) { this.freeActions.setValue(freeActions); }
    public void decrementFreeActions () { this.freeActions.setValue(this.freeActions.getValue() - 1); }
    public void increaseFreeActions (int increase) { this.setFreeActions(this.getFreeActions() + increase);}
    public SerializableSimpleIntegerProperty getFreeActionsProperty ()
    {
        return this.freeActions;
    }
}