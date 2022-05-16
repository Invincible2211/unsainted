package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.talents.Talent;

import java.util.LinkedList;

public class Hero extends Entity
{
    private Talent talent = null;
    private LinkedList<Talent> talents;

    public Hero(int hp, int attackDamage, int movement, int maxMoves, Player owner)
    {
        super(hp, attackDamage, movement, maxMoves, owner);
    }

    public void addTalents(Talent talent)
    {
        talents.add(talent);
    }

    public void removeTalents(Talent talent)
    {
        talents.remove(talent);
    }

    //Set- and Getters
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

    public void setTalents(LinkedList<Talent> talents)
    {
        this.talents = talents;
    }
}
