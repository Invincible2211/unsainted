package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.talents.Talent;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Entity
{
    private Talent mainTalent = null;
    private final List<Talent> talents = new ArrayList<>();

    public Hero(Card card, int hp, int attackDamage, int movement, int maxMoves, int attackRange, Player owner, Talent mainTalent)
    {
        super(card, hp, attackDamage, movement, maxMoves, attackRange, owner);
        this.mainTalent = mainTalent;
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
    public Talent getMainTalent()
    {
        return mainTalent;
    }

    public void setMainTalent(Talent mainTalent)
    {
        this.mainTalent = mainTalent;
    }

    public List<Talent> getTalents()
    {
        return talents;
    }
}
