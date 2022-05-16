package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.skills.ActiveSkill;

import java.util.ArrayList;
import java.util.UUID;

public class Minion extends Entity
{
    private String name = "";

    private final ArrayList<EntityClass> perks = new ArrayList<>();

    private int possibleAttacksPerRound = 0;

    private ActiveSkill activeSkill;

    public Minion (Card card, int hp, int attackDamage, int movement, int maxMoves, Player owner, String name, int possibleAttacksPerRound, ActiveSkill activeSkill)
    {
        super(card, hp, attackDamage, movement, maxMoves, owner);
        this.name = name;
        this.possibleAttacksPerRound = possibleAttacksPerRound;
        this.activeSkill = activeSkill;
    }


    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public String getName ()
    {
        return name;
    }
    public void setName (String name)
    {
        this.name = name;
    }
    public ArrayList<EntityClass> getPerks ()
    {
        return perks;
    }

    public void addPerk (EntityClass perk)
    {
        perks.add(perk);
    }
    public void removePerk (EntityClass perk)
    {
        perks.remove(perk);
    }

    public int getPossibleAttacksPerRound ()
    {
        return possibleAttacksPerRound;
    }

    public void setPossibleAttacksPerRound (int possibleAttacksPerRound)
    {
        this.possibleAttacksPerRound = possibleAttacksPerRound;
    }

    public ActiveSkill getActiveSkill ()
    {
        return activeSkill;
    }

    public void setActiveSkill (ActiveSkill activeSkill)
    {
        this.activeSkill = activeSkill;
    }

}
