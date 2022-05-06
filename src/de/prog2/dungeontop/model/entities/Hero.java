package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.talents.Talent;

public class Hero extends Entity {

    private Talent talent = null;

    public Talent getTalent ()
    {
        return talent;
    }

    public void setTalent (Talent talent)
    {
        this.talent = talent;
    }
}
