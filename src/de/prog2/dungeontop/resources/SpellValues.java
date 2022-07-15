package de.prog2.dungeontop.resources;

public interface SpellValues
{
    String HEALING_SPELL_NAME = "Healing Spell";
    String HEALING_SPELL_DESCRIPTION = "Heals all friendly units in a radius of %d by %d.";
    int HEALING_SPELL_ASSET_ID = 700;
    int HEALING_SPELL_RADIUS = 0;
    int HEALING_SPELL_HEAL = 10;

    String METEOR_SPELL_NAME = "Meteor";
    String METEOR_SPELL_DESCRIPTION = "Damages all enemy units in a radius of %d by %d.";
    int METEOR_SPELL_ASSET_ID = 705;
    int METEOR_SPELL_RADIUS = 1;
    int METEOR_SPELL_DAMAGE = 10;
}
