package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.game.Talent;

public class Hero extends Entity
{
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

}
