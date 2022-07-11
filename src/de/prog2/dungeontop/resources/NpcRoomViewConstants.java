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
    boolean SOULS_ICO_PRESERVE_RATIO = false;
    boolean SOULS_ICO_SMOOTH = false;


    double CLOSE_BUTTON_BORDER_RADIUS = 10;
    double CLOSE_BUTTON_BG_RADIUS = 15;
    double CLOSE_BUTTON_INSETS = -2.5;
    double SMALL_BUTTON_FIT_WIDTH = 100;
    double SMALL_BUTTON_FIT_HEIGHT = SMALL_BUTTON_FIT_WIDTH;
    double NUMBER_SMALL_BUTTONS = 2;
    double SMALL_BUTTON_MULTI = 2;
    double SMALL_BUTTON_PADDING = 5;
    double SMALL_BUTTON_BORDER_WIDTH = 5;
    boolean SMALL_BUTTON_FOCUS_TRAVERSABLE = false;
    String CLOSE_BUTTON_STYLE = "-fx-border-radius: "+ CLOSE_BUTTON_BORDER_RADIUS +
            "; -fx-background-color: red; -fx-border-color: black; -fx-border-width: " + SMALL_BUTTON_BORDER_WIDTH +
            "; -fx-background-radius: " + CLOSE_BUTTON_BG_RADIUS + "; -fx-border-insets: " + CLOSE_BUTTON_INSETS +"; " +
            "-fx-padding: " + SMALL_BUTTON_PADDING + ";";

    double STATBOARD_BG_WIDTH = SCENE_PREF_WIDTH - SMALL_BUTTON_FIT_WIDTH * SMALL_BUTTON_MULTI * NUMBER_SMALL_BUTTONS;
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
    String SHOP_BUTTON_TEXT = "Unlock for ";
    String PRICE_TEXT = "Price: ";
    String SOULS_SUBTITLE = "Souls";

    int FREE_ACTION_PRICE = 0;
    int FREE_ACTION_DECREMENT = 1;
    int FREE_ACTION_COMPARATOR = 1;

    double BUTTON_PREF_WIDTH = 460;
    double BUTTON_PREF_HEIGHT = 120;

}
