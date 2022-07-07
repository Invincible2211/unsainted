package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.InventoryController;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.MovementManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.MoveDirection;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.hellComponents.HellComponent;
import de.prog2.dungeontop.model.world.rooms.*;
import de.prog2.dungeontop.resources.*;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
public class HellView
{
    // representation of the player on the hell view
    private ImageView playerView = null;
    // is an animation currently in progress?
    private boolean isAnimating = HellViewConstants.IS_ANIMATING_DEFAULT_VALUE;
    // currently used HellView
    private static Scene currHellView;
    private static int playerAssetId = AssetIds.PLAYER;

    /**
     * Initialize the View for a given hell
     *
     * @param hell hell that has to be visualized
     * @return scene representing the given hell
     */
    public Scene initHellView(Hell hell)
    {
        // container for the canvas
        Pane pane = createBackground(hell);
        // container for the pane
        AnchorPane container = new AnchorPane(pane);

        // Set the background that is seen between the rooms
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Scene scene = new Scene(container, HellViewConstants.SCENE_STARTUP_WIDTH, HellViewConstants.SCENE_STARTUP_HEIGHT);

        // if the player is currently in no room
        if (PlayerManager.getInstance().getPlayer().getCurrentRoom() == null)
            PlayerManager.getInstance().getPlayer().setCurrentRoom(hell.getStartingRoom());

        // init a camera that follows the player and some UI elements
        initPlayerCamera(scene, pane);
        initOverlay(container);

        // key listener for player movement
        scene.setOnKeyPressed(e -> movePlayer(e.getCode()));

        GlobalLogger.log(LoggerStringValues.HELLVIEW_INIT);
        return scene;
    }

    /**
     * Initialize the overlay for the HellView.
     * The overlay consists of a settings button, an inventory button and a statboard showing the player stats.
     *
     * @param container AnchorPane used as container in the HellView
     */
    private void initOverlay (AnchorPane container)
    {
        // add settings button
        Button settingsButton = getOverlayButton(AssetIds.COGWHEEL);
        container.getChildren().add(settingsButton);
        // set position of the settings button
        AnchorPane.setTopAnchor(settingsButton, HellViewConstants.OVERLAY_BUTTON_FIT_HEIGHT *
                HellViewConstants.HALF);
        AnchorPane.setRightAnchor(settingsButton, HellViewConstants.OVERLAY_BUTTON_FIT_WIDTH *
                HellViewConstants.HALF);

        settingsButton.setOnAction(e -> openSettings());


        // add inventory button
        Button inventoryButton = getOverlayButton(AssetIds.BAG);
        container.getChildren().add(inventoryButton);
        // set position of the inventory button
        AnchorPane.setTopAnchor(inventoryButton, HellViewConstants.OVERLAY_BUTTON_FIT_HEIGHT *
                HellViewConstants.HALF);
        AnchorPane.setLeftAnchor(inventoryButton, HellViewConstants.OVERLAY_BUTTON_FIT_WIDTH *
                HellViewConstants.HALF);

        inventoryButton.setOnAction(e -> openInventory());

        initPlayerStats(container);
    }

    /**
     * Creates a new Button which is thought to be used in the HellView Overlay.
     *
     * @param assetId ID of the image-asset that shall be used inside the button
     * @return button with style that fits the HellView Overlay
     */
    private Button getOverlayButton (final int assetId)
    {
        Image buttonImage = AssetsManager.getImageByAssetId(assetId);
        ImageView buttonImageView = new ImageView(buttonImage);

        Button overlayButton = new Button();

        // set the button style
        overlayButton.setBackground(Background.EMPTY);
        overlayButton.setStyle(HellViewConstants.OVERLAY_BUTTON_STYLE);

        overlayButton.setGraphic(buttonImageView);

        buttonImageView.setFitWidth(HellViewConstants.OVERLAY_BUTTON_FIT_WIDTH - 2 * HellViewConstants.OVERLAY_BUTTON_PADDING -
                2 * HellViewConstants.OVERLAY_BUTTON_BORDER_WIDTH);
        buttonImageView.setFitHeight(HellViewConstants.OVERLAY_BUTTON_FIT_HEIGHT - 2 * HellViewConstants.OVERLAY_BUTTON_PADDING -
                2 * HellViewConstants.OVERLAY_BUTTON_BORDER_WIDTH);

        overlayButton.setFocusTraversable(HellViewConstants.OVERLAY_BUTTON_FOCUS_TRAVERSABLE);

        return overlayButton;
    }


