package de.prog2.dungeontop.resources;

public interface AssetIds
{
    // Room Types
    int RANDOM_EVENT_ROOM = 40;
    int ARENA_ROOM = 70;
    int FORGE_ROOM = 80;
    int LAVA_POND = 90;
    int BOSS_ROOM = 160;


    // Statboard Icons
    int SOUL_ICON = 50;
    int HEART_ICON = 60;
    int STATBOARD_BACKGROUND_SCROLL = 180;


    // Hell Asset IDs
    int WALL_CORNER = 100;
    int WALL = 110;
    int PASSAGE = 120;
    int CENTER = 130;
    int COGWHEEL = 150;
    int BAG = 140;

    // PlayerImage on HellView
    // Default
    int PLAYER = 190;
    // Depending on class
    int WARRIOR = 10;
    int MAGICIAN = 20;
    int ROGUE = 30;

    // NpcRoom Asset IDs
    int NPC_ROOM_BUTTON_BG = 170;
    int SMALL_BUTTON_IMG = 800;

    // Entity View
    int ENTITY_VIEW_BACKGROUND = 400;

    // Card Asset IDs
    int CARD_BACKGROUND_IMAGE_ID = 490;
    int CARD_DETAIL_BACKGROUND_IMAGE_ID = 500;
    int HP_ICON = 60;
    int ATTACK_ICON = 520;
    int SUMMON_COST_ICON = 530;
    int MOVEMENT_ICON = 510;
    int RANK1_ICON = 540;
    int RANK2_ICON = 550;
    int RANK3_ICON = 560;
    int RANK4_ICON = 570;
    int RANK5_ICON = 580;
    int RANK6_ICON = 590;
    static int getRankIcon(int rank)
    {
        return switch (rank)
        {
            case 1 -> RANK1_ICON;
            case 2 -> RANK2_ICON;
            case 3 -> RANK3_ICON;
            case 4 -> RANK4_ICON;
            case 5 -> RANK5_ICON;
            default -> RANK6_ICON;
        };
    }

    //Item AssetIds
    int HEALTH_POTION = 660;

    // Arena Asset IDs
    int ARENA_BG_DEFAULT_ID = 210;
    int BATTLEFIELDGRIDPANE_BACKGROUND_IMAGEID = 230;


}
