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

    List<RoomDialogueViewController.StageVariable> EVENT_ROOM_DESCRIPTIONS = new LinkedList<>();

    static void INIT_EVENT_ROOM_DESCRIPTIONS ()
    {
        // Event 1
        EVENT_ROOM_DESCRIPTIONS.add(
                new RoomDialogueViewController.StageVariable(
                        AssetIds.RANDOM_EVENT_ROOM,
                        "Auch fuer den RandomEventRoom soll es eine Anzahl an unterschiedlichen " +
                                "Asset-Flavourtext-Kombinationen geben. Dies funktioniert wie schon bei den " +
                                "ArenaRooms."
                )
        );
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
}
