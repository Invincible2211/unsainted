package de.prog2.dungeontop.model.world.rooms;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents the different peaceful Action-Rooms
 */
public abstract class NPCRoom extends Room implements Serializable
{
    private final SimpleIntegerProperty freeActions = new SimpleIntegerProperty(1);
    public NPCRoom () {}
    public NPCRoom (int freeActions)
    {
        this.freeActions.setValue(freeActions);
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