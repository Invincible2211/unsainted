package de.prog2.dungeontop.view.cardViews;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.resources.CardConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public abstract class CardView
{
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private AnchorPane container;
    @FXML
    private Label rankLabel;
    @FXML
    private ImageView rankImageView;
    @FXML
    private Label summonCostLabel;
    @FXML
    private ImageView summonImageView;
    @FXML
    private StackPane summonCostContainer;
    @FXML
    private StackPane rankContainer;
    @FXML
    private void mouseEntered() {CardViewController.zoomCardView(container);}
    @FXML
    private void mouseExited() {CardViewController.resetZoom(container);}

    /**
     * Sets the width of the card.
     * @param width the width, in pixels
     */
    public void setWidth(double width)
    {
        backgroundImageView.setFitWidth(width);
        container.setPrefWidth(width);
        container.setMaxWidth(width);
    }

    /**
     * Sets the height of the card.
     * @param height the height, in pixels
     */
    public void setHeight(double height)
    {
        backgroundImageView.setFitHeight(height);
        container.setPrefHeight(height);
        container.setMaxHeight(height);
    }

    /**
     * Sets the scale of the single elements on the card displaying the stats.
     * @param scale the scale, from 0 to 1
     */
    public void setAnchorScale(double scale)
    {
        summonCostContainer.setScaleX(scale);
        summonCostContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(summonCostContainer, scale * CardConstants.SUMMON_COST_TOP_ANCHOR - CardConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(summonCostContainer, scale * CardConstants.SUMMON_COST_LEFT_ANCHOR - CardConstants.ICON_OFFSET);
        rankContainer.setScaleX(scale);
        rankContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(rankContainer, scale * CardConstants.RANK_TOP_ANCHOR - CardConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(rankContainer, scale * CardConstants.RANK_LEFT_ANCHOR - CardConstants.ICON_OFFSET);
    }

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

    public ImageView getSummonImageView()
    {
        return summonImageView;
    }

    public ImageView getRankImageView()
    {
        return rankImageView;
    }

    public ImageView getBackgroundImageView()
    {
        return backgroundImageView;
    }

}
