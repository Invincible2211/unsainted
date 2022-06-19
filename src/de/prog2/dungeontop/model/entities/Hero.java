package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Player;

public class Hero extends Entity
{
    private String talent;

    public Hero(String name, int hp, int attackDamage, int movement, int assetId, Player owner)
    {
        super(name, hp, attackDamage, movement, assetId, owner);
    }

    public Hero(String name, int hp, int attackDamage, String talent)
    {
        super(name, hp, attackDamage, talent);
    }

}
