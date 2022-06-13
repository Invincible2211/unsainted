package de.prog2.dungeontop.resources;

public interface AssetIds {

    // Room Types
    int RANDOM_EVENT_ROOM = 4;
    int ARENA_ROOM = 7;
    int FORGE_ROOM = 8;
    int LAVA_POND = 9;
    int BOSS_ROOM = 16;


    // Statboard Iconsw
    int SOUL_ICON = 5;
    int HEART_ICON = 6;
    int STATBOARD_BACKGROUND_SCROLL = 18;


    // Hell Asset IDs
    // Range: 10-19

    int WALL_CORNER = 10;
    int WALL = 11;
    int PASSAGE = 12;
    int CENTER = 13;
    int PLAYER = 19;
    int COGWHEEL = 15;
    int BATTLEFIELDGRIDPANE_BACKGROUND_IMAGEID = 22;

    // Card Asset IDs
    int CARD_BACKGROUND_IMAGE_ID = 50;
    int HP_ICON = 6;
    int ATTACK_ICON = 52;
    int SUMMON_COST_ICON = 53;
    int MOVEMENT_ICON = 51;
    int RANK1_ICON = 54;
    int RANK2_ICON = 55;
    int RANK3_ICON = 56;
    int RANK4_ICON = 57;
    int RANK5_ICON = 58;
    int RANK6_ICON = 59;
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
}
