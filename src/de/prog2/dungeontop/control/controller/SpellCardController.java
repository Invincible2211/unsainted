package de.prog2.dungeontop.control.controller;

import com.rits.cloning.Cloner;
import de.prog2.dungeontop.model.game.SpellCard;
import de.prog2.dungeontop.model.spells.Spell;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;

public class SpellCardController
{
    public static boolean tryInstantiate(SpellCard card, Arena arena, Coordinate coordinate)
    {
        Cloner cloner = new Cloner();
        Spell spell = cloner.deepClone(card.getSpell());
        arena.castSpell(spell, coordinate);
        return true;
    }
}