    /**
     * Method that draws a visual representation of a given hell.
     *
     * @param hell Hell which contains the HellComponentHashmap and Coordinates for each room
     * @return Container Pane for the visual Hell representation
     */
    private Pane createBackground(Hell hell)
    {
        HashMap<Coordinate, HellComponent> roomComponents = hell.getHellComponentHashMap();
        // Creating the canvas on which the Hell will be drawn
        Canvas canvas = new Canvas(HellViewConstants.HORIZONTAL_TILES * HellViewConstants.ROOM_TILE_WIDTH,
                HellViewConstants.VERTICAL_TILES * HellViewConstants.ROOM_TILE_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Initializing the Container pane
        Pane containerPane = new Pane(canvas);

        containerPane.setMinSize(canvas.getWidth(), canvas.getHeight());
        containerPane.setPrefSize(canvas.getWidth(), canvas.getHeight());
        containerPane.setMaxSize(canvas.getWidth(), canvas.getHeight());

        // Iterating over all HellComponents and draw them onto the canvas
        for (Coordinate coordinate : roomComponents.keySet()) {
            // draw the tile with a rotation that is saved within the HellComponent on the
            // coordinate for the current iteration
            // the y coordinate has to be recalculated as we based our thoughts on (0, 0) being the bottom
            // left coordinate while JavaFX uses (0, 0) for the top left coordinate
            drawRotatedImage(gc,
                    AssetsManager.getImageByAssetId(roomComponents.get(coordinate).getAssetId()),
                    roomComponents.get(coordinate).getRotation().getAngle(),
                    coordinate.getX() * HellViewConstants.ROOM_TILE_WIDTH,
                    HellViewConstants.TRANSFORM_Y_COORDINATE - (coordinate.getY() +
                            HellViewConstants.OFFSET_ONE) * HellViewConstants.ROOM_TILE_HEIGHT
            );
        }
        drawRoomTypes(canvas, hell);

        GlobalLogger.warning(LoggerStringValues.BACKGROUND_CREATED);

        return containerPane;
    }

    /**
     * Draws images in the center of the special rooms, which indicate what kind of room it is.
     *
     * @param canvas Canvas that hast he rooms drawn on for which the different rooms have to be drawn
     * @param hell Hell which is already drawn onto the Canvas (when using another hell,
     *             the shown room types will not match the real ones!)
     */
    private void drawRoomTypes (Canvas canvas, Hell hell)
    {
        // TODO : Jesse
        // load the images of all roomtypes we want to show
        Image monsterRoom = AssetsManager.getImageByAssetId(AssetIds.ARENA_ROOM);
        Image bossRoom = AssetsManager.getImageByAssetId(AssetIds.BOSS_ROOM);
        Image forgeRoom = AssetsManager.getImageByAssetId(AssetIds.FORGE_ROOM);
        Image lavaPondRoom = AssetsManager.getImageByAssetId(AssetIds.LAVA_POND);
        Image randomEventRoom = AssetsManager.getImageByAssetId(AssetIds.RANDOM_EVENT_ROOM);

        // iterate over all rooms in the given hell and set the image for the next room that shall be drawn
        for (Room room : hell.getRoomHashMap().values())
        {
            Image currRoomImage = null;
            if(room instanceof EmptyRoom)
                continue;
            if (room instanceof ArenaRoom)
            {
                if (room == hell.getBossRoom())
                    currRoomImage = bossRoom;
                else
                    currRoomImage = monsterRoom;
            }
            else if (room instanceof RandomEventRoom)
                currRoomImage = randomEventRoom;
            else if (room instanceof ForgeRoom)
                currRoomImage = forgeRoom;
            else if (room instanceof LavaPondRoom)
                currRoomImage = lavaPondRoom;

            if (currRoomImage == null)
            {
                GlobalLogger.warning(LoggerStringValues.DRAW_ROOM_TYPES_IMAGE_NOT_FOUND);
                continue;
            }

            // draw the image of the room
            canvas.getGraphicsContext2D().drawImage
            (
                    currRoomImage,
                    (room.getCoordinate().getX() * WorldConstants.ROOM_SIZE + HellViewConstants.OFFSET_ONE) *
                            HellViewConstants.ROOM_TILE_FIT_WIDTH,
                    ((WorldConstants.HELL_SIZE - room.getCoordinate().getY() - HellViewConstants.OFFSET_ONE) *
                            WorldConstants.ROOM_SIZE + HellViewConstants.OFFSET_ONE)
                            * HellViewConstants.ROOM_TILE_FIT_HEIGHT,
                    HellViewConstants.ROOM_TILE_FIT_WIDTH, HellViewConstants.ROOM_TILE_FIT_HEIGHT
            );
        }
    }

    /**
     * Rotating a given GraphicsContext around a specified pivot point.
     *
     * @param gc     GraphicsContext that will be rotated
     * @param angle  angle by which the GraphicsContext shall be rotated
     * @param pivotX x coordinate around which the GraphicsContext shall be rotated
     * @param pivotY y coordinate around which the GraphicsContext shall be rotated
     */
    private void rotate(GraphicsContext gc, double angle, double pivotX, double pivotY) {
        Rotate r = new Rotate(angle, pivotX, pivotY);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

        GlobalLogger.log(LoggerStringValues.GC_ROTATED + angle);
    }

    /**
     * Draws an image rotated by a given angle.
     *
     * @param gc       GraphicsContext the image will be drawn onto
     * @param image    Image that will be drawn
     * @param angle    angle of the rotation
     * @param topLeftX top left x coordinate where the image will be drawn
     * @param topLeftY top left y coordinate where the image will be drawn
     */
    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double topLeftX, double topLeftY) {
        // saving the current state of the GraphicsContext
        gc.save();
        // rotating the GraphicsContext
        rotate(gc, angle, topLeftX + image.getWidth() * HellViewConstants.HALF,
                topLeftY + image.getHeight() * HellViewConstants.HALF);
        // drawing the image scaled to a fitting height and width
        gc.drawImage(image, topLeftX, topLeftY,
                HellViewConstants.ROOM_TILE_FIT_WIDTH, HellViewConstants.ROOM_TILE_FIT_HEIGHT);
        // restoring the previously saved state
        gc.restore();

        GlobalLogger.log(LoggerStringValues.TILE_DRAWN + topLeftX +
                LoggerStringValues.COMMA + topLeftY + LoggerStringValues.CLOSING_BRACKET);
    }

