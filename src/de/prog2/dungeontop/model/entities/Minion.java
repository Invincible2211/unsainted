package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.perks.Perk;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.skills.ActiveSkill;
import de.prog2.dungeontop.model.skills.ManaPool;
import de.prog2.dungeontop.model.skills.Skill;

import java.util.ArrayList;
import java.util.List;

public class Minion extends Entity
{
    private final String name;

    private final List<Perk> perks = new ArrayList<>();

    private int possibleAttacksPerRound = 0;

    private final ManaPool manaPool;
    private final List<Skill> skills = new ArrayList<>();

    private Minion (Card card, int hp, int attackDamage, int movement, int maxMoves, int attackRange, Player owner, String name,
                   int possibleAttacksPerRound, ActiveSkill activeSkill, ManaPool manaPool)
    {
        super(card, hp, attackDamage, movement, maxMoves, attackRange, owner);
        this.name = name;
        this.possibleAttacksPerRound = possibleAttacksPerRound;
        skills.add(activeSkill);
        this.manaPool = manaPool;
    }

    public Minion (Card card, int hp, int attackDamage, int movement, int maxMoves, int attackRange, Player owner, String name,
                  int possibleAttacksPerRound, ActiveSkill activeSkill, ManaPool manaPool, Perk perk1, Perk perk2)
    {
        this(card, hp, attackDamage, movement, maxMoves, attackRange, owner, name, possibleAttacksPerRound, activeSkill, manaPool);

        this.addPerk(perk1);
        this.addPerk(perk2);
    }
    public Minion (Card card, int hp, int attackDamage, int movement, int maxMoves, int attackRange, Player owner, String name,
                   int possibleAttacksPerRound, ActiveSkill activeSkill, ManaPool manaPool,
                   Perk perk1, Perk perk2, Perk perk3)
    {
        this(card, hp, attackDamage, movement, maxMoves, attackRange, owner, name, possibleAttacksPerRound, activeSkill, manaPool,
                perk1, perk2);
        this.addPerk(perk3);
    }


    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public String getName ()
    {
        return name;
    }
    public List<Perk> getPerks ()
    {
        return perks;
    }

    public void addPerk (Perk perk)
    {
        perks.add(perk);
    }
    public void removePerk (Perk perk)
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

    public List<Skill> getSkills ()
    {
        return this.skills;
    }
    public void addSkill(Skill skill)
    {
        this.skills.add(skill);
    }
    public void addSkills(List<Skill> skills)
    {
        this.skills.addAll(skills);
    }
    public ManaPool getManaPool()
    {
        return manaPool;
    }
}
