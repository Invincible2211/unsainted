package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.game.Talent;
import de.prog2.dungeontop.model.items.Artifact;
import de.prog2.dungeontop.model.items.Weapon;

import java.util.LinkedList;
import java.util.List;

public class Hero extends Entity
{
    private Weapon weapon;
    private final List<Artifact> artifacts = new LinkedList<>();
    private int artifactSlots;

    public Hero(String name, int hp, int attackDamage, int movement, int artifactSlots, int assetId, Player owner)
    {
        super(name, hp, attackDamage, movement, assetId, owner);
        this.artifactSlots = artifactSlots;
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

}
