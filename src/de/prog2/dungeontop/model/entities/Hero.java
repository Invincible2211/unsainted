package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.game.Talent;
import de.prog2.dungeontop.model.items.Weapon;

public class Hero extends Entity
{
    private Weapon weapon;
    private Talent talent;

    public Hero(String name, int hp, int attackDamage, int movement, Talent talent, int assetId, Player owner)
    {
        super(name, hp, attackDamage, movement, assetId, owner);
        this.talent = talent;
    }

    public Hero(String name, int hp, int attackDamage, int maxMovement, Talent talent)
    {
        super(name, hp, attackDamage, maxMovement, talent);
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

    public void equipWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }
    public void unequipWeapon()
    {
        this.weapon = null;
    }
}
