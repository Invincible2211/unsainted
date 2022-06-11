package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.spells.Spell;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.List;

public class SpellCard extends Card
{
    private final Spell spell;
    public SpellCard(Spell spell, int maxRank, int price, int rank, int summonCost, List... assets)
    {
        super(maxRank, price, rank, summonCost, assets);
        this.spell = spell;
        GlobalLogger.log(LoggerStringValues.SPELLCARD_CREATED);
    }

    public Spell getSpell()
    {
        return spell;
    }
}
