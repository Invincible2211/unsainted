package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.perks.Perk;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.skills.ActiveSkill;
import de.prog2.dungeontop.model.skills.ManaPool;
import de.prog2.dungeontop.model.skills.Skill;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.ArrayList;
import java.util.List;

public class Minion extends Entity
{
    private String name;
    @Deprecated
    private final List<Perk> perks = new ArrayList<>();
    @Deprecated
    private int possibleAttacksPerRound = 0;
    @Deprecated
    private ManaPool manaPool;
    @Deprecated
    private final List<Skill> skills = new ArrayList<>();

    private Minion (Card card, int hp, int attackDamage, int movement, int maxMoves, int attackRange, Player owner,
                   int possibleAttacksPerRound, ActiveSkill activeSkill, ManaPool manaPool)
    {
        super(card, hp, attackDamage, movement, maxMoves, attackRange, owner);

        this.possibleAttacksPerRound = possibleAttacksPerRound;
        skills.add(activeSkill);
        this.manaPool = manaPool;
        GlobalLogger.log(LoggerStringValues.MINION_CREATED);
    }

    public Minion (Card card, int hp, int attackDamage, int movement, int maxMoves, int attackRange, Player owner,
                  int possibleAttacksPerRound, ActiveSkill activeSkill, ManaPool manaPool, Perk perk1, Perk perk2)
    {
        this(card, hp, attackDamage, movement, maxMoves, attackRange, owner, possibleAttacksPerRound, activeSkill, manaPool);

        this.addPerk(perk1);
        this.addPerk(perk2);
    }
    public Minion (Card card, int hp, int attackDamage, int movement, int maxMoves, int attackRange, Player owner, String name,
                   int possibleAttacksPerRound, ActiveSkill activeSkill, ManaPool manaPool,
                   Perk perk1, Perk perk2, Perk perk3)
    {
        this(card, hp, attackDamage, movement, maxMoves, attackRange, owner, possibleAttacksPerRound, activeSkill, manaPool,
                perk1, perk2);
        this.addPerk(perk3);
    }

    public Minion (Card card, Player owner, int rank, Coordinate coordinate)
    {
        super(card, owner, rank, coordinate);
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

    //Will only be implemented when we implement furter battlelogik. Optional fuer die Abgabe
    @Override
    public Arena attackAction (Coordinate position, Arena arena)
    {

        // check active perks,
        // check if mana is full or not -> if so instead of attack it will do spell
        // call takeDamage on each reciever and set arenda to version with damge taken on reciever
        //fill up mana with fixed value
        //
        return null;
    }

    //Will only be implemented when we implement furter battlelogik. Optional fuer die Abgabe
    @Override
    public Arena takeDamage (Coordinate position, Arena arena)
    {
        //check if something happens when it takes damage
        //This can not trigger attacks as to avoid long loops

        //give mana half as much as an attack would give
        //reduce hitpoints
        //return Arena with lost hitpoints and or other effect have taken place
        return null;
    }


}
