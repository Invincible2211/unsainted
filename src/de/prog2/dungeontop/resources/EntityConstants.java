package de.prog2.dungeontop.resources;

public interface EntityConstants
{
    double ENTITY_BASE_WIDTH = 469;
    double ENTITY_BASE_HEIGHT = 469;
    // Entity Image
    double ENTITY_IMAGE_SCALE = 0.5;
    double ENTITY_IMAGE_WIDTH = 884 * ENTITY_IMAGE_SCALE;
    double ENTITY_IMAGE_HEIGHT = 849 * ENTITY_IMAGE_SCALE;

    double STATUS_BAR_HEIGHT = 350;
    // HP
    double HP_TOP_ANCHOR = STATUS_BAR_HEIGHT + CardConstants.ICON_OFFSET;
    double HP_LEFT_ANCHOR = 40 + CardConstants.ICON_OFFSET;
    // Movement
    double MOVEMENT_TOP_ANCHOR = STATUS_BAR_HEIGHT + CardConstants.ICON_OFFSET;
    double MOVEMENT_LEFT_ANCHOR = 200 + CardConstants.ICON_OFFSET;
    // Attack
    double ATTACK_TOP_ANCHOR = STATUS_BAR_HEIGHT + CardConstants.ICON_OFFSET;
    double ATTACK_LEFT_ANCHOR = 360 + CardConstants.ICON_OFFSET;
}
