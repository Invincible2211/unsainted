package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.talents.Talent;

import java.util.LinkedList;

public class Hero extends Entity {
    private Talent talent = null;
    private LinkedList<Talent> talents;

    public Talent getTalent ()
    {
        return talent;
    }

    public void setTalent (Talent talent)
    {
        this.talent = talent;
    }

    public LinkedList<Talent> getTalents()
    {
        return talents;
    }

    public void addTalents(Talent neu)
    {
        talents.add(neu);
    }

    public void removeTalents(Talent delete)
    {
        talents.remove(delete);
    }
}
