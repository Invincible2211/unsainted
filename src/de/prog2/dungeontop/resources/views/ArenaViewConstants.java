package de.prog2.dungeontop.resources.views;

import de.prog2.dungeontop.resources.ApplicationConstants;

public interface ArenaViewConstants
{
    //old
    double EGOPOINTS_BACKGROUND_HEIGHT = ApplicationConstants.RESOLUTION_X * 0.02;
    double EGOPOINTS_BACKGROUND_WIDTH = ApplicationConstants.RESOLUTION_Y * 0.05;
    double HAND_PLAYER_X = ApplicationConstants.RESOLUTION_X * 0.8;
    double HAND_PLAYER_Y = ApplicationConstants.RESOLUTION_Y * 0.15;
    //680 on 1920x1080 is what we want for now
    double BATTLEFIELD_SIZE_X = ApplicationConstants.RESOLUTION_X / 1920 * 680;
    double BATTLEFIELD_SIZE_Y = ApplicationConstants.RESOLUTION_Y / 1080 * 680;
    // How did they get here?
    double CARD_WIDTH = 343;
    double CARD_HEIGHT = 480;
    double BATTLEFIELD_HGAP_DEFAULT = 5;
    double BATTLEFIELD_VGAP_DEFAULT = 5;
    double BATTLEFIELD_CELL_MIN_SIZE_MODIFIER = 0.5;

    //new
    double CARD_DETAIL_VIEW_SCALE = 1;
    double HAND_CARD_SCALE = (HAND_PLAYER_Y / (CardConstants.CARD_BASE_HEIGHT * 0.75d)) / 1.2;
    double BATTLEFIELD_SIZE_X_MAX = ArenaViewConstants.BATTLEFIELD_SIZE_X * 1.1;
    double BATTLEFIELD_SIZE_Y_MAX = ArenaViewConstants.BATTLEFIELD_SIZE_Y * 1.1;
    double BATTLEFIELD_X_SIDE_OFFSET = ApplicationConstants.RESOLUTION_X / 1920 * 370;
    double BATTLEFIELD_TILE_TARGET_SIZE = ApplicationConstants.RESOLUTION_X / 1920 * 170;
}
