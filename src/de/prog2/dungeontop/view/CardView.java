package de.prog2.dungeontop.view;

import de.prog2.dungeontop.model.game.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardView
{
    @FXML
    private Label rankLabel;
    @FXML
    private Label summonCostLabel;
    @FXML
    private Label entityNameLabel;
    @FXML
    private ImageView entityImageView;

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

    public Label getSummonCostLabel()
    {
        return summonCostLabel;
    }

    public Label getEntityNameLabel()
    {
        return entityNameLabel;
    }

    public ImageView getEntityImageView()
    {
        return entityImageView;
    }
}
