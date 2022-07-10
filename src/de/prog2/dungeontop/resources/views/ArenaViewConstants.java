package de.prog2.dungeontop.resources.views;

import de.prog2.dungeontop.resources.ApplicationConstants;

public interface ArenaViewConstants
{
    double EGOPOINTS_BACKGROUND_HEIGHT = ApplicationConstants.RESOLUTION_X * 0.02;
    double EGOPOINTS_BACKGROUND_WIDTH = ApplicationConstants.RESOLUTION_Y * 0.05;
    double HAND_PLAYER_X = ApplicationConstants.RESOLUTION_X * 0.8;
    double HAND_PLAYER_Y = ApplicationConstants.RESOLUTION_Y * 0.15;
    double BATTLEFIELD_SIZE_X = ApplicationConstants.RESOLUTION_X * 0.9;
    double BATTLEFIELD_SIZE_Y = ApplicationConstants.RESOLUTION_Y * 0.85;
    // How did they get here?
    double CARD_WIDTH = 343;
    double CARD_HEIGHT = 480;
    double BATTLEFIELD_HGAP_DEFAULT = 5;
    double BATTLEFIELD_VGAP_DEFAULT = 5;
    double BATTLEFIELD_CELL_MIN_SIZE_MODIFIER = 0.5;
    double ZOOMFACTOR_ON_MOUSE_HOVER_CARD = 1.5;
}
