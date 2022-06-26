package de.prog2.dungeontop.control.controller;

import com.rits.cloning.Cloner;
import de.prog2.dungeontop.model.game.SpellCard;
import de.prog2.dungeontop.model.spells.Spell;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;

public class SpellCardController
{
    /**
     *  Instantiate a new spell stored in the card
     * @param card the card that stores the spell
     * @param arena the arena where the spell is cast
     * @param coordinate the position of the spell in the arena
     * @return always true
     */
    public static boolean tryInstantiate(SpellCard card, Arena arena, Coordinate coordinate)
    {
        Cloner cloner = new Cloner();
        Spell spell = cloner.deepClone(card.getSpell());
        arena.castSpell(spell, coordinate);
        return true;
    }
}