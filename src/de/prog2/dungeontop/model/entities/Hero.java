package de.prog2.dungeontop.model.entities;

import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;

public class Hero extends Entity
{
    public Hero(String name, int hp, int attackDamage, int movement, int assetId, Player owner)
    {
        super(name, hp, attackDamage, movement, assetId, owner);
    }
}
