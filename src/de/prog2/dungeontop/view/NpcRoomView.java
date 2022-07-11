package de.prog2.dungeontop.view;
import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.controller.DeckController;
import de.prog2.dungeontop.control.controller.NpcController;
import de.prog2.dungeontop.control.file.GameSaveFileWriter;
import de.prog2.dungeontop.control.manager.*;
import de.prog2.dungeontop.model.exceptions.customexceptions.CardAlreadyMaxRankException;
import de.prog2.dungeontop.model.exceptions.customexceptions.CardAlreadyUnlockedException;
import de.prog2.dungeontop.model.exceptions.customexceptions.NotEnoughSoulsException;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;
import de.prog2.dungeontop.model.world.rooms.ForgeRoom;
import de.prog2.dungeontop.model.world.rooms.LavaPondRoom;
import de.prog2.dungeontop.model.world.rooms.NPCRoom;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellViewConstants;
import de.prog2.dungeontop.resources.NpcRoomViewConstants;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.resources.views.CardConstants;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.skin.ScrollPaneSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Stack;

public class NpcRoomView
{
    private Scene npcRoomView;
    private ScrollPane cardContainer;
    private NPCRoom room;
    private int price = NpcRoomViewConstants.DEFAULT_PRICE;

    // *-------------------------------------- Constructors --------------------------------------------------------* //

    public NpcRoomView (NPCRoom room)
    {
        this.room = room;
        this.npcRoomView = createNpcRoomView();
    }

    // *-------------------------------------- Getter & Setter -----------------------------------------------------* //

    public Scene getNpcRoomView ()
    {
        return this.npcRoomView;
    }

    private ScrollPane getCardContainer ()
    {
        return this.cardContainer;
    }

    private void setScrollPaneContent (ScrollPane newContainer)
    {
        this.cardContainer = newContainer;
    }

    private NPCRoom getRoom ()
    {
        return this.room;
    }

    private void setRoom (NPCRoom room)
    {
        this.room = room;
    }

    private int getPrice ()
    {
        return this.price;
    }


    // *------------------------------------------- Methods --------------------------------------------------------* //

