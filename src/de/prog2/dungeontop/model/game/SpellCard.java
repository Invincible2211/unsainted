package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.spells.Spell;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public class SpellCard extends Card
{
    private final Spell spell;
    public SpellCard(Spell spell, int maxRank, int price, int rank, int summonCost)
    {
        super(maxRank, price, rank, summonCost);
        this.spell = spell;
        GlobalLogger.log(LoggerStringValues.SPELLCARD_CREATED);
    }

    public Spell getSpell()
    {
        return spell;
    }
}
