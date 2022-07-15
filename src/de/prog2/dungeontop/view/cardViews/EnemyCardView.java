package de.prog2.dungeontop.view.cardViews;

import de.prog2.dungeontop.resources.views.CardConstants;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class EnemyCardView extends CardView
{
    @FXML
    private ImageView imageView;
    @FXML
    private BorderPane imageContainer;
    @Override
    protected void setAnchorScale(double scale)
    {
        imageView.setFitWidth(scale * CardConstants.ENTITY_IMAGE_WIDTH);
        imageView.setFitHeight(scale * CardConstants.ENTITY_IMAGE_HEIGHT);
        AnchorPane.setTopAnchor(imageContainer, scale * (CardConstants.ENTITY_IMAGE_TOP_ANCHOR - CardConstants.ENTITY_IMAGE_OFFSET_Y));
    }
    public ImageView getImageView()
    {
        return imageView;
    }
}
