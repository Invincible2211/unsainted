package de.prog2.dungeontop.resources;

import javafx.geometry.Insets;
import javafx.scene.text.Font;

public interface NpcRoomViewConstants
{
    int CARDS_COLUMNS = 4;
    Font STATBOARD_FONT = new Font(HellViewConstants.STAT_BOARD_ICON_HEIGHT);
    Font SUBTITLE_FONT = HellViewConstants.STAT_BOARD_FONT;
    Font BUTTON_FONT = new Font(32);
    int DEFAULT_PRICE = 100;
    double SCENE_PREF_WIDTH = 1920;
    double SCENE_PREF_HEIGHT = 1080;
    Insets SCROLL_PANE_PADDING = new Insets(0, 10, 0, 10);

    double STATBOARD_BG_WIDTH = 1620.0;
    double STATBOARD_BG_HEIGHT = 100;
    double STATBOARD_TOP_ANCHOR = 10.0;
    double STATBOARD_LEFT_ANCHOR = (SCENE_PREF_WIDTH - STATBOARD_BG_WIDTH)/2;
    double STATBOARD_HGAP = 50;

    double GRID_HGAP = 10;
    double GRID_VGAP = 20;
    Insets GRID_PADDING = new Insets(100, 0, 0, 0);
    int GRID_START_COLUMN = 0;
    int GRID_START_ROW = 1;
    int VBOX_SPACING = 10;
    double CARD_SCALE = .956;

    String DISCARD_BUTTON_TEXT = "Discard";
    String UPGRADE_BUTTON_TEXT = "Upgrade";
    String PRICE_TEXT = "Price: ";
    String SOULS_SUBTITLE = "Souls";

    int FREE_ACTION_PRICE = 0;
    int FREE_ACTION_DECREMENT = 1;
    int FREE_ACTION_COMPARATOR = 1;

    double BUTTON_PREF_WIDTH = 460;
    double BUTTON_PREF_HEIGHT = 120;
}
