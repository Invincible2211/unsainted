package de.prog2.dungeontop.resources.views;

public interface CardConstants
{
    int ENTITY_NAME_FONT_SIZE = 35;
    double CARD_BASE_HEIGHT = 640;
    double CARD_BASE_WIDTH = 489;
    // EntityCard
    double ENTITY_IMAGE_SCALE = 0.3;
    double ENTITY_IMAGE_WIDTH = 884 * ENTITY_IMAGE_SCALE;
    double ENTITY_IMAGE_HEIGHT = 849 * ENTITY_IMAGE_SCALE;
    // Icon Size
    double ICON_SIZE = 70d;
    double ICON_OFFSET = ICON_SIZE / 2d;
    // Card Anchors
    double SUMMON_COST_TOP_ANCHOR = 25 + ICON_OFFSET;
    double SUMMON_COST_LEFT_ANCHOR = 225 + ICON_OFFSET;
    double STATUSBAR_TOP_ANCHOR = 460;

    double HP_TOP_ANCHOR = STATUSBAR_TOP_ANCHOR + ICON_OFFSET;
    double HP_LEFT_ANCHOR = 334 + ICON_OFFSET;

    double ATTACK_TOP_ANCHOR = STATUSBAR_TOP_ANCHOR + ICON_OFFSET;
    double ATTACK_LEFT_ANCHOR = 100 + ICON_OFFSET;

    double MOVEMENT_TOP_ANCHOR = STATUSBAR_TOP_ANCHOR + ICON_OFFSET;
    double MOVEMENT_LEFT_ANCHOR = 209 + ICON_OFFSET;

    double RANK_TOP_ANCHOR = 550 + ICON_OFFSET;
    double RANK_LEFT_ANCHOR = 225 + ICON_OFFSET;

    double NAME_OFFSET_Y = ENTITY_NAME_FONT_SIZE/2d;
    double NAME_TOP_ANCHOR = 125 + NAME_OFFSET_Y;
    String NAME_FONT_SIZE_STYLE = "-fx-font-size: %d px;";
    double ENTITY_IMAGE_OFFSET_Y = ENTITY_IMAGE_HEIGHT/2d;
    double ENTITY_IMAGE_TOP_ANCHOR = 60 + ENTITY_IMAGE_HEIGHT;
    // Zoom
    double ZOOM_FACTOR = 1.5d;
    double NO_ZOOM_FACTOR = 1d;
    double ZOOM_TRANSLATE_Y = 40;
}
