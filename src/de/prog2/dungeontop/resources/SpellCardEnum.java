package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.game.SpellCard;

public enum SpellCardEnum
{
    HEALING_SPELL_R1_CARD(new SpellCard(SpellEnum.HEALING_SPELL_R1.getSpell(), 3, 100, 1, 3, 40)),
    HEALING_SPELL_R2_CARD(new SpellCard(SpellEnum.HEALING_SPELL_R2.getSpell(), 3, 200, 2, 3, 41)),
    HEALING_SPELL_R3_CARD(new SpellCard(SpellEnum.HEALING_SPELL_R3.getSpell(), 3, 300, 3, 3, 42)),

    METEOR_SPELL_R1_CARD(new SpellCard(SpellEnum.METEOR_SPELL_R1.getSpell(), 3, 50, 1, 3, 43)),
    METEOR_SPELL_R2_CARD(new SpellCard(SpellEnum.METEOR_SPELL_R2.getSpell(), 3, 150, 2, 3, 44)),
    METEOR_SPELL_R3_CARD(new SpellCard(SpellEnum.METEOR_SPELL_R3.getSpell(), 3, 250, 3, 3, 45));
    private final SpellCard spellCard;

    private SpellCardEnum(SpellCard spellCard)
    {
        this.spellCard = spellCard;
    }

    public SpellCard getSpellCard()
    {
        return spellCard;
    }
}