    /**
     * Creates a view that is used to give the player the ability to interact with certain NpcRooms
     *
     * @return scene containing the view
     */
    public Scene createNpcRoomView()
    {
        // create the outer pane which will contain the statboard and the scrollpane
        AnchorPane outerContainer = new AnchorPane();

        // create and configure the scrollpane
        ScrollPane container = new ScrollPane();
        container.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        container.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        container.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        this.setScrollPaneContent(container);
        container.setPrefSize(NpcRoomViewConstants.SCENE_PREF_WIDTH, NpcRoomViewConstants.SCENE_PREF_HEIGHT);
        container.setPadding(NpcRoomViewConstants.SCROLL_PANE_PADDING);

        // add scrollpane to the outer pane
        outerContainer.getChildren().add(container);

        // create the scene containing this view
        Scene scene = new Scene(outerContainer, NpcRoomViewConstants.SCENE_PREF_WIDTH,
                NpcRoomViewConstants.SCENE_PREF_HEIGHT);

        // load the players deck
        this. loadDeck();

        // create the statboard, configure it and add it to the outer pane
        FlowPane statboard = this.createStatboard(this.getRoom());
        AnchorPane.setTopAnchor(statboard, NpcRoomViewConstants.STATBOARD_TOP_ANCHOR);
        AnchorPane.setLeftAnchor(statboard, NpcRoomViewConstants.STATBOARD_LEFT_ANCHOR);
        outerContainer.getChildren().add(statboard);

        // add a button to close the view and return to the hellViewF
        Button closeButton = createSmallButton(AssetIds.SMALL_BUTTON_IMG);
        outerContainer.getChildren().add(closeButton);
        AnchorPane.setTopAnchor(closeButton, NpcRoomViewConstants.STATBOARD_TOP_ANCHOR);
        AnchorPane.setRightAnchor(closeButton, NpcRoomViewConstants.SMALL_BUTTON_FIT_WIDTH *
                HellViewConstants.HALF);
        if (this.getRoom() != null)
        {
            closeButton.setOnAction(e ->
            {
                if (HellView.getCurrHellView() != null)
                    DungeonTop.getStage().setScene(HellView.getCurrHellView());
            });
        }
        else
        {
            closeButton.setOnAction(e ->
            {
                Scene mmScene = new Scene(new AnchorPane());
                try {
                    mmScene = new Scene(new FXMLLoader().load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.MAIN_MENUE_FXML)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                GameSaveFileWriter.getInstance().saveGame(GameManager.getInstance().getSaveGame());
                DungeonTop.getStage().setScene(mmScene);
            });
        }

        return scene;
    }

    /**
     * Creates a grid that contains all the cards from the the players deck and buttons depending on the roomtype
     */
    private void loadDeck()
    {
        NPCRoom room = this.getRoom();
        Deck deck = null;
        if (room != null)
            deck = PlayerManager.getInstance().getPlayer().getDeck();
        else
        {
            deck = new Deck();
            for (Card card : CardManager.getInstance().getLockedCards())
                deck.pushCard(card);
        }

        // create and configure the grid which will contain the cards from the players deck
        GridPane grid = new GridPane();
        grid.setHgap(NpcRoomViewConstants.GRID_HGAP);
        grid.setVgap(NpcRoomViewConstants.GRID_VGAP);
        grid.setPadding(NpcRoomViewConstants.GRID_PADDING);
        grid.setPrefWidth(NpcRoomViewConstants.SCENE_PREF_WIDTH);
        grid.setPrefHeight(NpcRoomViewConstants.SCENE_PREF_HEIGHT);
        grid.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // get the scrollpane and set its content to be the grid
        this.getCardContainer().setContent(grid);

        // get the cards from the players deck
        Stack<Card> cardStack = deck.getCards();
        // define the cell used for the first card
        int column = NpcRoomViewConstants.GRID_START_COLUMN, row = NpcRoomViewConstants.GRID_START_ROW;

        // iterate through all cards from the players deck and visualize them
        for (Card card : cardStack)
        {
            // get the cardView for the card
            Node cardView = CardViewController.getCardDetailView(card, NpcRoomViewConstants.CARD_SCALE);
            VBox entry = new VBox(cardView);
            entry.setSpacing(NpcRoomViewConstants.VBOX_SPACING);
            entry.setAlignment(Pos.CENTER);

            // add the corresponding buttons for the different room types
            if (room instanceof LavaPondRoom)
                this.addLavaPondButton(entry, card);
            else if (room instanceof ForgeRoom)
                this.addForgeButton(entry, card);
            else
                this.addShopButton(entry, card);

            // add the container element containing the cardView and buttons to the grid
            grid.add(entry, column, row);

            // increase indexes to iterate through the different cells of the grid
            column++;
            if (column == NpcRoomViewConstants.CARDS_COLUMNS)
            {
                column = NpcRoomViewConstants.GRID_START_COLUMN;
                row++;
            }
        }
    }

    /**
     * Adds a button to a container which can be used to discard a card from the players deck
     *
     * @param container VBox that will contain the button
     * @param card card that will be discarded upon clicking the button
     */
    private void addLavaPondButton(VBox container, Card card)
    {
        Button discard = createButton(NpcRoomViewConstants.DISCARD_BUTTON_TEXT);
        discard.setMinHeight(NpcRoomViewConstants.BUTTON_PREF_HEIGHT);

        discard.setOnAction(e ->
        {
            try
            {
                // if the room has remaining free actions the next action will cost nothing
                // otherwise use a defined price
                if (this.getRoom().getFreeActions() >= NpcRoomViewConstants.FREE_ACTION_COMPARATOR)
                {
                    NpcController.removeCard(card, NpcRoomViewConstants.FREE_ACTION_PRICE);
                    this.getRoom().setFreeActions(this.getRoom().getFreeActions() -
                            NpcRoomViewConstants.FREE_ACTION_DECREMENT);
                }
                else
                    NpcController.removeCard(card, this.getPrice());
            }
            catch (NotEnoughSoulsException notEnoughSouls)
            {
                GlobalLogger.warning(notEnoughSouls.getMessage());
            }
            // after changing the model reload the view
            this.loadDeck();
        });
        container.getChildren().add(discard);
    }

    /**
     * Adds a button to a container which can be used to upgrade a card
     *
     * @param container VBox that will contain the button
     * @param card card that will be upgraded upon clicking the button
     */
    private void addForgeButton(VBox container, Card card)
    {
        Button upgrade = createButton(NpcRoomViewConstants.UPGRADE_BUTTON_TEXT);
        upgrade.setMinHeight(NpcRoomViewConstants.BUTTON_PREF_HEIGHT);

        upgrade.setOnAction(e ->
        {
            try
            {
                NpcController.upgradeCard(card, NpcRoomViewConstants.DEFAULT_PRICE);
            }
            catch(CardAlreadyMaxRankException | NotEnoughSoulsException ex)
            {
                ex.printStackTrace();
            }

            this.loadDeck();
        });
        container.getChildren().add(upgrade);
    }

    /**
     * Adds a button to a container which can be used to unlock a card
     *
     * @param container VBox that will contain the button
     * @param card card that will be unlocked upon clicking the button
     */
    private void addShopButton (VBox container, Card card)
    {
        Image test = AssetsManager.getImageByAssetId(AssetIds.SOUL_ICON, HellViewConstants.STAT_BOARD_ICON_WIDTH,
                HellViewConstants.STAT_BOARD_ICON_HEIGHT, NpcRoomViewConstants.SOULS_ICO_PRESERVE_RATIO,
                NpcRoomViewConstants.SOULS_ICO_SMOOTH);
        Button unlock = createButton(NpcRoomViewConstants.SHOP_BUTTON_TEXT + card.getPrice());
        unlock.setContentDisplay(ContentDisplay.RIGHT);
        unlock.setGraphic(new ImageView(test));
        unlock.setMinHeight(NpcRoomViewConstants.BUTTON_PREF_HEIGHT);

        unlock.setOnAction(e ->
        {
            try
            {
                ShopManager.getInstance().unlockCard(card, card.getPrice());
            }
            catch (CardAlreadyUnlockedException | NotEnoughSoulsException ex)
            {
                ex.printStackTrace();
            }
            // after unlocking a new card reload the view
            this.loadDeck();
        });
        container.getChildren().add(unlock);
    }

    /**
     * Create a button which is thought to be used below a cardView to begin an action
     *
     * @param text Descriptive text which will be shown on the button
     * @return button consisting of a background image and the descriptive text
     */
    private static Button createButton(String text)
    {
        Button button = new Button();
        button.setAlignment(Pos.CENTER);
        button.setPrefSize(NpcRoomViewConstants.BUTTON_PREF_WIDTH, NpcRoomViewConstants.BUTTON_PREF_HEIGHT);

        button.setBackground(
                new Background(
                        new BackgroundImage(
                                AssetsManager.getImageByAssetId(AssetIds.NPC_ROOM_BUTTON_BG),
                                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT
                        )
                )
        );

        button.setText(text);
        button.setFont(NpcRoomViewConstants.BUTTON_FONT);
        button.setFocusTraversable(false);
        return button;
    }

    /**
     * Create a small button to be used in the UI
     *
     * @param assetId ID of the asset which shall be used for the background of the button
     * @return button which shows the backgroundimage with corner radius and border
     */
    private static Button createSmallButton (final int assetId)
    {
        Image buttonImage = AssetsManager.getImageByAssetId(assetId);
        ImageView buttonImageView = new ImageView(buttonImage);

        Button smallButton = new Button();

        // set the button style
        smallButton.setBackground(Background.EMPTY);
        smallButton.setStyle(NpcRoomViewConstants.CLOSE_BUTTON_STYLE);

        smallButton.setGraphic(buttonImageView);

        buttonImageView.setFitWidth(NpcRoomViewConstants.SMALL_BUTTON_FIT_WIDTH -
                2 * NpcRoomViewConstants.SMALL_BUTTON_PADDING -
                2 * NpcRoomViewConstants.SMALL_BUTTON_BORDER_WIDTH);

        buttonImageView.setFitHeight(NpcRoomViewConstants.SMALL_BUTTON_FIT_HEIGHT -
                2 * NpcRoomViewConstants.SMALL_BUTTON_PADDING -
                2 * NpcRoomViewConstants.SMALL_BUTTON_BORDER_WIDTH);

        smallButton.setFocusTraversable(NpcRoomViewConstants.SMALL_BUTTON_FOCUS_TRAVERSABLE);

        return smallButton;
    }

    /**
     * Create a statboard that shows the price of the next action and the current player souls.
     *
     * @param room room for which the statboard shall be created
     * @return FlowPane that contains the representation of the statboard
     */
    private FlowPane createStatboard (NPCRoom room)
    {
        // create the statboard
        FlowPane statboard = new FlowPane();
        statboard.setPrefSize(NpcRoomViewConstants.STATBOARD_BG_WIDTH, NpcRoomViewConstants.STATBOARD_BG_HEIGHT);
        statboard.setAlignment(Pos.CENTER);

        statboard.setBackground(
                new Background(
                        new BackgroundImage(
                                AssetsManager.getImageByAssetId(
                                        AssetIds.STATBOARD_BACKGROUND_SCROLL,
                                        NpcRoomViewConstants.STATBOARD_BG_WIDTH,
                                        NpcRoomViewConstants.STATBOARD_BG_HEIGHT,
                                        HellViewConstants.STAT_BOARD_BG_IMG_PRESERVE_RATIO,
                                        HellViewConstants.STAT_BOARD_BG_IMG_SMOOTH),
                                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT
                        )
                )
        );

        // create visualization of remaining free actions for this room
        if (room != null)
        {
            Text freebies = new Text();
            freebies.textProperty().bind(Bindings.createStringBinding(
                    () -> NpcRoomViewConstants.PRICE_TEXT +
                            (room.getFreeActions() >= NpcRoomViewConstants.FREE_ACTION_COMPARATOR ?
                                    NpcRoomViewConstants.FREE_ACTION_PRICE : this.getPrice()),
                    room.getFreeActionsProperty()
            ));

            freebies.setFont(NpcRoomViewConstants.STATBOARD_FONT);
            statboard.getChildren().add(freebies);
            statboard.setHgap(NpcRoomViewConstants.STATBOARD_HGAP);
        }

        // Create the visualization of player souls
        HBox element = new HBox();
        // set preferences for VBox (icon container)
        VBox leftHalf = new VBox();
        leftHalf.setAlignment(Pos.CENTER);
        leftHalf.setPadding(HellViewConstants.STAT_BOARD_ICON_TEXT_DISTANCE);
        // create icon
        Image iconImage = AssetsManager.getImageByAssetId(AssetIds.SOUL_ICON);
        ImageView icon = new ImageView(iconImage);
        icon.setFitWidth(HellViewConstants.STAT_BOARD_ICON_WIDTH);
        icon.setFitHeight(HellViewConstants.STAT_BOARD_ICON_HEIGHT);
        // create text for the subtitle
        Text subtitleText = new Text(NpcRoomViewConstants.SOULS_SUBTITLE);
        subtitleText.setFont(NpcRoomViewConstants.SUBTITLE_FONT);

        // assemble icon container
        leftHalf.getChildren().add(icon);
        leftHalf.getChildren().add(subtitleText);

        Text soulValue = new Text();
        // Bind the textProperty to the player's hpProperty so that it automatically changes on value change
        soulValue.textProperty().bind(Bindings.createStringBinding(
                () -> HellViewConstants.EMPTY_STRING + PlayerManager.getInstance().getPlayerSoulsProperty().getValue(),
                PlayerManager.getInstance().getPlayerSoulsProperty()
        ));
        soulValue.setFont(NpcRoomViewConstants.STATBOARD_FONT);

        // assemble statboard element and add it to the statboard
        element.setAlignment(Pos.CENTER);
        element.getChildren().add(leftHalf);
        element.getChildren().add(soulValue);
        statboard.getChildren().add(element);

        return statboard;
    }
}