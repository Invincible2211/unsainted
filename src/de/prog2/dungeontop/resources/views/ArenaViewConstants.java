package de.prog2.dungeontop.resources.views;

public interface ArenaViewConstants
{
    int RESOLUTION_X = 1920;
    int RESOLUTION_Y = 1080;
    double EGOPOINTS_BACKGROUND_HEIGHT = RESOLUTION_X * 0.02;
    double EGOPOINTS_BACKGROUND_WIDTH = RESOLUTION_Y * 0.05;
    double HAND_PLAYER_X = RESOLUTION_X * 0.8;
    double HAND_PLAYER_Y = RESOLUTION_Y * 0.15;
    double BATTLEFIELD_SIZE_X = RESOLUTION_X * 0.9;
    double BATTLEFIELD_SIZE_Y = RESOLUTION_Y * 0.85;
    // How did they get here?
    double CARD_WIDTH = 343;
    double CARD_HEIGHT = 480;
    double BATTLEFIELD_HGAP_DEFAULT = 5;
    double BATTLEFIELD_VGAP_DEFAULT = 5;
    double BATTLEFIELD_CELL_MIN_SIZE_MODIFIER = 0.5;
    double ZOOMFACTOR_ON_MOUSE_HOVER_CARD = 1.5;
}
