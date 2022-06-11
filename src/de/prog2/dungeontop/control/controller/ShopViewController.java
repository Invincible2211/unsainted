package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.StringValues;
import de.prog2.dungeontop.view.ShopView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.List;

public abstract class ShopViewController
{
    // TODO Remove Magic Numbers
    // TODO Add Logger
    public static void addCards(ShopView shopView, List<Card> cards)
    {
        int columns = 0, row = 1;
        for (Card card : cards)
        {
            Node cardView = CardViewController.getCardView(card);
            Button button = new Button(String.format(StringValues.CURRENCY, card.getPrice()));
            //button.setStyle("-fx-background-image: url(/view/button-background.png);");
            button.setPrefWidth(((AnchorPane)cardView).getPrefWidth());
            button.setPrefHeight(((AnchorPane)cardView).getPrefHeight()/5);
            shopView.getGrid().add(new VBox(cardView, button), columns, row);
            columns++;
            if (columns == 9)
            {
                columns = 0;
                row++;
            }
        }
    }
}
