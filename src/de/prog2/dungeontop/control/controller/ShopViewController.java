package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.CardConstants;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.ShopView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.List;

public abstract class ShopViewController
{
    /**
     * Add cards to the shop view.
     * @param shopView The shop view.
     * @param cards A List of cards to add.
     */
    public static void addCards(ShopView shopView, List<Card> cards)
    {
        GlobalLogger.log(LoggerStringValues.SHOP_VIEW_STARTED_ADDING_CARDS);
        int columns = 0, row = 1;
        for (Card card : cards)
        {
            Node cardView = CardViewController.getCardView(card, CardConstants.SHOP_CARD_SCALE);
            AnchorPane anchorPane = (AnchorPane) cardView;
            Button button = createButton(anchorPane.getPrefWidth(), anchorPane.getPrefHeight() / CardConstants.SHOP_SELL_BUTTON_HEIGHT_MULTIPLIER, card);
            button.setGraphic(createSoulIcon());
            shopView.getGrid().add(new VBox(cardView, button), columns, row);
            GlobalLogger.log(LoggerStringValues.SHOP_VIEW_ADDED_CARD);
            columns++;
            if (columns == CardConstants.SHOP_COLUMN_COUNT)
            {
                columns = 0;
                row++;
            }
        }
    }

    /**
     * Create a button for the shop.
     * @param width The width of the button.
     * @param height The height of the button.
     * @param card The card to sell.
     * @return The button.
     */
    private static Button createButton(double width, double height, Card card)
    {
        Button button = new Button(card.getPrice() + "");
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        return button;
    }
    /**
     * Create a soul icon for the shop.
     * @return The soul icon.
     */
    private static ImageView createSoulIcon()
    {
        var soulImageView = new ImageView(AssetsManager.getImageByAssetId(AssetIds.SOUL_ICON));

        soulImageView.setFitHeight(CardConstants.ICON_SIZE);
        soulImageView.setFitWidth(CardConstants.ICON_SIZE);

        return soulImageView;
    }
}
