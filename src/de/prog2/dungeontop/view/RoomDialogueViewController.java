package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.RandomEventRoomController;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.control.network.NetManager;
import de.prog2.dungeontop.model.world.rooms.*;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.NpcRoomViewConstants;
import de.prog2.dungeontop.resources.RoomDialogueConstants;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import de.prog2.dungeontop.resources.RandomEventConstants;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RoomDialogueViewController
{
    @FXML
    private ImageView roomImageView;

    @FXML
    private TextArea descriptionText;

    @FXML
    private Button upperButton;

    @FXML
    private Button lowerButton;

    private static final Stage roomDialogueStage = new Stage();
    private static RoomDialogueViewController instance;

    public RoomDialogueViewController()
    {
        instance = this;
    }

    public static RoomDialogueViewController getInstance()
    {
        return instance;
    }

    private static final HashMap<Room, StageVariable> dialogueStageAttributes = new HashMap<Room, StageVariable>();

    public record StageVariable(int assetId, String description){};

    /**
     * Initializes the RoomDialogueStage
     */
    public static void initStage()
    {
        final FXMLLoader fxmlLoader = new FXMLLoader();
        RoomDialogueConstants.INIT_ARENA_ROOM_DESCRIPTIONS();
        RoomDialogueConstants.INIT_EVENT_ROOM_DESCRIPTIONS();

        roomDialogueStage.initModality(Modality.APPLICATION_MODAL);
        roomDialogueStage.initOwner(DungeonTop.getStage());
        roomDialogueStage.initStyle(StageStyle.TRANSPARENT);
        Pane rootPane = new Pane();
        try {
            rootPane = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.ARENA_ROOM_DIALOGUE_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Scene settingsScene = new Scene(rootPane, Color.TRANSPARENT);
        roomDialogueStage.setScene(settingsScene);
    }

    /**
     * Puts the description and the assetId into the HashMap using room as the key.
     * @param room The room to show the dialogue for
     */
    private void handleDialogueStageAttributes(Room room)
    {
        // has the dialogue been shown yet?
        if (!dialogueStageAttributes.containsKey(room))
            // mark the room as visited so that it gets the same image and description shown next time
            dialogueStageAttributes.put(room, getRandomRoomDialogue(room));
    }

    /**
     * Opens the Dialogue for the ForgeRoom.
     */
    public void showForgeDialogue()
    {
        setDialogueProperties(RoomDialogueConstants.FORGE_ROOM_UPPER_BUTTON,
                RoomDialogueConstants.FORGE_ROOM_LOWER_BUTTON, RoomDialogueConstants.FORGE_ROOM_DESCRIPTION,
                RoomDialogueConstants.FORGE_VIEW_ASSET);
        upperButton.setOnAction(e -> openForge());
        upperButton.setDisable(false);
        roomDialogueStage.show();
    }
    /**
     * Opens the Dialogue for the LavaPondRoom.
     */
    public void showLavaPondDialogue()
    {
        setDialogueProperties(RoomDialogueConstants.LAVA_POND_UPPER_BUTTON,
                RoomDialogueConstants.LAVA_POND_LOWER_BUTTON, RoomDialogueConstants.LAVA_POND_DESCRIPTION,
                RoomDialogueConstants.LAVA_POND_VIEW_ASSET);
        upperButton.setOnAction(e -> openLavaPond());
        upperButton.setDisable(false);
        roomDialogueStage.show();
    }
    /**
     * Opens the Dialogue for the ArenaRoom.
     */
    public void showArenaDialogue(Room room)
    {
        handleDialogueStageAttributes(room);
        setDialogueProperties(RoomDialogueConstants.ARENA_ROOM_UPPER_BUTTON,
                RoomDialogueConstants.ARENA_ROOM_LOWER_BUTTON,
                dialogueStageAttributes.get(room).description(), dialogueStageAttributes.get(room).assetId());
        upperButton.setOnAction(e -> {startBattle();NetManager.getInstance().getNetworkAPI().sendOpenArenaPackage(((ArenaRoom) room).getArena());});
        upperButton.setDisable(!NetManager.getInstance().isConnected());
        roomDialogueStage.show();
    }
    /**
     * Opens the Dialogue for the Boss-ArenaRoom.
     */
    public void showBossDialogue(Room room)
    {
        handleDialogueStageAttributes(room);
        setDialogueProperties(RoomDialogueConstants.ARENA_ROOM_UPPER_BUTTON,
                RoomDialogueConstants.ARENA_ROOM_LOWER_BUTTON,RoomDialogueConstants.BOSS_ROOM_DESCRIPTION,
                RoomDialogueConstants.BOSS_ROOM_VIEW_ASSET);
        upperButton.setOnAction(e -> {startBattle();NetManager.getInstance().getNetworkAPI().sendOpenArenaPackage(((ArenaRoom) room).getArena());});
        upperButton.setDisable(!NetManager.getInstance().isConnected());
        roomDialogueStage.show();
    }
    /**
     * Opens the Dialogue for the RandomEventRoom.
     */
    public void showRandomEventDialogue(Room room)
    {
        handleDialogueStageAttributes(room);
        setDialogueProperties(RoomDialogueConstants.EVENT_ROOM_UPPER_BUTTON,
                RoomDialogueConstants.EVENT_ROOM_LOWER_BUTTON,
                dialogueStageAttributes.get(room).description(),dialogueStageAttributes.get(room).assetId());
        upperButton.setOnAction(e -> {
            ((RandomEventRoom)room).setEventFinished();
            startRandomEvent();
        });
        upperButton.setDisable(false);
        roomDialogueStage.show();
    }

    /**
     * Sets the properties for the dialogue that shows when entering a special room.
     *
     * @param upperButtonText text that will be shown on the upper button
     * @param lowerButtonText text that will be shown on the lower button
     * @param description description that will be shown above the buttons
     * @param assetId ID of the asset that will be shown for the room
     */
    private void setDialogueProperties (String upperButtonText, String lowerButtonText,
                                        String description, int assetId)
    {
        roomImageView.imageProperty().setValue(
                AssetsManager.getImageByAssetId(assetId)
        );
        descriptionText.textProperty().setValue(description);
        upperButton.textProperty().setValue(upperButtonText);
        lowerButton.textProperty().setValue(lowerButtonText);
    }

    /**
     * Hide the DialogueStage.
     */
    @FXML
    private void hideStage ()
    {
        roomDialogueStage.hide();
    }

    /**
     * Start the battle for the ArenaRoom.
     */
    private void startBattle ()
    {
        hideStage();
        GlobalLogger.log(LoggerStringValues.START_BATTLE_HANDLER);
    }

    /**
     * Execute a random event.
     */
    private void startRandomEvent()
    {
        // initialize a random number generator to decide which random event gets chosen
        Random random = new Random();
        int eventId = random.nextInt(RoomDialogueConstants.EVENT_ROOM_DESCRIPTIONS.size());

        // definition of the different allowed events
        switch (eventId)
        {
            case RandomEventConstants.CHANGE_SOULS:
                RandomEventRoomController.changeSoulsEvent();
                break;
            case RandomEventConstants.CHANGE_HP:
                RandomEventRoomController.changeHpEvent();
                break;
            default:
                GlobalLogger.warning(eventId + LoggerStringValues.INVALID_EVENT_ID);
        }

        hideStage();
        GlobalLogger.log(LoggerStringValues.RANDOM_EVENT_HANDLER);
    }

    /**
     * Open the ForgeView.
     */
    private void openForge ()
    {
        NpcRoomView view = new NpcRoomView((NPCRoom) PlayerManager.getInstance().getPlayer().getCurrentRoom());
        DungeonTop.getStage().setScene(view.createNpcRoomView());
        hideStage();
        GlobalLogger.log(LoggerStringValues.OPEN_FORGE_HANDLER);
    }

    /**
     * Open the NpcRoomView.
     */
    private void openLavaPond ()
    {
        NpcRoomView view = new NpcRoomView((NPCRoom) PlayerManager.getInstance().getPlayer().getCurrentRoom());
        DungeonTop.getStage().setScene(view.createNpcRoomView());
        hideStage();
        GlobalLogger.log(LoggerStringValues.OPEN_LAVAPOND_HANDLER);
    }

    /**
     * Get a pseudo-random Asset-Description-Pair to show in a RoomDialogue.
     *
     * @param room Room the Description shall be generated for.
     * @return Object that contains an assetId and a description.
     */
    private static StageVariable getRandomRoomDialogue(Room room)
    {
        Random rng = new Random();
        StageVariable var = null;
        List<StageVariable> descriptions = null;

        if (room instanceof ArenaRoom)
            descriptions = RoomDialogueConstants.ARENA_ROOM_DESCRIPTIONS;
        else if (room instanceof RandomEventRoom)
            descriptions = RoomDialogueConstants.EVENT_ROOM_DESCRIPTIONS;

        int size = descriptions.size();
        int index = rng.nextInt(size);
        var = descriptions.get(index);

        return var;
    }
}
