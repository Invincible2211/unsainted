package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.BattleManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.world.arena.ArenaStackPane;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.views.EntityConstants;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.ArenaBaseView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;

public abstract class ArenaBaseController
{

    private static ArenaBaseView currentArenaBaseView;

    //TODO the ArenabaseView to replace the parameter in every method as static variable
    /**
     * creates a default arena
     * @param arenaBaseView
     */
    public static void init(ArenaBaseView arenaBaseView)
    {
        currentArenaBaseView = arenaBaseView;
       initBattlefield(BattleManager.getInstance().getArena().getHeight(),
               BattleManager.getInstance().getArena().getWidth());

       initEgoPoints();
       setBackgroundImage(AssetIds.ARENA_BG_DEFAULT_ID);
       arenaBaseView.getBackGroundAnchorPane().setPrefSize(ViewStrings.RESOLUTION_X, ViewStrings.RESOLUTION_Y);
       arenaBaseView.getBackGroundAnchorPane().setMaxSize(ViewStrings.RESOLUTION_X, ViewStrings.RESOLUTION_Y);

       setPreferredMeasurements();

    }

    /**
     * sets the background image of the arena if an alternative image is wanted.
     * @param arenaBaseView
     * @param backGroundAlternativeID
     */
    public static void init(ArenaBaseView arenaBaseView, int backGroundAlternativeID)
    {
        currentArenaBaseView = arenaBaseView;
        initBattlefield(BattleManager.getInstance().getArena().getHeight(),
                BattleManager.getInstance().getArena().getWidth());
        initEgoPoints();
        setBackgroundImage(backGroundAlternativeID);
        arenaBaseView.getBackGroundAnchorPane().setPrefSize(ViewStrings.RESOLUTION_X, ViewStrings.RESOLUTION_Y);
        arenaBaseView.getBackGroundAnchorPane().setMaxSize(ViewStrings.RESOLUTION_X, ViewStrings.RESOLUTION_Y);
        setPreferredMeasurements();

    }


    /**
     * posssibility to automatically rescale the Arena for different resolutions

     */
    private static void setPreferredMeasurements()
    {
        currentArenaBaseView.getBorderPaneID().getTop().prefHeight(ViewStrings.HAND_PLAYER_Y);
        currentArenaBaseView.getBorderPaneID().getBottom().prefHeight(ViewStrings.HAND_PLAYER_Y);
        currentArenaBaseView.getBorderPaneID().getTop().maxHeight(ViewStrings.HAND_PLAYER_Y);
        currentArenaBaseView.getBorderPaneID().getTop().maxWidth(ViewStrings.HAND_PLAYER_Y);
        currentArenaBaseView.getBorderPaneID().getBottom().maxHeight(ViewStrings.HAND_PLAYER_Y);
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
                new BackgroundSize(ViewStrings.RESOLUTION_X, ViewStrings.RESOLUTION_Y, false, false, false, false));
        currentArenaBaseView.getBackGroundAnchorPane().setBackground(new Background(myBI));
    }

    /**
     * die Aufteilung auf player one und player two soll soaeter dabei helfen DM und Spieler in der Controlle zu unterscheiden
     */
    private static void initEgoPoints ()
    {
        ImageView egoPointsPlayerOneImageView = currentArenaBaseView.getEgopointsPlayerOneImageView();
        ImageView egoPointsPlayerTwoImageView = currentArenaBaseView.getEgopointsPlayerTwoImageView();
        egoPointsPlayerOneImageView.setImage(AssetsManager.getImageByAssetId(AssetIds.SUMMON_COST_ICON));
        egoPointsPlayerTwoImageView.setImage(AssetsManager.getImageByAssetId(AssetIds.SUMMON_COST_ICON));
        egoPointsPlayerOneImageView.setFitHeight(ViewStrings.EGOPOINTS_BACKROUND_HEIGHT);
        egoPointsPlayerOneImageView.setFitWidth(ViewStrings.EGOPOINTS_BACKROUND_WIDTH);
        egoPointsPlayerTwoImageView.setFitHeight(ViewStrings.EGOPOINTS_BACKROUND_HEIGHT);
        egoPointsPlayerTwoImageView.setFitWidth(ViewStrings.EGOPOINTS_BACKROUND_WIDTH);
    }

    //TODO BINDING THIS -> binddirectional maybe
    //TODO Nicht die skalierung aendern sondern die Ueberlappung bei groSen Haenden
    public static void updatePlayerHands (List<Card> handOne, List<Card> handTwo)
    {
        HBox handcontainer = currentArenaBaseView.getPlayerHandHBox();
        handcontainer.setPrefSize(ViewStrings.HAND_PLAYER_X, ViewStrings.HAND_PLAYER_Y);
        handcontainer.setMaxSize(ViewStrings.HAND_PLAYER_X, ViewStrings.HAND_PLAYER_Y);

        HBox secondHandContainer = currentArenaBaseView.getEnemyHandHBox();
        secondHandContainer.setPrefSize(ViewStrings.HAND_PLAYER_X, ViewStrings.HAND_PLAYER_Y);
        secondHandContainer.setMaxSize(ViewStrings.HAND_PLAYER_X, ViewStrings.HAND_PLAYER_Y);

        double handCardScale = ViewStrings.HAND_PLAYER_Y / ViewStrings.CARD_HEIGHT;

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
        double size = ViewStrings.BATTLEFIELDSIZE_Y / height;
        GridPane gridPane = currentArenaBaseView.getBattlefieldGridPane();
        gridPane.setVgap(ViewStrings.BATTLEFIELD_VGAP_DEFAULT);
        gridPane.setHgap(ViewStrings.BATTLEFIELD_HGAP_DEFAULT);

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
                stackPane.setMinSize(size * ViewStrings.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER, size * ViewStrings.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER);
                stackPane.setMaxSize(size * ViewStrings.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER, size * ViewStrings.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER);

                gridPane.add(stackPane, x, y);
            }
        }
    }

    private static void deleteAllMinionsFromArenaView ()
    {
        for (int x = 0; x < ArenaBaseController.getCurrentArenaBaseView().getBattlefieldGridPane().getColumnCount(); x++) {
            for (int y = 0; y < ArenaBaseController.getCurrentArenaBaseView().getBattlefieldGridPane().getRowCount(); y++) {
                getBattleFieldPane(x, y).getChildren().removeAll();
                GlobalLogger.log(LoggerStringValues.REMOVING_CHILDREN_OF_BATTLEFIELD + x + y);
            }
        }
    }

    /**
     * hilfmethode um auf einzelne Felder zugreifen zu koennen
     */
    public static ArenaStackPane getBattleFieldPane(int x, int y)
    {
        for (Node node : currentArenaBaseView.getBattlefieldGridPane().getChildren()) {
              if (GridPane.getColumnIndex(node) == y && GridPane.getRowIndex(node) == x) {
                GlobalLogger.log(LoggerStringValues.GOT_NODE_ON_BATTLEFIELD + GridPane.getColumnIndex(node) + GridPane.getRowIndex(node));
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

        double sizescaleY = ViewStrings.BATTLEFIELDSIZE_Y / arena.getHeight() / EntityConstants.ENTITY_BASE_HEIGHT * ViewStrings.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER;
        double sizescaleX = ViewStrings.BATTLEFIELDSIZE_X / arena.getWidth() / EntityConstants.ENTITY_BASE_WIDTH * ViewStrings.BATTLEFIELD_CELL_MIN_SIZE_MODIFIER;
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

    public static ArenaBaseView getCurrentArenaBaseView ()
    {
        return currentArenaBaseView;
    }
}
