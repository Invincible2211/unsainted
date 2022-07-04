package de.prog2.dungeontop.view.cardViews;

import de.prog2.dungeontop.resources.views.CardDetailConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SpellCardDetailView extends CardDetailView
{
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView spellImageView;
    @FXML
    private Label descriptionLabel;
    @FXML
    private BorderPane nameContainer;
    @FXML
    private VBox descriptionContainer;
    @FXML
    private BorderPane spellImageContainer;

    @Override
    protected void setAnchorScale(double scale)
    {
        super.setAnchorScale(scale);
        nameLabel.setStyle(String.format(CardDetailConstants.NAME_FONT_SIZE_STYLE, (int)(scale * (double) CardDetailConstants.ENTITY_NAME_FONT_SIZE)));
        AnchorPane.setTopAnchor(nameContainer, scale * (CardDetailConstants.NAME_TOP_ANCHOR - CardDetailConstants.NAME_OFFSET_Y));

        spellImageView.setFitWidth(scale * CardDetailConstants.ENTITY_IMAGE_WIDTH);
        spellImageView.setFitHeight(scale * CardDetailConstants.ENTITY_IMAGE_HEIGHT);
        AnchorPane.setTopAnchor(spellImageContainer, scale * (CardDetailConstants.ENTITY_IMAGE_TOP_ANCHOR - CardDetailConstants.ENTITY_IMAGE_OFFSET_Y));

        descriptionContainer.setScaleX(scale);
        descriptionContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(descriptionContainer, scale * CardDetailConstants.DESCRIPTION_TOP_ANCHOR);
        AnchorPane.setLeftAnchor(descriptionContainer, scale * CardDetailConstants.DESCRIPTION_HORIZONTAL_ANCHOR);
        AnchorPane.setRightAnchor(descriptionContainer, scale * CardDetailConstants.DESCRIPTION_HORIZONTAL_ANCHOR);
    }


    public ImageView getSpellImageView()
    {
        return spellImageView;
    }

    public Label getNameLabel()
    {
        return nameLabel;
    }

    public Label getDescriptionLabel()
    {
        return descriptionLabel;
    }
}
