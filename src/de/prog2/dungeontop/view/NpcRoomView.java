package de.prog2.dungeontop.view;
import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.controller.NpcController;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.control.manager.ShopManager;
import de.prog2.dungeontop.model.exceptions.customexceptions.NotEnoughSoulsException;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;
import de.prog2.dungeontop.model.world.rooms.ForgeRoom;
import de.prog2.dungeontop.model.world.rooms.LavaPondRoom;
import de.prog2.dungeontop.model.world.rooms.NPCRoom;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.HellViewConstants;
import de.prog2.dungeontop.resources.NpcRoomViewConstants;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Stack;

public class NpcRoomView
{
    // TODO: Add Button to close the view

    private Scene npcRoomView;
    private ScrollPane cardContainer;
    private NPCRoom room;
    private int price = NpcRoomViewConstants.DEFAULT_PRICE;

    // *-------------------------------------- Constructors --------------------------------------------------------* //

    public NpcRoomView (NPCRoom room)
    {
        this.room = room;
        this.npcRoomView = createNpcRoomView(room);
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
     * @param room room the player shall be able to interact with
     * @return scene containing the view
     */
    public Scene createNpcRoomView(NPCRoom room)
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
        FlowPane statboard = this.createStatboard(room);
        AnchorPane.setTopAnchor(statboard, NpcRoomViewConstants.STATBOARD_TOP_ANCHOR);
        AnchorPane.setLeftAnchor(statboard, NpcRoomViewConstants.STATBOARD_LEFT_ANCHOR);
        outerContainer.getChildren().add(statboard);

        return scene;
    }

    /**
     * Creates a grid that contains all the cards from the the players deck and buttons depending on the roomtype
     */
    private void loadDeck()
    {
        Deck deck = PlayerManager.getInstance().getPlayer().getDeck();
        NPCRoom room = this.getRoom();

        // create and configure the grid which will contain the cards from the players deck
        GridPane grid = new GridPane();
        grid.setHgap(NpcRoomViewConstants.GRID_HGAP);
        grid.setVgap(NpcRoomViewConstants.GRID_VGAP);
        grid.setPadding(NpcRoomViewConstants.GRID_PADDING);
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
            Node cardView = CardViewController.getCardView(card, NpcRoomViewConstants.CARD_SCALE);
            VBox entry = new VBox(cardView);
            entry.setSpacing(NpcRoomViewConstants.VBOX_SPACING);
            entry.setAlignment(Pos.CENTER);

            // add the corresponding buttons for the different room types
            if (room instanceof LavaPondRoom)
                this.addLavaPondButtons(entry, card);
            else if (room instanceof ForgeRoom)
                this.addForgeButtons(entry, card);

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
    private void addLavaPondButtons (VBox container, Card card)
    {
        Button discard = createButton(NpcRoomViewConstants.DISCARD_BUTTON_TEXT);
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
    private void addForgeButtons (VBox container, Card card)
    {
        Button upgrade = createButton(NpcRoomViewConstants.UPGRADE_BUTTON_TEXT);

        upgrade.setOnAction(e ->
        {
            // TODO: Add functionality -> implement method to upgrade cards
            // Idea being: upgrade card --> reload Grid
        });
        container.getChildren().add(upgrade);
    }

    /**
     * Create a button which is thought to be used below a cardView to begin an action
     *
     * @param text Descriptive text which will be shown on the button
     * @return button
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
                                AssetsManager.getImageByAssetId(AssetIds.STATBOARD_BACKGROUND_SCROLL),
                                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT
                        )
                )
        );

        // create visualization of remaining free actions for this room
        Text freebies = new Text();
        freebies.textProperty().bind(Bindings.createStringBinding(
                () -> NpcRoomViewConstants.PRICE_TEXT +
                        (room.getFreeActions()>=NpcRoomViewConstants.FREE_ACTION_COMPARATOR?
                                NpcRoomViewConstants.FREE_ACTION_PRICE:this.getPrice()),
                room.getFreeActionsProperty()
        ));

        freebies.setFont(NpcRoomViewConstants.STATBOARD_FONT);
        statboard.getChildren().add(freebies);
        statboard.setHgap(NpcRoomViewConstants.STATBOARD_HGAP);


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