    /**
     * Creates a pseudo-camera and binds it to the player
     *
     * @param scene scene the camera shall be added to
     * @param pane  pane the player will be added to
     */
    private void initPlayerCamera(Scene scene, Pane pane)
    {
        GlobalLogger.log(LoggerStringValues.CAM_INIT_START);

        // Initialize the visual representation for the player
        Player player = PlayerManager.getInstance().getPlayer();
        Image playerImage = AssetsManager.getImageByAssetId(playerAssetId);
        playerView = new ImageView(playerImage);
        playerView.setFitHeight(HellViewConstants.PLAYER_FIT_HEIGHT);
        playerView.setFitWidth(HellViewConstants.PLAYER_FIT_WIDTH);


        // Place the player representation to his current room (most likely the starting room of the current hell)
        playerView.setX((player.getCurrentRoom().getCoordinate().getX() *
                WorldConstants.ROOM_SIZE + HellViewConstants.OFFSET_ONE) * HellViewConstants.ROOM_TILE_FIT_WIDTH);
        playerView.setY(
                ((WorldConstants.HELL_SIZE
                        - player.getCurrentRoom().getCoordinate().getY()
                        - HellViewConstants.OFFSET_ONE)
                * WorldConstants.ROOM_SIZE + HellViewConstants.OFFSET_ONE)
                * HellViewConstants.ROOM_TILE_FIT_HEIGHT);

        pane.getChildren().add(playerView);


        // init the "camera"
        Rectangle camera = new Rectangle();
        camera.widthProperty().bind(scene.widthProperty());
        camera.heightProperty().bind(scene.heightProperty());


        // bind the camera to the player
        camera.xProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerView.getX() - scene.getWidth() * HellViewConstants.HALF,
                        HellViewConstants.X_ZERO, HellViewConstants.PANE_WIDTH - scene.getWidth()),
                playerView.xProperty(), scene.widthProperty()
        ));

        camera.yProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerView.getY() - scene.getHeight() * HellViewConstants.HALF,
                        HellViewConstants.X_ZERO, HellViewConstants.PANE_HEIGHT - scene.getHeight()),
                playerView.yProperty(), scene.heightProperty()
        ));

        pane.setClip(camera);
        pane.translateXProperty().bind(camera.xProperty().multiply(-HellViewConstants.OFFSET_ONE));
        pane.translateYProperty().bind(camera.yProperty().multiply(-HellViewConstants.OFFSET_ONE));

        GlobalLogger.log(LoggerStringValues.CAM_INIT_END);
    }

    /**
     * Restrict the value of a double to a specified range
     * (see "https://en.wikipedia.org/wiki/Clamping_(graphics)")
     *
     * @param value Value that shall be restricted to the given range
     * @param min   lower boundary of the range
     * @param max   upper boundary of the range
     * @return nearest available value
     */
    private double clampRange(double value, double min, double max)
    {
        // the lowest allowed value should never be lower than the biggest allowed value
        if (min > max)
        {
            GlobalLogger.warning(LoggerStringValues.CLAMPING_FAILURE);
            return 0.0;
        }

        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    /**
     * Handles a key press by either ignoring it (invalid key) or moving the player in a certain direction
     * (valid key and valid direction)
     *
     * @param key key which was pressed
     */
    private void movePlayer(KeyCode key) {
        // prohibit running the method multiple times simultaneously
        if (isAnimating)
            return;
        isAnimating = true;

        // change to the player coordinate
        double deltaX = HellViewConstants.DELTA_X_INIT;
        double deltaY = HellViewConstants.DELTA_Y_INIT;

        // sett the change in a certain direction and move the player on the underlying grid if valid
        switch (key) {
            case UP, W:
                deltaY -= HellViewConstants.PLAYER_MOVESPEED;
                if (!MovementManager.getInstance().moveTowards(MoveDirection.UP)) {
                    unlockIsAnimating();
                    return;
                }
                break;
            case DOWN, S:
                deltaY += HellViewConstants.PLAYER_MOVESPEED;
                if (!MovementManager.getInstance().moveTowards(MoveDirection.DOWN)) {
                    unlockIsAnimating();
                    return;
                }
                break;
            case LEFT, A:
                deltaX -= HellViewConstants.PLAYER_MOVESPEED;
                if (!MovementManager.getInstance().moveTowards(MoveDirection.LEFT)) {
                    unlockIsAnimating();
                    return;
                }
                break;
            case RIGHT, D:
                deltaX += HellViewConstants.PLAYER_MOVESPEED;
                if (!MovementManager.getInstance().moveTowards(MoveDirection.RIGHT)) {
                    unlockIsAnimating();
                    return;
                }
                break;

            // Cases to manipulate playerstats by a keystroke to test the statboard
            // TODO: Once we got mechanics in the game that change the values on their own remove this
            case PLUS:
                PlayerManager.getInstance().addSouls(HellViewConstants.STATBOARD_CHANGE_TEST_VALUE);
                PlayerManager.getInstance().addHp(HellViewConstants.STATBOARD_CHANGE_TEST_VALUE);
                unlockIsAnimating();
                return;
            case MINUS:
                PlayerManager.getInstance().removeSouls(HellViewConstants.STATBOARD_CHANGE_TEST_VALUE);
                PlayerManager.getInstance().removeHp(HellViewConstants.STATBOARD_CHANGE_TEST_VALUE);
                unlockIsAnimating();
                return;
            default:
                unlockIsAnimating();
                return;
        }

        // create a timeline and keyframes which are used to "animate" the movement of the player between rooms
        final Timeline timeline = new Timeline();
        final KeyValue kvx = new KeyValue(playerView.xProperty(), playerView.getX() + deltaX);
        final KeyFrame kfx = new KeyFrame(Duration.millis(HellViewConstants.KEYFRAME_DURATION_MS), kvx);
        timeline.getKeyFrames().add(kfx);

        final KeyValue kvy = new KeyValue(playerView.yProperty(), playerView.getY() + deltaY);
        final KeyFrame kfy = new KeyFrame(Duration.millis(HellViewConstants.KEYFRAME_DURATION_MS), kvy);
        timeline.getKeyFrames().add(kfy);

        // start the animation
        timeline.play();

        // unlock the movement method again
        timeline.setOnFinished(e -> {
            isAnimating = false;
            RoomDialogueViewController.getInstance().showStage(PlayerManager.getInstance().getPlayer().getCurrentRoom());
        });

        GlobalLogger.log(LoggerStringValues.MOVED_PLAYER + key);
    }

    /**
     * unlock the player movement animation
     */
    private void unlockIsAnimating() {
        isAnimating = HellViewConstants.IS_ANIMATING_DEFAULT_VALUE;
    }

    /**
     * Add a statboard to the UI that shows different stats depending the player like his current HP
     *
     * @param container Pane that will contain the statboard
     */
    private void initPlayerStats (AnchorPane container)
    {
        // init visualization of player status
        FlowPane playerStats = new FlowPane(Orientation.HORIZONTAL);

        // set height and width (width depends on the the size of the settings button and the size of the scene)
        playerStats.setPrefHeight(HellViewConstants.OVERLAY_BUTTON_FIT_HEIGHT);
        playerStats.prefWidthProperty().bind(Bindings.createDoubleBinding(
                () -> container.getWidth() - HellViewConstants.OVERLAY_BUTTON_FIT_WIDTH *
                        HellViewConstants.STAT_BOARD_WIDTH_MULTI * HellViewConstants.OVERLAY_BUTTON_NUMBER,
                container.widthProperty()
        ));

        // set the Background on which the stats will be shown
        playerStats.setBackground(new Background(new BackgroundImage
                (
                        AssetsManager.getImageByAssetId(AssetIds.STATBOARD_BACKGROUND_SCROLL,
                                playerStats.getPrefWidth(), playerStats.getPrefHeight(),
                                HellViewConstants.STAT_BOARD_BG_IMG_PRESERVE_RATIO,
                                HellViewConstants.STAT_BOARD_BG_IMG_SMOOTH),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        HellViewConstants.STAT_BOARD_BACKGROUND_SIZE)
        ));

        // spacing between the statboard elements and alignment
        playerStats.setHgap(HellViewConstants.PLAYER_STATS_HGAP);
        playerStats.setAlignment(Pos.CENTER);

        // bind the player stats to the player;
        AnchorPane.setLeftAnchor(playerStats, HellViewConstants.OVERLAY_BUTTON_FIT_WIDTH
                * HellViewConstants.OVERLAY_BUTTON_X_OFFSET_MULTI);

        AnchorPane.setTopAnchor(playerStats, HellViewConstants.OVERLAY_BUTTON_FIT_HEIGHT
                * HellViewConstants.HALF);

        // add the statboard to the pane containing all the HellView components
        container.getChildren().add(playerStats);

        // init the statboard elements
        initStatboardElement(playerStats, HellViewConstants.HP_SUBTITLE, AssetIds.HEART_ICON,
                PlayerManager.getInstance().getPlayerHpProperty());
        initStatboardElement(playerStats, HellViewConstants.SOUL_SUBTITLE, AssetIds.SOUL_ICON,
                PlayerManager.getInstance().getPlayerSoulsProperty());
    }

    /**
     * Called if the settings button is pressed.
     * Opens a dialogue for the game settings.
     */
    private void openSettings()
    {
        GameManager.getInstance().pause();
    }

    /**
     * Called if the inventory button is pressed.
     * Opens the player inventory.
     */
    private void openInventory()
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;

        try
        {
            root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.INVENTORY_FXML));
        }
        catch (IOException e)
        {
            GlobalLogger.warning(LoggerStringValues.FXML_LOAD_ERROR);
            return;
        }
        InventoryController.addItems(fxmlLoader.getController(),
                PlayerManager.getInstance().getPlayerInventory().getInventory());
        Scene scene = new Scene(root);
        DungeonTop.getStage().setScene(scene);
    }

    /**
     * Create and add a new element to the statboard
     *
     * @param playerStats statboard
     * @param subtitle Text shown under the icon
     * @param iconAssetId ID of the icon that will be shown on the statboard
     * @param valueProperty Property of the value that shall be shown and kept refreshed
     */
    private void initStatboardElement (FlowPane playerStats, final String subtitle,
                                         final int iconAssetId, SimpleIntegerProperty valueProperty)
    {
        Text elementText = new Text();

        // Bind the textProperty to the player's hpProperty so that it automatically changes on value change
        elementText.textProperty().bind(Bindings.createStringBinding(
                () -> HellViewConstants.EMPTY_STRING + valueProperty.getValue(),
                valueProperty
        ));

        // create the container element and add is to the statboard
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);
        container.setPadding(HellViewConstants.STAT_BOARD_ELEMENT_PADDING);

        // set preferences for VBox
        VBox leftHalf = new VBox();
        leftHalf.setAlignment(Pos.CENTER);
        leftHalf.setPadding(HellViewConstants.STAT_BOARD_ICON_TEXT_DISTANCE);


        // create icon
        Image iconImage = AssetsManager.getImageByAssetId(iconAssetId);
        ImageView icon = new ImageView(iconImage);
        icon.setFitWidth(HellViewConstants.STAT_BOARD_ICON_WIDTH);
        icon.setFitHeight(HellViewConstants.STAT_BOARD_ICON_HEIGHT);

        // create text for the subtitle
        Text subtitleText = new Text(subtitle);
        subtitleText.setFont(HellViewConstants.STAT_BOARD_FONT);

        // assemble StatBoardElement
        leftHalf.getChildren().add(icon);
        leftHalf.getChildren().add(subtitleText);
        container.getChildren().add(leftHalf);

        // add the defined Text next to the shown icon
        // --> should be the value of the stat that we want to show (e.g. souls)
        container.getChildren().add(elementText);
        elementText.setFont(new Font(HellViewConstants.STAT_BOARD_ICON_HEIGHT));

        playerStats.getChildren().add(container);
    }

    /**
     * Getter for the currently used HellView
     *
     * @return currently used HellView
     */
    public static Scene getCurrHellView ()
    {
        return HellView.currHellView;
    }

    /**
     * Set the currently used HellView to a new one.
     *
     * @param nextHellView HellView that shall be used from now on.
     */
    public static void setCurrHellView (Scene nextHellView)
    {
        HellView.currHellView = nextHellView;
    }

    /**
     * Getter for the AssetId of the selected class
     *
     * @return AssetId to be used when rendering the HellView
     */
    public static int getPlayerAssetId ()
    {
        return HellView.playerAssetId;
    }

    /**
     * Setter for the AssetId of the image which shall be used to represent the player on the HellView
     *
     * @param playerAssetId ID of the Image asset that shall be used for the HellView
     */
    public static void setPlayerAssetId (final int playerAssetId)
    {
        HellView.playerAssetId = playerAssetId;
    }
}
