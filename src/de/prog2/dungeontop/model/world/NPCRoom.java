package de.prog2.dungeontop.model.world;

/**
 * Represents the different peaceful Action-Rooms
 */
public abstract class NPCRoom extends Room
{
    private int freeActions = 1;
    public NPCRoom () {}
    public NPCRoom (int freeActions)
    {
        this.freeActions = freeActions;
    }

    public int getFreeActions () { return this.freeActions; }
    public void setFreeActions (int freeActions) { this.freeActions = freeActions; }
    public void decrementFreeActions () { this.freeActions++; }
    public void increaseFreeActions (int increase) { this.setFreeActions(this.getFreeActions() + increase);}
}