package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.view.RoomDialogueViewController;

import java.util.LinkedList;
import java.util.List;

public interface RoomDialogueConstants
{
    // ArenaRoom Constants
    String ARENA_ROOM_UPPER_BUTTON = "Zeit für ein Du-Du-Duell!";
    String ARENA_ROOM_LOWER_BUTTON = "Sorry Bahn kommt gleich.";

    List<RoomDialogueViewController.StageVariable> ARENA_ROOM_DESCRIPTIONS = new LinkedList<>();

    static void INIT_ARENA_ROOM_DESCRIPTIONS()
    {
        // Monster 1
        ARENA_ROOM_DESCRIPTIONS.add(
                new RoomDialogueViewController.StageVariable(
                        AssetIds.ARENA_ROOM,
                        "Auf diese Art und Weise koennen Paare aus Assets und Beschreibungen " +
                                "erzeugt werden damit diese zueinander passend angezeigt werden." +
                                "Dies ist lediglich ein proof of concept und soll beim fertigen Spiel durch " +
                                "sinnvolle Assets und zugehoerige Beschreibungen ersetzt werden."
                )
        );


        // Monster 2
        ARENA_ROOM_DESCRIPTIONS.add(
                new RoomDialogueViewController.StageVariable(
                        AssetIds.ARENA_ROOM,
                        "Dieses Element wurde pseudo.zufaellig ausgewaehlt."
                )
        );
    }

    // RandomEventRoom Constants
    String EVENT_ROOM_UPPER_BUTTON = "Abfahrt!";
    String EVENT_ROOM_LOWER_BUTTON = "LEAVE ME ALONE!";

    // Dialogues for the individual Random Event Rooms
    List<RoomDialogueViewController.StageVariable> EVENT_ROOM_DESCRIPTIONS = new LinkedList<>();

    // ID + description + asset id for each individual random event
    // (used to determine which event to start and which text to display)

    // change souls value event
    String CHANGE_SOULS_DESC = "Waehrend du einem Goblin hinterher jagst verliert er einen komisch leuchtenden Ball.\n" +
            "Willst du ihn aufheben?";
    int CHANGE_SOULS_ASSET = AssetIds.RANDOM_EVENT_ROOM;

    // change hp value event
    String CHANGE_HP_DESC = "Du betrittst einen Raum in dessen Mitte sich ein ominoeser Brunnen befindet.\n" +
            "Neben dem Brunnen liegen ein Haufen Gebeine, sowie ein blutverschmierter Silberbecher.\n" +
            "Moechtest du den Becher nehmen und aus dem Brunnen trinken?";
    int CHANGE_HP_ASSET = AssetIds.RANDOM_EVENT_ROOM;

    // Proof of Concept event
    int POC_EVENT = 999;
    String POC_EVENT_DESC = "Auch fuer den RandomEventRoom soll es eine Anzahl an unterschiedlichen " +
            "Asset-Flavourtext-Kombinationen geben. Dies funktioniert wie schon bei den " +
            "ArenaRooms.";
    int POC_EVENT_ASSET = AssetIds.RANDOM_EVENT_ROOM;

    static void INIT_EVENT_ROOM_DESCRIPTIONS ()
    {
        // change souls value event
        EVENT_ROOM_DESCRIPTIONS.add( RandomEventConstants.CHANGE_SOULS,
                new RoomDialogueViewController.StageVariable(CHANGE_SOULS_ASSET, CHANGE_SOULS_DESC));

        // change hp value event
        EVENT_ROOM_DESCRIPTIONS.add( RandomEventConstants.CHANGE_HP,
                new RoomDialogueViewController.StageVariable(CHANGE_HP_ASSET, CHANGE_HP_DESC));

        /*
        // Proof of Concept event
        EVENT_ROOM_DESCRIPTIONS.add( POC_EVENT,
                new RoomDialogueViewController.StageVariable(POC_EVENT_ASSET, POC_EVENT_DESC));
         */
    }

    // ForgeRoom Constants
    String FORGE_ROOM_UPPER_BUTTON = "Excalibur ich komme!";
    String FORGE_ROOM_LOWER_BUTTON = "Bin ich ein Schmied?";
    String FORGE_ROOM_DESCRIPTION = "Eine einsame Schmiede, es ist weit und breit niemand zu sehen. Mal sehen was wir " +
            "damit so anstellen koennen...";
    int FORGE_VIEW_ASSET = AssetIds.FORGE_ROOM;

    // LavaPondRoom Constants
    String LAVA_POND_UPPER_BUTTON = "Feuer frei!";
    String LAVA_POND_LOWER_BUTTON = "Niedliches Flaemmchen.";
    String LAVA_POND_DESCRIPTION = "Du findest einen kleinen Goblin vor einem noch kleineren Lavateich.\n" +
            "\"Das erste mal ist mehrkostenfrei, danach kostet es dich nicht mehr als ein paar Seelen.\"\n" +
            "Wie lautet deine Antwort?";
    int LAVA_POND_VIEW_ASSET = AssetIds.LAVA_POND;

    String BOSS_ROOM_DESCRIPTION = "Hier koennte Ihre Werbung stehen.";
    int BOSS_ROOM_VIEW_ASSET = AssetIds.BOSS_ROOM;
}
