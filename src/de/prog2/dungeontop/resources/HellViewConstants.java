package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.control.manager.AssetsManager;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public interface HellViewConstants
{
    // Scene Backgrounds
    Background BLACK_BG = new Background(new BackgroundFill(Color.BLACK, null, null));
    Background LAVA_BG_ANIMATED = new Background(new BackgroundImage(AssetsManager.getImageByAssetId(AssetIds.HELL_LAVA_BG),
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));

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
    Background SCENE_BG = BLACK_BG;

    // biggest y coordinate on the canvas
    int TRANSFORM_Y_COORDINATE = WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE * ROOM_TILE_HEIGHT;

    // player constants
    double PLAYER_MOVESPEED = ROOM_TILE_FIT_HEIGHT * WorldConstants.ROOM_SIZE;
    double PLAYER_FIT_WIDTH = 200;
    double PLAYER_FIT_HEIGHT = 200;

    // setting button constants
    double OVERLAY_BUTTON_FIT_HEIGHT = 100;
    double OVERLAY_BUTTON_FIT_WIDTH = 100;
    double SETTINGS_WIDTH_MULTI = 1.5;
    double SETTING_HEIGHT_MULTI = 1.5;
    boolean OVERLAY_BUTTON_FOCUS_TRAVERSABLE = false;
    double OVERLAY_BUTTON_X_OFFSET_MULTI = 2;

    // Settings Button Style
    double  SETTINGS_BORDER_RADIUS = 10;
    double OVERLAY_BUTTON_PADDING = 5;
    Insets SETTINGS_PADDING_INSET = new Insets(OVERLAY_BUTTON_PADDING);
    double SETTINGS_BG_RADIUS = 15;
    double SETTINGS_BORDER_INSETS = -2.5;
    double OVERLAY_BUTTON_BORDER_WIDTH = 5;


    String OVERLAY_BUTTON_STYLE = "-fx-border-radius: "+ SETTINGS_BORDER_RADIUS +
            "; -fx-background-color: #e0c59f; -fx-border-color: black; -fx-border-width: " + OVERLAY_BUTTON_BORDER_WIDTH +
            "; -fx-background-radius: " + SETTINGS_BG_RADIUS + "; -fx-border-insets: " + SETTINGS_BORDER_INSETS +"; " +
            "-fx-padding: " + OVERLAY_BUTTON_PADDING + ";";

    // statboard constants
    int OVERLAY_BUTTON_NUMBER = 2;
    double PLAYER_STATS_HGAP = 10;
    double STAT_BOARD_WIDTH_MULTI = 2;
    int STAT_BOARD_TEXT_SIZE = 18;
    Font STAT_BOARD_FONT = new Font(STAT_BOARD_TEXT_SIZE);
    double STAT_BOARD_ICON_WIDTH = 50;
    double STAT_BOARD_ICON_HEIGHT = 50;
    Insets STAT_BOARD_ELEMENT_PADDING = new Insets(STAT_BOARD_ICON_HEIGHT/6, STAT_BOARD_ICON_WIDTH,
            STAT_BOARD_ICON_HEIGHT/6, STAT_BOARD_ICON_WIDTH);
    Insets STAT_BOARD_ICON_TEXT_DISTANCE = new Insets(0, 25, 0, 25);
    BackgroundSize STAT_BOARD_BACKGROUND_SIZE =  new BackgroundSize(100, 100,
            true, true, false, true);
    boolean STAT_BOARD_BG_IMG_PRESERVE_RATIO = false;
    boolean STAT_BOARD_BG_IMG_SMOOTH = true;
    double STAT_BOARD_PREF_HEIGHT = OVERLAY_BUTTON_FIT_HEIGHT;
    double STAT_BOARD_PREF_WIDTH = SCENE_STARTUP_WIDTH  - HellViewConstants.OVERLAY_BUTTON_FIT_WIDTH *
            HellViewConstants.STAT_BOARD_WIDTH_MULTI * HellViewConstants.OVERLAY_BUTTON_NUMBER;

    Background STAT_BOARD_BG = new Background(new BackgroundImage
            (
                    AssetsManager.getImageByAssetId(AssetIds.STATBOARD_BACKGROUND_SCROLL,
                            STAT_BOARD_PREF_WIDTH, STAT_BOARD_PREF_HEIGHT,
                            HellViewConstants.STAT_BOARD_BG_IMG_PRESERVE_RATIO,
                            HellViewConstants.STAT_BOARD_BG_IMG_SMOOTH),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    HellViewConstants.STAT_BOARD_BACKGROUND_SIZE)
    );

    String HP_SUBTITLE = "HP";
    String SOUL_SUBTITLE = "Souls";
    String EMPTY_STRING = "";



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
