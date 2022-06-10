package de.prog2.dungeontop.view;

import de.prog2.dungeontop.model.game.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardView
{
    @FXML
    public Label rankLabel;

    private Card card;

    public Card getCard()
    {
        return card;
    }

    public void setCard(Card card)
    {
        this.card = card;
    }

    public Label getRankLabel()
    {
        return rankLabel;
    }
}
