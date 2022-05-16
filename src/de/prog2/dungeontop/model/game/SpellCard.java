package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.spells.Spell;
public class SpellCard extends Card
{
    private final Spell spell;
    public SpellCard(Spell spell, int maxRank, int price, int rank, int ego_points) {
        super(maxRank, price, rank, ego_points);
        this.spell = spell;
    }

    public Spell getSpell()
    {
        return spell;
    }
}
