package de.prog2.dungeontop.resources;

public interface ViewStrings
{
    // Main Menu
    String MAIN_MENUE_FXML = "view/mainmenue.fxml";
    String MAIN_MENUE_ICO = "view/Unsainted ICO.png";
    // Settings
    String SETTINGS_FXML = "view/settings.fxml";
    // Hero Select
    String SELECT_HERO_FXML = "view/selectHero.fxml";
    // Inventory
    String INVENTORY_FXML = "view/inventory.fxml";
    String ITEM_VIEW_FXML = "view/itemView.fxml";
    String ITEM_CLICKED_FXML = "view/itemClicked.fxml";

    // Arena
    String ARENA_ROOM_DIALOGUE_FXML = "view/roomDialogueView.fxml";
    String ARENABASE_VIEW = "view/arenaBase.fxml";
    // Entity View
    String ENTITY_VIEW_FXML = "view/entityView.fxml";
    // Card View
    String ENTITY_CARD_VIEW_FXML = "view/cardViews/entityCardView.fxml";
    String SPELL_CARD_VIEW_FXML = "view/cardViews/spellCardView.fxml";
    // Shop View
    String SHOP_VIEW_FXML = "view/shopView.fxml";
    String SHOP_VIEW_CSS = "view/shopView.css";
    String LOBBY_FXML = "view/lobby.fxml";

    //ist kein string aber kann hier trotzdem rein
    int RESOLUTION_X = 1920;
    int RESOLUTION_Y = 1080;
    double EGOPOINTS_BACKROUND_HEIGHT = RESOLUTION_X * 0.02;
    double EGOPOINTS_BACKROUND_WIDTH = RESOLUTION_Y * 0.05;
    double HAND_PLAYER_X = RESOLUTION_X * 0.8;
    double HAND_PLAYER_Y = RESOLUTION_Y * 0.10;
    double BATTLEFIELDSIZE_X = RESOLUTION_X * 0.9;
    double BATTLEFIELDSIZE_Y = RESOLUTION_Y * 0.9;
    double CARD_WIDTH = 343;
    double CARD_HEIGHT = 480;
    double BATTLEFIELD_HGAP_DEFAULT = 5;
    double BATTLEFIELD_VGAP_DEFAULT = 5;
    double BATTLEFIELD_CELL_MIN_SIZE_MODIFIER = 0.5;
    double ZOOMFACTO_ON_MOUSE_HOVER_CARD = 1.5;
}
