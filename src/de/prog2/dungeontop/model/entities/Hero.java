package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.talents.Talent;

import java.util.ArrayList;

public class Hero extends Entity {
    private Talent talent = null;
    private ArrayList<Talent> talents = new ArrayList<>(10);

    public Talent getTalent ()
    {
        return talent;
    }

    public void setTalent (Talent talent)
    {
        this.talent = talent;
    }

    public ArrayList<Talent> getTalents()
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
