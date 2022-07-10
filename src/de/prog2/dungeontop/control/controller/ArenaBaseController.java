package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.BattleManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.world.arena.ArenaStackPane;
import de.prog2.dungeontop.resources.ApplicationConstants;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.ColorKeys;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import de.prog2.dungeontop.resources.views.EntityConstants;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.ArenaBaseView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class ArenaBaseController
{

    private static ArenaBaseView currentArenaBaseView;

    //TODO the ArenabaseView to replace the parameter in every method as static variable
    /**
     * creates a default arena
     * @param arenaBaseView the view to be used
     */
    public static void init(ArenaBaseView arenaBaseView)
    {
        currentArenaBaseView = arenaBaseView;
       initBattlefield(BattleManager.getInstance().getArena().getHeight(),
               BattleManager.getInstance().getArena().getWidth());

       initEgoPoints();
       setBackgroundImage(AssetIds.ARENA_BG_DEFAULT_ID);
       arenaBaseView.getBackGroundAnchorPane().setPrefSize(ApplicationConstants.RESOLUTION_X, ApplicationConstants.RESOLUTION_Y);
       arenaBaseView.getBackGroundAnchorPane().setMaxSize(ApplicationConstants.RESOLUTION_X, ApplicationConstants.RESOLUTION_Y);

       setPreferredMeasurements();
       updatePhaseDisplay();
    }

    /**
     * sets the background image of the arena if an alternative image is wanted.
     * @param arenaBaseView the view to be used
     * @param backGroundAlternativeID the id of the alternative image
     */
    public static void init(ArenaBaseView arenaBaseView, int backGroundAlternativeID)
    {
        init(arenaBaseView);
        setBackgroundImage(backGroundAlternativeID);
    }


    /**
     * posssibility to automatically rescale the Arena for different resolutions
     */
    private static void setPreferredMeasurements()
    {
        currentArenaBaseView.getBorderPaneID().getTop().prefHeight(ArenaViewConstants.HAND_PLAYER_Y);
        currentArenaBaseView.getBorderPaneID().getBottom().prefHeight(ArenaViewConstants.HAND_PLAYER_Y);
        currentArenaBaseView.getBorderPaneID().getTop().maxHeight(ArenaViewConstants.HAND_PLAYER_Y);
        currentArenaBaseView.getBorderPaneID().getTop().maxWidth(ArenaViewConstants.HAND_PLAYER_Y);
        currentArenaBaseView.getBorderPaneID().getBottom().maxHeight(ArenaViewConstants.HAND_PLAYER_Y);
        currentArenaBaseView.getBorderPaneID().autosize();
        currentArenaBaseView.getBackGroundAnchorPane().autosize();
    }

    /**
     * sets the background image of the arena
     * @param imageID
     */
    public static void setBackgroundImage(int imageID)
    {
        Image image = AssetsManager.getImageByAssetId(imageID);
        BackgroundImage myBI= new BackgroundImage(image, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.DEFAULT,
                new BackgroundSize(ApplicationConstants.RESOLUTION_X, ApplicationConstants.RESOLUTION_Y, false, false, false, false));
        currentArenaBaseView.getBackGroundAnchorPane().setBackground(new Background(myBI));
    }

    /**
     * die Aufteilung auf player one und player two soll spaeter dabei helfen DM und Spieler in der Controlle zu unterscheiden
     */
    private static void initEgoPoints ()
    {
        ImageView egoPointsPlayerOneImageView = currentArenaBaseView.getEgopointsPlayerOneImageView();
        ImageView egoPointsPlayerTwoImageView = currentArenaBaseView.getEgopointsPlayerTwoImageView();
        egoPointsPlayerOneImageView.setImage(AssetsManager.getImageByAssetId(AssetIds.SUMMON_COST_ICON));
        egoPointsPlayerTwoImageView.setImage(AssetsManager.getImageByAssetId(AssetIds.SUMMON_COST_ICON));
        egoPointsPlayerOneImageView.setFitHeight(ArenaViewConstants.EGOPOINTS_BACKGROUND_HEIGHT);
        egoPointsPlayerOneImageView.setFitWidth(ArenaViewConstants.EGOPOINTS_BACKGROUND_WIDTH);
        egoPointsPlayerTwoImageView.setFitHeight(ArenaViewConstants.EGOPOINTS_BACKGROUND_HEIGHT);
        egoPointsPlayerTwoImageView.setFitWidth(ArenaViewConstants.EGOPOINTS_BACKGROUND_WIDTH);
        currentArenaBaseView.getEgopointsPlayerOne().setTextFill(ColorKeys.EGOPOINTS_TEXTCOLOR);
        currentArenaBaseView.getEgopointsPlayerTwo().setTextFill(ColorKeys.EGOPOINTS_TEXTCOLOR);
        currentArenaBaseView.getEgopointsPlayerOne().setPrefSize(ArenaViewConstants.EGOPOINTS_BACKGROUND_WIDTH, ArenaViewConstants.EGOPOINTS_BACKGROUND_HEIGHT);
        currentArenaBaseView.getEgopointsPlayerTwo().setPrefSize(ArenaViewConstants.EGOPOINTS_BACKGROUND_WIDTH, ArenaViewConstants.EGOPOINTS_BACKGROUND_HEIGHT);
    }

    //TODO BINDING THIS -> binddirectional maybe
    //TODO Nicht die skalierung aendern sondern die Ueberlappung bei groSen Haenden
    public static void updatePlayerHands (List<Card> handOne, List<Card> handTwo)
    {
        HBox handcontainer = currentArenaBaseView.getPlayerHandHBox();
        handcontainer.setPrefSize(ArenaViewConstants.HAND_PLAYER_X, ArenaViewConstants.HAND_PLAYER_Y);
        handcontainer.setMaxSize(ArenaViewConstants.HAND_PLAYER_X, ArenaViewConstants.HAND_PLAYER_Y);

        HBox secondHandContainer = currentArenaBaseView.getEnemyHandHBox();
        secondHandContainer.setPrefSize(ArenaViewConstants.HAND_PLAYER_X, ArenaViewConstants.HAND_PLAYER_Y);
        secondHandContainer.setMaxSize(ArenaViewConstants.HAND_PLAYER_X, ArenaViewConstants.HAND_PLAYER_Y);

        double handCardScale = ArenaViewConstants.HAND_PLAYER_Y / ArenaViewConstants.CARD_HEIGHT;

        handcontainer.getChildren().clear();
        secondHandContainer.getChildren().clear();

        for (Card card : handOne)
        {
            Node cardViewNode = CardViewController.getCardView(card, handCardScale);
            handcontainer.getChildren().add(cardViewNode);
        }
        for (Card card : handTwo)
        {
            Node cardViewNode = CardViewController.getCardView(card, handCardScale);
            secondHandContainer.getChildren().add(cardViewNode);
        }
    }

    /**
     *
     * @param egoPointsOne the amount of ego points of player one
     * @param egoPointsTwo the amount of ego points of player two
     */
    public static void updateEgoPoints (int egoPointsOne, int egoPointsTwo)
    {
        currentArenaBaseView.getEgopointsPlayerOne().setText(String.valueOf(egoPointsOne));
        currentArenaBaseView.getEgopointsPlayerTwo().setText(String.valueOf(egoPointsTwo));
    }

    /**
     * Initialisiert das Battlefield als visualisierung der Arena, update duruch UpdateBattlefield
     * @param height
     * @param width
     */
    private static void initBattlefield(int height, int width)
    {
        double size = ArenaViewConstants.BATTLEFIELD_SIZE_Y / height;
        GridPane gridPane = currentArenaBaseView.getBattlefieldGridPane();
        gridPane.setVgap(ArenaViewConstants.BATTLEFIELD_VGAP_DEFAULT);
        gridPane.setHgap(ArenaViewConstants.BATTLEFIELD_HGAP_DEFAULT);

        Image background = AssetsManager.getImageByAssetId(AssetIds.BATTLEFIELDGRIDPANE_BACKGROUND_IMAGEID);
        BackgroundImage myBI= new BackgroundImage(background, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.DEFAULT,
                new BackgroundSize(size, size, false, false, false, true));

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                //each cell is a StackPane if we want to add effects or something later
                ArenaStackPane stackPane = new ArenaStackPane(new Coordinate(x,y));
                stackPane.setBackground(new Background(myBI));;
                GlobalLogger.log(LoggerStringValues.ADDING_CELL_TO_VIEW_BATTLFIELD);
//                stackPane.setPrefSize(size, size); TODO they shall only be Square even with uneven resolution
                stackPane.setMinSize(size * ArenaViewConstants.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER, size * ArenaViewConstants.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER);
                stackPane.setMaxSize(size * ArenaViewConstants.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER, size * ArenaViewConstants.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER);

                gridPane.add(stackPane, x, y);
            }
        }
    }

    private static void deleteAllMinionsFromArenaView ()
    {
        int height = currentArenaBaseView.getBattlefieldGridPane().getRowCount();
        int width = currentArenaBaseView.getBattlefieldGridPane().getColumnCount();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                getBattleFieldPane(x, y).getChildren().clear();
            }
        }
        GlobalLogger.log(LoggerStringValues.DELETED_ALL_MINIONS_FROM_ARENA_VIEW, GlobalLogger.LoggerLevel.NONE);
    }

    /**
     * hilfmethode um auf einzelne Felder zugreifen zu koennen
     */
    public static ArenaStackPane getBattleFieldPane(int x, int y)
    {
        for (Node node : currentArenaBaseView.getBattlefieldGridPane().getChildren()) {
              if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
                GlobalLogger.log(LoggerStringValues.GOT_NODE_ON_BATTLEFIELD + GridPane.getColumnIndex(node) + GridPane.getRowIndex(node), GlobalLogger.LoggerLevel.NONE);
                return (ArenaStackPane) node;
            }
        }
        GlobalLogger.warning(String.format(LoggerStringValues.COULD_NOT_FIND_NODE_ON_BATTLEFIELD, x, y));
        return null;
    }

    /**
     * Die alternative zu einem Binding der Inhalte der Model-arena zu der View-arena.
     * @param arena
     */
    public static void updateBattlefield (Arena arena)
    {
        //redraw arena components by deleting them and adding them again
        deleteAllMinionsFromArenaView();

        double sizescaleY = ArenaViewConstants.BATTLEFIELD_SIZE_Y / arena.getHeight() / EntityConstants.ENTITY_BASE_HEIGHT * ArenaViewConstants.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER;
        double sizescaleX = ArenaViewConstants.BATTLEFIELD_SIZE_X / arena.getWidth() / EntityConstants.ENTITY_BASE_WIDTH * ArenaViewConstants.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER;
        double size = Math.min(sizescaleY, sizescaleX);

        for (Entity entity : arena.getArenaHashmap().values())
        {
            Node entityView = EntityViewController.getEntityView(entity, size);
            getBattleFieldPane(
                    entity.getPosition().getX(),
                    entity.getPosition().getY())
                    .getChildren().add(entityView);

        }
    }

    public static void updatePhaseDisplay()
    {
        currentArenaBaseView.getPhaseDisplayLabelID().setText(BattleManager.getInstance().getCurrentPhaseAsString());
    }

    public static ArenaBaseView getCurrentArenaBaseView ()
    {
        return currentArenaBaseView;
    }
}
