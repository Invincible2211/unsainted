package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.MovementManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.MoveDirection;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.hellComponents.HellComponent;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellViewConstants;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.desktop.SystemSleepEvent;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

public class HellView {
    //  variable to remember the last pressed and not yet handled key input
    private ImageView playerView = null;
    private boolean isAnimating = false;
    Pane pane;

    /**
     * Initialize the View for a given hell
     *
     * @param hell hell that has to be visualized
     * @return scene representing the given hell
     */
    public Scene initHellView(Hell hell) {
        // create hell
        pane = createBackground(hell.getHellComponentHashMap());
        BorderPane border = new BorderPane(pane);

        Player player = PlayerManager.getInstance().getPlayer();
        player.setCurrentRoom(hell.getStartingRoom());

        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Scene scene = new Scene(border, HellViewConstants.SCENE_STARTUP_WIDTH, HellViewConstants.SCENE_STARTUP_HEIGHT);

        initPlayerCamera(scene, pane);
        initOverlay(scene, pane);

        scene.setOnKeyPressed(e -> movePlayer(e.getCode()));

        GlobalLogger.log(LoggerStringValues.HELLVIEW_INIT);

        return scene;
    }

    /**
     * Method that draws a visual representation of a given hell.
     *
     * @param roomComponents HashMap which contains the HellComponents and Coordinates for each room
     * @return Container Pane for the visual Hell representation
     */
    private Pane createBackground(HashMap<Coordinate, HellComponent> roomComponents) {
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
                    HellViewConstants.TRANSFORM_Y_COORDINATE - (coordinate.getY() + 1) *
                            HellViewConstants.ROOM_TILE_HEIGHT
            );
        }

        GlobalLogger.warning(LoggerStringValues.BACKGROUND_CREATED);

        return containerPane;
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
        rotate(gc, angle, topLeftX + image.getWidth() / 2, topLeftY + image.getHeight() / 2);
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
    private void initPlayerCamera(Scene scene, Pane pane) {
        GlobalLogger.log(LoggerStringValues.CAM_INIT_START);
        // Inititalize the visual representation for the player
        Player player = PlayerManager.getInstance().getPlayer();
        Image playerImage = AssetsManager.getImageByAssetId(AssetIds.PLAYER);
        playerView = new ImageView(playerImage);

        // Place the player representation to his current room (most likely the starting room of the current hell)
        playerView.setX((player.getCurrentRoom().getCoordinate().getX() *
                WorldConstants.ROOM_SIZE + 1) * HellViewConstants.ROOM_TILE_FIT_WIDTH);
        playerView.setY((player.getCurrentRoom().getCoordinate().getY() *
                WorldConstants.ROOM_SIZE + 1) * HellViewConstants.ROOM_TILE_FIT_HEIGHT);

        pane.getChildren().add(playerView);


        // init the "camera"
        Rectangle camera = new Rectangle();
        camera.widthProperty().bind(scene.widthProperty());
        camera.heightProperty().bind(scene.heightProperty());


        // bind the camera to the player
        camera.xProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerView.getX() - scene.getWidth() / 2, 0, HellViewConstants.PANE_WIDTH - scene.getWidth()),
                playerView.xProperty(), scene.widthProperty()
        ));

        camera.yProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerView.getY() - scene.getHeight() / 2, 0, HellViewConstants.PANE_HEIGHT - scene.getHeight()),
                playerView.yProperty(), scene.heightProperty()
        ));

        pane.setClip(camera);
        pane.translateXProperty().bind(camera.xProperty().multiply(-1));
        pane.translateYProperty().bind(camera.yProperty().multiply(-1));

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
    private double clampRange(double value, double min, double max) {
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
        double deltaX = 0;
        double deltaY = 0;

        // sett the change in a certain direction and move the player on the underlying grid if valid
        switch (key) {
            case UP:
                deltaY -= HellViewConstants.PLAYER_MOVESPEED;
                if (!MovementManager.getInstance().moveTowards(MoveDirection.UP)) {
                    unlockIsAnimating();
                    return;
                }
                break;
            case DOWN:
                deltaY += HellViewConstants.PLAYER_MOVESPEED;
                if (!MovementManager.getInstance().moveTowards(MoveDirection.DOWN)) {
                    unlockIsAnimating();
                    return;
                }
                break;
            case LEFT:
                deltaX -= HellViewConstants.PLAYER_MOVESPEED;
                if (!MovementManager.getInstance().moveTowards(MoveDirection.LEFT)) {
                    unlockIsAnimating();
                    return;
                }
                break;
            case RIGHT:
                deltaX += HellViewConstants.PLAYER_MOVESPEED;
                if (!MovementManager.getInstance().moveTowards(MoveDirection.RIGHT)) {
                    unlockIsAnimating();
                    return;
                }
                break;
            default:
                unlockIsAnimating();
                return;
        }

        // create a timeline and keyframes which is used to "animate" the movement of the player between rooms
        final Timeline timeline = new Timeline();
        final KeyValue kvx = new KeyValue(playerView.xProperty(), playerView.getX() + deltaX);
        final KeyFrame kfx = new KeyFrame(Duration.millis(500), kvx);
        timeline.getKeyFrames().add(kfx);

        final KeyValue kvy = new KeyValue(playerView.yProperty(), playerView.getY() + deltaY);
        final KeyFrame kfy = new KeyFrame(Duration.millis(500), kvy);
        timeline.getKeyFrames().add(kfy);

        timeline.play();

        // unlock the method again
        timeline.setOnFinished(e -> isAnimating = false);

        GlobalLogger.log(LoggerStringValues.MOVED_PLAYER + key);
    }

    /**
     * unlock the player movement animation
     */
    private void unlockIsAnimating() {
        isAnimating = false;
    }

    // TODO: Comment Method + Split into seperate Methods
    public void initOverlay(Scene scene, Pane pane) {
        // Init settings button
        Image cogwheel = AssetsManager.getImageByAssetId(AssetIds.COGWHEEL);
        ImageView settingsImage = new ImageView(cogwheel);
        settingsImage.setFitHeight(HellViewConstants.SETTINGS_FIT_HEIGHT);
        settingsImage.setFitWidth(HellViewConstants.SETTINGS_FIT_WIDTH);

        Button settings = new Button();

        pane.getChildren().add(settings);
        settings.setGraphic(settingsImage);

        settings.setOnMouseClicked(e -> openSettings());
        settings.setFocusTraversable(HellViewConstants.SETTINGS_FOCUS_TRAVERSABLE);


        // bind the settings button to the player
        settings.layoutXProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(
                        playerView.getX() + scene.getWidth() * HellViewConstants.HALF -
                                HellViewConstants.SETTINGS_FIT_WIDTH * HellViewConstants.SETTINGS_WIDTH_MULTI,
                        scene.getWidth() - HellViewConstants.SETTINGS_FIT_WIDTH * HellViewConstants.SETTINGS_WIDTH_MULTI,
                        HellViewConstants.PANE_WIDTH - HellViewConstants.SETTINGS_FIT_WIDTH * HellViewConstants.SETTINGS_WIDTH_MULTI
                ),
                playerView.xProperty(), scene.widthProperty()
        ));

        settings.layoutYProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerView.getY() - scene.getHeight() / 2 + HellViewConstants.SETTINGS_FIT_HEIGHT / 2,
                        HellViewConstants.SETTINGS_FIT_HEIGHT / 2,
                        HellViewConstants.PANE_HEIGHT - scene.getHeight() + HellViewConstants.SETTINGS_FIT_HEIGHT / 2
                ),
                playerView.yProperty(), scene.heightProperty()
        ));

        // TODO: MAKE IT BEAUTIFUL
        // init visualization of player status
        FlowPane playerStats = new FlowPane(Orientation.HORIZONTAL);
        playerStats.setPrefHeight(HellViewConstants.SETTINGS_FIT_HEIGHT);
        playerStats.prefWidthProperty().bind(Bindings.createDoubleBinding(
                () -> scene.getWidth() - settingsImage.getFitWidth() * HellViewConstants.STAT_BOARD_WIDTH_MULTI,
                scene.widthProperty(), settingsImage.fitWidthProperty()
        ));


        playerStats.setHgap(HellViewConstants.PLAYER_STATS_HGAP);
        playerStats.setAlignment(Pos.CENTER);
        playerStats.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, null)));
        playerStats.setBorder(
                new Border(
                        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)
                ));

        // bind the player stats to the player;
        playerStats.layoutXProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerView.getX() - scene.getWidth() * HellViewConstants.HALF +
                                HellViewConstants.SETTINGS_FIT_WIDTH * HellViewConstants.HALF,
                        HellViewConstants.SETTINGS_FIT_WIDTH * HellViewConstants.HALF,
                        HellViewConstants.PANE_WIDTH - scene.getWidth() + HellViewConstants.SETTINGS_FIT_WIDTH * HellViewConstants.HALF),
                playerView.xProperty(), scene.widthProperty()
        ));

        playerStats.layoutYProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerView.getY() - scene.getHeight() * HellViewConstants.HALF +
                                HellViewConstants.SETTINGS_FIT_HEIGHT * HellViewConstants.HALF,
                        HellViewConstants.SETTINGS_FIT_HEIGHT * HellViewConstants.HALF,
                        HellViewConstants.PANE_HEIGHT - scene.getHeight() + HellViewConstants.SETTINGS_FIT_HEIGHT * HellViewConstants.HALF),
                playerView.yProperty(), scene.heightProperty()
        ));
        pane.getChildren().add(playerStats);

        // PLACEHOLDER
        // ANSATZ: Binding nur im Properties/Observables --> Stats == Observable machen?
        Text souls = new Text();
        souls.textProperty().bind(Bindings.createStringBinding(
                () -> {return "" + PlayerManager.getInstance().getPlayerSoulsProperty().getValue();}, PlayerManager.getInstance().getPlayerSoulsProperty()
        ));

        HBox soulsContainer = createStatboardElement(15, "Souls");
        soulsContainer.getChildren().add(souls);

        souls.setFont(new Font(HellViewConstants.STAT_BOARD_ICON_HEIGHT));

        playerStats.getChildren().add(soulsContainer);
    }

    /**
     * Called if the settings button is pressed.
     * Opens a dialogue for the game settings.
     * <p>
     * TODO: Add functionality to open Dialogue for settings
     */
    private void openSettings() {
        // REPLACE WITH CODE TO OPENw DIALOGUE
        System.out.println("MOEP: " + PlayerManager.getInstance().getPlayerSoulsProperty().get());
        PlayerManager.getInstance().addSouls(10);
        SettingsController.showSettings();
    }

    /**
     * Method to create an item which is thought to be used as element inside the StatBoard on the HellMap
     *
     * @param assetId ID of the asset which represents the icon
     * @param subtitle Text that will be shown below the icon
     * @return HBox object that represents an element from the StatBoard
     */
    private HBox createStatboardElement (final int assetId, String subtitle)
    {
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);
        container.setPadding(HellViewConstants.STAT_BOARD_ELEMENT_PADDING);

        // set preferences for VBox
        VBox leftHalf = new VBox();
        leftHalf.setAlignment(Pos.CENTER);
        leftHalf.setPadding(HellViewConstants.STAT_BOARD_ICON_TEXT_DISTANCE);


        // create icon
        Image iconImage = AssetsManager.getImageByAssetId(assetId);
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

        // JUST FOR TESTS
        /*
        Text test = new Text("36");
        test.setFont(new Font(60));
        container.getChildren().add(test);
         */

        return container;
    }
}
