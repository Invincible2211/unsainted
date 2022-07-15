package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.game.Talent;
import de.prog2.dungeontop.model.items.Artifact;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.Weapon;
import de.prog2.dungeontop.model.items.artifacts.DefenseArtifact;
import de.prog2.dungeontop.utils.GlobalLogger;

import javax.swing.text.TabableView;
import java.util.LinkedList;
import java.util.List;

public class Hero extends Entity
{
    private Weapon weapon;
    private final List<Artifact> artifacts = new LinkedList<>();
    private int artifactSlots;
    private final Talent talent;

    public Hero(String name, int hp, int attackDamage, int defense, int movement, int artifactSlots, Talent talent, int assetId, Player owner)
    {
        super(name, hp, attackDamage, defense, movement, assetId, owner);
        this.artifactSlots = artifactSlots;
        this.talent = talent;
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
        else
        {
            int def = 0;
            for (Artifact item: artifacts)
            {
                DefenseArtifact item1 = null;
                try
                {
                    item1 = (DefenseArtifact) item;
                    def = def + item1.getDefBonus();
                }
                catch (ClassCastException e)
                {
                    GlobalLogger.warning(e.getMessage());
                }
            }
            return getDefense() + def;
        }
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

    @Override
    public Talent getTalent()
    {
        return talent;
    }
}
