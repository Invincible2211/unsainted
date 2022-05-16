package de.prog2.dungeontop.model.effects;

import de.prog2.dungeontop.model.entities.EntityClass;

public abstract class Effect {
    private EntityClass perk;
    private int perkLevel1; //attribute for bonuses at this perk level
    private int perkLevel2;
    private int perkLevel3;
    private int numberMinion; //to check the number of minions on the field

    public Effect(EntityClass perk, int numberMinion, int perkLevel1, int perkLevel2, int perkLevel3)
    {
        this.perk = perk;
        this.numberMinion = numberMinion;
        this.perkLevel1 = perkLevel1;
        this.perkLevel2 = perkLevel2;
        this.perkLevel3 = perkLevel3;
    }

    public abstract void effect();

    //Set- and Getters
    public EntityClass getPerk()
    {
        return perk;
    }

    public void setPerk(EntityClass perk)
    {
        this.perk = perk;
    }

    public int getPerkLevel1()
    {
        return perkLevel1;
    }

    public int getNumberMinion()
    {
        return numberMinion;
    }

    public void setNumberMinion(int numberMinion)
    {
        this.numberMinion = numberMinion;
    }

    public void setPerkLevel1(int perkLevel1)
    {
        this.perkLevel1 = perkLevel1;
    }

    public int getPerkLevel2()
    {
        return perkLevel2;
    }

    public void setPerkLevel2(int perkLevel2)
    {
        this.perkLevel2 = perkLevel2;
    }

    public int getPerkLevel3()
    {
        return perkLevel3;
    }

    public void setPerkLevel3(int perkLevel3)
    {
        this.perkLevel3 = perkLevel3;
    }
}
