package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.spells.Spell;
public class SpellCard extends Card
{
    private final Spell spell;
    public SpellCard(Spell spell, int maxRank, int price, int rank)
    {
        super(maxRank, price, rank);
        this.spell = spell;
    }

    public Spell getSpell()
    {
        return spell;
    }
}
