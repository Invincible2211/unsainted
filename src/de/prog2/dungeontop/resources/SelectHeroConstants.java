package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.game.Talent;

import javax.swing.text.TabableView;

public interface SelectHeroConstants
{
    // warrior
    String WARRIOR_NAME = "Krieger";
    int WARRIOR_HP = 12;
    int WARRIOR_ATK = 2;
    int WARRIOR_MAX_MOVES = 1;
    int WARRIOR_ARTIFACT_SLOTS = 2;
    Talent WARRIOR_TALENT = Talent.STURDY;
    Hero WARRIOR = new Hero(SelectHeroConstants.WARRIOR_NAME, SelectHeroConstants.WARRIOR_HP,
            SelectHeroConstants.WARRIOR_ATK, SelectHeroConstants.WARRIOR_MAX_MOVES, WARRIOR_ARTIFACT_SLOTS,
            WARRIOR_TALENT, AssetIds.WARRIOR_ICO, PlayerManager.getInstance().getPlayer());

    // mage
    String MAGE_NAME = "Magier";
    int MAGE_HP = 8;
    int MAGE_ATK = 4;
    int MAGE_MAX_MOVES = 1;
    int MAGE_ARTIFACT_SLOTS = 2;
    Talent MAGE_TALENT = Talent.INTELLIGENT;
    Hero MAGE = new Hero(SelectHeroConstants.MAGE_NAME, SelectHeroConstants.MAGE_HP,
            SelectHeroConstants.MAGE_ATK, SelectHeroConstants.MAGE_MAX_MOVES, MAGE_ARTIFACT_SLOTS,
            MAGE_TALENT, AssetIds.MAGE_ICO, PlayerManager.getInstance().getPlayer());

    // rogue
    String ROGUE_NAME = "Schurke";
    int ROGUE_HP = 10;
    int ROGUE_ATK = 3;
    int ROGUE_MAX_MOVES = 2;
    int ROGUE_ARTIFACT_SLOTS = 2;
    Talent ROGUE_TALENT = Talent.SNEAKY;
    Hero ROGUE = new Hero(SelectHeroConstants.ROGUE_NAME, SelectHeroConstants.ROGUE_HP,
            SelectHeroConstants.ROGUE_ATK, SelectHeroConstants.ROGUE_MAX_MOVES, ROGUE_ARTIFACT_SLOTS,
            ROGUE_TALENT, AssetIds.ROGUE_ICO, PlayerManager.getInstance().getPlayer());

    String PLAYER_CLASS = "Klasse: ";
    String PLAYER_HP = "Leben: ";
    String PLAYER_ATK = "Angriff: ";
    String PLAYER_MAX_MOVES = "Zuege: ";
    String PLAYER_TALENT = "Talent: ";

    // DM
    String DUNGEON_MASTER_NAME = "Harald";
    int DM_HP = 1;
    int DM_ATK = 3;
    int DM_MOVES = 1;
    int DM_ARTIFACT_SLOTS = 2;
    Talent DM_TALENT = Talent.SNEAKY;
    Hero DUNGEON_MASTER = new Hero(DUNGEON_MASTER_NAME, DM_HP, DM_ATK, DM_MOVES, DM_ARTIFACT_SLOTS, DM_TALENT, AssetIds.DM_ICO,PlayerManager.getInstance().getPlayer());
}
