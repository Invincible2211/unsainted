package de.prog2.dungeontop.resources;

public interface LoggerStringValues {
    String CARD_RANK_INCREASED = "Rank of a Card increased.";
    String SHOPMANAGER_CREATED = "ShopManager erstellt.";
    String SHOPMANAGER_GET = "ShopManager abgefragt.";
    String CARD_UNLOCKED = "Karte wurde freigeschaltet";
    String PRESSED = " gedrueckt.";
    String RELEASED = " losgelassen.";
    String CARD_REMOVED_FROM_DECK = "Eine Karte wurde aus dem Deck entfernt.";
    String CARD_FROM_DECK_SOLD = "Eine Karte aus dem Deck wurde verkauft.";
    String ITEM_SOLD = "Ein Gegenstand wurde verkauft.";
    String PLAYERSOULS_GET = "Playersouls abgefragt.";
    String SET = " gesetzt.";
    String PLAYERSOULS_SET = "Playersouls wurden auf ";

    String ADD_ROOM_ERROR = "ERROR: Out of bounds or overlapping with an already existing room.";
    String ADDED_ROOM_TO_GRID = "Added room to hellgrid.";
    String ADDED_TOP_ROOM = "Added new top side room to the current room.";
    String ADDED_BOTTOM_ROOM = "Added new bottom side room to the current room.";
    String ADDED_LEFT_ROOM = "Added new left room to the current room.";
    String ADDED_RIGHT_ROOM = "Added new right room to the current room.";
    String HAS_TOP_ROOM = "Room hat top room abgefragt.";
    String HAS_BOTTOM_ROOM = "Room hat bottom room abgefragt.";
    String HAS_LEFT_ROOM = "Room hat left room abgefragt.";
    String HAS_RIGHT_ROOM = "Room hat right room abgefragt.";
    String TOP_AVAILABLE = "Abgefragt ob top room verfuegbar ist.";
    String BOTTOM_AVAILABLE = "Abgefragt ob bottom room verfuegbar ist.";
    String LEFT_AVAILABLE = "Abgefragt ob left room verfuegbar ist.";
    String RIGHT_AVAILABLE = "Abgefragt ob right room verfuegbar ist.";
    String ROOM_RNG_FAILURE = "ERROR: Room RNG delivered a value that is either too high or too low.";
    String ROOM_RNG_ROLL = "Room RNG rolled: ";
    String TOP_ROOM_RECURSION_BEGUN = "Top room Rekursion beginnt.";
    String BOTTOM_ROOM_RECURSION_BEGUN = "Bottom room Rekursion beginnt.";
    String LEFT_ROOM_RECURSION_BEGUN = "Left room Rekursion beginnt.";
    String RIGHT_ROOM_RECURSION_BEGUN = "Right room Rekursion beginnt.";
    String TOP_ROOM_RECURSION_ENDED = "Top room Rekursion hat geendet.";
    String BOTTOM_ROOM_RECURSION_ENDED = "Bottom room Rekursion hat geendet.";
    String LEFT_ROOM_RECURSION_ENDED = "Left room Rekursion hat geendet.";
    String RIGHT_ROOM_RECURSION_ENDED = "Right room Rekursion hat geendet.";
    String START_ROOM_CREATED = "Starting room wurde erstellt und dem Raumgrid hinzugefuegt.";
    String START_ROOM_NEIGHBORS = "Starting room neighbors wurden gesetzt.";

    String INIT_HELL_COMPONENT_HASH_MAP_START = "Starting to convert RoomHashMap to HellComponentHashMap.";
    String INIT_HELL_COMPONENT_HASH_MAP_END = "Finished converting RoomHashMap to HellComponentHashMap.";

    String ADDED_HELL_TO_WORLD = "Created Hell Nr ";
    String GOT_NEXT_BATTLEPHASE = "Battlemanager got new phase";
    String TRY_TO_GET_PHASE_AFTER_END = "battlemanager tried to get battlephase after end";
    String START_BATTLE = "Starting an arenaBattle";
    String PLAYER_CAN_CHOOSE_WHO_IS_FIRST = "Der Spieler darf aussuchen wer zuerst im duell Zieht";
    String DM_CAN_CHOOSE_WHO_IS_FIRST = "Der DM darf aussucher wer zuerst im duell Zieht";
}
