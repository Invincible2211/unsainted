package de.prog2.dungeontop.resources;

import javafx.geometry.Insets;
import javafx.scene.text.Font;

public interface HellViewConstants
{
    int HORIZONTAL_TILES = WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE;
    int VERTICAL_TILES = WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE;

    int SCENE_STARTUP_HEIGHT = 1080;
    int SCENE_STARTUP_WIDTH = 1920;

    int ROOM_TILE_WIDTH = 200;
    int ROOM_TILE_HEIGHT = 200;

    int ROOM_TILE_FIT_WIDTH = 200;
    int ROOM_TILE_FIT_HEIGHT = 200;

    int TRANSFORM_Y_COORDINATE = WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE * ROOM_TILE_HEIGHT;

    double PLAYER_MOVESPEED = ROOM_TILE_FIT_HEIGHT * WorldConstants.ROOM_SIZE;

    double PLAYER_STATS_HGAP = 10;
    double SETTINGS_FIT_HEIGHT = 100;
    double SETTINGS_FIT_WIDTH = 100;
    double SETTINGS_WIDTH_MULTI = 1.5;
    double SETTING_HEIGHT_MULTI = 1.5;
    double HALF = .5;
    double STAT_BOARD_WIDTH_MULTI = 3;

    int STAT_BOARD_TEXT_SIZE = 18;
    Font STAT_BOARD_FONT = new Font(STAT_BOARD_TEXT_SIZE);
    double STAT_BOARD_ICON_WIDTH = 60;
    double STAT_BOARD_ICON_HEIGHT = 60;
    Insets STAT_BOARD_ELEMENT_PADDING = new Insets(STAT_BOARD_ICON_HEIGHT/10, STAT_BOARD_ICON_WIDTH,
            STAT_BOARD_ICON_HEIGHT/10, STAT_BOARD_ICON_WIDTH);
    Insets STAT_BOARD_ICON_TEXT_DISTANCE = new Insets(0, 25, 0, 25);

    boolean SETTINGS_FOCUS_TRAVERSABLE = false;
}
