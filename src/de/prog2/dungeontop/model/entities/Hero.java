package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.items.Artifact;
import de.prog2.dungeontop.model.items.Weapon;
import de.prog2.dungeontop.model.items.artifacts.DefenseArtifact;

import java.util.LinkedList;
import java.util.List;

public class Hero extends Entity
{
    private Weapon weapon;
    private final List<Artifact> artifacts = new LinkedList<>();
    private int artifactSlots;
    private int handCardLimit;

    public Hero(String name, int hp, int attackDamage, int defense, int attackRange, int movement, int artifactSlots, int handCardLimit, Talent talent, int assetId)
    {
        super(name, hp, attackDamage, defense, attackRange, movement, talent, assetId);
        this.artifactSlots = artifactSlots;
        this.handCardLimit = handCardLimit;
    }

    @Override
    public int getAttackDamage()
    {
        if (weapon == null)
        {
            return super.getAttackDamage();
        }
        return super.getAttackDamage() + weapon.getAttackDamage();
    }

    @Override
    public int getDefense()
    {
        if (artifacts.isEmpty())
        {
            return super.getDefense();
        }

        int def = 0;
        for (Artifact artifact: artifacts)
        {
            if(artifact instanceof DefenseArtifact defenseArtifact)
            {
                def += defenseArtifact.getDefense();
            }
        }
        return super.getDefense() + def;
    }

    @Override
    public int getAttackRange()
    {
        if (weapon == null)
        {
            return super.getAttackRange();
        }
        return super.getAttackRange() + weapon.getAttackRange();
    }

    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }
    public Weapon getWeapon()
    {
        return weapon;
    }

    public void addArtifact(Artifact artifact)
    {
        artifacts.add(artifact);
    }
    public void removeArtifact(Artifact artifact)
    {
        artifacts.remove(artifact);
    }
    public boolean hasArtifact(Artifact artifact)
    {
        return artifacts.contains(artifact);
    }
    public List<Artifact> getArtifacts()
    {
        return artifacts;
    }
    public int getArtifactSize()
    {
        return artifacts.size();
    }

    public int getArtifactSlots()
    {
        return artifactSlots;
    }

    public void setArtifactSlots(int artifactSlots)
    {
        this.artifactSlots = artifactSlots;
    }

    public int getHandCardLimit()
    {
        return handCardLimit;
    }

    public void setHandCardLimit(int handCardLimit)
    {
        this.handCardLimit = handCardLimit;
    }
}
