package de.prog2.dungeontop.resources;

import javafx.geometry.Insets;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;

public interface HellViewConstants
{
    // tile constants
    int HORIZONTAL_TILES = WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE;
    int VERTICAL_TILES = WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE;
    int ROOM_TILE_WIDTH = 200;
    int ROOM_TILE_HEIGHT = 200;
    int ROOM_TILE_FIT_WIDTH = 200;
    int ROOM_TILE_FIT_HEIGHT = 200;

    // scene constants
    int SCENE_STARTUP_HEIGHT = 1080;
    int SCENE_STARTUP_WIDTH = 1920;

    // biggest y coordinate on the canvas
    int TRANSFORM_Y_COORDINATE = WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE * ROOM_TILE_HEIGHT;

    // player constants
    double PLAYER_MOVESPEED = ROOM_TILE_FIT_HEIGHT * WorldConstants.ROOM_SIZE;
    double PLAYER_FIT_WIDTH = 200;
    double PLAYER_FIT_HEIGHT = 200;

    // statboard constants
    double PLAYER_STATS_HGAP = 10;
    double STAT_BOARD_WIDTH_MULTI = 3;
    int STAT_BOARD_TEXT_SIZE = 18;
    Font STAT_BOARD_FONT = new Font(STAT_BOARD_TEXT_SIZE);
    double STAT_BOARD_ICON_WIDTH = 50;
    double STAT_BOARD_ICON_HEIGHT = 50;
    Insets STAT_BOARD_ELEMENT_PADDING = new Insets(STAT_BOARD_ICON_HEIGHT/6, STAT_BOARD_ICON_WIDTH,
            STAT_BOARD_ICON_HEIGHT/6, STAT_BOARD_ICON_WIDTH);
    Insets STAT_BOARD_ICON_TEXT_DISTANCE = new Insets(0, 25, 0, 25);
    BackgroundSize STATBOARD_BACKGROUND_SIZE =  new BackgroundSize(100, 100,
            true, true, false, true);

    String HP_SUBTITLE = "HP";
    String SOUL_SUBTITLE = "Souls";
    String EMPTY_STRING = "";

    // setting button constants
    double SETTINGS_FIT_HEIGHT = 100;
    double SETTINGS_FIT_WIDTH = 100;
    double SETTINGS_WIDTH_MULTI = 1.5;
    double SETTING_HEIGHT_MULTI = 1.5;
    boolean SETTINGS_FOCUS_TRAVERSABLE = false;
    String SETTINGS_STYLE_STRING = "-fx-border-radius: 10; -fx-background-color: #e0c59f; -fx-border-color: black; " +
            "-fx-border-width: 5; -fx-background-radius: 15; -fx-background-insets: 0; -fx-border-insets: 0;";

    // container pane constants
    double PANE_WIDTH = HORIZONTAL_TILES * ROOM_TILE_WIDTH;
    double PANE_HEIGHT = VERTICAL_TILES * ROOM_TILE_HEIGHT;

    // generic constants
    double HALF = .5;
    int OFFSET_ONE = 1;
    int X_ZERO = 0;
    int KEYFRAME_DURATION_MS = 500;

    // movement animation constants
    int DELTA_X_INIT = 0;
    int DELTA_Y_INIT = 0;

    boolean IS_ANIMATING_DEFAULT_VALUE = false;

    // test constants
    int STATBOARD_CHANGE_TEST_VALUE = 10;
}
