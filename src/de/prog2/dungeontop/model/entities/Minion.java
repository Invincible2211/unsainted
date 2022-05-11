package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.skills.ActiveSkill;

import java.util.ArrayList;
import java.util.UUID;

public class Minion extends Entity {

    private int attackValue = 0;

    private int hitpoints = 0;

    private String name = "";

    private ArrayList<EntityClass> perks = null;

    private int possibleAttacksPerRound = 0;

    private ActiveSkill activeSkill= null;

    private UUID uuid = null;

    public Minion ()
    {

    }

    /**
     * Constructor for creating new instance of Minion
     * @param attackValue
     * @param hitpoints
     * @param name
     * @param perks
     * @param possibleAttacksPerRound
     * @param activeSkill
     */
    public Minion (int attackValue, int hitpoints, String name,
                   ArrayList<EntityClass> perks, int possibleAttacksPerRound,
                   ActiveSkill activeSkill)
    {
        this.attackValue = attackValue;
        this.hitpoints = hitpoints;
        this.name = name;
        this.perks = perks;
        this.possibleAttacksPerRound = possibleAttacksPerRound;
        this.activeSkill = activeSkill;
        this.uuid = UUID.randomUUID();
    }

    /**
     * Constructer for the fileHandler where UUID is already given
     * @param attackValue
     * @param hitpoints
     * @param name
     * @param perks
     * @param possibleAttacksPerRound
     * @param activeSkill
     * @param uuid
     */
    public Minion(int attackValue, int hitpoints, String name, ArrayList<EntityClass> perks, int possibleAttacksPerRound, ActiveSkill activeSkill, UUID uuid) {
        this.attackValue = attackValue;
        this.hitpoints = hitpoints;
        this.name = name;
        this.perks = perks;
        this.possibleAttacksPerRound = possibleAttacksPerRound;
        this.activeSkill = activeSkill;
        this.uuid = uuid;
    }

    /****************************************** Getter and Setter *******************************/
    public int getAttackValue ()
    {
        return attackValue;
    }

    public void setAttackValue (int attackValue)
    {
        this.attackValue = attackValue;
    }

    public int getHitpoints ()
    {
        return hitpoints;
    }

    public void setHitpoints (int hitpoints)
    {
        this.hitpoints = hitpoints;
    }

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

    public void setPerks (ArrayList<EntityClass> perks)
    {
        this.perks = perks;
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

    public UUID getUuid() {
        return uuid;
    }
}
