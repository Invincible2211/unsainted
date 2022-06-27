package de.prog2.dungeontop.resources;

public interface EntityConstants
{
    double ENTITY_BASE_WIDTH = 469;
    double ENTITY_BASE_HEIGHT = 469;
    // Entity Image
    double ENTITY_IMAGE_SCALE = 0.4;
    double ENTITY_IMAGE_WIDTH = 849d * ENTITY_IMAGE_SCALE;
    double ENTITY_IMAGE_HEIGHT = 884d * ENTITY_IMAGE_SCALE;

    double STATUS_BAR_HEIGHT = 60;
    double ICON_OFFSET = 120 / 2d;
    // HP
    double HP_TOP_ANCHOR = STATUS_BAR_HEIGHT + ICON_OFFSET;
    double HP_LEFT_ANCHOR = 40 + ICON_OFFSET;
    // Movement
    double MOVEMENT_TOP_ANCHOR = 350 + ICON_OFFSET;
    double MOVEMENT_LEFT_ANCHOR = 200 + ICON_OFFSET;
    // Attack
    double ATTACK_TOP_ANCHOR = STATUS_BAR_HEIGHT + ICON_OFFSET;
    double ATTACK_LEFT_ANCHOR = 350 + ICON_OFFSET;
}
