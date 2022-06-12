package de.prog2.dungeontop.resources;

public interface ViewStrings {

    String MAIN_MENUE_FXML = "view/mainmenue.fxml";

    String MAIN_MENUE_ICO = "view/Unsainted ICO.png";

    String SETTINGS_FXML = "view/settings.fxml";

    String SELECT_HERO_FXML = "view/selectHero.fxml";

    String ARENA_ROOM_DIALOGUE_FXML = "view/roomDialogueView.fxml";

    String ARENABASE_VIEW = "view/arenaBase.fxml";

    //ist kein string aber kann hier trotzdem rein
    int RESOLUTION_X = 1920;
    int RESOLUTION_Y = 300;
    double EGOPOINTS_BACKROUND_HEIGHT = RESOLUTION_X * 0.05;
    double EGOPOINTS_BACKROUND_WIDTH = RESOLUTION_Y * 0.05;
    double HAND_PLAYER_X = RESOLUTION_X * 0.8;
    double HAND_PLAYER_Y = RESOLUTION_Y * 0.05;
    double BATTLEFIELDSIZE_X = RESOLUTION_X * 0.9;
    double BATTLEFIELDSIZE_Y = RESOLUTION_Y * 0.65;
    double CARD_WIDTH = 343;
    double CARD_HEIGHT = 480;
    double BATTLEFIELD_HGAP_DEFAULT = 10;
    double BATTLEFIELD_VGAP_DEFAULT = 10;
}
