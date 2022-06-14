package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Player;

public class Hero extends Entity
{
    public Hero(String name, int hp, int attackDamage, int movement, int assetId, Player owner)
    {
        super(name, hp, attackDamage, movement, assetId, owner);
    }
}
