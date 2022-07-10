package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.game.Talent;

public interface SelectHeroConstants
{
    // warrior
    String WARRIOR_NAME = "Krieger";
    int WARRIOR_HP = 12;
    int WARRIOR_ATK = 2;
    Talent WARRIOR_TALENT = Talent.STURDY;

    // mage
    String MAGE_NAME = "Magier";
    int MAGE_HP = 8;
    int MAGE_ATK = 4;
    Talent MAGE_TALENT = Talent.INTELLIGENT;

    // rogue
    String ROGUE_NAME = "Schurke";
    int ROGUE_HP = 10;
    int ROGUE_ATK = 3;
    Talent ROGUE_TALENT = Talent.SNEAKY;

    String PLAYER_CLASS = "Klasse: ";
    String PLAYER_HP = "Leben: ";
    String PLAYER_ATK = "Angriff: ";
    String PLAYER_TALENT = "Talent: ";
}
