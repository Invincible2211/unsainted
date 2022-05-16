package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.perks.Perk;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.skills.ActiveSkill;

import java.util.ArrayList;
import java.util.List;

public class Minion extends Entity
{
    private String name = "";

    private final List<Perk> perks = new ArrayList<>();

    private int possibleAttacksPerRound = 0;

    private ActiveSkill activeSkill;

    private Minion (Card card, int hp, int attackDamage, int movement, int maxMoves, Player owner, String name,
                   int possibleAttacksPerRound, ActiveSkill activeSkill)
    {
        super(card, hp, attackDamage, movement, maxMoves, owner);
        this.name = name;
        this.possibleAttacksPerRound = possibleAttacksPerRound;
        this.activeSkill = activeSkill;
    }

    public Minion (Card card, int hp, int attackDamage, int movement, int maxMoves, Player owner, String name,
                  int possibleAttacksPerRound, ActiveSkill activeSkill, Perk perk1, Perk perk2)
    {
        this(card, hp, attackDamage, movement, maxMoves, owner, name, possibleAttacksPerRound, activeSkill);

        this.addPerk(perk1);
        this.addPerk(perk2);
    }
    public Minion (Card card, int hp, int attackDamage, int movement, int maxMoves, Player owner, String name,
                   int possibleAttacksPerRound, ActiveSkill activeSkill, Perk perk1, Perk perk2, Perk perk3)
    {
        this(card, hp, attackDamage, movement, maxMoves, owner, name, possibleAttacksPerRound, activeSkill,
                perk1, perk2);
        this.addPerk(perk3);
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

    public ActiveSkill getActiveSkill ()
    {
        return activeSkill;
    }

    public void setActiveSkill (ActiveSkill activeSkill)
    {
        this.activeSkill = activeSkill;
    }

}
