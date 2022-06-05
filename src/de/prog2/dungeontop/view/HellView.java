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
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.HashMap;

public class HellView
{
    //  variable to remember the last pressed and not yet handled key input
    private ImageView playerView = null;
    private boolean isAnimating = false;
    private Scene scene = null;


    /**
     * Initialize the View for a given hell
     *
     * @param hell hell that has to be visualized
     * @return scene representing the given hell
     */
    public Scene initHellView (Hell hell)
    {
        Pane pane = createBackground(hell.getHellComponentHashMap());
        BorderPane border = new BorderPane(pane);
        Player player = PlayerManager.getInstance().getPlayer();
        player.setCurrentRoom(hell.getStartingRoom());

        scene = new Scene(border, 1920, 1080);
        initPlayerCamera(scene, pane);

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
    private Pane createBackground (HashMap<Coordinate, HellComponent> roomComponents)
    {
        // Creating the canvas on which the Hell will be drawn
        Canvas canvas = new Canvas(HellViewConstants.HORIZONTAL_TILES * HellViewConstants.ROOM_TILE_WIDTH,
                HellViewConstants.VERTICAL_TILES * HellViewConstants.ROOM_TILE_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Initializing the Container pane
        Pane containerPane = new Pane(canvas);

        containerPane.setMinSize(HellViewConstants.HORIZONTAL_TILES * HellViewConstants.ROOM_TILE_WIDTH,
                HellViewConstants.VERTICAL_TILES * HellViewConstants.ROOM_TILE_HEIGHT);
        containerPane.setPrefSize(containerPane.getMinWidth(), containerPane.getMinHeight());
        containerPane.setMaxSize(containerPane.getMinWidth(), containerPane.getMinHeight());

        // Iterating over all HellComponents and draw them onto the canvas
        for (Coordinate coordinate : roomComponents.keySet())
        {
            // draw the tile with a rotation that is saved within the HellComponent on the
            // coordinate for the current iteration
            // the y coordinate has to be recalculated as we based our thoughts on (0, 0) being the bottom
            // left coordinate while JavaFX uses (0, 0) for the top left coordinate
            drawRotatedImage(gc,
                    AssetsManager.getImageByAssetId(roomComponents.get(coordinate).getAssetId()),
                    roomComponents.get(coordinate).getRotation().getAngle(),
                    coordinate.getX() * HellViewConstants.ROOM_TILE_WIDTH,
                    HellViewConstants.TRANSFORM_Y_COORDINATE - (coordinate.getY()+1) *
                            HellViewConstants.ROOM_TILE_HEIGHT
            );
        }
        GlobalLogger.warning("CREATED BACKGROUND");
        return containerPane;
    }

    /**
     * Rotating a given GraphicsContext around a specified pivot point.
     *
     * @param gc GraphicsContext that will be rotated
     * @param angle angle by which the GraphicsContext shall be rotated
     * @param pivotX x coordinate around which the GraphicsContext shall be rotated
     * @param pivotY y coordinate around which the GraphicsContext shall be rotated
     */
    private void rotate (GraphicsContext gc, double angle, double pivotX, double pivotY)
    {
        Rotate r = new Rotate(angle, pivotX, pivotY);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

        GlobalLogger.log(LoggerStringValues.GC_ROTATED + angle);
    }

    /**
     * Draws an image rotated by a given angle.
     *
     * @param gc GraphicsContext the image will be drawn onto
     * @param image Image that will be drawn
     * @param angle angle of the rotation
     * @param topLeftX top left x coordinate where the image will be drawn
     * @param topLeftY top left y coordinate where the image will be drawn
     */
    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double topLeftX, double topLeftY)
    {
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
     * @param pane pane the player will be added to
     */
    private void initPlayerCamera (Scene scene, Pane pane)
    {
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
                () -> clampRange(playerView.getX() - scene.getWidth()/2, 0, pane.getWidth() - scene.getHeight()),
                playerView.xProperty(), scene.widthProperty()
        ));

        camera.yProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerView.getY() - scene.getHeight()/2, 0, pane.getHeight() - scene.getHeight()),
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
     * @param min lower boundary of the range
     * @param max upper boundary of the range
     * @return nearest available value
     */
    private double clampRange (double value, double min, double max)
    {
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
    private void movePlayer(KeyCode key)
    {
        // prohibit running the method multiple times simultaneously
        if(isAnimating)
            return;
        isAnimating = true;

        // change to the player coordinate
        double deltaX = 0;
        double deltaY = 0;

        // sett the change in a certain direction and move the player on the underlying grid if valid
        switch (key)
        {
            case UP:
                deltaY -= HellViewConstants.PLAYER_MOVESPEED;
                if(!MovementManager.getInstance().moveTowards(MoveDirection.UP))
                {
                    unlockIsAnimating();
                    return;
                }
                break;
            case DOWN:
                deltaY += HellViewConstants.PLAYER_MOVESPEED;
                if(!MovementManager.getInstance().moveTowards(MoveDirection.DOWN))
                {
                    unlockIsAnimating();
                    return;
                }
                break;
            case LEFT:
                deltaX -= HellViewConstants.PLAYER_MOVESPEED;
                if(!MovementManager.getInstance().moveTowards(MoveDirection.LEFT))
                {
                    unlockIsAnimating();
                    return;
                }
                break;
            case RIGHT:
                deltaX += HellViewConstants.PLAYER_MOVESPEED;
                if(!MovementManager.getInstance().moveTowards(MoveDirection.RIGHT))
                {
                    unlockIsAnimating();
                    return;
                }
                break;
            default:
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
    private void unlockIsAnimating ()
    {
        isAnimating = false;
    }
}
