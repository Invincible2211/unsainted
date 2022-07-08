package de.prog2.dungeontop.model.world.rooms;

import java.io.Serializable;

import de.prog2.dungeontop.model.data.SerializableSimpleIntegerProperty;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.actions.Action;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents the different peaceful Action-Rooms
 */
public abstract class NPCRoom extends Room
{
    private final SerializableSimpleIntegerProperty freeActions = new SerializableSimpleIntegerProperty(1);
    public NPCRoom (Room room, Action action, int assetId)
    {
        super(room, action, assetId);
    }

    public int getFreeActions () { return this.freeActions.getValue(); }
    public void setFreeActions (int freeActions) { this.freeActions.setValue(freeActions); }
    public void decrementFreeActions () { this.freeActions.setValue(this.freeActions.getValue() - 1); }
    public void increaseFreeActions (int increase) { this.setFreeActions(this.getFreeActions() + increase);}
    public SimpleIntegerProperty getFreeActionsProperty ()
    {
        return this.freeActions;
    }
}