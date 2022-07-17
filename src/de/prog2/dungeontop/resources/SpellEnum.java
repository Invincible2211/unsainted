package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.spells.DamageSpell;
import de.prog2.dungeontop.model.spells.HealingSpell;
import de.prog2.dungeontop.model.spells.Spell;

public enum SpellEnum
{
    HEALING_SPELL_R1(new HealingSpell(SpellValues.HEALING_SPELL_NAME, "Heals the friendly unit by 1.", SpellValues.HEALING_SPELL_ASSET_ID, 1, 0)),
    HEALING_SPELL_R2(new HealingSpell(SpellValues.HEALING_SPELL_NAME, "Heals the friendly unit by 2.", SpellValues.HEALING_SPELL_ASSET_ID, 2, 0)),
    HEALING_SPELL_R3(new HealingSpell(SpellValues.HEALING_SPELL_NAME, "Heals the friendly unit by 3.", SpellValues.HEALING_SPELL_ASSET_ID, 3, 0)),

    METEOR_SPELL_R1(new DamageSpell(SpellValues.METEOR_SPELL_NAME, "Damages the enemy unit by 2.", SpellValues.METEOR_SPELL_ASSET_ID, 2, 0)),
    METEOR_SPELL_R2(new DamageSpell(SpellValues.METEOR_SPELL_NAME, "Damages all enemy units in a radius of 1 by 3.", SpellValues.METEOR_SPELL_ASSET_ID, 3, 1)),
    METEOR_SPELL_R3(new DamageSpell(SpellValues.METEOR_SPELL_NAME, "Damages all enemy units in a radius of 1 by 4.", SpellValues.METEOR_SPELL_ASSET_ID, 4, 1));

    private final Spell spell;

    private SpellEnum(Spell spell)
    {
        this.spell = spell;
    }

    public Spell getSpell()
    {
        return spell;
    }
}
